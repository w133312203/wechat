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
	<link href="${ctx }/dist/wxmenu/css/common.css" rel="stylesheet">
	<link rel="stylesheet" href="${ctx }/js/kindeditor-4.1.10/themes/default/default.css" />
	
	<link rel="stylesheet" href="${ctx }/js/UEditor/weixin/1.4.3.3/themes/default/css/ueditor.css" />
	
    <script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>    
	<script src="${ctx }/js/bootstrapjs/bootstrap.min.js"></script>
    	
</head>

<body>

	<div class="wrapper wrapper-content animated fadeIn" id="wrapper">
   		<!--左侧栏导航-->
		<%@include file="/WEB-INF/messageMenu.jsp"%>
       	<div id="page-wrapper" style="padding-bottom: 105px">
       		<!--头部导航-->
   			<%@include file="/WEB-INF/wxHead.jsp"%>
       		<div class="container-fluid">
				<div class="row">
					<div class="col-xs-12 col-sm-1 col-lg-1"></div>			
					<div class="col-xs-12 col-sm-10 col-lg-10">
						<span class="form form-horizontal ng-pristine ng-valid ng-scope">
						<!-- <form action="" class="form form-horizontal ng-pristine ng-valid ng-scope"> -->
							<input type="hidden" name="replies" value="">
							<div class="panel panel-default clearfix">
								<div class="panel-heading">图文素材</div>
								<div class="panel-body">
									<div class="row clearfix reply">
										<div class="col-xs-6 col-sm-3 col-md-3 panel-group">
											<div class="panel panel-default ng-scope first panel-btn" id="news0">
												<div class="panel-body ng-scope">
													<div class="img">
														<i class="default">封面图片</i>
														<c:if test="${firstNews!=null && firstNews.thumb_url!=null && firstNews.thumb_url!=''}">
															<img src="http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=${firstNews.thumb_url }" ng-src="">
														</c:if>
														<c:if test="${firstNews==null || firstNews.thumb_url==null || firstNews.thumb_url!=''}">
															<img src="" ng-src="">
														</c:if>
														<span class="text-left ng-binding">
															<c:if test="${firstNews!=null}">${firstNews.title }</c:if>
														</span>
														<div class="mask">
															<!-- <a href="javascript:;"><i class="fa fa-book"></i>导入文章</a>
															<a href="javascript:;"><i class="fa fa-book"></i>导入图文</a> -->
															<a href="javascript:void(0);" onclick="editItem(this);"><i class="fa fa-edit"></i>编辑</a>
															<!-- <a href="javascript:;"><i class="fa fa-times"></i>删除</a> -->
														</div>
													</div>
												</div>
											</div>
											
											<div class="panel panel-default add-box">
												<div class="panel-body">
													<div class="add"><span><i class="fa fa-plus"></i> 添加</span></div>
												</div>
											</div>
										</div>
										<div class="col-xs-6 col-sm-9 col-md-9 aside" id="edit-container">
											<div class="card">
												<div class="arrow-left"></div>
												<div class="inner">
													<div class="panel panel-default">
														<div class="panel-body">
															<div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label">标题&nbsp;<span style="color: red;">*</span></label>
																<div class="col-sm-9 col-xs-12">
																	<input type="text" id="title" onblur="changeTitle()" class="form-control ng-pristine ng-untouched ng-valid ng-empty" placeholder="添加图文消息的标题">
																	<input type="hidden" class="ng-pristine ng-untouched ng-valid ng-empty">
																</div>
															</div>
															<div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label">作者&nbsp;<span>&nbsp;</span></label>
																<div class="col-sm-9 col-xs-12">
																	<input type="text" id="author" onblur="changeAuthor()" class="form-control ng-pristine ng-untouched ng-valid ng-empty" placeholder="添加图文消息的作者">
																</div>
															</div>
															<div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label">排序&nbsp;<span style="color: red;">*</span></label>
																<div class="col-sm-9 col-xs-12">
																	<input type="text" id="sortLevel" onblur="changeSortLevel()" class="form-control ng-pristine ng-untouched ng-valid ng-not-empty" placeholder="添加排序">
																	<span class="help-block">排序只能在提交后显示。按照从大到小的顺序对图文排序</span>
																</div>
															</div>
															<form action="${ctx}/wx/material/uploadImage" method="post" id="uploadform" name="imgiframe" target="imgiframe" enctype="multipart/form-data">
																<input type="file" accept="image/*" id="imgfile" name="imageFile" style="display:none" />
																<div class="form-group">
																	<label class="col-xs-12 col-sm-3 col-md-2 control-label">封面&nbsp;<span style="color: red;">*</span></label>
																	<div class="col-sm-9 col-xs-12">
																		<div class="col-xs-3 img ng-scope">
																			<input type="hidden" id="thumb_media_id" />
																			<img id="pic" alt="" src="" style="display: none;">
																			<span id="addPic"><i class="fa fa-plus-circle green"></i>&nbsp;添加图片</span>
																		</div>
																	</div>
																</div>
															</form>
															<div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
																<div class="col-sm-9 col-xs-12">
																	<label>封面（大图片建议尺寸：360像素 * 200像素）</label>
																</div>
															</div>
															<div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label"></label>
																<div class="col-sm-9 col-xs-12">
																	<label class="checkbox-inline">
																		<input type="checkbox" id="coverPic" onclick="changeCoverPic()" value="1" class="ng-pristine ng-untouched ng-valid ng-empty"> 封面图片显示在正文中
																	</label>
																</div>
															</div>
															<div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label">描述&nbsp;<span>&nbsp;</span></label>
																<div class="col-sm-9 col-xs-12">
																	<textarea id="digest" onblur="changeDigest()" class="form-control ng-pristine ng-untouched ng-valid ng-empty" placeholder="添加图文消息的简短描述"></textarea>
																</div>
															</div>
															<!-- <div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label">详情&nbsp;<span style="color: red;">*</span></label>
																<div class="col-sm-9 col-xs-12">
																	<textarea  id="content" name="content" cssClass="form-control" rows="7" style="width:100%;"></textarea>
																</div>
															</div> -->
															<div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label">详情&nbsp;<span style="color: red;">*</span></label>
																<div class="col-sm-9 col-xs-12">
																	<input type="hidden" id="content" name="content"/>
																	<script id="editor" type="text/plain" style="width:100%;height:300px"></script>
																</div>
															</div>
															<div class="form-group">
																<label class="col-xs-12 col-sm-3 col-md-2 control-label">来源&nbsp;<span>&nbsp;</span></label>
																<div class="col-sm-9 col-xs-12">
																	<input type="text" id="content_source_url" onblur="changeSourceUrl()" class="form-control ng-pristine ng-untouched ng-valid ng-empty" placeholder="图文消息的来源地址">
																</div>
																<!-- <div class="col-sm-9 col-xs-12">
																	<div class="input-group">
																		<input type="text" id="content_source_url" onblur="changeSourceUrl()" class="form-control ng-pristine ng-untouched ng-valid ng-empty" placeholder="图文消息的来源地址">
																		<span class="input-group-btn">
																			<button class="btn btn-default" type="button"><i class="fa fa-external-link"></i> 系统链接</button>
																		</span>
																	</div>
																</div> -->
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="btn btn-primary" id="btn-submit">提 交</div>
										</div>
									</div>
								</div>
							</div>
						<!-- </form> -->
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<form action="${ctx}/wx/material/editNews"  method="post" id="newsForm">
		<input type="hidden" id="newsJsonStr" name="newsJsonStr"/>
		<input type="hidden" id="mediaId" name="mediaId" value="${mediaId }"/>
	</form>
	<iframe id='imgiframe' name='imgiframe' style="display: none"></iframe>
	
	<script src="${ctx}/js/kindeditor-4.1.10/kindeditor-min.js" type="text/javascript"></script>	
	<script src="${ctx}/js/kindeditor-4.1.10/lang/zh_CN.js" type="text/javascript"></script>
	
	<script src="${ctx}/js/UEditor/weixin/1.4.3.3/ueditor.config.js" type="text/javascript"></script>
	<script src="${ctx}/js/UEditor/weixin/1.4.3.3/ueditor.all.min.js" type="text/javascript"></script>
	<script src="${ctx}/js/UEditor/weixin/1.4.3.3/lang/zh-cn/zh-cn.js" type="text/javascript"></script>
	
	<script>
		var ue;
		var items = [], concatItems = [], addBnt = $(".panel-body").find(".add");
		var activeIndex = 0, //当前编辑的回复项目的索引 
			activeItem= items[activeIndex];  //当前编辑的回复项目
			var mediaId = '${mediaId}';
			var mark = '${mark}';
		/* var newsJsonStr = '${newsJsonStr}';
		newsJsonStr = newsJsonStr.replace("\n", "\\n");
		newsJsonStr = newsJsonStr.replace("\t", "\\t");
		newsJsonStr = newsJsonStr.replace("\"","//\"");
		console.log(newsJsonStr);
		if(newsJsonStr!=null&&newsJsonStr!=''){
			console.log(newsJsonStr);
			items = JSON.parse(newsJsonStr);
			//items = eval(newsJsonStr);
			console.log(items);
		} */
		
		$(function(){
			
			ue = UE.getEditor('editor',{
	    		elementPathEnabled : false,
	    		wordCount : false
	    	});
			
			var content = $("#content").val();
			
			ue.addListener("ready", function () {
        		ue.setContent(content);
			});
			
			ue.addListener('blur',function(editor){
				$("#content").val(ue.getContent());
				items[activeIndex].content=$('#content').val()
			});
		
			$.ajax({
           		type: "POST",
           		url: "${ctx}/wx/material/getNewsJson",
           		async: false,
           		data: {mediaId:mediaId,mark:mark},
           		dataType: "json",
           		success: function(data){
           			var obj = eval(data);
           			if(obj!=null&&obj!=''){
           				items = obj;
               			$("#news0").find("span").text(obj[0].title);
               			$("#news0").find("img").attr('src', 'http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl='+obj[0].thumb_url);
               			for (var i = 1; i < obj.length; i++) {
               				var aIndex =Number(i)-1;
               				var pbody = $('<div class="panel panel-default ng-scope panel-btn"  id="news'+i+'"></div>'),
               					  panelBody = $('<div class="panel-body ng-scope"></div>'),
               					  titles = $('<div class="text"><h4>'+obj[i].title+'</h4></div>'),
               					  imgs = $('<div class="img"><img src="http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl='+obj[i].thumb_url+'"/><i class="default">缩略图</i></div>'),
               					  masks = $('<div class="mask"></div>'),
               					  editBtn = $('<a href="javascript:;" onclick="editItem(this);"><i class="fa fa-edit"></i> 编辑</a>'),
               					  deleteBtn = $('<a href="javascript:;" onclick="removeItem(this);"><i class="fa fa-times"></i> 删除</a>');
               				
               				panelBody.append(titles, imgs, masks);
               				masks.append(editBtn, deleteBtn);
               				pbody.append(panelBody);
               				$("#news"+aIndex+"").after(pbody);
    					}	
           			}
           		}
       		});
			triggerActiveItem(-1);
		});
		//富文本
		/* function KE(name){
			KindEditor.ready(function(K){
				K.create(name, {
					
					resizeType : 1,
					
	                allowPreviewEmoticons: false,
	                
	                allowImageUpload:true,//允许上传图片
	                
	                allowFileManager:true, //允许对上传图片进行管理
	                
	                uploadJson:'${ctx}/kindeditor/fileUpload', //上传图片的java代码，只不过放在jsp中
	                
	                fileManagerJson:'${ctx}/kindeditor/fileManager',
	                
	                afterUpload: function(){this.sync();}, //图片上传后，将上传内容同步到textarea中
	                
	                afterBlur: function(){
	                	this.sync();
	                	items[activeIndex].content=$('#content').val();
	                },   ////失去焦点时，将上传内容同步到textarea中
	                
	                items : ['source','preview','|','fontname','fontsize', '|','forecolor', 'hilitecolor','bold', 'italic','underline',
	                         'removeformat','|', 'justifyleft','justifycenter', 'justifyright','insertorderedlist',
	                         'insertunorderedlist','|','image']  
	                
					});
			})
		}
		
		KE('textarea[name="content"]'); */
		
		(function() {
			if(!$.isArray(items)) {
				items = [];
			}
				
			if(items.length == 0) {
				items.push({
					id: '',
					title: '',
					author: '',
					sortLevel: 0,
					thumb_media_id: '',
					thumb_url: '',
					show_cover_pic: 0,
					digest: '',
					content: '',
					content_source_url: ''
				});
			}
			activeIndex = 0;
			activeItem= items[activeIndex];
			
			addBnt.on('click', function(event) {
				items.length >= 8 ? '' : addItem(activeIndex);
			});
		})(jQuery);
		
		//添加
		function addItem(activeIndex){
			var num =Number(activeIndex)+1;
			var pbody = $('<div class="panel panel-default ng-scope panel-btn"  id="news'+num+'"></div>'),
				  panelBody = $('<div class="panel-body ng-scope"></div>'),
				  titles = $('<div class="text"><h4></h4></div>'),
				  imgs = $('<div class="img"><img src=""/><i class="default">缩略图</i></div>'),
				  masks = $('<div class="mask"></div>'),
				  /* importArticles = $('<a href="javascript:;"><i class="fa fa-book"></i> 导入文章</a>'), */
				  editBtn = $('<a href="javascript:;" onclick="editItem(this);"><i class="fa fa-edit"></i> 编辑</a>'),
				  deleteBtn = $('<a href="javascript:;" onclick="removeItem(this);"><i class="fa fa-times"></i> 删除</a>');
			
			panelBody.append(titles, imgs, masks);
			masks.append(editBtn, deleteBtn);
			/* masks.append(importArticles, editBtn, deleteBtn); */
			pbody.append(panelBody);
			$("#news"+activeIndex+"").after(pbody);
			
			items.push({
				id: '',
				title: '',
				author: '',
				sortLevel: 0,
				thumb_media_id: '',
				thumb_url: '',
				show_cover_pic: 0,
				digest: '',
				content: '',
				content_source_url: ''
			});
			var index = items.length-1;
			triggerActiveItem(index);
		}
		
		//删除
		function removeItem(item){
			var pindex = $(item).parents(".panel-btn").index();
			$(item).parents(".panel-btn").remove();
			items.splice(pindex, 1);
			triggerActiveItem(0);
		}
		
		//编辑
		function editItem(item) {
			var pindex = $(item).parents(".panel-btn").index();
			var itemsM = items[pindex];
			var index = $.inArray(itemsM, items);
			if(index == -1) return false
			triggerActiveItem(index);
		};
		
		//切换
		function triggerActiveItem(index){
			console.log(items)
			if(index!=-1){
				var top = index * 110 + 120;
				$('#edit-container').css('marginTop', top);
				
				$("html,body").animate({
					scrollTop: top + 50
				}, 500);
			}else{
				index=0;
			}
			activeIndex = index;
			activeItem = items[activeIndex];
			$('#title').val(activeItem.title);
			$('#author').val(activeItem.author);
			$('#sortLevel').val(activeItem.sortLevel);
			if(activeItem.thumb_url==''){
				$('#pic').attr('src', '');
		       	$('#pic').hide();
		       	$('#addPic').show();
			}else{
				$('#pic').attr('src', 'http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl='+activeItem.thumb_url);
		       	$('#pic').show();
		       	$('#addPic').hide();
			}
			if(activeItem.show_cover_pic=='0'){
				$("#coverPic").prop("checked",false);
			}else{
				$("#coverPic").prop("checked",true);
			}
			$('#digest').val(activeItem.digest);
			//KindEditor.instances[0].html(activeItem.content);
			ue.ready(function(){
			    ue.setContent(activeItem.content);    
			});
			$('#content_source_url').val(activeItem.content_source_url);
		}
		
		function changeTitle(){
			if(activeIndex==0){
				$("#news"+activeIndex+"").find("span").text($('#title').val());
			}else{
				$("#news"+activeIndex+"").find(".text").find("h4").text($('#title').val());
			}
			items[activeIndex].title=$('#title').val();
		}
		
		function changeAuthor(){
			items[activeIndex].author=$('#author').val();
		}
		
		function changeSortLevel(){
			items[activeIndex].sortLevel=$('#sortLevel').val();
		}
		
		function changeCoverPic(){
			if($("input[type='checkbox']").is(':checked')){
				items[activeIndex].show_cover_pic=1;
			}else{
				items[activeIndex].show_cover_pic=0;
			}
		}
		
		function changeDigest(){
			items[activeIndex].digest=$('#digest').val();
		}
		
		function changeSourceUrl(){
			items[activeIndex].content_source_url=$('#content_source_url').val();
		}
		
		$('#addPic').click(function(){
		 	var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false;
	        if(ie){   
	            document.getElementById("imgfile").click(); 
	        }else{  
	            var a=document.createEvent("MouseEvents");//FF的处理   
	            a.initEvent("click", true, true);    
	            document.getElementById("imgfile").dispatchEvent(a);   
	        }
	    });
		
		$('#pic').click(function(){
		 	var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false;
	        if(ie){   
	            document.getElementById("imgfile").click(); 
	        }else{  
	            var a=document.createEvent("MouseEvents");//FF的处理   
	            a.initEvent("click", true, true);    
	            document.getElementById("imgfile").dispatchEvent(a);   
	        }
	    });
		
		$("#imgfile").on('change',function() {
			$("#uploadform").submit();
		});
		
		
		function uploadSuccess(mediaId,url) {
			items[activeIndex].thumb_media_id=mediaId;
			items[activeIndex].thumb_url=url;
	       	$('#pic').attr('src', 'http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl='+url);
	       	$('#pic').show();
	       	$('#addPic').hide();
	       	if(activeIndex==0){
				$("#news"+activeIndex+"").find("img").attr('src', 'http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl='+url);
			}else{
				$("#news"+activeIndex+"").find("img").attr('src', 'http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl='+url);
			}
	    }
		
		$('#btn-submit').click(function(){
			
			concatItems = items.concat();
		    var newItems = concatItems.sort(  
	                function(a, b)  
	                {  
	                    if(a.sortLevel < b.sortLevel) return 1;  
	                    if(a.sortLevel > b.sortLevel) return -1;  
	                    return 0;  
	                }  
	            );
			var articles = [];
			articles.push({
				articles: newItems
			});
			$('#newsJsonStr').val(JSON.stringify(articles[0]));
			$('#newsForm').submit();
		});
	</script>
</body>

</html>