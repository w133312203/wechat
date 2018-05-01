<%@page import="java.util.List"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	
	<nav class="navbar-static-side" role="navigation">
			<div class="nav-close"><i class="fa fa-times-circle"></i></div>
			<div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
				<div class="sidebar" style="width: auto; height: 100%; background-color:#333744;">
					<ul class="nav in" id="side-menu">
						<li style="">
							<a class="logo-element-a" style="background:#333333;padding-bottom: 14px;padding-top: 14px;" data-toggle="dropdown">
								<span><img src="${ctx}/img/ehui_logo.png" style="width:auto;height:20px;margin-right: 4px;"></span>
								<img class="logo-element" src="${ctx}/img/ehui_logo.png">
								<span class="caret"></span>
							</a>
							<div class="dropdown-menu dropdown-user sevice_header_ehub head_console">
								<ul>
									<li onclick="window.location.href='/weixin/responseEhub?url=showDashboard'" style="cursor: pointer;font-size:14px">
										<a href="#" style="color:#ffffff;margin-left: 26px;">Marketing</a>
									</li>
		        				</ul>
            				</div>
						</li>
						<li>
							<a href="${ctx}/wx/home"><i class="fa fa-home fa-fw"></i> <span class="nav-label">我的公众平台</span></a>
						</li>
						<li>
							<a href="#" class="sd_menu"><i class="fa fa-th-large fa-fw"></i> <span class="nav-label">基本功能</span><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
					            <li>
					             	<a href="${ctx}/wx/users"><span class="nav-label">粉丝管理</span></a>
					         	</li>
					         	<li>
					             	<a href="#"><span class="nav-label clors">菜单回复</span><span class="fa arrow"></span></a>
					             	<ul class="nav nav-third-level">
						              	<li>
											<a href="${ctx}/reply/textReply"><span class="nav-label">文本回复</span></a>
										</li>
										<li>
											<a href="${ctx}/reply/imageReply"><span class="nav-label">图文回复</span></a>
										</li>
										<li>
											<a href="${ctx}/wx/reply"><span class="nav-label">系统回复</span></a>
										</li>
										<li>
											<a href="${ctx}/wx/menu"><span class="nav-label">自定义菜单</span></a>
										</li>
						          	</ul>
					         	</li>
					         	<li>
					             	<a href="#"><span class="nav-label clors">素材管理</span><span class="fa arrow"></span></a>
					             	<ul class="nav nav-third-level">
						              	<li>
											<a href="${ctx}/wx/material/image"><span class="nav-label">图片</span></a>
										</li>
										<li>
											<a href="${ctx}/wx/material/news"><span class="nav-label">图文</span></a>
										</li>
						          	</ul>
					         	</li>
					         	<li>
					             	<a href="${ctx}/wx/templates"><span class="nav-label">模板管理</span></a>
					         	</li>
					         	<%-- <li>
					             	<a href="${ctx}/wx/channel/qrCode"><span class="nav-label">渠道二维码管理</span></a>
					         	</li>  --%>
					         	<%-- <li>
					             	<a href="${ctx}/wx/mass/wxMass"><span class="nav-label">群发管理</span></a>
					         	</li>  --%>
					     	</ul>
						</li>
						<li>
							<a href="${ctx }/responseECloud?url=events"><i class="fa fa-flag fa-fw"></i> <span class="nav-label">活动管理</span></a>
						</li>
						<li>
				          	<a href="javascript:void(0);" class="sd_menu"><i class="fa fa-cloud fa-fw"></i> <span class="nav-label">签到管理</span><span class="fa arrow"></span></a>
				          	<ul class="nav nav-second-level">
				              	<li>
				                  	<a href="${ctx }/responseECloud?url=checkin" target="_top">电子签到</a>
				              	</li>
				             	<li>
				                 	<a href="${ctx }/responseECloud?url=checkin" target="_top">微信签到</a>
				             	</li>
				             	
				          	</ul>
				      	</li>
						<li>
				          	<a href="javascript:void(0);" class="sd_menu"><i class="fa fa-envelope-o fa-fw"></i> <span class="nav-label">互动管理</span><span class="fa arrow"></span></a>
				          	<ul class="nav nav-second-level">
				             	<li>
				                 	<a href="${ctx }/responseECloud?url=screens" target="_top">微现场</a>
				             	</li>
				             	<li>
				                 	<a href="${ctx}/responseECloud?url=marketing/webapps" target="_top">微会议</a>
				             	</li>
				             	<li>
				                 	<a href="${ctx}/responseECloud?url=marketing/nativeapps" target="_top">会议APP</a>
				             	</li>
				          	</ul>
				      	</li>
				      	<li>
				      		<a href="${ctx}/responseECloud?url=webinar" ><i class="fa fa-laptop fa-fw"></i> <span class="nav-label">直播管理</span></a>
				      	</li>
					</ul>
				</div>
				<div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 416.244px; background: rgb(0, 0, 0);">         		
				</div>
				<div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.9; z-index: 90; right: 1px; background: rgb(51, 51, 51);">	
				</div>
			</div>
		</nav>