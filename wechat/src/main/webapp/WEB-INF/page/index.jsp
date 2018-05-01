<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>
<html>  
	<head>
		<title>JSAPI测试</title>
		<script type="text/javascript" src="${ctx }/js/jquery.min.js"></script>
		<script type="text/javascript">
	   		var ua = navigator.userAgent.toLowerCase();//获取判断用的对象
	   		if (ua.match(/MicroMessenger/i) == "micromessenger") {
	   			var param=getUrlParam("wx");
	   			if(param==null||param!='y'){
	   				var uri=encodeURI("http://dpweixin.ehub.net/ehubWeChat/wechat/weiXinResponse");
		        	var htmlurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7a2c68a7408dd929&redirect_uri="+uri+"&response_type=code&scope=snsapi_base&state=123&connect_redirect=1#wechat_redirect";
		    		window.location=htmlurl;
	   			}
	   		}
   			
	   	 //获取url中的参数
	   	 function getUrlParam(name) {
	   	 	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	   	 	var r = window.location.search.substr(1).match(reg); //匹配目标参数
	   	 	if (r != null) return unescape(r[2]); return null; //返回参数值
	   	 }

	</script>
	</head>
	<body>
		<button onclick="jsapiOrder()" style="width: 200px;height: 100px;">JSAPI支付</button>
		
		<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script type="text/javascript">
			
			function jsapiOrder(){
				
				$.ajax({
	           		type: "POST",
	           		url: "${ctx }/wechat/jsapiPay",
	           		async: false,
	           		dataType: "json",
	           		success: function(data){
	           			var obj = eval(data);
	           			if(obj.code=='success'){
	        				WeixinJSBridge.invoke(
       					       'getBrandWCPayRequest', {
       					           "appId":obj.appId,			//公众号名称，由商户传入     
       					           "timeStamp":obj.timeStamp,	//时间戳，自1970年以来的秒数     
       					           "nonceStr":obj.nonceStr,		//随机串     
       					           "package":obj.packages,     
       					           "signType":obj.signType, 	//微信签名方式     
       					           "paySign":obj.paySign 		//微信签名 
       					       },
       					       function(res){     
       					           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
       					        	window.location.href=obj.sendUrl;
       					           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
       					       }
       					    );
	        				
	        				if (typeof WeixinJSBridge == "undefined"){
	        					   if( document.addEventListener ){
	        					       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
	        					   }else if (document.attachEvent){
	        					       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
	        					       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
	        					   }
	        					}else{
	        					   onBridgeReady();
	        					}
	        				
	           			}
	           		}
	       		});
				
			}

		</script>
	</body>
</html> 
