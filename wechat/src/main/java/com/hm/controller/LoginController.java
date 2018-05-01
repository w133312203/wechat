package com.hm.controller;  
  
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.EnterpriseUserInfo;
import com.hm.domain.EnterpriseUserPassport;
import com.hm.service.EnterpriseUserInfoService;
import com.hm.service.EnterpriseUserPassportService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.PSMD5;
import com.hm.utils.StringUtil;

@Controller
public class LoginController extends BaseCotroller{ 
	
    @Resource  
    private EnterpriseUserPassportService euserPassportService;
    
    @Resource  
    private EnterpriseUserInfoService euserInfoService;
	
    //登录
    @RequestMapping("/login")
  	public ModelAndView login() {
    	if(getSessionPassport()!=null) {
    		return new ModelAndView("redirect:/account/home");
		}else {
			return new ModelAndView("/login/login");
		}
  	}
    
    //登出
    @ResponseBody
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession httpSession, HttpServletResponse response) {
    	httpSession.invalidate();
    	try {
    		response.sendRedirect(ApplicationUtil.EHUB_HTTP_HEAD + "/logout");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    //查找企业用户是否存在
    @ResponseBody
    @RequestMapping(value = "/checkPassport",method = RequestMethod.POST, produces="application/json")
	public Map<String, Object> checkPassport(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	if(!StringUtil.isEmpty(username)&&!StringUtil.isEmpty(password)) {
    		EnterpriseUserPassport euserPassport = euserPassportService.findEnterpriseUserPassport(username, username, PSMD5.MD5Encode(password));
    		if(euserPassport!=null) {
    			EnterpriseUserInfo euserInfo = euserInfoService.updateLastLoginTime(euserPassport.getId());
				httpSession.setAttribute("euserPassport", euserPassport);
				httpSession.setAttribute("euserInfo", euserInfo);
				map.put("CODE", "200");
			}else {
				map.put("CODE", "10001");
			}
    	}else {
    		map.put("CODE", "10002");
    	}
    	return map;
	}
  	
}