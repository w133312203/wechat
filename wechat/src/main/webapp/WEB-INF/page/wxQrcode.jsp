<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
		<link href="${ctx }/css/fans.css" rel="stylesheet" type="text/css"/>   
	    <script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>    
		<script src="${ctx }/js/bootstrapjs/bootstrap.min.js"></script>
		<style type="text/css">
          .codeBtn{
          	background:transparent;
          	border:none;
          }
          .codeBtn.active,
		  .codeBtn:active,
		  .codeBtn:focus,
		  .codeBtn:hover{
			  background:transparent;
         	  border:none;
			  color: #FFF
		  }
		  .codeBtn.codeBtnc{
          	color:#fb942f;
          	border:1px #fb942f solid;
          	margin-right:10px;
          }
          .codeBtn.codeBtns{
          	color:#6b93ce;
          	border:1px #6b93ce solid;
          }
          .fixed-table-toolbar{
          	margin-top:0;
          }
          .dataModal-div .fixed-table-toolbar,
          .userModal-div .fixed-table-toolbar{
          	height:0;
          }
          .form-group{
          	text-align:right;
          	overflow:hidden;
          	margin-top:5px;
          }
          .btn-primary{
          		background-color: #6b93ce;
    			border-color: #6b93ce;
			    float:right;
          }
           .btn-primary:hover,
          .btn-primary:focus{
          		background-color: #6b93ce;
    			border-color: #6b93ce;
          } 
        .form-control{
         		margin-right:5px;
          		width:150px;
          		background-color: #FFF;
			    background-image: none;
			    border: 1px solid #e5e6e7;
			    border-radius: 1px;
			    color: inherit;
			    display: inline-block;
			    padding: 6px 12px;
			    -webkit-transition: border-color .15s ease-in-out 0s, box-shadow .15s ease-in-out 0s;
			    transition: border-color .15s ease-in-out 0s, box-shadow .15s ease-in-out 0s;
			    font-size: 14px;
			    float:left;
          }
          #myChannelModal th,
          #myChannelModal td{
          		padding:8px 0 !important;
          		border-bottom:none;
          }
          #myChannelModal td:last-child,
          #myChannelModal th:last-child{
          	border-right:none;
          }
          #myChannelModal table{
          		border:none;
          }
          .nav-tabs{
          		border:none;
          }
          .downs h5{
          		padding-bottom:20px;
          		text-align: center;
          		font-size:16px;
          		color:#555;
          		overflow:hidden;
          }
          .downs h5 a{
          		float: right;
          		padding:3px 5px;
          		border:1px #ccc solid;
          		border-radius:3px;
          		color:#6b93ce;
          		font-size:12px;
          }
          .downs .fixed-table-container{
          		margin-top:0;
          		border-top-left-radius:0;
          		border-top-right-radius:0;
          }
          #channelDiv ul{
          		padding:10px;
          		border:1px #ccc solid;
          		border-bottom-left-radius:0;
          		border-bottom-right-radius:0;
          		border-bottom:none;
          }
          #channelDiv ul button{
          		border:none;
          		color:#888;
          		font-size:16px;
          		background:#fff;
          		outline: none;
          }
          #channelDiv ul button.off{
          		color:#fb942f;
          }
          #channelDiv ul button:focus,
          #channelDiv ul button:hover{
          		border:none;
          		outline: none;
          }
          #channelDiv ul button.bnst{
          		position: relative;
          		padding-right:15px;
          		margin-right:5px;
          }
          #channelDiv ul button.bnst:before{
          		position: absolute;
				right: 0;
				top: 5px;
				content: "";
				width: 2px;
				height: 15px;
				background: #ccc;
          }
          .btn-group ul.dropdown-menu li a, .btn-group ul.dropdown-menu li a{
          		color:#333;
          }
          .btn-group ul.dropdown-menu>.active>a, 
          .btn-group ul.dropdown-menu>.active>a:focus, 
          .btn-group ul.dropdown-menu>.active>a:hover{
          		color:#fff;
          }
          @media (min-width:768px) {
			 #myChannelModal .modal-dialog {
				  width:700px;
				  margin:30px auto
			  }	
		   }
		   #channelDiv ul.pagination{
			   	border:none;
		   }
		   #myChannelModal .page-list{
		   		display:none;
		   }
		   #myChannelModal .pagination-info{
		   		line-height:50px;
		   }
		</style>
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
										<div class="table-margin">	
									         <table class="table table-bordered table-header" id="data_table"> </table>
								         </div>		
								    </div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	      </div>
		<!-- 新建二维码 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle">新建二维码</h4>
					 </div>
					 <div class="modal-body">
						<form action="${ctx}/wx/channel/addQrcode" method="post" id="qrcodeForm">
							<div class="container-fluid">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group" style="position: relative; padding-left: 65px;">
											<span class="onp" style="position: absolute;left: 0;top: 6px;color:#666">
												<label>渠道名称</label><b style="color: red;">*</b>
											</span>
											<input class="form-control content_input_height" 
											style="width:100%;border-radius:5px;"
											placeholder="渠道名称" type="text" id="qName" 
											name="qName" data-container="body" tabindex="0" 
											role="button"  data-toggle="popover" 
											data-trigger="focus" data-placement="top" 
											>
										 </div>
									 </div>
								 </div>
							 </div>
						 </form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">取消</button>
						<button type="button" class="btn btn-primary" id="subtn" onclick="saveTag()">提交</button>
					</div>
				</div>
			</div>
		</div>		
		<!-- 获取二维码 -->
		<div class="modal fade" id="myQrcodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" align="right">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalTitle" style="text-align:left;">二维码</h4>
					</div>
					<div class="modal-body" style="text-align: center;">
						<img src="" style="margin:0 auto;" id="img" width="300" height="300"></img>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">取消</button>
					</div>
				</div>
			 </div>
		</div>		
		<!-- 新建渠道数据表单 -->
		<div class="modal fade" id="myChannelModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop=”static>
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick=" refresh()">&times;</button>
						<h3 class="modal-title" id="myModalTitle">渠道数据表单</h3>
					</div>
					<div class="modal-body downs">
						<h5 class="modal-title" text-align="center ! important">
							<span>渠道二维码名称</span>
							<a href="#"  onclick="downloadList()" id="downLoadId">下载列表</a>
						</h5>
					  <div class="row" id="channelDiv">
	                     <ul class="nav nav-tabs registerTitle">
							 <button type="button" class="bnst off"  onclick="showDataModal(this)">渠道数据表单</button>
			                 <button type="button"  onclick="showUserModal(this)">渠道粉丝明细</button>
			                 <span class="span-div">(共<strong>N</strong>条)</span>
						 </ul>
	                     <div class="dataModal-div">
							 <table class="table table-bordered table-header" id="channel_table"></table>
						  </div>
						  <div class="userModal-div fans_onp">
							 <div class="row">
						        <div class="fans">
									<ul class="clearfix">
									</ul>
								</div>
							</div>
							<div class="fanseLast clearfix">
								<div class="fenye">
									<ul class="pagination">
										<li class="prev contstr" onclick="fansprev()"><a><</a></li>
										<li class="fenyex"><a>1</a></li>
										<li class="next" onclick="fansnext()"><a>></a></li>
									</ul>
								</div>
								<div class="jump">
								    
									<span>共<strong id="ye">N</strong>页&nbsp;&nbsp;跳转到</span>
									<input type="text" class="jumpval" value="" />
								</div>
							</div>
						  </div> 
						</div>
					</div>
				</div>
			</div>
		</div>		
		
		<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="delModalTitle"></h4>
					</div>
					<div class="modal-body">
						<form action="${ctx}/wx/channel/delQrcode" method="post" id="delKeywordsForm">
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
		$(document).ready(function() {
			$("#data_table").bootstrapTable({
					pagination:true,
					search:true,
					pageSize:10,
					pageNumber:1,
					pageList: [10,20,30],
					clickToSelect: true,
					showRefresh:true,
					toolbar: '#toolbar',
					sidePagination:'server',
					columns:  [ {
						field : "name",
						title : "渠道名称",
						 align: 'center'
					}, {
						field : "newuser",
						title : "新粉丝"+"<br/>关注/取消",
						align: 'center',
					},{
						field : "olduser",
						title : "老粉丝 "+"<br/>关注/取消",
						align: 'center',
						
					} ,{
						field : "dataForm",
						title : "数据表单",
						 align: 'center',
						 formatter:function(value,row,index){  
					          return [ 
					                  '<button type="button" class="RoleOfA btn btn-default  btn-sm codeBtn codeBtnc"  id="'+row.id+'" onclick=openModal(\''+ row.id + '\')>查看</button>',
					                  '<button type="button" class="RoleOfB btn btn-default  btn-sm codeBtn codeBtns" id="'+row.id+'" onclick=exportExcel(\''+ row.id + '\')>导出</button>'
						             ].join(""); 
					        } 
					} ,{
				         field: "operate",
						 title: "操作",
				          align: 'center',
				         formatter:function(value,row,index){  
				        	 return [ 
				        	         '<button type="button" class="RoleOfA btn btn-default  btn-sm codeBtn"  id="'+row.id+'" onclick=getQrcode(\''+ row.id + '\')><img src="/ehubWeChat/img/qrcode.png" /></button>',
				                     '<button type="button" class="RoleOfB btn btn-default  btn-sm codeBtn" id="'+row.id+'" onclick=del(\''+ row.id + '\') data-toggle="modal" data-target="#deleteModal" ><img src="/ehubWeChat/img/delete.png" /></button>'
					                  ].join("")
				        }  
				    } ],
				    url:'${ctx}/wx/channel/showWxChannelListJson'
				});
			var btns = '<button id="addTag" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">'+
               			'<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新建二维码</button>';
               $(".search").append(btns);
            var head = '<span class="mark_h1">渠道二维码</span>';
           	$(".bars").append(head);
		})
		  var cid;
		  function openModal(id){
			  $(".dataModal-div").show();
			  $(".userModal-div").hide();
			  $(".span-div").hide();
			  $("#myChannelModal").modal({backdrop:"static"});
			  drawChannelTable(id);
			  cid=id;
			  }
		  function showDataModal(obj){
			  $(obj).addClass("off").siblings("button").removeClass("off");
			  $(".dataModal-div").show();
			  $(".userModal-div").hide();
			  $(".span-div").hide();
		    	 /*  var opt = {
		    		        url: "${ctx}/wx/channel/showWxChannelListJson?format=json",
		    		        silent: true,
		    		        query:{
		    		            id:cid
		    		        }
		    		    };
		    	  $("#channel_table").bootstrapTable('refresh', opt);   */
			}
		  function drawChannelTable(id) {
			  var table=$("#channel_table").bootstrapTable({
					columns : columns1,
					pagination:true,
					pageSize:10,
					pageNumber:1,
					clickToSelect: true,	
					sidePagination:'server',
					url:'${ctx}/wx/channel/showWxChannelListByDay?id='+id,
					
				});
		 }
		  var columns1= [
                     [
                        {
                            field: 'days',
                             title: "关注时间",
                             valign:"middle",
                            align:"center",
                             colspan: 1,
                             rowspan: 2
                         },{
                             title: "新粉丝",
                             valign:"middle",
                             align:"center",
                             colspan: 2,
                             rowspan: 1
                         }, {
                            title: "老粉丝",
                             valign:"middle",
                             align:"center",
                             colspan: 2,
                             rowspan: 1
                         }
                     ],
                     [
                         {
                             field: 'count2',
                            title: '关注人数',
                             valign:"middle",
                             align:"center"
                         }, {
                             field: 'count1',
                             title: '取消人数',
                             valign:"middle",
                             align:"center"
                         },
                         {
                             field: 'count4',
                            title: '关注人数',
                             valign:"middle",
                             align:"center"
                         },{
                             field: 'count3',
                             title: '取消人数',
                             valign:"middle",
                             align:"center"
                         },
                    ]
		             ]
		  function showUserModal(obj){
			  $(obj).addClass("off").siblings("button").removeClass("off");
			  $(".dataModal-div").hide();
			  $(".userModal-div").show();
			  $(".span-div").show();
			  drawUserTable(cid);
			} 
		  function ajaxs(data,fn,id){
			  $.ajax({
					async:false,
					url: '${ctx}/wx/channel/showWxUserListJson',
					type: "GET",
					data:{"pageNum":data,"pageSize":10,"id":id},
					timeout: 5000,
					beforeSend: function(){},
					success: function (json){
						var obj = eval(json);
						fn(obj,data);
					}
				}) 
		  }
		  var fanslen = "";
		  $(".jumpval").bind("input propertychange",function(){
			  	var ids = $(".fenyex a").attr("nameId");
			  	var pageNums = $(this).val();
	  			if(pageNums != ""){
	  				if(pageNums>0 && pageNums<=fanslen){
	  					$(".fans ul").html("");
	  					ajaxs(pageNums,fansli,ids);
	  				}
	  			}
	  		})
			function fansli(obj,fenye){
				console.log(obj)
				$(".span-div strong").html(obj.total);
				$("#ye").html(obj.pageCount);
				var lists = obj.records;
				$(".fenyex a").text(fenye);
				fanslen = Math.ceil(obj.total/10);
				for(var i=0;i<lists.length;i++){
					var fansImg = lists[i].headimgurl;
					var fansName = lists[i].nickname;
					var fansTime = lists[i].time;
					if(fansImg == "" || fansImg == null){
						var imgs = "/ehubWeChat/img/wxicon.jpg";
					}else{
						var imgs = fansImg;
					}
					var fansAddress = lists[i].province + " " +lists[i].city;
					var lis = '<li><dl class="clearfix">'+
							'<dt><span><img src="'+imgs+'"/></span></dt>'+
							'<dd><p class="fansName">'+fansName+
							'<span class="fansTime">'+fansTime.replace(/-/g,"/").substr(0,16)+'</span></p >'+
							'<p class="fansAddress">'+fansAddress+'</p ></dd></dl></li>';
					$(".fans ul").append(lis);
				}
			}
			function fansprev(){
				var fenyex = $(".fenyex a").text();
				var ids = $(".fenyex a").attr("nameId");
				if(fenyex > 1){
					$(".fans ul").html("");
					fenyex--;
					ajaxs(fenyex,fansli,ids);
				}
				if(fenyex <=1){
					$(".prev").addClass("contstr");
				}else if(fenyex > 1 && fenyex < fanslen){
					$(".prev,.next").removeClass("contstr");
				}else if(fenyex >=fanslen){
					$(".prev").addClass("contstr");
				}
			}
			function fansnext(){
				var fenyey = $(".fenyex a").text();
				var ids = $(".fenyex a").attr("nameId");
				if(fenyey < fanslen){
					$(".fans ul").html("");
					fenyey++;
					ajaxs(fenyey,fansli,ids);
				}
				if(fenyey <=1){
					$(".prev").addClass("contstr");
				}else if(fenyey > 1 && fenyey < fanslen){
					$(".prev,.next").removeClass("contstr");
				}else if(fenyey >=fanslen){
					$(".prev").addClass("contstr");
				}
			}
		  function drawUserTable(id) {
			  $(".fans ul").html("");
			  $(".fenyex a").attr("nameId",id);
			  ajaxs(1,fansli,id);
			}
			  var columns2= 
				    [{
                        field: 'headimgurl',
                        title:'图片',
                        formatter: headimgFMT
                      },{
                   	   field: 'nickname',
                         title: "粉丝昵称",
                         align:"center",
                     },{
                   	  field: 'area',
                        title: "粉丝地域",
                         align:"center",
                     },{
                         field: 'subscribetime',
                         title: '时间',
                         valign:"middle",
                         align:"center"
                     }
	             ]
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
		 function downloadList(){
			 $("#downLoadId").attr("href","${ctx}/wx/channel/downloadExcel?id="+cid);
		}
		  
		function getQrcode(id){
			 $("#myQrcodeModal").modal("show"); 
			 $.ajax({
				 type:"post",
				 url:"${ctx}/wx/channel/getQrCode?id="+id,
				 success:function(data){
					 var jsonobj=eval("("+data+")")
					 $("#img").attr("src",jsonobj.url);
				 }		 
			 })
		 } 
		
		/* $('#qName').focus(function(){
			var ints = $("#qName").val();
			if(ints == ""){
				$('#qName').attr("data-content","渠道名称未填写");
				$('#qName').popover("show");
			}else{
				$('#qName').removeAttr("data-content");
				$('#qName').popover("hide");
			}
		}).blur(function(){
			var ints = $("#qName").val();
			if(ints == ""){
			}else{
				$('#qName').removeAttr("data-content");
				$('#qName').popover("hide");
			}
		}) */
		function saveTag(){
			var name =$("#qName").val();
			if(name ==""){
				$('#qName').attr('data-content','请输入渠道名称');
	    		$('#qName').popover("show");
	    		return;
	    	}else{
	    		 $.ajax({
	           		type: "POST",
	           		url: "${ctx}/wx/channel/findInfoByName",
	           		async: false,
	           		data: {name : name},
	           		dataType: "json",
	           		success: function(data){
	           			var obj = eval(data);
	           			if(obj.code=='00'){
	           				$('#qName').attr('data-content','此渠道名称已存在');
	           				$('#qName').focus();
	           				return;
	           			}else{
	           				$('#qrcodeForm').attr("action","${ctx}/wx/channel/addQrcode?name="+name);
	        	    		$('#qrcodeForm').submit();
	    		    		}
	           			}
	       		});
	    	}
		 }
		 function del(id){
			 $("#delKeywordsId").val(id);
		  } 
			function delKeywords() {
				$('#delKeywordsForm').submit();
			}
		  function exportExcel(id){
			  window.location.href="${ctx}/wx/channel/downloadExcel?id="+id;
		  }
		  function refresh(){
			  $(".bnst").addClass("off").siblings("button").removeClass("off");
			 $(".dataModal-div").html('<table class="table table-bordered table-header" id="channel_table"></table>');
		  }
	    </script>
		
	</body>
</html>