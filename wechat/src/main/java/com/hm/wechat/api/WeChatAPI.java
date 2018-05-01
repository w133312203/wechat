package com.hm.wechat.api;  
  
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.hm.base.controller.BaseCotroller;
import com.hm.base.service.CoreService;
import com.hm.domain.OfficialAccounts;
import com.hm.exception.AesException;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.service.OfficialAccountsService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.HttpClientUtil;
import com.hm.utils.HttpUtils;
import com.hm.utils.PSMD5;
import com.hm.utils.SignUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/wechat/api")
public class WeChatAPI extends BaseCotroller{ 
	
    @Resource  
    private EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    private EnterpriseUserInfoService euserInfoService;

    @Resource  
    private OfficialAccountsService officialAccountsService;
    
    private static Logger errorlogger = Logger.getLogger("errorinfo");
	private static Logger infologger = Logger.getLogger("dayinfo");
	
	/**
     * 处理微信服务器验证消息
     * @param signature 微信加密签名
     * @param timestamp 时间戳
     * @param nonce 随机数
     * @param echostr 随机字符串
     * @throws AesException 
     */
    @ResponseBody
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
	private void authGet(String code,String signature, String timestamp, String nonce, String echostr, HttpServletResponse response) throws AesException {
    	if(!StringUtil.isEmpty(code)){
    		OfficialAccounts account = officialAccountsService.findByCode(code.trim());
        	if(account!=null&&!StringUtil.isEmpty(account.getToken())){
        		if(!StringUtil.isEmpty(signature)&&!StringUtil.isEmpty(timestamp)&&!StringUtil.isEmpty(nonce)){
        			//通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        			if(SignUtil.checkSignature(account.getToken(), signature, timestamp, nonce)) {
        				printWriter(response,echostr);
                    }
            	}else{
            		printWriter(response,"如果你在浏览器中看到这句话，说明此地址可以被作为微信公众号后台的URL，请注意保持两个平台Token一致。");
            	}
        	}else{
        		printWriter(response,"请在托管平台填写Token，并注意与微信公众号后台的Token保持一致。");
        	}
        }else{
        	printWriter(response,"参数code不能为空，请在eHub微信托管平台获取正确URL");
        }
    }
    
    /**
     * 接收来自微信发来的消息
     * @param out
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public void authPost(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        String responseMessage = CoreService.processRequest(request);
        out.print(responseMessage);
        out.flush();
    }
        
    /**
	 * 微信登录接口
     * @throws IOException 
	 */
    @ResponseBody
    @RequestMapping("/weiXinResponse")
	public void weiXinResponse(String code, HttpSession httpSession, HttpServletResponse response) throws IOException {
    	String access_json = HttpClientUtil.getInstance().doGetRequest("https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ApplicationUtil.WX_APPID+"&secret="+ApplicationUtil.WX_APPSECRET+"&code="+code+"&grant_type=authorization_code");
		JSONObject jsonb = JSONObject.fromObject(access_json);
        String openid = jsonb.get("openid").toString();
        httpSession.setAttribute("openid", openid);
        infologger.info("微信返回openid信息："+openid);
	    response.sendRedirect("http://dpweixin.ehub.net/ehubWeChat/wechat/jsapi?wx=y");
	}
    
    /**
	 * JSAPI微信支付接口
	 */
    @ResponseBody
    @RequestMapping("/jsapiPay")
    public Map<String, Object> jsapiPay(HttpSession httpSession) {
    	infologger.info("发起微信JSAPI支付交易");
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		String strA = "appid=wx7a2c68a7408dd929&body=cs&device_info=WEB&mch_id=1230993002&nonce_str="+uuid+"&"
				+ "notify_url=http://dpweixin.ehub.net/ehubWeChat/wechat/notifyUrl&"
				+ "openid="+httpSession.getAttribute("openid")+"&out_trade_no=CS001&spbill_create_ip=47.93.123.117&total_fee=1&"
				+ "trade_type=JSAPI&key=Doit2015EHUI2015netawuBeiJing123";
		String sendOrderString = "<xml>";
        sendOrderString += "<appid>wx7a2c68a7408dd929</appid>";
        sendOrderString += "<body>cs</body>";
        sendOrderString += "<device_info>WEB</device_info>";
        sendOrderString += "<mch_id>1230993002</mch_id>";
        sendOrderString += "<nonce_str>"+uuid+"</nonce_str>";
        sendOrderString += "<notify_url>http://dpweixin.ehub.net/ehubWeChat/wechat/notifyUrl</notify_url>";
        sendOrderString += "<openid>"+httpSession.getAttribute("openid")+"</openid>";
        sendOrderString += "<out_trade_no>CS001</out_trade_no>";
        sendOrderString += "<spbill_create_ip>47.93.123.117</spbill_create_ip>";
        sendOrderString += "<total_fee>1</total_fee>";
        sendOrderString += "<trade_type>JSAPI</trade_type>";
        sendOrderString += "<sign>"+PSMD5.MD5Encode(strA)+"</sign></xml>";
        infologger.info("微信返回信息："+sendOrderString);
        String returnStr = HttpUtils.doPost("https://api.mch.weixin.qq.com/pay/unifiedorder", sendOrderString, "UTF-8");
        infologger.info("微信返回信息："+returnStr);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(returnStr);
		    InputSource is = new InputSource(sr);
	        Document document = db.parse(is);
	        Element root = document.getDocumentElement();
	        String return_code = root.getElementsByTagName("return_code").item(0).getTextContent();
	        String result_code = root.getElementsByTagName("result_code").item(0).getTextContent();
	        if(return_code.equals("SUCCESS")&&result_code.equals("SUCCESS")) {
		        String prepay_id = root.getElementsByTagName("prepay_id").item(0).getTextContent();
		        Map<String, Object> map = new HashMap<String, Object>();
		        map.put("appId", "wx7a2c68a7408dd929");
		        map.put("nonceStr", uuid);
		        map.put("packages", "prepay_id="+prepay_id);
		        map.put("signType", "MD5");
		        String time = System.currentTimeMillis()+"";
		        map.put("timeStamp", time.substring(0,10));
		        String h5Str = "appId=wx7a2c68a7408dd929&nonceStr="+uuid+"&package=prepay_id="+prepay_id+"&signType=MD5&timeStamp="+time.substring(0,10)+"&key=Doit2015EHUI2015netawuBeiJing123";
		        map.put("paySign", PSMD5.MD5Encode(h5Str).toUpperCase());
  				map.put("sendUrl", "http://www.ehui.net");
  				map.put("code", "success");
  				infologger.info("微信统一下单成功sign信息"+h5Str);
  				infologger.info("微信统一下单成功信息");
  				infologger.info("微信统一下单成功map信息"+map);
  				return map;
	        }
		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			errorlogger.error ( "微信支付异常_ParserConfigurationException : " + str ) ;
		} catch (SAXException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			errorlogger.error ( "微信支付异常_SAXException : " + str ) ;
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			errorlogger.error ( "微信支付异常_IOException : " + str ) ;
		}
		return null;
    }
    
    /**
     * 微信回调
     */
    @ResponseBody
    @RequestMapping("/notifyUrl")
    public void notifyUrl(HttpServletResponse response) {
		StringBuffer info=new StringBuffer();
		InputStream in;
		try {
			in = request.getInputStream();
			BufferedInputStream buf=new BufferedInputStream(in);
			byte[] buffer=new byte[1024];     
			int iRead;    
			while((iRead=buf.read(buffer))!=-1) {     
				info.append(new String(buffer,0,iRead,"UTF-8"));    
			}
			infologger.info("微信异步返回信息："+info);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        StringReader sr = new StringReader(info.toString());
	        InputSource is = new InputSource(sr);
	        Document document = db.parse(is);
	        Element root = document.getDocumentElement();
	        String return_code = root.getElementsByTagName("return_code").item(0).getTextContent();
	        String out_trade_no = root.getElementsByTagName("out_trade_no").item(0).getTextContent();
	        if(return_code.equals("SUCCESS")) {
	        	infologger.info("微信支付异步返回信息：订单:"+out_trade_no+"支付成功！");
	    		printWriter(response, "success");
	        }
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			errorlogger.error ( "微信异步返回错误异常_IOException : " + str ) ;
			return;
		} catch (ParserConfigurationException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			errorlogger.error ( "微信异步返回错误异常_ParserConfigurationException : " + str ) ;
			return;
		} catch (SAXException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			errorlogger.error ( "微信异步返回错误异常_SAXException : " + str ) ;
			return;
		}
	}
    
} 