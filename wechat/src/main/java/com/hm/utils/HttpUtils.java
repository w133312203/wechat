package com.hm.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStoreException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

@SuppressWarnings("deprecation")
public class HttpUtils {
	
	private static Logger infologger = Logger.getLogger("dayinfo");
	
	public static byte[] readInputStream(InputStream inStream) throws Exception{
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
		//创建一个Buffer字符串 
		byte[] buffer = new byte[1024]; 
	 	//每次读取的字符串长度，如果为-1，代表全部读取完毕 
		int len = 0; 
		//使用一个输入流从buffer里把数据读取出来 
		while( (len=inStream.read(buffer)) != -1 ){ 
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度 
			outStream.write(buffer, 0, len); 
		} 
		//关闭输入流 
		inStream.close(); 
		//把outStream里的数据写入内存 
		return outStream.toByteArray();
	 } 
	
	public static String downloadImage(String urlStr,String dirPath){
		URL url;
		String imageUrl = "";
		try {
			//new一个URL对象 
			url = new URL(urlStr);
			//打开链接 
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
			//设置请求方式为"GET" 
			conn.setRequestMethod("GET"); 
			//超时响应时间为5秒 
			conn.setConnectTimeout(5 * 1000); 
			//通过输入流获取图片数据 
			InputStream inStream = conn.getInputStream(); 
			//得到图片的二进制数据，以二进制封装得到数据，具有通用性 
			byte[] data = readInputStream(inStream); 
			//new一个文件对象用来保存图片，默认保存当前工程根目录 
			long time=System.currentTimeMillis();
			File dirFile=new File(dirPath);
			if(!dirFile.exists()){
				dirFile.mkdirs();
			}
			//创建输出流 
			FileOutputStream outStream = new FileOutputStream(dirPath+time+".jpg"); 
			//写入数据 
			outStream.write(data); 
			//关闭输出流 
			outStream.close();
			File imageFile = new File(dirPath+time+".jpg");
			String urlFilePath = DateUtil.getServerTime("/yyyy/MMdd") + "/";
			imageUrl = FileUtil.saveFile(imageFile, urlFilePath, time+".jpg");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageUrl; 
	}
	
	public static String httpPost(String jsonData,String urlData) throws IOException {
        URL url = null;
        HttpURLConnection httpConn = null;
        OutputStream output = null;
        OutputStreamWriter outr = null;

        url = new URL(urlData);
        httpConn = (HttpURLConnection) url.openConnection();
        HttpURLConnection.setFollowRedirects(true);
        httpConn.setDoOutput(true);
        httpConn.setRequestMethod("POST");
        httpConn.setRequestProperty("Content-Type", "text/xml");
        httpConn.connect();
        output = httpConn.getOutputStream();
        outr = new OutputStreamWriter(output);
        // 写入请求参数
        outr.write(jsonData.toString().toCharArray(), 0, jsonData
                .toString().length());
        outr.flush();
        outr.close();
        int code = httpConn.getResponseCode();
        
        //读取响应内容
        String sCurrentLine = ""; 
        String sTotalString = ""; 
        if (code == 200)
        {
            java.io.InputStream is = httpConn.getInputStream();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is));
            while ((sCurrentLine = reader.readLine()) != null)
                if (sCurrentLine.length() > 0)
                    sTotalString = sTotalString + sCurrentLine.trim();
        } else
        {
            sTotalString = 0+"";

        }
        return sTotalString;
	}
	
	@SuppressWarnings("resource")
	public static String doPost(String url,String str,String charset){  
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
            httpClient = new SSLClient();  
            httpPost = new HttpPost(url);  
            httpPost.addHeader("Content-Type", "text/xml");
            StringEntity entity = new StringEntity(str,"utf-8");
            httpPost.setEntity(entity);  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
              System.out.println(line);
            }
            if (entity != null) {
              entity.consumeContent();
            }
        }catch(Exception ex){  
            ex.printStackTrace();  
        }  
        return result;  
    }
	
	@SuppressWarnings("resource")
	public static String xmlPost(String url, byte[] str) throws KeyStoreException, FileNotFoundException {
		InputStream input = null;
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			String result = null;
			if (str != null) {
				ByteArrayEntity s = new ByteArrayEntity(str);
				s.setContentEncoding("UTF-8");
				s.setContentType("application/xml");
				// HttpEntity requestEntity = new
				// ByteArrayEntity(str.getBytes("UTF-8"));
				// requestEntity.
				post.setEntity(s);
			}
			HttpResponse res = client.execute(post);
			if (res != null) {
				HttpEntity resEntity = res.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, "UTF-8");
				}
			}
			HttpEntity entity = res.getEntity();
			infologger.info("post response[" + url + "]:" + result);
			EntityUtils.consume(entity);
			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String arg0[]) {
		String c="<xml><appid>wx7a2c68a7408dd929</appid><mch_id>1230993002</mch_id><device_info>WEB</device_info><nonce_str>60c1d8ce22ba424a8f039046e057209f</nonce_str><body>cs</body><out_trade_no>CS001</out_trade_no><total_fee>1</total_fee><spbill_create_ip>47.93.123.117</spbill_create_ip><notify_url>http://dpweixin.ehub.net/ehubWeChat/wechat/notifyUrl</notify_url><trade_type>JSAPI</trade_type><openid>oFOnXjkLzSCM5tPe_9ju9If6f_mM</openid><sign>21da6a1d21372e01d614740dfb9caf7e</sign></xml>";
		try {
			System.out.println(doPost("https://api.mch.weixin.qq.com/pay/unifiedorder",c,"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
