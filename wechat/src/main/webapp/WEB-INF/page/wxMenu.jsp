<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="author" content="">
	<title>微信自定义菜单</title>
	
	<link rel="shortcut icon" href="http://scrm.ehui.net/attachment/images/global/wechat.jpg">
	
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
	<link href="${ctx}/dist/wxmenu/css/common.css" rel="stylesheet">
	<link href="${ctx}/dist/wxmenu/css/app.css" rel="stylesheet">
	
	<script type="text/javascript" src="${ctx}/dist/wxmenu/js/app/util.js"></script>
	<script type="text/javascript" src="${ctx}/dist/wxmenu/js/app/common.min.js"></script>
	<script type="text/javascript" src="${ctx}/dist/wxmenu/js/require.js"></script>
	<script type="text/javascript" src="${ctx}/dist/wxmenu/js/app/config.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>    
	<script src="${ctx }/js/bootstrapjs/bootstrap.min.js"></script>
	
</head>

<body>
    <div class="wrapper wrapper-content" id="wrapper">
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
								<div class="col-lg-12 group_save_btn">
						           	<button id="addTag" type="button" class="btn btn-primary" data-toggle="modal" data-target="#synchronizeMenuModal">
						               	同步自定义菜单
						           	</button>
	                        	</div>
								<div class="container record" style="width:100%;">
								    <div class="row">
										<div class="col-xs-12 col-sm-9 col-lg-10">
											<div class="conditionMenu" ng-controller="conditionMenuDesigner" id="conditionMenuDesigner" ng-cloak>
												<div class="app clearfix">
													<div class="app-preview">
														<div class="app-header"></div>
														<div class="app-content">
															<div class="inner">
																<div class="title">
																	<h1><span>{{context.group.type == 3 ? context.group.title : "自定义菜单"}}</span></h1>
																</div>
															</div>
															<div class="nav-menu">
																<div class="js-quickmenu nav-menu-wx clearfix" ng-class="{0 : 'has-nav-0', 1 : 'has-nav-1', 2: 'has-nav-2', 3: 'has-nav-3', 4 : 'has-nav-3'}[context.group.button.length + 1]">
																	<ul class="nav-group designer-x">
																		<li class="nav-group-item js-sortable" ng-repeat="but in context.group.button" ng-class="{0 : '', 1 : 'active'}[context.activeItem == but ? 1 : 0 ]">
																			<input type="hidden" data-role="parent" data-hash="{{but.$$hashKey}}"/>
																			<a href="javascript:void(0);" title="拖动排序" ng-click="context.editBut('', but);">
																				<i class="fa fa-minus-circle" ng-show="but.sub_button.length > 0"></i>{{but.name}}
																			</a>
																			<dl class="designer-y" id="designer-y">
																				<dd ng-repeat="subBut in but.sub_button"  ng-class="{0 : '', 1 : 'active'}[context.activeItem == subBut ? 1 : 0 ]">
																					<input type="hidden" data-role="sub" data-hash="{{subBut.$$hashKey}}"/>
																					<a href="javascript:void(0)" ng-click="context.editBut(subBut, but);">{{subBut.name}}</a>
																				</dd>
																				<dd ng-if="but.sub_button.length < 5" class="js-not-sortable">
																					<a href="javascript:void(0)" ng-click="context.addSubBut(but);"><i class="fa fa-plus"></i></a>
																				</dd>
																			</dl>
																		</li>
																		<li class="nav-group-item" class="js-not-sortable"ng-if="context.group.button.length < 3" ng-hide="context.group.disabled">
																			<a href="javascript:void(0);" ng-click="context.addBut();" class="text-success">
																				<i class="fa fa-plus"></i> 添加菜单
																			</a>
																		</li>
																	</ul>
																</div>
															</div>
														</div>
													</div>
													<div class="app-side">
														<div class="menu app-conditionMenu-edit">
															<div class="arrow-left"></div>
															<div class="inner">
																<div class="panel panel-default">
																	<div class="panel-body form-horizontal">
																		<div class="conditionMenu-wx">
																			<div class="card" ng-if="context.group.button.length > 0">
																				<div class="btns">
																					<a href="javascript:;" ng-click="context.removeBut(context.activeItem, context.activeType)"><i class="fa fa-times"></i></a>
																				</div>
																				<div class="nav-region">
																					<div class="first-nav">
																						<h3>菜单设置</h3>
																						<div class="alert">
																							<div class="form-group">
																								<label class="control-label col-xs-2">菜单名称</label>
																								<div class="col-xs-10">
																									<div class="input-group" style="width:100%;">
																										<input type="text" class="form-control" name="" id="title" ng-model="context.activeItem.name" ng-disabled="context.group.disabled"/>
																									</div>
																								</div>
																							</div>
																							<div class="form-group" ng-if="context.activeType == 2 || (context.activeType == 1 && context.activeItem.sub_button.length == 0)">
																								<label class="control-label col-xs-2">菜单动作</label>
																								<div class="col-xs-10 menu-action">
																									<span >
																										<label class="radio-inline">
																											<input type="radio" name="ipt" ng-model="context.activeItem.type" value="view" ng-disabled="context.group.disabled"> 链接
																										</label>
																										<label class="radio-inline">
																											<input type="radio" name="ipt" ng-model="context.activeItem.type" value="click" ng-disabled="context.group.disabled"> 触发关键字
																										</label>
																									</span>
																									
																									<div ng-show="context.activeItem.type == 'view';">
																										<hr />
																										<div class="input-group" style="width:100%;">
																											<input class="form-control" id="ipt-url" type="text" ng-model="context.activeItem.url" ng-disabled="context.group.disabled"/>
																											<!-- <div class="input-group-btn">
																												<button class="btn btn-primary" id="search" ng-click="context.select_link()" ng-disabled="context.group.disabled"><i class="fa fa-external-link"></i> 系统链接</button>
																											</div> -->
																										</div>
																										<span class="help-block">指定点击此菜单时要跳转的链接（注：链接需加http://或https://）</span>
																										<span class="help-block"><strong>注意： 由于接口限制，如果你没有网页oAuth接口权限，这里输入链接直接进入微站个人中心时将会有缺陷（有可能获得不到当前访问用户的身份信息。如果没有oAuth接口权限，建议您使用图文回复的形式回复用户）</strong></span>
																									</div>
																									
																									<div ng-show="context.activeItem.type != 'view' && context.activeItem.type != 'media_id' && context.activeItem.type != 'view_limited'" style="position:relative;">
																										<hr />
																										<div class="input-group">
																											<input class="form-control" id="ipt-forward" type="text" ng-model="context.activeItem.key" ng-disabled="context.group.disabled"/>
																											<div class="input-group-btn">
																												<button class="btn btn-primary" id="search" ng-click="context.search()" ng-disabled="context.group.disabled"><i class="fa fa-search"></i> 搜索</button>
																											</div>
																										</div>
																										<div id="key-result" style="width:100%;position:absolute;top:55px;left:0px;display:none;z-index:10000">
																											<ul class="dropdown-menu" style="display:block;width:88%;">
																												<c:forEach var="keywords" items="${keywordsList}" varStatus="st">
																													<li><a href="javascript:void(0);">${keywords.keywords }</a></li>
																												</c:forEach>
																											</ul>
																										</div>
																										<span class="help-block">指定点击此菜单时要执行的操作，你可以在这里输入关键字或点击【搜索】选择关键字，那么用户在公众号点击此菜单时就相当于发送此关键字下的回复内容给用户。</span>
																										<span class="help-block"><strong>如果您输入关键字，则需要在【文本回复】、【图文回复】等模块添加此关键字（即回复规则）及回复内容。</strong></span>
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
														</div>
													</div>
												</div>
												<div class="text-center alert alert-warning" id="submit-wx">
														<span class="btn btn-primary" id="btn-submit" ng-click="context.saveMenu()">上架</span>
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
	</div>
	<!-- 同步所有用户标签 -->
	<div class="modal fade" id="synchronizeMenuModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalTitle">同步自定义菜单</h4>
				</div>
				<div class="modal-body">
					<form action="${ctx}/wx/menu/get" method="post" id="synchronizeMenuForm">
						<div class="container-fluid">
							<h4>是否同步该微信公众号的自定义菜单？</h4>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" name="cld" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="subtn" onclick="synchronizeMenu()">确定</button>
				</div>
			</div>
		</div>
	</div>
	<form action="${ctx}/editMenu" method="post" id="wxMenuForm">
			<input type="hidden" id="menuJsonStr" name="menuJsonStr" />
	</form>
		
	<script type="text/javascript" src="${ctx }/js/menu.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery.slimscroll.min.js"></script>
    <script type="text/javascript" src="${ctx }/js/jquery-ui.js"></script>
   	<script type="text/javascript" src="${ctx }/js/mark.js"></script> 
   	<script type="text/javascript" src="${ctx }/js/nav.js"></script>
   	<script src="${ctx }/dist/js/sb-admin-2.js"></script>
    	
	<script type="text/javascript">
	
		var menuObj=null;
		var menuJsonStr = '${menuJson}';
		if(menuJsonStr!=null&&menuJsonStr!=''){
			menuObj = jQuery.parseJSON(menuJsonStr);
		}
		
		require(['underscore', 'jquery.ui', 'jquery.caret', 'district'], function(_, $, $, dis){
			
			angular.module('app', []).controller('conditionMenuDesigner', function($scope, $http){
				
				$scope.context = {};
				$scope.context.group = null;
				if(menuObj!=null){
					$scope.context.group = menuObj;
				}
				if(!$scope.context.group) {
					$scope.context.group = {
						title: '标题',
						type: "3",
						button: [{
							name: '菜单名称',
							type: 'url',
							url: '',
							key: '',
							media_id : '',
							sub_button: []
						}]
						/* ,
						matchrule: {
							sex: 0,
							client_platform_type: 0,
							group_id: -1,
							country: '',
							province: '',
							city: '',
							language: ''
						} */
					};
					
					if($scope.context.group.type == 1) {
						$scope.context.group.title = '系统默认菜单';
					} else if($scope.context.group.type == 2) {
						$scope.context.group.title = '个性化菜单';
					}
				}
				
			$scope.context.activeIndex = 0;
			$scope.context.activeBut = $scope.context.group['button'][$scope.context.activeIndex];
			$scope.context.activeItem = $scope.context.activeBut;
			$scope.context.activeType = 1; //标识一级菜单
			
			//删除默认菜单
			$scope.context.remove = function(){
				if(!confirm('删除默认菜单会清空所有菜单记录，确定吗？')) {
					return false;
				}
				location.href = "";
				return false;
			};
			
			$scope.context.triggerActiveBut = function(but){
				var index = $.inArray(but, $scope.context.group.button);
				if(index == -1) return false;
				$scope.context.activeIndex = index;
				$scope.context.activeBut = $scope.context.group['button'][$scope.context.activeIndex];
				$scope.context.activeItem = $scope.context.activeBut;
				$scope.context.activeType = 1;
			};
			
			$scope.context.editBut = function(subbut, but){
				$scope.context.triggerActiveBut(but);
				if(!subbut) {
					$scope.context.activeItem = but;
					$scope.context.activeType = 1;
				} else {
					$scope.context.activeItem = subbut;
					$scope.context.activeType = 2;
				}
			};
			
			$scope.context.addBut = function(){
				
				if($scope.context.group['button'].length >= 3) {
					return;
				}
				
				$scope.context.group['button'].push({
					name: '菜单名称',
					type: 'view',
					url: '',
					key: '',
					media_id : '',
					sub_button: []
				});
				
				var but = $scope.context.group['button'][$scope.context.group.button.length - 1];
				
				$scope.context.triggerActiveBut(but);
				
				$('.designer-x').sortable({
					items: '.js-sortable',
					axis: 'x'
				});
			}
			
			$scope.context.removeBut = function(but, type){
				if(type == 1) {
					if(!confirm('将同时删除所有子菜单,是否继续')) {
						return false;
					}
					$scope.context.group.button = _.without($scope.context.group.button, but);
					$scope.context.triggerActiveBut($scope.context.group['button'][0]);
				} else {
					$scope.context.activeBut.sub_button = _.without($scope.context.activeBut.sub_button, but);
					$scope.context.triggerActiveBut($scope.context.activeBut);
				}
			};
			
			$scope.context.addSubBut = function(but){
				if($scope.context.group.disabled == 1) {
					return false;
				}
				
				$scope.context.triggerActiveBut(but);
				
				if($scope.context.activeBut.sub_button.length >= 5) {
					return;
				}
				
				$scope.context.activeBut.sub_button.push({
					name: '子菜单名称',
					type: 'view',
					url: '',
					key: '',
					media_id : ''
				});
				
				$('.designer-y').sortable({
					items: 'dd',
					axis: 'y',
					cancel: '.js-not-sortable'
				});
				
				$scope.context.activeItem = $scope.context.activeBut.sub_button[$scope.context.activeBut.sub_button.length - 1];
				$scope.context.activeType = 2;
			}
			
			$scope.context.saveMenu = function(){
				var group = $scope.context.group;
				group.button = _.sortBy(group.button, function(h){
					var elm = $(':hidden[data-role="parent"][data-hash="' + h.$$hashKey + '"]');
					return elm.parent().index();
				});
				
				angular.forEach(group.button, function(j){
					j.sub_button = _.sortBy(j.sub_button, function(h){
						var e = $(':hidden[data-role="sub"][data-hash="' + h.$$hashKey + '"]');
						return e.parent().index();
					});
				});
				//alert($scope.context.group['button']);
				//console.log(JSON.stringify(group));
				//console.log($scope.context);
				$('#menuJsonStr').val(JSON.stringify(group));
				$('#wxMenuForm').submit();
				//console.log($scope.context.group);
				//alert($scope.context.activeBut.sub_button);
			}
			
			//点击选择【系统连接】事件
			$scope.context.select_link = function(){
				
			};
			
			$scope.context.search = function(){
				$('#key-result ul li a[id!="no-result"]').click(function(){
					$('#ipt-forward').val($(this).html());
					$scope.context.activeItem.key = $(this).html();
					$('#key-result').hide();
				});
				$('#key-result').show();
			};
			
			});
			
			angular.bootstrap($('#conditionMenuDesigner')[0], ['app']);
			
			$(function(){
				$('.designer-y').sortable({
				items: 'dd',
				axis: 'y',
				cancel: '.js-not-sortable'
				});
				
				$('.designer-x').sortable({
					items: '.js-sortable',
					axis: 'x'
				});
			});
		});
		
		function synchronizeMenu(){
			$('#synchronizeMenuForm').submit();
		}
	</script>
	
</body>
</html>
