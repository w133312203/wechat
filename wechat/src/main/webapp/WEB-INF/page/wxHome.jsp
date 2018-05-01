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
			<%@include file="/WEB-INF/menu.jsp"%>
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
														<div class="customize"><span id="addAccount" data-toggle='modal' data-target='#myModal'>+添加公众号</span></div>
													</div>
													<div class="cuset_esse_a cuset_back">
														<h4>公众账号管理</h4>
														<div class="cuset_esse">
															<table border="0" cellspacing="0" cellpadding="0" class="cuset_esse_type">
																<tr><th>公众帐号名称</th><th>微信号</th><th>公众号原始id</th><th>创建时间</th><th>修改</th><th>操作</th></tr>
																<c:forEach var="account" items="${accounts}" varStatus="st">
																<tr class="${account.id }">
																	<td>${account.name }</td>
																	<td>${account.account }</td>
																	<td>${account.originalId }</td>
																	<td><fmt:formatDate value="${account.createTime }" pattern="yyyy-MM-dd HH:mm"/></td>
																	<td>
																		<img onclick="editAccount(${account.id })" class="cuset_esse_img1" src="${ctx }/img/modify1.png" data-toggle="modal" data-target="#myModal"/>
																		<img src="${ctx }/img/delete.png" onclick="sendId(${account.id })" data-toggle="modal" data-target="#deleteModal"/>
																	</td>
																	<td>
																		<span class="setup_btn">功能设置</span>
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
		
		<!-- 添加微信公众号 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">添加微信公众号</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/account/editAccount" method="post" id="accountForm">
							<input type="hidden" name="id" id="accountId">
							<div class="container-fluid">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>公众号名称</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="公众号名称" type="text" id="name" name="name" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="公众号名称未填写">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>微信号</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="微信号" type="text" id="account" name="account" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="微信号未填写">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>原始ID</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="原始ID" type="text" id="originalId" name="originalId" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="原始未填写">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>接口地址</label><b style="color: red;">保存后自动生成URL，并把此URL添加在微信公众平台服务器配置里</b>
											<input class="form-control content_input_height" placeholder="接口地址" type="text" id="serverUrl" name="serverUrl"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>TOKEN</label><b style="color: red;">*&nbsp;&nbsp;与公众帐号官方网站上保持一致</b>
											<input class="form-control content_input_height" placeholder="TOKEN" type="text" id="token" name="token" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="TOKEN未填写">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>AppId</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="AppId" type="text" id="appId" name="appId" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="AppId未填写">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>AppSecret</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="AppSecret" type="text" id="appSecret" name="appSecret" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="AppSecret未填写">
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="saveAccount()">保存</button>
					</div>
				</div>
			</div>
		</div>		
		
		<!-- 删除微信公众号 -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">删除微信公众号</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/account/deleteAccount" method="post" id="delAccountForm">
							<input type="hidden" name="id" id="delAccountId">
							<div class="container-fluid">
								<h4>删除后将无法恢复，您确定删除所选的微信公众号吗？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="delAccount()">确定</button>
					</div>
				</div>
			</div>
		</div>
   		
   		<form action="${ctx}/account/show" method="post" id="setupAccountForm">
   			<input type="hidden" name="id" id="setupAccountId">
   		</form>
   		
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
			
			$('#addAccount').click(function(){
				$('#myModalTitle').text('添加微信公众号');
	        	$('#subtn').text('保存');
	        	$('#accountId').val(null);
				$('#name').val('');
				$('#account').val('');
				$('#originalId').val('');
				$('#serverUrl').val('');
				$('#token').val('');
				$('#appId').val('');
				$('#appSecret').val('');
			});
			
			function editAccount(id){
				$('#myModalTitle').text('修改微信公众号');
	        	$('#subtn').text('更新');
	        	$.ajax({
	           		type: "POST",
	           		url: "${ctx}/account/findAccount",
	           		async: false,
	           		data: {id:id},
	           		dataType: "json",
	           		success: function(data){
	           			var obj = eval(data);
	           			if(obj.CODE=='200'){
	           				$('#accountId').val(obj.accounts.id);
	           				$('#name').val(obj.accounts.name);
	        				$('#account').val(obj.accounts.account);
	        				$('#originalId').val(obj.accounts.originalId);
	        				$('#serverUrl').val(obj.accounts.serverUrl);
	        				$('#token').val(obj.accounts.token);
	        				$('#appId').val(obj.accounts.appId);
	        				$('#appSecret').val(obj.accounts.appSecret);
	           			}
	           		}
	       		});
			};
			
			function saveAccount(){
				var name=$('#name').val();
				var account=$('#account').val();
				var originalId=$('#originalId').val();
				var token=$('#token').val();
				var appId=$('#appId').val();
				var appSecret=$('#appSecret').val();
				if(name==''){
		    		$('#name').focus();
		    		return;
		    	}else if(account==''){
		    		$('#account').focus();
		    		return;
		    	}else if(originalId==''){
		    		$('#originalId').focus();
		    		return;
		    	}else if(token==''){
		    		$('#token').focus();
		    		return;
		    	}else if(appId==''){
		    		$('#appId').focus();
		    		return;
		    	}else if(appSecret==''){
		    		$('#appSecret').focus();
		    		return;
		    	}else{
		    		$.ajax({
		           		type: "POST",
		           		url: "${ctx}/account/findByAppId",
		           		async: false,
		           		data: {appId:appId},
		           		dataType: "json",
		           		success: function(data){
		           			var obj = eval(data);
		           			if(obj.code=='Y'){
		           				$('#appId').attr('data-content','此公众号已存在');
		           				$('#appId').focus();
		           				return;
		           			}else{
		           				$('#accountForm').submit();	
		           			}
		           		}
		       		});
		    	}
			}
			
			function sendId(id) {
				$("#delAccountId").val(id);
			}
			
			function delAccount() {
				$("#delAccountForm").submit();
			}
			
			$('.setup_btn').click(function(){
				var id=$(this).parent().parent().attr("class");
				$("#setupAccountId").val(id);
				$("#setupAccountForm").submit();
			});
	    </script>
		
	</body>
</html>