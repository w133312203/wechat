package com.hm.wechat.controller;  
  
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.WxTag;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxTagService;
   
@Controller
@RequestMapping("/tag")
public class TagController extends BaseCotroller{ 
	
    @Resource  
    private WxGlobalParamService wxGlobalParamService;
    
    @Resource  
    private WxTagService wxTagService;
    
    private static Logger infologger = Logger.getLogger("dayinfo");
    
    //添加/修改用户标签
    @RequestMapping("/editTag")
	public ModelAndView editTag(WxTag tag) {
		ModelAndView mav = new ModelAndView();
		if(tag.getId() != null) {
			WxTag oldTag = wxTagService.findById(tag.getId());
			if(!tag.getTagName().trim().equals(oldTag.getTagName())){
				wxTagService.updateTagName(getAccessToken(), tag.getTagName(), oldTag);
			}
		}else {
			wxTagService.addTagName(getSessionAccounts().getId(), getAccessToken(), tag.getTagName());
		}
		mav.setViewName("redirect:/user/list");
		return mav; 
	}
    
    @RequestMapping("/deleteTag")
	public ModelAndView deleteTag(Integer id) {
		ModelAndView mav = new ModelAndView();
		if(id!=null) {
			wxTagService.deleteTagName(getAccessToken(), id);
		}
		mav.setViewName("redirect:/user/list");
		return mav; 
	}
    
    //根据id查询用户标签信息
    @ResponseBody
    @RequestMapping("/findTagById")
    public Map<String,Object> findTagById(Integer id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		WxTag wxTag=wxTagService.findById(id);
		if(wxTag!=null){
			map.put("TAG", wxTag);
			map.put("CODE", "200");
		}else{
			map.put("CODE", "10001");
		}
    	return map;
    }
    
} 