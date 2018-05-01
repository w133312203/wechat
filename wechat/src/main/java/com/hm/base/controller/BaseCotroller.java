package com.hm.base.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;

import com.hm.domain.EnterpriseUserPassport;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.Software;
import com.hm.domain.WxGlobalParam;
import com.hm.service.WxGlobalParamService;

public class BaseCotroller {
	
	@Autowired
	protected HttpServletRequest request;
	
	@Autowired
	protected WxGlobalParamService wxGlobalParamService;
	
	protected void printJson(HttpServletResponse response, Object object) throws IOException {
		JSON json = JSONSerializer.toJSON(object);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(json);
		response.getWriter().close();
	}
	
	/**
	 * 向页面输出
	 */
	public void printWriter(HttpServletResponse response, Object object) {
		try {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println(object);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected EnterpriseUserPassport getSessionPassport() {
		EnterpriseUserPassport sessionPassport = (EnterpriseUserPassport) request.getSession().getAttribute("euserPassport");
		return sessionPassport;
	}
	
	protected OfficialAccounts getSessionAccounts() {
		OfficialAccounts sessionAccounts = (OfficialAccounts) request.getSession().getAttribute("officialAccounts");
		return sessionAccounts;
	}
	
	protected Software getSessionSoftware() {
		Software sessionSoftware = (Software) request.getSession().getAttribute("software");
		return sessionSoftware;
	}
	
	protected String getAccessToken() {
		WxGlobalParam wp = (WxGlobalParam) request.getSession().getAttribute("wxGlobalParam");
		if(wp==null||wp.getExpirationTime()==null||new Date().compareTo(wp.getExpirationTime())>-1) {
			wp = wxGlobalParamService.getAccessToken(getSessionAccounts());
			request.getSession().setAttribute("wxGlobalParam", wp);
		}
		return wp.getAccessToken();
	}
}
