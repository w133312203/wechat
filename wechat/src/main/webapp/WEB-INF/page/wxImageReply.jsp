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
					</div>
				</div>
	       	</div>
		</div>
   		
   		<!-- 添加/修改消息回复规则 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/reply/editImageKeyword" method="post" id="keywordsForm">
							<input type="hidden" name="id" id="keywordsId"/>
							<div class="container-fluid">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group">
											<label>关键词</label><b style="color: red;">*多个关键词请用|格开：例如: 美丽|漂亮|好看</b>
											<p id="errkeywords" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="关键词未填写">
												<input class="form-control content_input_height" placeholder="关键词" type="text" id="keywords" name="keywords">
											</p>
										</div>
									</div>
								</div> 
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="saveKeywords()">保存</button>
					</div>
				</div>
			</div>
		</div>		
		
		<!-- 删除图文回复规则 -->
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="delModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/reply/deleteImageKeyword" method="post" id="delKeywordsForm">
							<input type="hidden" name="id" id="delKeywordsId">
							<div class="container-fluid">
								<h4>删除此条数据后将无法恢复。是否确定删除？</h4>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="delKeywords()">确定</button>
					</div>
				</div>
			</div>
		</div>
		
		<form action="" method="post" id="messageForm">
			<input type="hidden" name="wxKeywordsId" id="toKeywordsId">
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
			var $table = $('#table');
			$(document).ready(function() {
				
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
					height:700,
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
						field: 'id',
						title: 'ID',
						visible: false   
					},{
						field: 'keywords',
						title: '关键词'
					}, {
						field: 'status',
						title: '类型',
						formatter: typeFMT
					},{
	                    title: '操作',
	                    align: 'center',
	                	formatter: operateFormatter
					}],
					url:'${ctx}/reply/showImageReplyListJson'
				});
			    
				$(".search").css("width","45%");
		        $(".search").children().css("width","30%");
		        $(".search").children().css("display","");
		        $(".search").children().css("float","right");
		        $(".search").append("<div onclick='addKeywords()' class='mark_zj' data-toggle='modal' data-target='#myModal'>添加图文回复规则</div>");
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
		        $(".bars").append("<span class='mark_h1'>图文回复规则</span>");
		        
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
			
			function typeFMT(value) {
		    	if(value==0) {
		    		return '完全匹配';
		    	}else if(value==1) {
		    		return '包含匹配';
		    	}
		    }
			
			function operateFormatter(value, row, index) {
				return [
		    	            '<a onclick="editKeywords('+row.id+',\''+row.keywords+'\',\''+row.status+'\')" href="javascript:void(0)" data-toggle="modal" data-target="#myModal" title="修改">',
		    	                '<i class="fa fa-pencil-square-o" aria-hidden="true"></i>&nbsp;&nbsp;',
		    	            '</a>'+     
	    	                '<a onclick="deleteKeywords('+row.id+')" href="javascript:void(0)" data-toggle="modal" data-target="#deleteModal" title="删除">',
	    	                	'<i class="fa fa-trash fa-fw"></i>&nbsp;&nbsp;',
		    	            '</a>'+
		    	            '<a onclick="toMessage('+row.id+')" href="javascript:void(0)" title="编辑内容">',
	    	                	'<i class="fa fa-cog fa-fw"></i>&nbsp;&nbsp;',
		    	            '</a>'
		    	        ].join('');
		    }
			
		    function addKeywords() {
		    	$('#myModalTitle').text('新增图文回复规则');
				$('#subtn').text('保存');
				$('#keywordsId').val(null);
				$('#keywords').val("");
				$("input[name='status']:eq(0)").attr("checked",'checked');
			};
			
			function editKeywords(id,keywords,status,content){
				$('#myModalTitle').text('修改图文回复规则');
	        	$('#subtn').text('更新');
				$('#keywordsId').val(id);
				$('#keywords').val(keywords);
			}
			
			function saveKeywords(){
				var id=$('#keywordsId').val();
				var keywords=$('#keywords').val();
				var content=$('#content').val();
				if(keywords==''){
					$('#errkeywords').attr('data-content','请输入关键词');
		    		$('#errkeywords').focus();
		    		return;
		    	}else{
		    		$.ajax({
		           		type: "POST",
		           		url: "${ctx}/reply/findKeywords",
		           		async: false,
		           		data: {id : id,keywords : keywords},
		           		dataType: "json",
		           		success: function(data){
		           			var obj = eval(data);
		           			if(obj.code=='Y'){
		           				$('#errkeywords').attr('data-content','此关键词已存在');
		           				$('#errkeywords').focus();
		           				return;
		           			}else if(obj.code=='N'){
		           				$('#keywordsForm').submit();
		           			}
		           		}
		       		});
		    	}
			}
			
			function deleteKeywords(id){
				$('#delModalTitle').text('删除图文回复规则');
				$('#delKeywordsId').val(id);
				
			}
			
			function delKeywords() {
				$('#delKeywordsForm').submit();
			}
			
			function toMessage(id) {
				$("#messageForm").attr("action", "${ctx}/reply/editReplyInfo");
				$('#toKeywordsId').val(id);
				$('#messageForm').submit();
			}
			
	    </script>
		
	</body>
</html>