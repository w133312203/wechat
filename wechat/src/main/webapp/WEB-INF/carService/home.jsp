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
			<%@include file="/WEB-INF/carService/menu.jsp"%>
	       	<div id="page-wrapper" style="padding-bottom: 105px">
	       		<!--头部导航-->
    			<%@include file="/WEB-INF/wxHead.jsp"%>
	       		<div class="container-fluid ">
					<div class="row" style="margin:0;">
						<div class="col-lg-12 ">
							<div class="panel panel-default ">
								<div class="panel-heading">
		                           	 <label>基本信息</label>
		                        </div>
								<div class="panel-body "> 
									<div class="container record" style="width:100%;">
										<form action="${ctx}/carService/edit" method="post" id="information">
									    	<div class="row">
				                            	<div class="col-lg-12">
													<div class="form-group">
														<div class="col-lg-2">
															<label>商店名称:</label>
														</div>
														<div class="col-lg-10">
															<div class="wxReply">
																<input class="form-control content_input_height" placeholder="商店名称" type="text" id="storeName" name="storeName" value="${information.storeName }"/>
															</div>
														</div>
													</div>
												</div>
				                            </div>
				                            <div class="row">
				                            	<div class="col-lg-12">
													<div class="form-group">
														<div class="col-lg-2">
															<label>联系电话:</label>
														</div>
														<div class="col-lg-10">
															<div class="wxReply">
																<input class="form-control content_input_height" placeholder="联系电话" type="text" id="telephone" name="telephone" value="${information.telephone }"/>
															</div>
														</div>
													</div>
												</div>
				                            </div>
				                            <div class="row">
				                            	<div class="col-lg-12">
													<div class="form-group">
														<div class="col-lg-2">
															<label>商店地址:</label>
														</div>
														<div class="col-lg-10">
															<div class="wxReply">
																<input class="form-control content_input_height" placeholder="商店地址" type="text" id="address" name="address" value="${information.address }"/>
															</div>
														</div>
													</div>
												</div>
				                            </div>
			                            </form>
			                            <div class="row">
			                            	<div class="col-lg-12 " style="text-align: center;">
												<span id="btn-submit" class="btn btn-primary ng-binding" onclick="submit()">保存</span>
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
			
			function submit(){
				var storeName = $("#storeName").val();
				if($.trim(storeName)=="") {
					alert("请输入商店名称！");
				}else {
					$("#information").submit();
				} 
			}
			
	    </script>
		
	</body>
</html>