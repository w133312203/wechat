<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>

    #navbar li a:focus, #navbar li a:hover {
    	text-decoration: none;
    	background-color: #2b2b2b;
    }
</style>
<nav class="navbar navbar-default navbar-static-top" role="navigation" >	    	
        <div class="row nav-box border-bottom">
            <nav class="navbar navbar-static-top" role="navigation">
                <div class="navbar-header">
               		<div class="col-md-12">
               			<ul class="nav-links-left" id="nav_rela">
               				<li class="nav-main-item nav_client4">
               					<a href="${ctx}/responseEhub?url=showDashboard" onclick="loginOp(this);return false;"><span>仪表板</span></a>
               				</li>
               				<li class="nav-main-item nav_client nav_client1">
               					<a><span>联系人</span><span class="caret"></span></a>
               					<ul>
               						<li class="off"><a href="${ctx}/responseEhub?url=showCustomer" onclick="loginOp(this);return false;">客户</a></li>
               						<li><a href="${ctx}/responseEhub?url=showGroup" onclick="loginOp(this);return false;">群组</a></li>
               						<li><a href="${ctx}/responseEhub?url=showSource" onclick="loginOp(this);return false;">来源</a></li>
               						<li><a href="${ctx}/responseEhub?url=showTag" onclick="loginOp(this);return false;">标签管理</a></li>
               						<li><a href="${ctx}/responseEhub?url=showField" onclick="loginOp(this);return false;">属性</a></li>
               					</ul>
               				</li>
               				<li class="nav-main-item nav_weixin">
               					<a href="${ctx }/wx/home" onclick="loginOp(this);return false;"><span>微信</span></a>
               				</li>
               				<li class="nav-main-item nav_huiyi">
               					<a href="${ctx }/responseECloud?url=events" onclick="checkEuser(this); return false;"><span>会议</span></a>
               				</li>
               				<li class="nav-main-item nav_zidonghua">
               					<a href="${ctx }/responseEhub?url=showCampaign" onclick="loginOp(this);return false;"><span>自动化</span></a>
               				</li>
               				<li class="nav-main-item nav_client nav_zujian">
               					<a><span>组件</span><span class="caret"></span></a>
               					<ul>
               						<li class="off"><a href="${ctx }/responseEhub?url=showEmail" onclick="loginOp(this);return false;">电子邮件</a></li>
               						<li><a href="${ctx }/responseEhub?url=showSMS" onclick="loginOp(this);return false;">短信</a></li>
               						<li><a href="${ctx }/responseEhub?url=showLandingPage" onclick="loginOp(this);return false;">着陆页</a></li>
               						<!--  <li><a href="${ctx }/table/showTable">报名表单</a></li>
               						<li><a href="${ctx }/showShurvey">调查问卷</a></li>-->
               					</ul>
               				</li>
               			</ul>
               		</div>
                </div>
                
                <div class="navbar-collapse collapse" id="navbar">
                		<div class="col-md-12" style="padding-right: 0px;">
                			<div class="col-md-6 helpper">
                				
                					<div class="col-md-4" style="padding: 0px;">
                						<div class="btn-group personalbox">
			                                <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" style="background-color: #333333" aria-expanded="false">
			                                	<c:if test="${!empty sessionScope.euserInfo.headImage}">
			                                		<img class="cuimg_1" src="${  sessionScope.euserInfo.headImage}" />
			                                	 </c:if>
			                                	 <c:if test="${empty sessionScope.euserInfo.headImage}">
			                                	 	<img class="cuimg_1" src="${ctx}/img/50.png" />
			                                	  </c:if>
			                                		<span class="caret"></span>
			                                </button>
			                                <ul class="dropdown-menu" style="background-color: #333333 !important;">
			                                    <li><a id="cuInfo" href="javascript:void(0);" data-toggle="modal" data-target="#cuModal">个人信息</a></li>
			                                    <li><a id="upCuPWD" href="javascript:void(0);"  data-toggle="modal" data-target="#cuPasswordModal">修改密码</a></li>
			                                    <li><a href="${ctx }/logout">退出</a></li>
			                                </ul>
		                            	</div>
                					</div>
                					<div class="col-md-7 personal-id" style="background-color: #333333">
                						<span>${euserPassport.email }</span>
                					</div>
                			</div>
                		</div>
                </div>
            </nav>
        </div><!--nav-box-->
	   <iframe id='returniframe' name='returniframe' style="display: none"></iframe>
	   <iframe id='imgiframe' name='imgiframe' style="display: none"></iframe>
	   <div class="modal fade" id="cuPasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		   	<div class="modal-dialog">
		       	<div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">修改密码</h4>
		            </div>
		            <div class="modal-body">
		               	<form action="updateEuserPwd" method="post" id="cuPWDForm" name="returniframe" target="returniframe">
		                   	<div class="container-fluid">
		                        <div class="row">
		                        	<div class="col-lg-12">
			                           	<div class="form-group">
		                       				<label>原密码</label><b style="color: red;">*</b>
			                               	<input class="form-control content_input_height" placeholder="原密码" type="password" value="" id="old_password" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="原密码未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                    </div>
		                    <div class="container-fluid">
		                        <div class="row">
		                        	<div class="col-lg-12">
			                           	<div class="form-group">
		                       				<label>新密码</label><b style="color: red;">*</b>
			                               	<input class="form-control content_input_height" name="newPassword" placeholder="新密码" type="password" value="" id="new_password" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="新密码未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                    </div>
		                   	<div class="container-fluid">
		                        <div class="row">
		                        	<div class="col-lg-12">
			                           	<div class="form-group">
		                       				<label>确认密码</label><b style="color: red;">*</b>
			                               	<input class="form-control content_input_height" placeholder="确认密码" type="password" value="" id="rnew_password" name="euserPassport.password" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="确认密码未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		            <div class="modal-footer">
		                <button type="button" name="cld" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary" onclick="updateCuPWD()">修改</button>
		            </div>
		        </div>
		    </div>
		</div>
	   
	   <div class="modal fade" id="cuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">更新个人信息</h4>
		            </div>
		            <div class="modal-body">
		            	<form action="${ctx}/uploadPic" method="post" id="cu_img_form" name="imgiframe" target="imgiframe" enctype="multipart/form-data">
		                	<!-- <s:textfield onblur="checkFile()" name="cuimgText" id="cuimgText" cssStyle="display:none" ></s:textfield> -->
							<input type="file" id="cuimgfile" name="imageFile" style="display:none" />
		                </form>
		                <form action="${ctx }/editEuserInfo" method="post" id="cuForm" name="returniframe" target="returniframe">
		                    <input type="hidden" name="id" value='${ sessionScope.euserPassport.id}'/>
		                    <input type="hidden" name="headimage" id="cuheadimage_1" value=''/>
			                <div class="container-fluid">
			                	<div class="row">
		                        	<div class="form-group" style="text-align: center;">
		                        		<img src="https://pic.ehub.net/2016/0106/872973.gif" style="border-radius: 50%;width: 50px;height: 50px;" id="cuimgeLoad">
				                        <img src="${ sessionScope.euserInfo.headImage}" style="border-radius: 50%;width: 85px;height: 85px" id="cuheadimage">
				                        <p style="margin: 10px 0px">
				                        	<button type="button" name="button" onclick="CUOpenBrowse()" class="btn btn-primary btn-sm">上传头像</button>
				                        </p>
				                    </div>
				                </div>
			                    <div class="row">
			                    	<div class="col-lg-2">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>姓名：
					   				   	</div>
					   				</div>
			                       	<div class="col-lg-10">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="姓名" type="text" value="${ sessionScope.euserInfo.realName}" id="curealname" name="realname" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="姓名未填写">
			                           	</div>
			                        </div>
			                    </div>
		                        <div class="row">
		                        	<div class="col-lg-2">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>邮箱：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-10">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="邮箱" disabled="disabled"  type="text" value="${ sessionScope.euserPassport.email}" id="cuemail" name="email" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="邮箱未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-2">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>手机：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-10">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="电话" disabled="disabled" type="text" value="${ sessionScope.euserPassport.mobileNum}" id="cumobilenum" name="mobilenum" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="电话未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-2">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>部门：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-10">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="部门" type="text" value="${ sessionScope.euserInfo.companyName}" id="cupart" name="companyname" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="部门未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                        <div class="row">
		                        	<div class="col-lg-2">
					   					<div class="form-group form-group-title">
					   						<b style="color: red;">*</b>职位：
					   				   	</div>
					   				</div>
		                        	<div class="col-lg-10">
			                           	<div class="form-group">
			                               	<input class="form-control content_input_height" placeholder="职位" type="text" value="${ sessionScope.euserInfo.position}" id="cuposition" name="position" data-container="body" tabindex="0" role="button"  data-toggle="popover" data-trigger="focus" data-placement="top" data-content="职位未填写">
			                           	</div>
		                           	</div>
		                        </div>
		                    </div>
		                </form>
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary" onclick="onCUSubmit()">更新</button>
		            </div>
		        </div>
		    </div>
		  </div>
	   </nav>
	   <script type="text/javascript">
	   $(document).ready(function() {
			    $("#returniframe").load(function () {
					var msg = $(document.getElementById('returniframe').contentWindow.document.body).html();   
					$("[name='cld']").click();
					if($.trim(msg)!=''&&$.trim(msg).indexOf("<")==-1) {
						if($.trim(msg).indexOf("数量不足")!=-1) {
							$('#alertText').text($.trim(msg));
							$('#buyPackage').show();
							$('#sureBtn').hide();
							$('#alertBtn').click();
						}else {
							$('#alertText').text($.trim(msg));
							$('#sureBtn').hide();
							$('#buyPackage').hide();
							$('#alertBtn').click();
						}
					} 
					if($("table").length>0) { 
						$("table").bootstrapTable('refresh');
					}
		       }); 
			   
			   $('#cuimgeLoad').hide();
			   $("#cuPasswordModal [data-toggle=popover],#cuModal [data-toggle=popover],#myModal [data-toggle=popover]").popover();
			   $("#cuPasswordModal [data-toggle=popover]").focus(function () {
					if($(this).val()!='') {
						if($(this).attr('id')=='new_password'&&$('#rnew_password').val()!=''&&$(this).val()!=$('#rnew_password').val()) {
							$(this).attr('data-content','两次密码输入不一致');
							$(this).popover('show');
						}else if($(this).attr('id')=='old_password'&&$(this).val()==failPassword) {
							$(this).attr('data-content','原密码不正确');
							$(this).popover('show');
						}else {
							$(this).popover('hide');
						}
					}else {
						if($(this).attr('id')=='new_password') {
							$(this).attr('data-content','新密码未填写');
						}
						if($(this).attr('id')=='old_password') {
							$(this).attr('data-content','原密码未填写');
						}
						$(this).popover('show');
					} 
				});
			   $("#cuModal [data-toggle=popover]").focus(function () {
					if($(this).val()!='') {
						if($(this).attr('id')=='cuemail') {
							if(!isemail.test($(this).val())) {
								$(this).attr('data-content','邮箱格式不正确，请填写正确格式的邮箱。例如：biz@ehui.net');
								$(this).popover('show');
							}else {
								if($(this).val()!=cuexistEmail) {
									$(this).popover('hide');
								}
							}
						}else if($(this).attr('id')=='cumobilenum') {
							if($(this).val()!=cuexistMobilenum) {
								$(this).popover('hide');
							}
						}else {
							$(this).popover('hide');
						}
					}else {
						if($(this).attr('id')=='cuemail') {
							$(this).attr('data-content','邮箱未填写')
						}
						if($(this).attr('id')=='cumobilenum') {
							$(this).attr('data-content','电话未填写')
						}
					} 
				});
			   $("#cuimgfile").on('change',function() {
					var strFileName = this.value.replace(
						/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi, "$1");
					$(this).prev().val(strFileName);
					if($("#cuimgfile").val()!=''){
						var FileName = new String($("#cuimgfile").val());
						var laststr = new String(FileName.substring(FileName.lastIndexOf("."),FileName.length));
						if(laststr!=".png"&&laststr!=".jpg"&&laststr!=".jpeg"&&laststr!=".gif"&&laststr!=".bmp"){
							$('#alertText').text("请选择正确的图片格式上传，支持'png'、'jpg'、'jpeg'、'gif'、'bmp'");
							$('#alertBtn').click();
		   					$('#sureBtn').hide();
							return false;
						}else {
							$("#cu_img_form").submit();
							$('#cuheadimage').hide();
							$('#cuimgeLoad').show();
						}
					}
				});
			   
			   $('#upCuPWD').click(function() {
			        $("#old_password").val("");
			    	$("#new_password").val("");
			    	$("#rnew_password").val("");
			    });
	   });
	   
	   function CUOpenBrowse(){
		 	var ie=navigator.appName=="Microsoft Internet Explorer" ? true : false;
	        if(ie){   
	            document.getElementById("cuimgfile").click();   
	            document.getElementById("cuimgText").value=document.getElementById("cuimgfile").value;  
	        }else{  
	            var a=document.createEvent("MouseEvents");//FF的处理   
	            a.initEvent("click", true, true);    
	            document.getElementById("cuimgfile").dispatchEvent(a);   
	        }   
	    }
	   
	   function updateCuPWD() {
	    	var oldpassword = $("#old_password").val();
	    	var newpassword = $("#new_password").val();
	    	var rnewpassword = $("#rnew_password").val();
	    	var flag = false;
	   		$.ajax({
	          		type: "POST",
	          		url: "checkEuserOldPwd",
	          		async: false,
	          		data: {oldpassword:oldpassword},
	          		dataType: "json",
	          		success: function(data){
	       			var msg = eval(data);
	          			if(msg.RCODE=='SUCCESS') {
	          				flag = true;
	          			}else if(msg.RCODE=='FAIL'){
	          				failPassword = oldpassword;
	          				flag = false;
	          			}
	          		}
	      		});
	    	
	    	if(oldpassword==''){
				$("#old_password").focus();
				return;
			}else if(!flag) {
				$("#old_password").focus();
				return;
			}else if(newpassword==''){
				$("#new_password").focus();
				return;	
			}else if(rnewpassword==''){
				$("#rnew_password").focus();
				return;
			}else if(newpassword!=rnewpassword){
				$("#new_password").focus();
				return;
			}else {
				$("#cuPWDForm").submit();
			}
	    }
	   
	   function uploadSuccess(msg) {
			$('#cuimgeLoad').hide();
	       	$('#cuheadimage').attr('src', msg);
	       	$('#cuheadimage').show();
	       	$('#cuheadimage_1').val(msg);
	    }
	   
	   function onCUSubmit() {
	    	var realname = $("#curealname").val();
	    	var part = $("#cupart").val();
	    	var position = $("#cuposition").val();
	    	if(realname==''){
				$("#curealname").focus();
				return;
			}else if(part==''){
				$("#cupart").focus();
				return;
			}else if(position==''){
				$("#cuposition").focus();
				return;
			}else {
				$("#cuForm").submit();
				$(".cuimg_1").attr('src', $('#cuheadimage').attr('src'));
			//	$("#curealname_1").text(realname);
			}
	    }
	   
	   function undetermined() {
		   alert("营销活动即将上线，敬请关注！");
	   }
	/*    function updataInfo() {
		   $.ajax({
          		type: "POST",
          		url: "${ctx }/updataInfo",
          		async: false,
          		dataType: "json",
          		success: function(data){
          			var obj = eval(data);
          			
          		}
          	});
	   } */
	   </script>     
