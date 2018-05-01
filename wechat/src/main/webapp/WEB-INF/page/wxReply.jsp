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
								<div class="panel-heading">
		                           	 <label>系统回复</label>
		                        </div>
								<div class="panel-body "> 
									<div class="container record" style="width:100%;">
										<form action="${ctx}/editReply" method="post" id="wxReplyForm">
									    	<div class="row">
				                            	<div class="col-lg-12">
													<div class="form-group">
														<div class="col-lg-2">
															<label>关注时回复关键字:</label>
														</div>
														<div class="col-lg-10">
															<div class="wxReply">
																<input class="form-control content_input_height" placeholder="关注时回复关键字" type="text" id="joinKey" name="joinKey" value="${joinKey }"/>
																<div class="search-bnt guanzhu" onclick="searchBntFn('.guanzhu');">搜索</div>
																
																<div class="key-word">
																	<ul>
																		<c:forEach var="keywords" items="${keywordsList}" varStatus="st">
																			<li><span>${keywords.keywords }</span></li>
																		</c:forEach>
																	</ul>
																</div>
															</div>
															
															<p>设置用户关注公众帐号时，发送的欢迎信息，不填写则表示关注时不发送消息。 你可以在这里输入关键字或点击【搜索】选择关键字，那么用户关注公众号时就相当于发送此关键字下的回复内容给用户。
																如果您输入关键字，则需要在【文本回复】、【图文回复】等模块添加此关键字（即回复规则）及回复内容。</p>
														</div>
													</div>
												</div>
				                            </div>
				                            <div class="row">
				                            	<div class="col-lg-12">
													<div class="form-group">
														<div class="col-lg-2">
															<label>默认回复关键字:</label>
														</div>
														<div class="col-lg-10">
															<div class="wxReply">
																<input class="form-control content_input_height" placeholder="默认回复关键字" type="text" id="defaultKey" name="defaultKey"  value="${defaultKey }"/>
																<div class="search-bnt moren" onclick="searchBntFn('.moren');">搜索</div>
																
																<div class="key-word">
																	<ul>
																		<c:forEach var="keywords" items="${keywordsList}" varStatus="st">
																			<li><span>${keywords.keywords }</span></li>
																		</c:forEach>
																	</ul>
																</div>
															</div>
															<p>指定系统不知道该如何回复粉丝的消息时，发送的默认信息，不填写则表示不会默认回复消息。 你可以在这里输入关键字或点击【搜索】选择关键字, 那么系统不知道该如何回复粉丝的消息时就相当于发送此关键字下的回复内容给用户。
																如果您输入关键字，则需要在【文本回复】、【图文回复】等模块添加此关键字（即回复规则）及回复内容。</p>
														</div>
													</div>
												</div>
				                            </div>
			                            </form>
			                            <div class="row">
			                            	<div class="col-lg-12 " style="text-align: center;">
												<span id="btn-submit" class="btn btn-primary ng-binding" onclick="saveReply()">保存</span>
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
			
			function saveReply(){
				$('#wxReplyForm').submit();
			}
			
			function searchBntFn(bnt){
				$(bnt).parents(".wxReply").find(".key-word").show();
			}
			
			$(".key-word li").click(function(){
				var vels = $(this).find("span").text();
				$(this).parents(".wxReply").find("input").val(vels);
				$(this).parents(".key-word").hide();
			});
			
	    </script>
		
	</body>
</html>