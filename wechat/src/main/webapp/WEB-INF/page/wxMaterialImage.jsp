<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="renderer" content="webkit">
    <meta name="referrer" content="never">
    <title>eHub微信账号管理</title>
    
    <link href="${ctx }/dist/css/timeline.css" rel="stylesheet">
    <link href="${ctx }/bower_components/morrisjs/morris.css" rel="stylesheet">
    <link href="${ctx }/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${ctx }/bower_components/datatables-responsive/css/dataTables.responsive.css" rel="stylesheet">
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
	
	<style type="text/css">
			
	</style>
</head>

<body>

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
							    	<form action="${ctx}/wx/material/image" method="post" name="newsForm" id="materialNewsList">
										<input type="hidden" name="pageIndex" id="pageIndex" />
										<div class="row">
											<div class="col-lg-4 cusetmaterial">
												<span id="addMaterialImage" data-toggle="modal" data-target="#myModal">+添加图片素材</span>&nbsp;&nbsp;
												<span data-toggle="modal" data-target="#synchronizeModal">同步所有图片素材</span>
											</div>
											<div class="col-lg-4 cusetmaterial">
											</div>
							                <div class="col-lg-4">
							            	</div>
							            </div>
							            <div class="row">
							            	<c:forEach var="image" items="${wxImageList}" varStatus="newsStatus">
										    	<div class="col-lg-4" >
								                    <div class="panel panel-default" style="border:none">
								                        <div style="position: relative;" class="panel-heading">
								                           	
								                        </div>
								                        <div class="panel-body" style="padding:0px" >
								                        	<div style="position:relative;">
								                        		<img src="https://pic.ehub.net/${image.picUrl }" class="img-full" style="width: 100%;height: 250px;">
								                        	</div>
								                        </div>
								                        <div class="panel-footer">
								                        	<!-- <a href="javascript:void(0);"><span class="pull-left">预览</span></a> -->
								                            <a href="" onclick="delMaterialImage('${image.mediaId }')" data-toggle="modal" data-target="#deleteModal"><span class="pull-right"><span class="pull-left">删除</span></span></a>
								                            <div class="clearfix"></div>
								                        </div>
								                    </div>
								                </div>
							                </c:forEach>
							            </div>
							        </form>
							        
						            <div class="row">
						            	<div class="col-lg-6">
						            		<c:if test="${count!=0}">
							        			<div class="dataTables_info">
							        				<c:if test="${pageIndex+1<pageCount}">
							        					显示第 ${pageIndex*maxResult+1}到 第${pageIndex*maxResult+maxResult}条记录，总共 ${count}条记录，每页显示 ${maxResult}条记录
							        				</c:if>
							        				<c:if test="${pageIndex+1==pageCount}">
							        					显示第 ${pageIndex*maxResult+1}到 第${count}条记录，总共 ${count}条记录，每页显示 ${maxResult}条记录
							        				</c:if>
								        		</div>
							        		</c:if>
							        		<c:if test="${count==0}">
							        			<div class="dataTables_info">
							        				显示第 0 到第 0 条记录，总共 0 条记录
							        			</div>
							        		</c:if>
						            	</div>
						            	<div class="col-lg-6">
						            		<s:hidden name="pageIndex" />
								        	<div class="dataTables_paginate paging_simple_numbers">
								        		<ul class="pagination">
								        			<c:if test="${count!=0}">
									        			<c:if test="${pageIndex>0}">
									        				<li class="paginate_button previous">
										        				<a href="javascript:void(0);" onclick="topage('-1')">上一页</a>
										        			</li>
									        			</c:if>
									        			
									        			<c:if test="${pageCount<=10}">
								        					<c:forEach begin="1" end="${pageCount}" varStatus="pc">
									        					<c:if test="${pageIndex+1==pc.count}">
														    		<li class="paginate_button active">
										        						<a href="javascript:void(0);">${pc.count }</a>
										        					</li>
														    	</c:if>
									        					<c:if test="${pageIndex+1!=pc.count}">
														    		<li class="paginate_button">
										        						<a href="javascript:void(0);" onclick="topage('${pc.count-1}')">${pc.count }</a>
										        					</li>
														    	</c:if>
									        				</c:forEach>
								        				</c:if>
								        				
								        				<c:if test="${pageCount>10}">
								        					<c:if test="${pageIndex<10}">
								        						<c:forEach begin="1" end="10" varStatus="pc" var="i">
										        					<c:if test="${pageIndex+1==pc.count}">
															    		<li class="paginate_button active">
											        						<a href="javascript:void(0);">${pc.count }</a>
											        					</li>
															    	</c:if>
										        					<c:if test="${pageIndex+1!=pc.count}">
															    		<li class="paginate_button">
											        						<a href="javascript:void(0);" onclick="topage('${pc.count-1}')">${pc.count }</a>
											        					</li>
															    	</c:if>
										        				</c:forEach>
								        					</c:if>
								        					<c:if test="${pageIndex>=10}">
								        						<c:if test="${pageIndex+4>pageCount}">
								        							<c:forEach begin="${pageIndex-4}" end="${pageCount}" var="i">
											        					<c:if test="${pageIndex+1==i}">
																    		<li class="paginate_button active">
												        						<a href="javascript:void(0);">${i}</a>
												        					</li>
																    	</c:if>
											        					<c:if test="${pageIndex+1!=i}">
																    		<li class="paginate_button">
												        						<a href="javascript:void(0);" onclick="topage('${i-1}')">${i}</a>
												        					</li>
																    	</c:if>
											        				</c:forEach>
								        						</c:if>
								        						<c:if test="${pageIndex+4<=pageCount}">
								        							<c:forEach begin="${pageIndex-4}" end="${pageIndex+4}" var="i">
											        					<c:if test="${pageIndex+1==i}">
																    		<li class="paginate_button active">
												        						<a href="javascript:void(0);">${i}</a>
												        					</li>
																    	</c:if>
											        					<c:if test="${pageIndex+1!=i}">
																    		<li class="paginate_button">
												        						<a href="javascript:void(0);" onclick="topage('${i-1}')">${i}</a>
												        					</li>
																    	</c:if>
											        				</c:forEach>
								        						</c:if>
								        					</c:if>
								        				</c:if>
								        				
									        			<c:if test="${pageIndex+1<pageCount}">
									        				<li class="paginate_button next">
										        				<a href="javascript:void(0);" onclick="topage('+1')">下一页</a>
										        			</li>
									        			</c:if>
													</c:if>
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
	</div>
	
	<!-- 添加图片素材 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalTitle">添加图片素材</h4>
				</div>
				<div class="modal-body">
					<form action="${ctx}/wx/material/image/add" method="post" id="addImageform" enctype="multipart/form-data">
						<input id="upload" name="imageFile" accept="image/*" type="file" style="display: none"/>
						<div class="container-fluid">
							<div class="row">
	                        	<div class="form-group" style="text-align: center;">
	                        		<span id="errpic" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content=""></span>
	                        		<img src="https://pic.ehub.net/2016/0106/872973.gif" style="border-radius: 50%;width: 50px;height: 50px;" id="imgLoad">
			                        <img src="" id="pic" style="width: 150px;height: 100px">
			                        <p style="margin: 10px 0px">
			                        	<button type="button" name="button" id="selectBtn" class="btn btn-primary btn-sm">点击选择图片</button>
			                        </p>
			                    </div>
			                </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="subtn" onclick="saveImage()">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 删除图片素材 -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="delModalTitle">删除图片素材</h4>
				</div>
				<div class="modal-body">
					<form action="${ctx}/wx/material/deleteMaterial" method="post" id="delMaterialForm">
						<input type="hidden" id="delMediaId" name="mediaId"/>
  						<input type="hidden" name="type" value="image"/>
						<div class="container-fluid">
							<h4>删除此条数据后将无法恢复。是否确定删除？</h4>
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
	
	<!-- 同步所有图片素材 -->
	<div class="modal fade" id="synchronizeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalTitle">同步所有图片素材</h4>
				</div>
				<div class="modal-body">
					<form action="${ctx}/wx/material/synchronizeImages" method="post" id="synchronizeForm">
						<div class="container-fluid">
							<h4>是否同步该微信公众号下的所有永久图片素材？</h4>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="subtn" onclick="synchronizeImages()">确定</button>
				</div>
			</div>
		</div>
	</div>
	
    <script src="${ctx }/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <script src="${ctx }/bower_components/raphael/raphael-min.js"></script>
    <script src="${ctx }/bower_components/morrisjs/morris.min.js"></script>
    <script src="${ctx }/dist/js/sb-admin-2.js"></script>
    <script type="text/javascript" src="${ctx }/js/nav.js"></script>
    
    <script type="text/javascript">
    	
    	$('#imgLoad').hide();
    
    	$(function() {
	    	$("#selectBtn").click(function () {
		    	$("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
		    	$("#upload").on("change",function(){
			    	var objUrl = getObjectURL(this.files[0]) ; //获取图片的路径，该路径不是图片在本地的路径
			    	if (objUrl) {
			    		$("#pic").attr("src", objUrl) ; //将图片路径存入src中，显示出图片
			    	}
		    	});
	    	});
    	});
    	 
    	//建立一個可存取到該file的url
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
    	
	    function topage(moveIndex){
	 		var indexs = '${pageIndex}';
	 		var form = '';
	 		form = document.forms['newsForm'];
	 		if(moveIndex=='+1') {
	 			form.pageIndex.value=parseInt(indexs)+1;
	 			form.submit();
	 		}else if(moveIndex=='-1') {
	 			form.pageIndex.value=parseInt(indexs)-1;
	 			form.submit();
	 		}else {
	 			$('#pageIndex').val(moveIndex);
	 			form.submit();
	 		}
		}
		
		$('#addMaterialImage').click(function(){
			$('#picUrl').val('https://pic.ehub.net/2017/0524/991377.jpg');
		});
		
		function saveImage() {
			$("#addImageform").submit();
		}
		
		function delMaterialImage(mediaId){
			$('#delMediaId').val(mediaId);
		}
		
		function delSub(){
			$('#delMaterialForm').submit();
		}
		
		function synchronizeImages(){
			$('#synchronizeForm').submit();
		}
		
    </script>

</body>

</html>