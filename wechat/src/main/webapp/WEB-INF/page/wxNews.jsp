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
															<span class="glyphicon glyphicon-chevron-left" aria-hidden="true" id="barsLeft" onclick="toNewsKey()">返回图文规则列表</span>
															<span id="addNews" data-toggle='modal' data-target='#myModal'>+添加图文回复内容</span>
														</div>
													</div>
													<div class="cuset_esse_a cuset_back">
														<h4>图文回复内容管理</h4>
														<div class="cuset_esse">
															<table border="0" cellspacing="0" cellpadding="0" class="cuset_esse_type">
																<tr><th style="width: 15%">图片</th><th style="width: 20%">标题</th><th style="width: 45%">链接</th><th style="width: 10%">排序</th><th style="width: 10%">操作</th></tr>
																<c:forEach var="news" items="${newsList}" varStatus="st">
																	<tr>
																		<td><img src="${news.picUrl }" style="max-height: 50px;max-width: 100px"></td>
																		<td>${news.title }</td>
																		<td style="word-break:break-all;word-wrap:break-word;">${news.url }</td>
																		<td>${news.sortLevel }</td>
																		<td>
																			<img onclick="editNews(${news.id })" class="cuset_esse_img1" src="${ctx }/img/modify1.png" data-toggle="modal" data-target="#myModal"/>
																			<img src="${ctx }/img/delete.png" onclick="sendId(${news.id })" data-toggle="modal" data-target="#deleteModal"/>
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
   		
   		<!-- 添加/修改图文回复内容 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">添加图文回复内容</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/reply/editReplyInfo" method="post" id="newsForm" enctype="multipart/form-data">
							<input type="hidden" name="id" id="newsId">
							<input type="hidden" name="picUrl" id="picUrl"/>
							<input type="file" id="upload" accept="image/*" name="imageFile" style="display:none" />
							<div class="container-fluid">
								<div class="row">
									
		                        	<div class="form-group" style="text-align: center;">
		                        		<span id="errpic" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content=""></span>
		                        		<img src="https://pic.ehub.net/2016/0106/872973.gif" style="border-radius: 50%;width: 50px;height: 50px;" id="imgLoad">
				                        <img src="https://pic.ehub.net/2017/0524/991377.jpg" style="width: 150px;height: 100px" id="pic">
				                        <p style="margin: 10px 0px">
				                        	<button type="button" name="button" id="selectBtn" class="btn btn-primary btn-sm">上传图片</button>
				                        </p>
				                    </div>
				                </div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>标题</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="标题" type="text" id="title" name="title" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="标题未填写">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>链接</label><b style="color: red;">*</b>
											<input class="form-control content_input_height" placeholder="链接" type="text" id="url" name="url" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="链接未填写">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>简介</label>
											<textarea class="form-control" id="description" name="description" rows="3" cols="3"></textarea>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>排序</label><b style="color: red;">*数字，越小越向前</b>
											<input class="form-control content_input_height" placeholder="排序" type="text" id="sortLevel" name="sortLevel" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="排序数字未填写">
										</div>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="saveNews()">保存</button>
					</div>
				</div>
			</div>
		</div>		
		
		<!-- 删除图文回复内容数据 -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">删除图文回复内容</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/reply/deleteReplyInfo" method="post" id="delNewsForm">
							<input type="hidden" name="id" id="delNewsId">
							<div class="container-fluid">
								<h4>删除后将无法恢复，您确定删除所选的图文回复内容吗？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="delNews()">确定</button>
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
			
			$('#imgLoad').hide(); 
			
			$("#selectBtn").click(function () {
		    	$("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
		    	$("#upload").on("change",function(){
			    	var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
			    	if (objUrl) {
			    		$("#pic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			    	}
		    	});
	    	});
		    
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
			
			$('#addNews').click(function(){
				$('#myModalTitle').text('添加图文回复内容');
	        	$('#subtn').text('保存');
				$('#newsId').val(null);
				$('#picUrl').val('https://pic.ehub.net/2017/0524/991377.jpg');
				$('#title').val('');
				$('#url').val('');
				$('#description').val('');
				$('#sortLevel').val('1');
			});
			
			function editNews(id){
				$('#myModalTitle').text('修改用户标签');
	        	$('#subtn').text('更新');
	        	$.ajax({
	           		type: "POST",
	           		url: "${ctx}/reply/findImgaeReply",
	           		async: false,
	           		data: {id:id},
	           		dataType: "json",
	           		success: function(data){
	           			var obj = eval(data);
	           			if(obj.CODE=='200'){
	           				$('#newsId').val(obj.news.id);
	        				$('#picUrl').val(obj.news.picUrl);
	        				$('#headimg').attr('src', obj.news.picUrl);
	        				$('#title').val(obj.news.title);
	        				$('#url').val(obj.news.url);
	        				$('#description').val(obj.news.description);
	        				$('#sortLevel').val(obj.news.sortLevel);
	           			}
	           		}
	       		});
			}
			
			function saveNews(){
				var title=$('#title').val();
				if(title==''){
		    		$('#title').focus();
		    		return;
		    	}else{
		    		$('#newsForm').submit();
		    	}
			}
			
			function sendId(id) {
				$("#delNewsId").val(id);
			}
			
			function delNews() {
				$("#delNewsForm").submit();
			}
			
			function toNewsKey() {
				window.localtion.href="${ctx}/reply/imgaeReply";
			}
			
	    </script>
		
	</body>
</html>