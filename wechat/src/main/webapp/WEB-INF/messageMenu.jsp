<%@page import="java.util.List"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	
	<nav class="navbar-static-side" role="navigation">
			<div class="nav-close"><i class="fa fa-times-circle"></i></div>
			<div class="slimScrollDiv" style="position: relative; width: auto; height: 100%;">
				<div class="sidebar" style="width: auto; height: 100%;">
					<ul class="nav in" id="side-menu">
						<li style="background:#333333;">
							<a class="logo-element-a active">
								<span><img src="${ctx}/img/ehui_logo.png"></span>
								<img class="logo-element" src="${ctx}/img/ehui_logo.png">
							</a>
						</li>
						<li>
							<a href="${ctx}/wx/home"><i class="fa fa-home fa-fw"></i> <span class="nav-label">我的公众平台</span></a>
						</li>
						<li>
							<a href="${ctx}/wx/textkey"><i class="fa fa-file-text fa-fw"></i> <span class="nav-label">文本回复</span></a>
						</li>
						<li>
							<a href="${ctx}/wx/newskey"><i class="fa fa-image fa-fw"></i> <span class="nav-label">图文回复</span></a>
						</li>
						<li>
							<a href="${ctx}/wx/reply"><i class="fa fa-wechat fa-fw"></i> <span class="nav-label">系统回复</span></a>
						</li>
						<li>
							<a href="${ctx}/wx/menu"><i class="fa fa-th-list fa-fw"></i> <span class="nav-label">自定义菜单</span></a>
						</li>
						<li>
							<a href="javascript:void(0);" class="sd_menu"><i class="fa fa-envelope-o fa-fw"></i> <span class="nav-label">素材管理</span><span class="fa arrow"></span></a>
				          	<ul class="nav nav-second-level">
				              	<li>
				                  	<a href="${ctx}/wx/material/image" target="_top">图片</a>
				              	</li>
				             	<%-- <li>
				                 	<a href="${ctx}/wx/material/voice" target="_top">语音</a>
				             	</li>
				             	<li>
				                 	<a href="${ctx}/wx/material/video" target="_top">视频</a>
				             	</li> --%>
				             	<li>
				                 	<a href="${ctx}/wx/material/news" target="_top">图文</a>
				             	</li>
				          	</ul>
						</li>
					</ul>
				</div>
				<div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 416.244px; background: rgb(0, 0, 0);">         		
				</div>
				<div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.9; z-index: 90; right: 1px; background: rgb(51, 51, 51);">	
				</div>
			</div>
		</nav>