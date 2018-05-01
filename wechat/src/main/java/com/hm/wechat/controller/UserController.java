package com.hm.wechat.controller;  
  
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.WxTag;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxTagService;
import com.hm.service.WxUserService;
import com.hm.utils.BaseUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller{ 
	
    @Resource  
    private WxGlobalParamService wxGlobalParamService;
    
    @Resource  
    private WxTagService wxTagService;
    
    @Resource  
    private WxUserService wxUserService;
    
    private static Logger infologger = Logger.getLogger("dayinfo");
    
    //获取微信用户标签列表
    @RequestMapping("/list")
  	public ModelAndView list() {
    	ModelAndView mav = new ModelAndView();
    	if(getSessionAccounts()!=null) {
    		Integer count = wxUserService.findUserCount(getSessionAccounts().getId(), null, null);
    		List<WxTag> tagList = wxTagService.findList(getSessionAccounts().getId());
    		//首次加载
    		if(count==0) {
    			wxUserService.loadUser(getSessionAccounts().getId(), getAccessToken());
    			tagList = wxTagService.loadTagList(getSessionAccounts().getId(), getAccessToken());
    		}
    		mav.addObject("tagList", tagList);
    		mav.setViewName("/page/wxUsers");

    	}else {
    		mav.setViewName("redirect:/account/home");
    	}
    	return mav;
  	}
    
    //获取用户列表json数据
    @ResponseBody
	@RequestMapping("showWxUserListJson")
	public Map<String,Object> showGroupListJson(Integer tagId) {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map<String, Object>> wxUserList = wxUserService.findUserList(getSessionAccounts().getId(), tagId, search,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = wxUserService.findUserCount(getSessionAccounts().getId(), tagId, search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", wxUserList); 
		map.put("total", count);
		return map;
	}
    
    //为用户打标签
    @RequestMapping("/addTags")
  	public ModelAndView addTags() {
    	//被打标签的用户openId
    	String openidStr = request.getParameter("openidStr");
    	//要打的标签Id数组
    	String[] tagIdArray = request.getParameterValues("tagIdStr");
    	ModelAndView mav = new ModelAndView();
    	if(getSessionAccounts()!=null) {
			if(!StringUtil.isEmpty(openidStr)) {
				JSONArray jsonb = JSONArray.fromObject(openidStr);
				if(jsonb.size()>1) {
					for(int i=0;i<tagIdArray.length;i++) {
						List openIdList = new ArrayList();
						for(int n=0;n<jsonb.size();n++) {
							Map otMap = (Map) jsonb.get(n);
							String tagIdStr = ","+otMap.get("tageIds").toString()+",";
							if(tagIdStr.indexOf(","+tagIdArray[i]+",")==-1) {
								openIdList.add(otMap.get("openId").toString());
							}
						}
						wxTagService.updateTag(tagIdArray[i], openIdList);
						wxTagService.addTag(getAccessToken(), openidStr.split(","), tagIdArray[i].split(","));
					}
				}
				if(jsonb.size()==1) {
					Map otMap = (Map) jsonb.get(0);
					if(tagIdArray==null) {
						tagIdArray = new String[1];
					}
					wxTagService.updateSingleTag(BaseUtil.ArryToString(tagIdArray), otMap.get("openId").toString());
					String[] clearArr = BaseUtil.defferenceArray(otMap.get("tageIds").toString().split(","), tagIdArray);
					wxTagService.clearTag(getAccessToken(), otMap.get("openId").toString().split(","), clearArr);
				}
			}
			mav.setViewName("redirect:/user/list");
		}else{
			mav.setViewName("redirect:/account/home");
		}
    	return mav;
  	}
    
    //同步所有微信用户标签
    @RequestMapping("/synchronizeTags")
  	public ModelAndView synchronizeTags() {
    	ModelAndView mav = new ModelAndView();
		if(getSessionAccounts()!=null) {
			wxTagService.loadTagList(getSessionAccounts().getId(), getAccessToken());
			mav.setViewName("/page/wxUsers");
		}else{
			mav.setViewName("redirect:/account/home");
		}
    	return mav;
  	}
    
    //同步所有微信用户
	@RequestMapping("/synchronizeUsers")
  	public ModelAndView synchronizeUsers() {
    	ModelAndView mav = new ModelAndView();
		if(getSessionAccounts()!=null) {
			wxUserService.loadUser(getSessionAccounts().getId(), getAccessToken());
			mav.setViewName("/page/wxUsers");
		}else{
			mav.setViewName("redirect:/account/home");
		}
    	return mav;
  	}
    
} 