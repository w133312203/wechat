
package com.hm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hm.domain.EnterpriseUserPassport;

/**
 * 登录认证的拦截器
 */
public class LoginInterceptor implements HandlerInterceptor{

	/**
	 * Handler执行完成之后调用这个方法
	 */
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exc)
			throws Exception {
		
	}

	/**
	 * Handler执行之后，ModelAndView返回之前调用这个方法
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
	}

	/**
	 * Handler执行之前调用这个方法
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		//获取请求的URL
		String url = request.getRequestURI();
		String path = request.getContextPath();
		String basePath = request.getScheme()
				+ "://"
				+ request.getServerName()
				+ (request.getServerPort() == 80 ? "" : ":"
						+ request.getServerPort()) + path;
		if(url.indexOf("login")>=0){
			return true;
		}
		//获取Session
		EnterpriseUserPassport euserPassport = (EnterpriseUserPassport) request.getSession().getAttribute("euserPassport");
		if(euserPassport != null){
			return true;
		}
		//不符合条件的，跳转到登录界面
		response.sendRedirect(basePath+"/login");
		return false;
	}

}


