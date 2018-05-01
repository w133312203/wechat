<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<title>微信后台管理系统</title>
<link rel="stylesheet" type="text/css" href="dist/css/login.css" />
<link rel="stylesheet" type="text/css" href="dist/css/login-type.css" />
<script type="text/javascript">
	if (window != top) {
		top.location.href = location.href;
	}
</script>
</head>
<body>
	<div class="login_content">
		<div class="login_img">
			<!-- <img src="img/ehui_logo.png" width="200px" /> -->
		</div>
		<form action="sendActivateEmail" method="post">
			<input type="hidden" name="email" id="email" />
			<div class="login_column">
				<div class="login_text">
					<div class="inputBox">
						<input type="text" id="username" class="" placeholder="邮箱／手机">
						<div id="div_verification" class="verif div_verification">
							<span id="verification">请输入账户</span> <i></i>
						</div>
					</div>

					<div class="pass">
						<input type="password" id="password" class="" placeholder="密码">
						<div id="div_verification" class="verif div_verifications">
							<span id="verification">请输入密码</span> <i></i>
						</div>

					</div>
				</div>
				<input class="subm button_webkit" type="button" value="登 录"
					onclick="checkEuser(event)">
				<div class="forget">
					<p>
						<label for="reb"><input id="reb" type="checkbox" />下次自动登录</label>
					</p>
					<p class="forget_pass">
						<a href="forgetPwd">忘记密码?</a>
					</p>
				</div>
			</div>
		</form>
	</div>
	<div>
		<img class="back" src="././img/login/bj.png" />
	</div>
</body>

<!-- jQuery -->
<script src="./bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="./bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
	function verification(e, obj) {
		$('.verif').hide().css({
			"top" : "-50px",
			"opacity" : "0"
		});
		if (e && e.preventDefault) {
			e.preventDefault();
		} else {
			window.event.returnValue = false;
			return false;
		}
		if (e && e.stopPropagation) {
			e.stopPropagation();
		} else {
			window.event.cancelBubble = true;
			return false;
		}
		$(obj).show().animate({
			"top" : "-65px",
			"opacity" : "1",
			"display" : "block"
		}, 300);
	}

	$(document).click(function() {
		$('.verif').hide().css({
			"top" : "-50px",
			"opacity" : "0"
		});
	})

	$(function() {
		$("#username").val(getCookie("ehadmin"));
		$("#password").val(getCookie("ehpwd"));
	})
	function checkEuser(e) {
		var usr = $("#username").val();
		var pwd = $("#password").val();
		$('.verif').hide().css({
			"top" : "-50px",
			"opacity" : "0"
		});
		if (e && e.preventDefault) {
			e.preventDefault();
		} else {
			window.event.returnValue = false;
			return false;
		}
		if (e && e.stopPropagation) {
			e.stopPropagation();
		} else {
			window.event.cancelBubble = true;
			return false;
		}
		if (usr == '') {
			$(".div_verification").show().animate({
				"top" : "-65px",
				"opacity" : "1",
				"display" : "block"
			}, 300);
			return;
		}
		if (pwd == '') {
			$(".div_verifications").show().animate({
				"top" : "-65px",
				"opacity" : "1",
				"display" : "block"
			}, 300);
			return;
		}
		$.ajax({
			type : "post",
			url : "checkPassport",
			data : {
				'username' : usr,
				'password' : pwd
			},
			dataType : "json",
			success : function(data) {
				var obj = eval(data);
				if (obj.CODE == '200') {
					$("#ck").hide();
					if ($("#reb").is(':checked')) {
						setCookie("ehadmin", usr, 24 * 60 * 60 * 7); //一周后失效
						setCookie("ehpwd", pwd, 24 * 60 * 60 * 7); //一周后失效
					}
					window.location.href = 'account/home';
				} else {
					$("#verification").text("用户名或密码不正确");
					$(".div_verification").show().animate({
						"top" : "-65px",
						"opacity" : "1",
						"display" : "block"
					}, 300);
				}
			}
		});
	}

	function setCookie(name, value, expires, path, domain, secure) {
		var expSecs = expires * 1000;
		var expDate = new Date();
		expDate.setTime(expDate.getTime() + expSecs);
		var expString = ((expires == "-1") ? "" : (";expires=" + expDate
				.toGMTString()))
		var pathString = ((path == null) ? "" : (";path=" + path))
		var domainString = ((domain == null) ? "" : (";domain=" + domain))
		var secureString = ((secure == true) ? ";secure" : "")
		document.cookie = name + "=" + encodeURI(value) + expString
				+ pathString + domainString + secureString;
	}

	function getCookie(name) {
		var result = null;
		var myCookie = document.cookie + ";";
		var searchName = name + "=";
		var startOfCookie = myCookie.indexOf(searchName);
		var endOfCookie;
		if (startOfCookie != -1) {
			startOfCookie += searchName.length;
			endOfCookie = myCookie.indexOf(";", startOfCookie);
			if (endOfCookie == -1) {
				endOfCookie = mycookie.indexOf("&", startOfCookie);
			}
			result = decodeURI(myCookie.substring(startOfCookie, endOfCookie));
		}
		if (result == null)
			result = "";
		return result;
	}
</script>
</html>


