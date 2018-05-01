<%@page import="java.util.List"%>
<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>

	.sevice_header_ehub {
    background: #333;
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    margin: 0px;
    padding: 0px;
    border: 0px;
}
</style>
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
							<a href="${ctx}/account/home"><i class="fa fa-home fa-fw"></i> <span class="nav-label">我的公众平台</span></a>
						</li>
						<li>
							<a href="${ctx}/software/home"><i class="fa fa-home fa-fw"></i> <span class="nav-label">我的小程序</span></a>
						</li>
					</ul>
				</div>
				<div class="slimScrollBar" style="width: 7px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 416.244px; background: rgb(0, 0, 0);">         		
				</div>
				<div class="slimScrollRail" style="width: 7px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; opacity: 0.9; z-index: 90; right: 1px; background: rgb(51, 51, 51);">	
				</div>
			</div>
		</nav>