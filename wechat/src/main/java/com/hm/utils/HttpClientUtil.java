package com.hm.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

public class HttpClientUtil {
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);
    private static HttpClient client = null;

    // 构造单例
    private HttpClientUtil() {

		MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
		HttpConnectionManagerParams params = new HttpConnectionManagerParams();
		// 默认连接超时时间
		//params.setConnectionTimeout(5000);
		params.setConnectionTimeout(30000);
		// 默认读取超时时间
		//params.setSoTimeout(10000);
		params.setSoTimeout(30000);
		// 默认单个host最大连接数
		//params.setDefaultMaxConnectionsPerHost(100);// very important!!
		params.setDefaultMaxConnectionsPerHost(2);
		// 最大总连接数
		//params.setMaxTotalConnections(256);// very important!!
		params.setMaxTotalConnections(2);
		httpConnectionManager.setParams(params);
	
		client = new HttpClient(httpConnectionManager);
	
		client.getParams().setConnectionManagerTimeout(3000);
		//client.getParams().setIntParameter("http.socket.timeout", 10000);
		//client.getParams().setIntParameter("http.connection.timeout", 5000);
    }

    private static class ClientUtilInstance {
    	
    	private static final HttpClientUtil ClientUtil = new HttpClientUtil();
    }

    public static HttpClientUtil getInstance() {
    	
    	return ClientUtilInstance.ClientUtil;
    }

    /**
     * 发送http GET请求，并返回http响应字符串
     * @param url 完整的请求url字符串
     * @return
     */
    public String doGetRequest(String url) {
		String response = "";
		HttpMethod httpmethod = new GetMethod(url);
		try {
		    int statusCode = client.executeMethod(httpmethod);
		    InputStream _InputStream = null;
		    if (statusCode == HttpStatus.SC_OK) {
			_InputStream = httpmethod.getResponseBodyAsStream();
		    }
		    if (_InputStream != null) {
			response = GetResponseString(_InputStream, "UTF-8");
		    }
		} catch (HttpException e) {
		    logger.error("获取响应错误，原因1：" + e.getMessage());
		    e.printStackTrace();
		} catch (IOException e) {
		    logger.error("获取响应错误，原因2：" + e.getMessage());
		    e.printStackTrace();
		} finally {
		    httpmethod.releaseConnection();
		}
		return response;
    }
    
    /**
     *发送http POST请求，并返回http响应字符串
     * @param url	完整的请求url字符串
     * @param data
     * @return
     */
    public String doPostRequest(String url,String data) {
		String str = "";
		HttpClient httpclient = new HttpClient(); 
		httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
		PostMethod method = new UTF8PostMethod(url);
		if(!StringUtil.isEmpty(data)) {
			((PostMethod) method).addParameter("data", data); 
		}
		try {
			httpclient.executeMethod(method);
			str = method.getResponseBodyAsString();
		} catch (HttpException e) {
			logger.error("获取响应错误，原因3：" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("获取响应错误，原因4：" + e.getMessage());
			e.printStackTrace();
		}
		return str;
	}
	
    /**
     * 
     * @param _InputStream
     * @param Charset
     * @return
     */
    public String GetResponseString(InputStream _InputStream, String Charset) {
		String response = "";
		try {
		    if (_InputStream != null) {
			StringBuffer buffer = new StringBuffer();
			InputStreamReader isr = new InputStreamReader(_InputStream, Charset);
			Reader in = new BufferedReader(isr);
			int ch;
			while ((ch = in.read()) > -1) {
			    buffer.append((char) ch);
			}
			response = buffer.toString();
			buffer = null;
		    }
		} catch (Exception e) {
		    logger.error("获取响应错误，原因：" + e.getMessage());
		    response = response + e.getMessage();
		    e.printStackTrace();
		}
		return response;
    }
    
    public static class UTF8PostMethod extends PostMethod{     
		public UTF8PostMethod(String url){     
			super(url);     
		}     
	    @Override     
	    public String getRequestCharSet() {        
	    	return "UTF-8";     
		}  
	}
    
    public static void main(String[] args) {
    	
    }
}
