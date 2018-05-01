package com.hm.listener;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

public class MyCommonsMultipartResolver extends CommonsMultipartResolver{
	
	public boolean isMultipart(HttpServletRequest request) {
		String uri = request.getRequestURI();
		if(uri.indexOf("ueditor/config")>0||uri.indexOf("ueditor/wx/config")>0){
			return false;
		}
		return super.isMultipart(request);
		
	}
}
