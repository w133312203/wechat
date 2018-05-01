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
							    	<form action="${ctx}/wx/material/news" method="post" name="newsForm" id="materialNewsList">
										<input type="hidden" name="pageIndex" id="pageIndex" />
										<div class="row">
											<div class="col-lg-4 cusetmaterial">
												<span id="addMaterialNews">+添加图文素材</span>&nbsp;&nbsp;
												<span data-toggle="modal" data-target="#synchronizeModal">同步所有图文素材</span>
											</div>
											<div class="col-lg-4">
											</div>
							                <div class="col-lg-4">
							            		<div class="form-group input-group">
							            			<input class="form-control" name="search" value="${search}"/>
							                       	<span class="input-group-btn">
							                           	<button class="btn btn-default" onclick="sub()" type="button"><i class="fa fa-search"></i></button>
							                        </span>
							                    </div>
							            	</div>
							            </div>
							            <div class="row">
							            	<c:forEach var="news" items="${wxNewsList}" varStatus="newsStatus">
							            		<c:if test="${fn:length(news.itemNews)==1}">
							            			<c:forEach var="itemNews" items="${news.itemNews}" varStatus="status">
								            			<c:if test="${status.count == 1}">
													    	<div class="col-lg-4" >
											                    <div class="panel panel-default" style="border:none">
											                        <div style="position: relative;" class="panel-heading">
											                           	<span>${itemNews.title }</span>
											                        </div>
											                        <div class="panel-body" style="padding:0px" >
											                        	<div style="position:relative;">
											                        		<img src="https://pic.ehub.net/${itemNews.picUrl }" class="img-full" style="width: 100%;height: 250px;">
											                        	</div>
											                        </div>
											                        <div class="panel-footer">
											                        	<%-- <a href="javascript:void(0);" onclick="editMaterialNews('${itemNews.mediaId }')"><span class="pull-left">编辑</span></a> --%>
											                            <a href="" onclick="delMaterialNews('${itemNews.mediaId }')" data-toggle="modal" data-target="#deleteModal"><span class="pull-right"><span class="pull-left">删除</span></span></a>
											                            <div class="clearfix"></div>
											                        </div>
											                    </div>
											                </div>
														</c:if>
								                	</c:forEach>
							            		</c:if>
							            		<c:if test="${fn:length(news.itemNews)>1}">
							            			<c:forEach var="itemNews" items="${news.itemNews}" varStatus="status">
								            			<c:if test="${status.count == 1}">
													    	<div class="col-lg-4" >
											                    <div class="panel panel-default" style="border:none">
											                        <div style="position: relative;" class="panel-heading">
											                           	<span>${itemNews.title }</span>
											                        </div>
											                        <div class="panel-body" style="padding:0px" >
											                        	<div style="position:relative;">
											                        		<img src="https://pic.ehub.net/${itemNews.picUrl }" class="img-full" style="width: 100%;height: 250px;">
											                        	</div>
											                        </div>
											                        <div class="panel-footer">
											                        	<a href="javascript:void(0);" onclick="editMaterialNews('${itemNews.mediaId }')"><span class="pull-left">编辑</span></a>
											                            <a href="javascript:void(0);" onclick="delMaterialNews('${itemNews.mediaId }')"><span class="pull-right"><span class="pull-left">删除</span></span></a>
											                            <div class="clearfix"></div>
											                        </div>
											                    </div>
											                </div>
														</c:if>
								                	</c:forEach>
							            		</c:if>
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
	
	<!-- 删除图文素材 -->
	<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="delModalTitle">删除图文素材</h4>
				</div>
				<div class="modal-body">
					<form action="${ctx}/wx/material/deleteMaterial" method="post" id="delMaterialForm">
						<input type="hidden" id="delMediaId" name="mediaId"/>
  						<input type="hidden" name="type" value="news"/>
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
	
	<!-- 同步所有图文素材 -->
	<div class="modal fade" id="synchronizeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalTitle">同步所有图文素材</h4>
				</div>
				<div class="modal-body">
					<form action="${ctx}/wx/material/synchronizeNews" method="post" id="synchronizeForm">
						<div class="container-fluid">
							<h4>是否同步该微信公众号下的所有永久图文素材？</h4>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="subtn" onclick="synchronizeNews()">确定</button>
				</div>
			</div>
		</div>
	</div>
		
  	<form action="${ctx}/wx/material/news/edit" method="post" id="addMaterialNewsForm">
  		<input type="hidden" id="mediaId" name="mediaId"/>
  		<input type="hidden" id="mark" name="mark"/>
	</form>
	<form action="${ctx}/wx/material/deleteMaterial" method="post" id="delMaterialNewsForm">
  		<input type="hidden" id="delMediaId" name="mediaId"/>
  		<input type="hidden" name="type" value="news"/>
	</form>
    <script src="${ctx }/bower_components/metisMenu/dist/metisMenu.min.js"></script>
    <script src="${ctx }/bower_components/raphael/raphael-min.js"></script>
    <script src="${ctx }/bower_components/morrisjs/morris.min.js"></script>
    <script src="${ctx }/dist/js/sb-admin-2.js"></script>
    <script type="text/javascript" src="${ctx }/js/nav.js"></script>
    
    <script type="text/javascript">
    	
	    $(document).ready(function() {
	    	
	    	
	    });
	    
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
		
		function sub() {
			$("#materialNewsList").submit();
		}
		
		$('#addMaterialNews').click(function(){
			$('#mark').val("create");
			$('#mediaId').val("");
			$('#addMaterialNewsForm').submit();
		});
		
		function editMaterialNews(mediaId){
			$('#mark').val("update");
			$('#mediaId').val(mediaId);
			$('#addMaterialNewsForm').submit();
		}
		
		function delMaterialNews(mediaId){
			$('#delMediaId').val(mediaId);
		}
		
		function delSub(){
			$('#delMaterialForm').submit();
		}
		
		function synchronizeNews(){
			$('#synchronizeForm').submit();
		}
		
    </script>

</body>

</html>