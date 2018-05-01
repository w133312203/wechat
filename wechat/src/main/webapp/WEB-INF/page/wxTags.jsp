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
		<link href="${ctx }/dist/css/sb-admin-2.css" rel="stylesheet">
		
	    <link href="${ctx }/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
	   	<link href="${ctx }/dist/css/bootstrap-table.css" rel="stylesheet">
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
			<%@include file="/WEB-INF/wxMenu.jsp"%>
	       	<div id="page-wrapper" style="padding-bottom: 105px">
	       		<!--头部导航-->
    			<%@include file="/WEB-INF/head.jsp"%>
	       		<div class="container-fluid ">
					<div class="row" style="margin:0;">
						<div class="col-lg-12 ">
							<div class="panel panel-default ">
								<div class="panel-body "> 
									<div class="container record" style="width:100%;">
								    	<div class="row">
											<div class="col-md-12 record-right cuset_txt">
												<div class="cuset_s off">
													<div class="bootstrap-table">
														<div class="customize"><span id="addTag" data-toggle='modal' data-target='#myModal'>+添加标签</span></div>
													</div>
													<div class="cuset_esse_a cuset_back">
														<h4>用户标签管理</h4>
														<div class="cuset_esse">
															<table border="0" cellspacing="0" cellpadding="0" class="cuset_esse_type">
																<tr><th>标签ID</th><th>标签名称</th><th>标签内用户数量</th><th>操作</th></tr>
																<c:forEach var="tag" items="${tagList}" varStatus="st">
																	<tr>
																		<td>${tag.tagId }</td>
																		<td>${tag.tagName }</td>
																		<td>${tag.count }</td>
																		<td>
																			<img onclick="editTag(${tag.id })" class="cuset_esse_img1" src="${ctx }/img/modify1.png" data-toggle="modal" data-target="#myModal"/>
																			<img src="${ctx }/img/delete.png" onclick="sendId(${tag.id })" data-toggle="modal" data-target="#deleteModal"/>
																		</td>
																	</tr>
																</c:forEach>
															</table>
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
	       	</div>
		</div>
   		
   		<!-- 添加/修改用户标签 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">添加用户标签</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/tag/editTag" method="post" id="tagForm">
							<input type="hidden" name="id" id="tagId">
							<div class="container-fluid">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>标签名称</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="标签名称" type="text" id="tagName" name="tagName" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="标签名称未填写">
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="saveTag()">保存</button>
					</div>
				</div>
			</div>
		</div>		
		
		<!-- 删除用户标签 -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">删除用户标签</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/tag/deleteTag" method="post" id="delTagForm">
							<input type="hidden" name="id" id="delTagId">
							<div class="container-fluid">
								<h4>删除后将无法恢复，您确定删除所选的用户标签吗？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="delTag()">确定</button>
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
			
			$('#addTag').click(function(){
				$('#myModalTitle').text('添加用户标签');
	        	$('#subtn').text('保存');
				$('#tagName').val('');
			});
			
			function editTag(id){
				$('#myModalTitle').text('修改用户标签');
	        	$('#subtn').text('更新');
	        	$.ajax({
	           		type: "POST",
	           		url: "${ctx}/tag/findTagById",
	           		async: false,
	           		data: {id:id},
	           		dataType: "json",
	           		success: function(data){
	           			var obj = eval(data);
	           			if(obj.CODE=='200'){
	           				$('#tagName').val(obj.TAG.tagName);
	           			}
	           		}
	       		});
			}
			
			function saveTag(){
				var tagName=$('#tagName').val();
				if(tagName==''){
		    		$('#tagName').focus();
		    		return;
		    	}else{
		    		$('#tagForm').submit();
		    	}
			}
			
			function sendId(id) {
				$("#delTagId").val(id);
			}
			
			function delTag() {
				$("#delTagForm").submit();
			}
		
	    </script>
		
	</body>
</html>