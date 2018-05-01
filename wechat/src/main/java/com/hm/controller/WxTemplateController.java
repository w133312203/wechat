package com.hm.controller;  
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.WxGlobalParam;
import com.hm.domain.WxTemplate;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxTemplateService;
import com.hm.utils.AccessTokenUtil;
import com.hm.utils.WxApiUtil;
   

@Controller
public class WxTemplateController extends BaseCotroller{ 
	
    @Resource  
    private WxGlobalParamService wxGlobalParamService;
    
    @Resource  
    private WxTemplateService wxTemplateService;
    
    private static Logger infologger = Logger.getLogger("dayinfo");
    
    //获取微信模板消息列表
    @RequestMapping("/wx/templates")
  	public ModelAndView wxTemplates() {
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			ModelAndView mav = new ModelAndView("/wxTemplates");
    			List<WxTemplate> templateList = wxTemplateService.findList(getSessionAccounts().getId());
    			if(templateList==null||templateList.size()==0){
    				AccessTokenUtil.checkAccessToken(getSessionAccounts());
    				WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    				List<Map<String, Object>> wxTemplateList = WxApiUtil.getTemplates(getSessionAccounts().getId(), param.getAccessToken());
    				if(wxTemplateList!=null&&wxTemplateList.size()>0){
    					wxTemplateService.saveList(getSessionAccounts().getId(), wxTemplateList);
    				}
    				templateList = wxTemplateService.findList(getSessionAccounts().getId());
    			}
    			mav.addObject("templateList", templateList);
				return mav;
    		}else{
    			return new ModelAndView("redirect:/wx/home");
    		}
		}else {
			return new ModelAndView("redirect:/login");
		}
  	}
    
    //删除模板消息
    @RequestMapping("/deleteTemplate")
	public ModelAndView deleteTemplate(Integer id) {
		ModelAndView mav = new ModelAndView();
		if(id!=null) {
			AccessTokenUtil.checkAccessToken(getSessionAccounts());
			WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
			WxTemplate template = wxTemplateService.find(id);
			Map<String, Object> templateMap = new HashMap<String, Object>();
			templateMap.put("template_id", template.getTemplateId());
			JSON templateJson=JSONSerializer.toJSON(templateMap);
			String templateStr=WxApiUtil.deleteTemplate(param.getAccessToken(), templateJson.toString());
			JSONObject jsonobj = JSONObject.fromObject(templateStr);
			if(jsonobj.getInt("errcode")==0){
				mav.setViewName("redirect:/wx/templates");
				wxTemplateService.delete(id);
			}else {
				mav.setViewName("wxError");
				mav.addObject("urlStr", "/wx/templates");
				mav.addObject("errmsg", jsonobj.getString("errmsg"));
			}
		}else {
			mav.setViewName("wxError");
			mav.addObject("urlStr", "/wx/templates");
			mav.addObject("errmsg", "此ID不存在");
		}
		return mav; 
	}
    
    //根据ID查询模板信息
    @ResponseBody
    @RequestMapping(value = "/getTemplateInfo",method = RequestMethod.GET, produces="application/json")
	public  Map<String, Object> getTemplateInfo(@RequestParam Integer id) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	if(id!=null) {
    		WxTemplate template = wxTemplateService.find(id);
    		String example = template.getExample().replace("\n", "<br/>");
    		map.put("example", example);
    		map.put("code", 1);
    		map.put("msg", "success");
    	}else {
    		map.put("code", 0);
    		map.put("msg", "data is null");
    	}
    	return map;
	}
    
    //获取微信模板消息列表
    @RequestMapping("/wx/templates/get")
  	public ModelAndView synchronizeTemplates() {
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			ModelAndView mav = new ModelAndView("redirect:/wx/templates");
				AccessTokenUtil.checkAccessToken(getSessionAccounts());
				WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
				List<Map<String, Object>> wxTemplateList = WxApiUtil.getTemplates(getSessionAccounts().getId(), param.getAccessToken());
				if(wxTemplateList!=null&&wxTemplateList.size()>0){
					wxTemplateService.saveList(getSessionAccounts().getId(), wxTemplateList);
				}
				return mav;
    		}else{
    			return new ModelAndView("redirect:/wx/home");
    		}
		}else {
			return new ModelAndView("redirect:/login");
		}
  	}
    
} 