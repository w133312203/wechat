package com.hm.wechat.controller;  
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.OfficialAccounts;
import com.hm.service.OfficialAccountsService;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/account")
public class OfficialAccountsController extends BaseCotroller{ 
	
    @Resource  
    private OfficialAccountsService officialAccountsService;
    
    //微信公众号配置页
    @RequestMapping("/home")
  	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("/page/wxHome");
		List<OfficialAccounts> accounts = officialAccountsService.findListByEuserId(getSessionPassport().getId());
		mav.addObject("accounts", accounts);
		return mav;
  	}
    
    //编辑微信公众号
    @RequestMapping("/editAccount")
	public ModelAndView editAccount(OfficialAccounts accounts) {
		ModelAndView mav = new ModelAndView("redirect:/account/home");
		if(accounts.getId() != null) {
			officialAccountsService.update(accounts);
		}else {
			accounts.setEuserId(getSessionPassport().getId());
			officialAccountsService.save(accounts);
		}
		return mav; 
	}
    
    //查询微信公众号
    @ResponseBody
    @RequestMapping("/findAccount")
    public Map<String,Object> findAccount(Integer id) {
		Map<String, Object> map = new HashMap<>();
		OfficialAccounts accounts=officialAccountsService.findById(id);
		if(accounts!=null&&accounts.getEuserId().equals(getSessionPassport().getId())){
			map.put("accounts", accounts);
			map.put("CODE", "200");
		}else{
			map.put("CODE", "10001");
		}
    	return map;
    }
    
    //禁用公众号
    @RequestMapping("/deleteAccount")
	public ModelAndView deleteAccount(Integer id) {
		ModelAndView mav = new ModelAndView("redirect:/account/home");
		officialAccountsService.deleteByEuserId(id, getSessionPassport().getId());
		return mav; 
	}
    
    //登录成功跳转微信公众号主页面
    @RequestMapping("/show")
  	public ModelAndView show(OfficialAccounts officialAccounts,HttpSession httpSession) {
    	ModelAndView mav = new ModelAndView();
		OfficialAccounts accounts=officialAccountsService.findById(officialAccounts.getId());
		if(accounts!=null&&accounts.getEuserId().equals(getSessionPassport().getId())){
			httpSession.setAttribute("officialAccounts", accounts);
    		mav.setViewName("redirect:/user/list");
		}else{
			mav.setViewName("redirect:/account/home");
		}
    	return mav;
  	}
    
    //查询公众号是否已经存在
    @ResponseBody
    @RequestMapping("/findByAppId")
    public Map<String,Object> findByAppId(String appId) {
		Map<String, Object> map = new HashMap<>();
		if(!StringUtil.isEmpty(appId)){
			OfficialAccounts accounts = officialAccountsService.findByAppId(appId);
			if(accounts!=null){
				map.put("CODE", "200");
				map.put("TYPE", "TRUE");
			}else{
				map.put("CODE", "200");
				map.put("TYPE", "FALSE");
			}
		}else{
			map.put("CODE", "10002");
		}
    	return map;
    }
} 