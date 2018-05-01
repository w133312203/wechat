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
			<%@include file="/WEB-INF/wxMenu.jsp"%>
	       	<div id="page-wrapper" style="padding-bottom: 105px">
	       		<!--头部导航-->
    			<%@include file="/WEB-INF/wxHead.jsp"%>
	       		<div class="container-fluid ">
					<div class="row" style="margin:0;">
						<div class="col-lg-12 ">
							<div class="col-lg-9 padding_right_0">
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
							<div class="col-lg-3 padding_left_0">
			                   <div class="panel panel-default panel_border_left_none" style="border-top: none;" >
			                        <div class="panel-body" style="height: 1150px"> 
			                        	<div class="col-lg-12 group_save_btn">
			                        		<button type="button" class="btn btn-tag" data-toggle="modal" data-target="#synchronizeTagsModal">
								               	同步所有标签
								           	</button>
								           	<button id="addTag" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
								               	<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新建标签
								           	</button>
			                        	</div>
			                        	<div class="col-lg-12 padding_0">
			                        		<div class="panel panel-default group_panel" id="groupDiv" >
			                        			<ul class="group_list" id="tagList">
			                        				<li class="active" value="">
			                        					<span>全部</span>
			                        				</li>
			                        				<c:forEach var="tag" items="${tagList}" varStatus="st">
			                        					<li value="${tag.tagId }">
		                        							<span>${tag.tagName }</span>
		                        							<c:if test="${tag.tagId!=2 }">
		                        								<span class="group_edit_span1" >
		                        									<a href="#" data-toggle="modal" data-target="#deleteModal" onclick="sendId(${tag.id })">删除</a>
		                        								</span>
				                        						<span class="group_edit_span2" >
				                        							<a href="#" data-toggle="modal" data-target="#myModal" onclick="editTag('${tag.id }','${tag.tagName }')">编辑</a>
				                        						</span>
		                        							</c:if>
				                        				</li>
			                        				</c:forEach>
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
								<h4>删除标签后，该标签下的所有用户将失去该标签属性。是否确定删除？</h4>
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
		
		<!-- 给用户打标签 -->
		<div class="modal fade" id="tagsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">用户打标签</h4>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<div class="col-lg-12">
									<div class="form-group" id="nuser" style="display: none;">
										<label>请先选择用户（可以批量选择）</label>
									</div>
									<div class="form-group" id="yuser">
										<form action="${ctx}/user/addTags" method="post" id="addTagForm">
											<input type="hidden" name="openidStr" id="openidStr">
											<input type="hidden" name="oldTagIds" id="oldTagIds">
											<label>标签名称</label><b style="color: red;">*</b><br/>
											<span id="tagSpan" data-toggle="popover" data-trigger="focus" data-placement="top" data-content="请选择标签"></span>
											<c:forEach var="tag" items="${tagList}" varStatus="st">
												<div class="col-lg-4">
													<label class="checkbox inline">
													  <input type="checkbox" class="tags-cbox" id="${tag.tagId }" name="tagIdStr" value="${tag.tagId }">
													  <span>${tag.tagName }</span>
													</label>
												</div>
	                        				</c:forEach>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="checkbtn" onclick="saveTags()">保存</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 同步所有用户标签 -->
		<div class="modal fade" id="synchronizeTagsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">同步所有用户标签</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/synchronizeTags" method="post" id="synchronizeTagsForm">
							<div class="container-fluid">
								<h4>是否同步该微信公众号下的所有用户标签？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="synchronizeTags()">确定</button>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 同步所有用户 -->
		<div class="modal fade" id="synchronizeUsersModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">同步所有用户</h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/synchronizeUsers" method="post" id="synchronizeUsersForm">
							<div class="container-fluid">
								<h4>是否同步该微信公众号下的所有用户？</h4>
								<span style="color: red;">点击【确定】之后，粉丝列表优先展现粉丝openid，粉丝其他信息展现稍慢，请耐心等待。</span>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="synchronizeUsers()">确定</button>
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
				
				$("#tagList").slimScroll({ 
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
						field: 'headimgurl',
						title: '头像',
						formatter: headimgFMT
					},{
						field: 'nickname',
						title: '昵称'
					},{
						field: 'openid',
						title: 'openid'
					},{
						field: 'tagNames',
						title: '用户标签'
					},{
						field: 'subscribetime',
						title: '创建时间',
						formatter: dateFMT
					},{
						field: 'tagIds',
						title: 'tagIds',
						visible: false
					}],
					url:'${ctx}/user/showWxUserListJson'
				});
				
				var groupHtml = "";
					
				$(".search").css("width","50%");
				$(".search").children().css("width","60%");
		        $(".search").children().css("display","");
		        $(".search").children().css("float","right");
				$(".search").append("<div class='mark_zj' data-toggle='modal' data-target='#synchronizeUsersModal'>同步所有用户</div>");
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
				$(".bars").append("<div onclick='addTags()' class='mark-tag' data-toggle='modal' data-target='#tagsModal'>打标签</div>");
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
			});
			
			function headimgFMT(value) {
		    	if(value==''||value==null) {
		    		return '';
		    	}else {
		    		return '<img src="'+value+'" style="height: 49px;width: 48px" />';
		    	}
		    }
			
			function dateFMT(value) {
		    	if(value!=null){
		    		return new Date(value).toLocaleDateString();
		    	}else{
		    		return '';
		    	}
		    }
			
		    $("#tagList li").click(function() {
	    		$("#tagList li").removeClass("active");
	    		$("#tagList li span").removeClass("active");
	    		$(this).addClass("active");
	    		$(this).find("span").addClass("active");
	    		var tagId = $(this).val();
	    		$table.bootstrapTable('refresh',{url:'${ctx}/user/showWxUserListJson?tagId='+$(this).val()});
	    	});
		    
		    $('#addTag').click(function(){
				$('#myModalTitle').text('添加用户标签');
	        	$('#subtn').text('保存');
				$('#tagName').val('');
			});
			
			function editTag(id,tagName){
				$('#tagId').val(id);
				$('#tagName').val(tagName);
				$('#myModalTitle').text('修改用户标签');
	        	$('#subtn').text('更新');
			}
			
			function saveTag(){
				var tagName=$('#tagName').val();
				if(tagName==''){
		    		$('#tagName').focus();
		    		return;
		    	}else if(tagName.length>30){
		    		$('#tagName').attr('data-content','标签名长度超过30个字节');
		    		$('#tagName').focus();
		    		return;
		    	}else{
		    		$('#tagForm').submit();
		    	}
			}
			
			function sendId(id) {
				$('#delTagId').val(id);
			}
			
			function delTag() {
				$('#delTagForm').submit();
			}
			
			function addTags(){
				clearTagInfo();
				var user = $('#table').bootstrapTable('getSelections');
		    	var openidStr = [];
		    	if(user.length==0){
		    		$("#nuser").css('display','block'); 
		    		$("#yuser").css('display','none');
		    		$('#checkbtn').hide();
		    	}else{
		    		$("#nuser").css('display','none'); 
		    		$("#yuser").css('display','block');
		    		$('#checkbtn').show();
		    		if(user.length==1){
		    			var tagIds=user[0].tagIds;
	    				var context = [{
	    					"openId":user[0].openid, 
	    					"tageIds":user[0].tagIds
	    				}];
	    				context = JSON.stringify(context);
	    				$("#openidStr").val(context);
	    				if(tagIds!=null&&tagIds!=''&&tagIds!=undefined){
	    					var tagids = tagIds.split(',');
	    					for (var i = 0; i < tagids.length; i++) {
	    						if(tagids[i]!="") {
	    							$("input[type='checkbox'][value="+tagids[i]+"]").prop("checked", true);
	    						}
							}
	    				}
		    		}else if(user.length>1) {
		    			var tagIdArray = user[0].tagIds;
		    			var repArr = [];
		    			var repArr1 = "";
		    			if(tagIdArray!=""&&tagIdArray!=undefined) {
		    				repArr = tagIdArray.split(",");
		    				repArr1 = ","+tagIdArray+",";
		    			}
		    			var context = {
	    					"openId":user[0].openid, 
	    					"tageIds":user[0].tagIds
	    				};
	    				openidStr.push(context);
		    			for(var i=1;i<user.length;i++) {
		    				var othTagIdArray=","+user[i].tagIds+",";
		    				for(var m=0;m<repArr.length;m++) {
		    					if(othTagIdArray.indexOf(","+repArr[m]+",")==-1) {
		    						repArr1 = repArr1.replace(repArr[m]+",","");
		    					}
		    				}
		    				var repArr2 = repArr1;
		    				if(repArr1.charAt(0)==",") {
		    					repArr2 = repArr1.substring(1);
		    				}
		    				if(repArr2.charAt(repArr2.length-1)==",") {
		    					repArr2 = repArr2.substring(0,repArr2.length-1);
		    				}
		    				repArr = repArr2.split(',');
		    				context = {
	    						"openId":user[i].openid, 
	    						"tageIds":user[i].tagIds
	    					};
	    					openidStr.push(context);
		    			}
		    			openidStr = JSON.stringify(openidStr);
		    			$("#openidStr").val(openidStr);
		    			if(repArr.length>0) {
		    				for (var i = 0; i < repArr.length; i++) {
		    					if(repArr[i]!="") {
		    						$("input[type='checkbox'][value="+repArr[i]+"]").prop("checked", true);
		    						$("input[type='checkbox'][value="+repArr[i]+"]").prop("disabled", true);
		    					}
							}
		    			}
		    		}
		    	}
			};
			
			function clearTagInfo() {
				$("#addTagForm input[type='checkbox']").attr("checked", false);
				$('#oldTagIds').val("");
			}
			
			function saveTags(){
				var flag = false;
				$("#addTagForm input[type='checkbox']").each(function(e,obj){
					if($(obj).is(":checked")&&$(obj).prop("disabled")==false) {
						flag = true;
					}
				});
				var openidStr = $("#openidStr").val();
				openidStr = JSON.parse(openidStr);
				if(openidStr.length==1&&openidStr[0].tageIds!="") {
					flag = true;
				}
		    	if(flag) {
		    		$('#addTagForm').submit();
		    	}
			}
			
			function synchronizeTags(){
				$('#synchronizeTagsForm').submit();
			}
			
			function synchronizeUsers(){
				$('#synchronizeUsersForm').submit();
			}
			
			function fn(keyArr,valArr){
           		var  data = {};
           		for(i=0;i<keyArr.length;i++){
               		var key = keyArr[i];
               		var val = valArr[i];
               		data[key] = val;
           		}
           		return data;
       		}
			
	    </script>
		
	</body>
</html>