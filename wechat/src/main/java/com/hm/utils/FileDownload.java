package com.hm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;

public class FileDownload {
	
	/**

	    * 根据文件id下载文件

	    * 

	    * @param mediaId

	    *            媒体id

	    * @throws Exception

	    */

	   public static InputStream getInputStream(String url) {
	       InputStream is = null;
	       try {
	           URL urlGet = new URL(url);
	           HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
	           http.setRequestMethod("GET"); // 必须是get方式请求
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	           http.setDoOutput(true);
	           http.setDoInput(true);
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
	           http.connect();

	           // 获取文件转化为byte流
	           is = http.getInputStream();
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	       return is;
	   }

	   public static String saveFile(String url) throws OSSException, ClientException, IOException {
		   
		   InputStream content = getInputStream(url);
		   String urlFilePath = DateUtil.getServerTime("yyyy/MMdd") + "/";
		   String lstr = Long.toString(System.currentTimeMillis());
		   String fileName = lstr.substring(7) + ".jpg";
		   String key = "261kofj0t3fzifelgntbl4na";
		   String secret = "jzi1A++HwGkgVeu0KhxjnVm+qw4=";
		   OSSClient client = new OSSClient(key, secret);
		   String bucketName = "ehuipic";
		   String fn = (urlFilePath+fileName).substring(1);
			
		   ObjectMetadata meta = new ObjectMetadata();
		   meta.setContentLength(512);
		   PutObjectResult result = client.putObject(bucketName, fn, content, meta);
		   System.out.println(result.getETag());
		   return urlFilePath + fileName;
		}
	   
	   public static void main(String[] args) throws OSSException, ClientException, IOException {
		   String access_token="hjCT4y_NW1y1PfHj0WxKhCP0aPSpc5d9Prs-VYbN0U5p4bK85sEZo_NJWfMqeHF9dnrV-iLGfjDNQgeHDCA2SCqpzvamvXjzjmWl6c2zme4eYiwZm4DN26U9oKMQibjQJOHcAAAUZS";
		   String media_id="SulemUr4845ji6UKb6YN9cTg8m-";
		   String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+access_token+"&media_id="+media_id+"";
		   String a = HttpClientUtil.getInstance().doGetRequest(url);
		   System.out.println(a);
		   String result = saveFile(url);
		   System.out.println(result);
	   }
}
