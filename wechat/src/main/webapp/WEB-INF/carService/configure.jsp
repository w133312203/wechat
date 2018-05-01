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
						<h4 class="modal-title" id="myModalTitle">删除配置内容</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/carService/deleteConfigure" method="post" id="delFieldForm">
							<input type="hidden" name="id" id="delFieldId">
							<input type="hidden" name="subdivisionId" value="${subdivisionId}" />
							<div class="container-fluid">
								<h4>是否确定删除？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="delFieldSub()">确定</button>
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
		                <h4 class="modal-title" id="myModalLabel">配置字段内容</h4>
		            </div>
					<div class="modal-body">
						<form action="${ctx }/carService/editConfigure" method="post" id="fieldForm" enctype="multipart/form-data">
							<input type="hidden" id="fieldId" name="id" />
							<input type="hidden" id="fieldType" name="type" />
							<input type="hidden" name="subdivisionId" value="${subdivisionId}" />
							<div class="container-fluid">
								<div class="row">
		                        	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>配置字段：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-9">
			                           	<div class="form-group">
			                           		<select class="form-control" name="fieldId" id="fieldSelect" onchange="selectField(this)">
			                           			<c:forEach var="configure" items="${configureList}" varStatus="st">
			                           				<option value="${configure.id}" data-type="${configure.type}">${configure.fieldName}</option>
			                           			</c:forEach>
			                                </select>
			                           	</div>
		                           	</div>
		                        </div>
								<div class="row">
			                    	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>配置字段内容：
					   				   	</div>
					   				</div>
			                       	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="配置字段内容" type="text" value="" id="context" name="context" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="配置字段内容未填写">
			                           	</div>
			                        </div>
			                    </div>
			                    <div class="row">
			                    	<div class="col-lg-3">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>排序级别：
					   				   	</div>
					   				</div>
			                       	<div class="col-lg-9">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="排序级别" type="text" value="" id="level" name="level" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="排序级别未填写">
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
		
			var maxLevel = 0;
			var $table = $('#table');
			
			$(document).ready(function() {
				
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
					toolbar: '#toolbar',
					showRefresh:true,
					showColumns:true,
					sidePagination:'server',
					onLoadSuccess: function(data) {
	        			maxLevel = data.level;
	        		},
					columns: [{
						field: 'state',
	                    title: '',
	                    checkbox: true
	                },{
						field: 'id',
						title: 'ID',
						visible: false   
					},{
						field: 'fieldName',
						title: '配置字段名称'
					},{
						field: 'context',
						title: '配置字段内容'
					},{
						field: 'level',
						title: '排序级别'
					},{
						title: '操作',
						events: operateEvents,
						formatter: operateFormatter
					}],
					url:'${ctx}/carService/showConfigureListJson?subdivisionId='+${subdivisionId}
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
				$(".bars").append("<div onclick='addField()' class='mark-tag' data-toggle='modal' data-target='#carModal'>添加配置内容</div>");
				$(".bars").append("<div onclick='synField()' class='mark-tag' data-toggle='modal' data-target='#carModal'>同步配置字段</div>");
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
			});
			
			function operateFormatter(value, row, index) {
	    		return [
	    	            '<a class="editField" name="editField" href="javascript:void(0)" data-toggle="modal" data-target="#carModal" title="修改">',
	    	                '<i class="fa fa-pencil fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>',
	    	            '<a class="userList" name="delCar" href="javascript:void(0)" onclick="delField('+row.id+')" data-toggle="modal" data-target="#deleteCarModal" title="删除">',
	    	                '<i class="fa fa-trash fa-fw"></i>&nbsp;&nbsp;',
	    	            '</a>'
	    	        ].join('');
	    	}
	    	
	    	window.operateEvents = {
            	'click .editField': function (e, value, row, index) {
            		$("#fieldId").val(row.id);
					$("#context").val(row.context);
					$("#fieldSelect").val(row.fieldId);
					$("#level").val(row.level);
					var type = $("#fieldSelect").find("option:selected").data("type");
					$("#fieldType").val(type);
					if(type==1) {
       					$("#context").attr("disabled","disabled");
       				}else {
       					$("#context").attr("disabled",false);
       				}
            	}
	    	}
			
			function addField(){
				$("#fieldId").val("");
				$("#context").val("");
				$("#fieldSelect").val("");
				var maxLevels = parseInt(maxLevel)+parseInt(1);
				$("#level").val(maxLevels);
				$("#context").attr("disabled",false);
				$("#fieldType").val(0);
			};
			
			function submit() {
       			$("#fieldForm").submit();
       		}
       		
       		function delField(id) {
       			$("#delFieldId").val(id);
       		}
       		
       		function delFieldSub() {
       			$("#delFieldForm").submit();
       		}
       		
       		function synField() {
       			window.location.href = '${ctx}/carService/synConfigureField?subdivisionId='+${subdivisionId};
       		}
       		
       		function selectField(obj) {
       			var type = $(obj).find("option:selected").data("type");
       			$("#fieldType").val(type);
       			if(type==1) {
       				$("#context").attr("disabled","disabled");
       			}else {
       				$("#context").attr("disabled",false);
       			}
       		}
			
	    </script>
		
	</body>
</html>