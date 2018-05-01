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
    			<%@include file="/WEB-INF/wxHead.jsp"%>
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
														<div class="customize">
														</div>
													</div>
													<div class="cuset_esse_a cuset_back">
														<h4>模板消息管理</h4>
														<div class="cuset_esse">
															<table border="0" cellspacing="0" cellpadding="0" class="cuset_esse_type">
																<tr><th>模板ID</th><th>标题</th><th>一级行业</th><th>二级行业</th><th>操作</th></tr>
																<c:forEach var="template" items="${templateList}" varStatus="tl">
																	<tr>
																		<td>${template.templateId }</td>
																		<td>${template.title }</td>
																		<td>${template.primaryIndustry }</td>
																		<td>${template.deputyIndustry }</td>
																		<td>
																			<i title="查看例子"><img src="${ctx }/img/modify2.png" onclick="review(${template.id })" data-toggle="modal" data-target="#reviewModal"/></i>
																			<i title="删除"><img src="${ctx }/img/delete.png" onclick="sendId(${template.id })" data-toggle="modal" data-target="#deleteModal"/></i>
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
		
		<!-- 查看模板消息例子 -->
		<div class="modal fade" id="reviewModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">查看模板消息例子</h4>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<span id="content"></span>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 删除模板消息 -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">删除模板消息</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/deleteTemplate" method="post" id="delTemplateForm">
							<input type="hidden" name="id" id="delTagId">
							<div class="container-fluid">
								<h4>删除后将无法恢复，您确定删除所选的模板消息吗？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="delTemplate()">确定</button>
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
			
			function sendId(id) {
				$("#delTagId").val(id);
			}
			
			function delTemplate() {
				$("#delTemplateForm").submit();
			}
			
			function review(id) {
				$.ajax({
             		type: "get",
             		url: "${ctx}/getTemplateInfo",
             		data: {'id':id},
             		dataType: "json",
             		success: function(data){
             			var obj = eval(data);
             			if(obj.msg=='success') {
             				$("#content").html(obj.example);
             			}
             		}
         		});
			}
	    </script>
		
	</body>
</html>