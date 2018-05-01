<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <meta> <meta name="renderer" content="webkit">
	    <title>eHub微信账号管理</title>
	    
	    <link href="${ctx }/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">
	    <link href="${ctx }/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	   	<link href="${ctx }/dist/css/bootstrap-table.css" rel="stylesheet">
	   	<link href="${ctx }/dist/css/sb-admin-2.css" rel="stylesheet">
	   	<link href="${ctx }/css/style.css" rel="stylesheet" type="text/css"/>
	   	<link href="${ctx }/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	   	<link href="${ctx }/css/jointly/animate.min.css" rel="stylesheet" type="text/css"/>
	   	<link href="${ctx }/css/header.css" rel="stylesheet" type="text/css"/>
	    <link href="${ctx }/css/detcrm.css" rel="stylesheet" type="text/css"/> 
	    <link href="${ctx }/css/mark.css" rel="stylesheet" type="text/css"/>
	    <link href="${ctx }/css/nav.css" rel="stylesheet" type="text/css"/>
		<link href="${ctx }/css/cuset.css" rel="stylesheet" type="text/css"/>  
	    <script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>    
		<script src="${ctx }/js/bootstrapjs/bootstrap.min.js"></script>
    </head>
	<body class="gray-bg">
    	<div class="wrapper wrapper-content animated fadeIn" id="wrapper">
    		<!--左侧栏导航-->
			<%@include file="/WEB-INF/carService/menu.jsp"%>
	       	<div id="page-wrapper" style="padding-bottom: 105px">
	       		<!--头部导航-->
    			<%@include file="/WEB-INF/wxHead.jsp"%>
	       		<div class="container-fluid ">
					<div class="row" style="margin:0;">
						<div class="col-lg-12 ">
							<div class="col-lg-9 padding_right_0">
								<div class="panel panel-default ">
									<div class="panel-body "> 
										<div class="container record" style="width:100%;">
									    	<div class="row">
												<div class="col-md-12 record-right">
			                						<table id="table" data-mobile-responsive="true"></table>
			                					</div>
										    </div>
										</div>
									</div>
								</div>
							</div>
							<div class="col-lg-3 padding_left_0">
			                   <div class="panel panel-default panel_border_left_none" style="border-top: none;" >
			                        <div class="panel-body" style="height: 1150px"> 
			                        	<div class="col-lg-12 group_save_btn">
								           	<button id="addGroup" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
								               	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新建分组
								           	</button>
			                        	</div>
			                        	<div class="col-lg-12 padding_0">
			                        		<div class="panel panel-default group_panel" id="groupDiv" >
			                        			<ul class="group_list" id="groupList">
			                        				<li class="active" value="">
			                        					<span>全部</span>
			                        				</li>
			                        				<c:forEach var="group" items="${groupList}" varStatus="st">
			                        					<li value="${group.id}">
		                        							<span>${group.groupName }</span>
		                        							<span class="group_edit_span1" >
		                        								<a href="#" data-toggle="modal" data-target="#deleteModal" onclick="delGroup(${group.id})">删除</a>
		                        							</span>
				                        					<span class="group_edit_span2" >
				                        						<a href="#" data-toggle="modal" data-target="#myModal" onclick="editGroup('${group.id}','${group.groupName}')">编辑</a>
				                        					</span>
				                        				</li>
			                        				</c:forEach>
			                        			</ul>
			                        		</div>
			                        	</div>
			                        </div>
			                   </div>
			                </div>
		                </div>
					</div>
				</div>
	       	</div>
		</div>
   		
   		<!-- 添加/修改用户标签 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">编辑分组</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/carService/editGroup" method="post" id="groupForm">
							<input type="hidden" name="id" id="groupId">
							<div class="container-fluid">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>分组名称</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="分组名称" type="text" id="groupName" name="groupName" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="分组名称未填写">
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="saveGroup()">保存</button>
					</div>
				</div>
			</div>
		</div>		
		
		<div class="modal fade" id="deleteCarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">删除在售车辆</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/carService/deleteCar" method="post" id="delCarForm">
							<input type="hidden" name="id" id="delCarId">
							<div class="container-fluid">
								<h4>是否确定删除？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="delCarSub()">确定</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 删除分组 -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">删除分组</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/carService/deleteGroup" method="post" id="delGroupForm">
							<input type="hidden" name="id" id="delId">
							<div class="container-fluid">
								<h4>是否确定删除？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="delSub()">确定</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 添加售卖车辆 -->
		<div class="modal fade" id="carModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">车辆信息</h4>
		            </div>
					<div class="modal-body">
						<form action="${ctx }/carService/editCar" method="post" id="carForm" enctype="multipart/form-data">
							<input type="hidden" id="carId" name="id" />
							<input type="file" accept="image/*" name="imageFile" style="display:none" />
							<div class="container-fluid">
								<div class="row">
		                        	<div class="form-group" style="text-align: center;">
				                        <img src="" style="border-radius: 50%;width: 85px;height: 85px" id="carImage">
				                        <p style="margin: 10px 0px">
				                        	<button type="button" name="button" onclick="uploadImage(this)" class="btn btn-primary btn-sm">上传图片</button>
				                        </p>
				                    </div>
				                </div>
								<div class="row">
			                    	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>车辆名称：
					   				   	</div>
					   				</div>
			                       	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="车辆名称" type="text" value="" id="carName" name="carName" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="车辆名称未填写">
			                           	</div>
			                        </div>
			                    </div>
			                    <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>原价：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="原价" type="text" value="" id="oldPrice" name="oldPrice" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="原价未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>现价：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="现价"  type="text" value="" id="price" name="price" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="现价未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>油耗：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="油耗"  type="text" value="" id="avg" name="avg" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="油耗未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>描述：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="现价"  type="text" value="" id="describes" name="describes" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="描述未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>排序等级：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="排序等级"  type="text" value="" id="level" name="level" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="排序等级未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>分组名称：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                           		<select class="form-control" name="groupId" id="groupSelect">
			                           			<option value="">无分组</option>
			                           			<c:forEach var="group" items="${groupList}" varStatus="st">
			                           				<option value="${group.id}">${group.groupName}</option>
			                           			</c:forEach>
			                                </select>
			                           	</div>
		                           	</div>
		                        </div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="checkbtn" onclick="submit()">保存</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 添加售卖车辆 -->
		<div class="modal fade" id="videoModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">车辆宣传视频信息</h4>
		            </div>
					<div class="modal-body">
						<form action="${ctx }/carService/editVideo" method="post" id="videoForm" enctype="multipart/form-data">
							<input type="hidden" id="vId" name="id" />
							<input type="file" accept="image/*" name="imageFile" style="display:none" />
							<div class="container-fluid">
								<div class="row">
		                        	<div class="form-group" style="text-align: center;">
				                        <img src="" style="border-radius: 50%;width: 85px;height: 85px" id="videoImage">
				                        <p style="margin: 10px 0px">
				                        	<button type="button" name="button" onclick="uploadImage(this)" class="btn btn-primary btn-sm">上传宣传视频图片</button>
				                        </p>
				                    </div>
				                </div>
		                         <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>宣传视频地址：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="宣传视频地址"  type="text" value="" id="videoUrl" name="videoUrl" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="宣传视频地址未填写">
			                           	</div>
		                           	</div>
		                        </div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="checkbtn" onclick="videoSubmit()">保存</button>
					</div>
				</div>
			</div>
		</div>
		
		
		
	    <script src="${ctx }/dist/js/bootstrap-table.js"></script>
	    <script src="${ctx }/dist/js/bootstrap-table-zh-CN.js"></script>
	    <script src="${ctx }/dist/js/bootstrap-table-mobile.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/menu.js"></script>
	    <script type="text/javascript" src="${ctx }/js/jquery.slimscroll.min.js"></script>
	    <script type="text/javascript" src="${ctx }/js/jquery-ui.js"></script>
    	<script type="text/javascript" src="${ctx }/js/mark.js"></script> 
    	<script type="text/javascript" src="${ctx }/js/nav.js"></script>
    	
    	<script src="${ctx }/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    	<script src="${ctx }/dist/js/sb-admin-2.js"></script>
		
		<script type="text/javascript">
			var $table = $('#table');
			
			$(document).ready(function() {
				
				$("#groupList").slimScroll({ 
		        	height: "100%", 
		        	railOpacity: .9, 
		        	alwaysVisible: !1 
		        });
		        
		        $("#selectBtn").click(function () {
		    		$("input[name='imageFile']").click(); //隐藏了input:file样式后，点击头像就可以本地上传
		    		$("input[name='imageFile']").on("change",function(){
			    		var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
			    		if (objUrl) {
			    			$("#pic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			    		}
		    		});
	    		});
				
				var windowH = $(window).height()-51;
				var dropScrollH = $(".dropScroll_1").outerHeight();
				var vcontrolH = $(".view-controls").outerHeight();
				var menuH = windowH - dropScrollH - vcontrolH;
				
				$(".record-left").height(windowH);
				$(".menu-nav").height(menuH);
				
				$("#menuNav").slimScroll({ 
					height: menuH,
					size: '4px',
					distance: '-1px',
					railOpacity: .9, 
					alwaysVisible: !1 
				});
			   
			    $(".view-controls").find("dd").click(function(){
					$(this).addClass("active").siblings("dd").removeClass("active");
			    });
					
				$table.bootstrapTable({
					height:1100,
					pagination:true,
					search:true,
					pageSize:10,
					pageNumber:1,
					pageList: [10,20,30],
					clickToSelect: true,	
					toolbar: '#toolbar',
					showRefresh:true,
					showColumns:true,
					sidePagination:'server',
					columns: [{
						field: 'state',
	                    title: '',
	                    checkbox: true
	                },{
						field: 'id',
						title: 'ID',
						visible: false   
					},{
						field: 'headImage',
						title: '图片',
						formatter: headimgFMT
					},{
						field: 'carName',
						title: '车辆名称'
					},{
						field: 'oldPrice',
						title: '原价'
					},{
						field: 'price',
						title: '现价'
					},{
						field: 'avg',
						title: '油耗'
					},{
						field: 'describes',
						title: '描述'
					},{
						field: 'groupName',
						title: '分组名称',
						formatter: groupFMT
					},{
						title: '操作',
						events: operateEvents,
						formatter: operateFormatter
					}],
					url:'${ctx}/carService/showCarListJson'
				});
				
				var groupHtml = "";
					
				$(".search").css("width","50%");
				$(".search").children().css("width","60%");
		        $(".search").children().css("display","");
		        $(".search").children().css("float","right");
				$(".search .mark_zj").css({
		            width: "auto",
			        float: "right",
			        display: "inline-block",
			        padding: "0px 15px",
			        background: "#ff7a59",
			        color: "#fff",
			        "border-radius": "3px",
			        "margin-right": "5px",
			        cursor: "pointer"
		        })
				$(".bars").append("<div onclick='addCar()' class='mark-tag' data-toggle='modal' data-target='#carModal'>添加售卖车辆</div>");
				$(".bars .mark-tag").css({
					width: "auto",
					float: "right",
					display: "inline-block",
					padding: "0px 15px",
					background: "#ff7a59",
					color: "#fff",
					"border-radius": "3px",
					"margin-right": "5px",
					cursor: "pointer"
				})
				var addGroup = $("#addGroup");
				addGroup.click(function () {
					$('#myModalTitle').text('新增');
					$('#subtn').text('保存');
					$('#name').val("");
				});
				
				$(window).resize(function(){
					var windowH = $(window).height()-51;
					var dropScrollH = $(".dropScroll_1").outerHeight();
					var vcontrolH = $(".view-controls").outerHeight();
					var menuH = windowH - dropScrollH - vcontrolH;
					
					$(".record-left").height(windowH);
					$(".menu-nav").height(menuH);
					
					$("#menuNav").slimScroll({ 
						height: menuH,
						size: '4px',
						distance: '-1px',
						railOpacity: .9, 
						alwaysVisible: !1 
				   });
					
				});
				
				$("#groupList li").click(function() {
	    			$("#groupList li").removeClass("active");
	    			$("#groupList li span").removeClass("active");
	    			$(this).addClass("active");
	    			$(this).find("span").addClass("active");
	    			$table.bootstrapTable('refresh',{url:'${ctx}/carService/showCarListJson?groupId='+$(this).val()});
	    		});
			});
			
			
			
			function headimgFMT(value) {
		    	if(value==''||value==null) {
		    		return '';
		    	}else {
		    		return '<img src="'+value+'" style="height: 49px;width: 48px" />';
		    	}
		    }
		    
		    function groupFMT(value) {
		    	if(value==''||value==null) {
		    		return '暂无分组';
		    	}else {
		    		return value;
		    	}
		    }
		    
		    function editGroup(id,groupName){
				$('#groupId').val(id);
				$('#groupName').val(groupName);
			}
			
			function delGroup(id) {
				$('#delId').val(id);
			}
			
			function operateFormatter(value, row, index) {
	    		
	    		return [
	    	            '<a class="editCar" name="editCar" href="#" data-toggle="modal" data-target="#carModal" title="修改">',
	    	                '<i class="fa fa-pencil fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>',
	    	            '<a class="userList" name="delCar" href="#" onclick="delCar('+row.id+')" data-toggle="modal" data-target="#deleteCarModal" title="删除">',
	    	                '<i class="fa fa-trash fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>',
	    	            '<a class="userList" href="${ctx}/carService/subdivisionList?carId='+row.id+'" title="详细分类">',
	    	                '<i class="fa fa-plus fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>',
	    	            '<a class="userList" href="${ctx}/carService/imageList?carId='+row.id+'" title="图片列表">',
	    	                '<i class="fa fa-picture-o fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>',
	    	            '<a class="addVideo" href="#" data-toggle="modal" data-target="#videoModal" title="宣传视频">',
	    	                '<i class="fa fa-file-movie-o fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>'
	    	        ].join('');
	    	}
	    	
	    	window.operateEvents = {
            	'click .editCar': function (e, value, row, index) {
            		$("#carId").val(row.id);
            		$("#carImage").attr("src",row.headImage);
					$("#carName").val(row.carName);
					$("#oldPrice").val(row.oldPrice);
					$("#price").val(row.price);
					$("#avg").val(row.avg);
					$("#describes").val(row.describes);
					$("#videoUrl").val(row.videoUrl);
					$("#level").val(row.level);
					$("#groupSelect").val(row.groupId);
            	},
            	'click .addVideo': function (e, value, row, index) {
            		$("#vId").val(row.id);
            		$("#videoImage").attr("src",row.videoImage);
					$("#videoUrl").val(row.videoUrl);
            	}
	    	}
			
			function addCar(){
				$("#carId").val("");
				$("#pic").attr("src","");
				$("#carName").val("");
				$("#oldPrice").val("");
				$("#price").val("");
				$("#avg").val("");
				$("#describes").val("");
				$("#groupSelect").val("");
				$("#level").val("");
			};
			
			function saveGroup() {
				var groupName = $("#groupName").val();
				if($.trim(groupName)=="") {
					alert("请输入分组名称");
				}else {
					$("#groupForm").submit();
				}
			}
			
			function getObjectURL(file) {
	    		var url = null ;
	    		if (window.createObjectURL!=undefined) { // basic
	    			url = window.createObjectURL(file) ;
	    		} else if (window.URL!=undefined) { // mozilla(firefox)
	    			url = window.URL.createObjectURL(file) ;
	    		} else if (window.webkitURL!=undefined) { // webkit or chrome
	    			url = window.webkitURL.createObjectURL(file) ;
	    		}
	    		return url ;
    		}
    	
			
			function delSub() {
				$("#delGroupForm").submit();
			}
			
			function submit() {
       			$("#carForm").submit();
       		}
       		
       		function videoSubmit() {
       			$("#videoForm").submit();
       		}
       		
       		function delCar(id) {
       			$("#delCarId").val(id);
       		}
       		
       		function delCarSub() {
       			$("#delCarForm").submit();
       		}
       		
       		function uploadImage(obj) {
       			$(obj).parent().parent().parent().parent().prev().click();
       			$("input[name='imageFile']").on("change",function(){
			    	var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
			    	if(objUrl) {
			    		$(obj).parent().prev().attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			    	}
		    	});
       		}
			
	    </script>
		
	</body>
</html>