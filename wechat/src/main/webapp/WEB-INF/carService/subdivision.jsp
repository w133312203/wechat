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
							<div class="col-lg-12 padding_right_0">
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
		                </div>
					</div>
				</div>
	       	</div>
		</div>
		
		<div class="modal fade" id="deleteCarModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">删除车辆类型</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/carService/deleteSubdivision" method="post" id="delCarForm">
							<input type="hidden" name="id" id="delCarId">
							<input type="hidden" name="carId" value="${carId}">
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
		
		<!-- 添加售卖车辆 -->
		<div class="modal fade" id="carModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">车辆细分信息</h4>
		            </div>
					<div class="modal-body">
						<form action="${ctx }/carService/editSubdivision" method="post" id="carForm" enctype="multipart/form-data">
							<input type="hidden" id="carId" name="id" />
							<input type="hidden" name="carId" value="${carId}" />
							<div class="container-fluid">
								<div class="row">
			                    	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>车辆类型：
					   				   	</div>
					   				</div>
			                       	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="车辆类型" type="text" value="" id="carName" name="carName" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="车辆类型未填写">
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
			                               	<input class="form-control content_input_height" placeholder="现价" type="text" value="" id="price" name="price" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="现价未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>指导价：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="指导价"  type="text" value="" id="guidancePrice" name="guidancePrice" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="指导价未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>马力：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="马力"  type="text" value="" id="horsepower" name="horsepower" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="马力未填写">
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
					   						<b style="color: red;">*</b>款式：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                           		<select class="form-control" name="style" id="styleSelect">
			                           			<option value="2017款">2017款</option>
			                           			<option value="2018款">2018款</option>
			                                </select>
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>车辆类型：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                           		<select class="form-control" name="carType" id="carType">
			                           			<option value="0">国产</option>
			                           			<option value="1">进口</option>
			                                </select>
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>座位数：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="座位数"  type="text" value="" id="seatNum" name="seatNum" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="座位数未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>排量：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                           		<select class="form-control" name="displacement" id="displacement">
			                           			<option value="0">1.0L(含)以下</option>
			                           			<option value="1">1.0L-1.6L(含)</option>
			                           			<option value="2">1.6L-2.0L(含)</option>
			                           			<option value="3">2.0L-2.5L(含)</option>
			                           			<option value="4">2.5L-3.0L(含)</option>
			                           			<option value="5">3.0L-4.0L(含)</option>
			                           			<option value="6">4.0L以上</option>
			                                </select>
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>裸车价：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="裸车价"  type="text" value="" id="barePrice" name="barePrice" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="裸车价未填写">
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
						field: 'carName',
						title: '车辆类型'
					},{
						field: 'price',
						title: '现价'
					},{
						field: 'guidancePrice',
						title: '指导价'
					},{
						field: 'horsepower',
						title: '马力'
					},{
						field: 'describes',
						title: '描述'
					},{
						field: 'style',
						title: '款式'
					},{
						field: 'carType',
						title: '类型',
						formatter: carTypeFormatter
					},{
						field: 'seatNum',
						title: '座位数'
					},{
						field: 'displacement',
						title: '排量',
						formatter: displacementFormatter
					},{
						field: 'barePrice',
						title: '裸车价'
					},{
						title: '操作',
						events: operateEvents,
						formatter: operateFormatter
					}],
					url:'${ctx}/carService/showSubdivisionListJson?carId='+${carId}
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
				$(".bars").append("<div onclick='addCar()' class='mark-tag' data-toggle='modal' data-target='#carModal'>添加车辆类型</div>");
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
			
			
			
			function carTypeFormatter(value) {
		    	if(value==0) {
		    		return '国产';
		    	}else {
		    		return '进口';
		    	}
		    }
		    
		    function displacementFormatter(value) {
		    	if(value==0) {
		    		return '1.0L(含)以下';
		    	}else if(value==1) {
		    		return '1.0L-1.6L(含)';
		    	}else if(value==2) {
		    		return '1.6L-2.0L(含)';
		    	}else if(value==3) {
		    		return '2.0L-2.5L(含)';
		    	}else if(value==4) {
		    		return '2.5L-3.0L(含)';
		    	}else if(value==5) {
		    		return '3.0L-4.0L(含)';
		    	}else if(value==6) {
		    		return '4.0L以上';
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
	    	            '<a class="editCar" name="editCar" href="javascript:void(0)" data-toggle="modal" data-target="#carModal" title="修改">',
	    	                '<i class="fa fa-pencil fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>',
	    	            '<a class="userList" name="delCar" href="javascript:void(0)" onclick="delCar('+row.id+')" data-toggle="modal" data-target="#deleteCarModal" title="删除">',
	    	                '<i class="fa fa-trash fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>',
	    	            '<a class="userList" href="${ctx}/carService/configure?subdivisionId='+row.id+'" title="车型配置信息">',
	    	                '<i class="fa fa-bars fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>'
	    	        ].join('');
	    	}
	    	
	    	window.operateEvents = {
            	'click .editCar': function (e, value, row, index) {
            		$("#carId").val(row.id);
					$("#carName").val(row.carName);
					$("#price").val(row.price);
					$("#guidancePrice").val(row.guidancePrice);
					$("#horsepower").val(row.horsepower);
					$("#describes").val(row.describes);
					$("#styleSelect").val(row.style);
					$("#carType").val(row.carType);
					$("#seatNum").val(row.seatNum);
					$("#displacement").val(row.displacement);
					$("#barePrice").val(row.barePrice);
            	}
	    	}
			
			function addCar(){
				$("#carId").val("");
				$("#carName").val("");
				$("#price").val("");
				$("#guidancePrice").val("");
				$("#horsepower").val("");
				$("#describes").val("");
				$("#styleSelect").val("");
				$("#carType").val("");
				$("#seatNum").val("");
				$("#displacement").val("");
				$("#barePrice").val("");
			};
			
			function saveGroup() {
				var groupName = $("#groupName").val();
				if($.trim(groupName)=="") {
					alert("请输入分组名称");
				}else {
					$("#groupForm").submit();
				}
			}
			
			function delSub() {
				$("#delGroupForm").submit();
			}
			
			function submit() {
       			$("#carForm").submit();
       		}
       		
       		function delCar(id) {
       			$("#delCarId").val(id);
       		}
       		
       		function delCarSub() {
       			$("#delCarForm").submit();
       		}
			
	    </script>
		
	</body>
</html>