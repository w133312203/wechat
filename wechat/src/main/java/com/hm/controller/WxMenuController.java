package com.hm.controller;  
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.WxGlobalParam;
import com.hm.domain.WxMenu;
import com.hm.domain.WxSubMenu;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxKeywordsService;
import com.hm.service.WxMenuService;
import com.hm.service.WxSubMenuService;
import com.hm.utils.AccessTokenUtil;
import com.hm.utils.StringUtil;
import com.hm.utils.WxApiUtil;
   

@Controller
public class WxMenuController extends BaseCotroller{ 
	
    @Resource  
    private WxGlobalParamService wxGlobalParamService;
    
    @Resource  
    private WxMenuService wxMenuService;
    
    @Resource  
    private WxSubMenuService wxSubMenuService;
    
    @Resource  
    private WxKeywordsService wxKeywordsService;
    
    private static Logger infologger = Logger.getLogger("dayinfo");
    
    @RequestMapping("/wx/menu")
  	public ModelAndView wxMenu() {
    	ModelAndView mav = new ModelAndView("/wxMenu");
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			Map<String, Object> btnMap = new HashMap<String, Object>();
    			List<Map<String, Object>> menuList = wxMenuService.findList(getSessionAccounts().getId());
    				for (Map<String, Object> menuMap : menuList) {
    					List<Map<String, Object>> subMenuList = wxSubMenuService.findList(Integer.parseInt(menuMap.get("id").toString()));
    					menuMap.put("sub_button", subMenuList);
    				}
        			btnMap.put("button", menuList);
        			btnMap.put("title", getSessionAccounts().getName());
        			btnMap.put("type", menuList.size());
        			JSON menuJson =JSONSerializer.toJSON(btnMap);
        			mav.addObject("menuJson", menuJson);
    			List<Map<String, Object>> keywordsList = wxKeywordsService.findList(getSessionAccounts().getId());
    			mav.addObject("keywordsList", keywordsList);
    			mav.setViewName("/wxMenu");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
    	}else {
			mav.setViewName("redirect:/login");
		}
		return mav; 	
    }
    
    @RequestMapping("/editMenu")
  	public ModelAndView editMenu() {
    	String menuJsonStr = request.getParameter("menuJsonStr");
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			if(!StringUtil.isEmpty(menuJsonStr)){
    				AccessTokenUtil.checkAccessToken(getSessionAccounts());
    				WxGlobalParam param = wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    				JSONObject menuobj = JSONObject.fromObject(menuJsonStr);
    				JSONArray jsona = JSONArray.fromObject(menuobj.get("button"));
    				if(jsona!=null&&jsona.size()>0){
    					String errmsg = WxApiUtil.createMenu(param.getAccessToken(), menuJsonStr);
        		    	if("ok".equals(errmsg)){
        		    		wxMenuService.deleteList(getSessionAccounts().getId());
            				wxSubMenuService.deleteList(getSessionAccounts().getId());
            	    		JSONObject jsonobj = JSONObject.fromObject(menuJsonStr);
            	    		JSONArray btnArr = jsonobj.getJSONArray("button");
            	    		for (int i = 0; i < btnArr.size(); i++) {
            	    			JSONObject btnObj = btnArr.getJSONObject(i);
        	    				JSONArray subBtnArr = btnObj.getJSONArray("sub_button");
            	    			if(subBtnArr==null||subBtnArr.size()==0){
            	    				WxMenu wxMenu = new WxMenu();
            	    				wxMenu.setAccountId(getSessionAccounts().getId());
            	    				wxMenu.setName(btnObj.getString("name"));
            	    				wxMenu.setType(btnObj.getString("type"));
            	    				if("click".equals(btnObj.getString("type"))){
            	    					wxMenu.setKey(btnObj.getString("key"));
            	    				}else if("view".equals(btnObj.getString("type"))){
            	    					wxMenu.setUrl(btnObj.getString("url"));
            	    				}else if("miniprogram".equals(btnObj.getString("type"))){
            	    					wxMenu.setUrl(btnObj.getString("url"));
            	    					wxMenu.setAppid(btnObj.getString("appid"));
            	    					wxMenu.setPagepath(btnObj.getString("pagepath"));
            	    				}
            	    				wxMenuService.save(wxMenu);
            	    			}else{
            	    				WxMenu wxMenu = new WxMenu();
            	    				wxMenu.setAccountId(getSessionAccounts().getId());
            	    				wxMenu.setName(btnObj.getString("name"));
            	    				wxMenuService.save(wxMenu);
            	    				
            	    				for (int j = 0; j < subBtnArr.size(); j++) {
            	    					JSONObject subBtnObj = subBtnArr.getJSONObject(j);
            							WxSubMenu wxSubMenu = new WxSubMenu();
            							wxSubMenu.setAccountId(getSessionAccounts().getId());
            							wxSubMenu.setMenuId(wxMenu.getId());
            							wxSubMenu.setName(subBtnObj.getString("name"));
            							wxSubMenu.setType(subBtnObj.getString("type"));
            		    				if("click".equals(subBtnObj.getString("type"))){
            		    					wxSubMenu.setKey(subBtnObj.getString("key"));
            		    				}else if("view".equals(subBtnObj.getString("type"))){
            		    					wxSubMenu.setUrl(subBtnObj.getString("url"));
            		    				}else if("miniprogram".equals(subBtnObj.getString("type"))){
            		    					wxSubMenu.setUrl(subBtnObj.getString("url"));
            		    					wxSubMenu.setAppid(subBtnObj.getString("appid"));
            		    					wxSubMenu.setPagepath(subBtnObj.getString("pagepath"));
            		    				}
            		    				wxSubMenuService.save(wxSubMenu);
            						}
            	    			}
            				}
        		    	}
    				}else{
    					String errmsg=WxApiUtil.deleteMenu(param.getAccessToken());
    			    	if("ok".equals(errmsg)){
    			    		wxMenuService.deleteList(getSessionAccounts().getId());
    			    		wxSubMenuService.deleteList(getSessionAccounts().getId());
    			    	}
    				}
    	    	}
    			mav.setViewName("redirect:/wx/menu");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
    	}else {
			mav.setViewName("redirect:/login");
		}
		return mav;
    }
    
    //创建自定义菜单
    @RequestMapping("/wx/menu/create")
  	public ModelAndView createMenu(String menuJson) {
    	ModelAndView mav = new ModelAndView();
    	AccessTokenUtil.checkAccessToken(getSessionAccounts());
    	WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    	String errmsg=WxApiUtil.createMenu(param.getAccessToken(), menuJson);
    	if("ok".equals(errmsg)){
    		wxMenuService.deleteList(getSessionAccounts().getId());
    		wxSubMenuService.deleteList(getSessionAccounts().getId());
    		JSONObject jsonobj = JSONObject.fromObject(menuJson);
    		JSONArray btnArr = jsonobj.getJSONArray("button");
    		for (int i = 0; i < btnArr.size(); i++) {
    			JSONObject btnObj = btnArr.getJSONObject(i);
    			if(!btnObj.has("sub_button")){
    				WxMenu wxMenu = new WxMenu();
    				wxMenu.setAccountId(getSessionAccounts().getId());
    				wxMenu.setName(btnObj.getString("name"));
    				wxMenu.setType(btnObj.getString("type"));
    				if("click".equals(btnObj.getString("type"))){
    					wxMenu.setKey(btnObj.getString("key"));
    				}else if("view".equals(btnObj.getString("type"))){
    					wxMenu.setUrl(btnObj.getString("url"));
    				}else if("miniprogram".equals(btnObj.getString("type"))){
    					wxMenu.setUrl(btnObj.getString("url"));
    					wxMenu.setAppid(btnObj.getString("appid"));
    					wxMenu.setPagepath(btnObj.getString("pagepath"));
    				}
    				wxMenuService.save(wxMenu);
    			}else{
    				WxMenu wxMenu = new WxMenu();
    				wxMenu.setAccountId(getSessionAccounts().getId());
    				wxMenu.setName(btnObj.getString("name"));
    				wxMenuService.save(wxMenu);
    				
    				JSONArray subBtnArr = btnObj.getJSONArray("sub_button");
    				for (int j = 0; j < subBtnArr.size(); j++) {
    					JSONObject subBtnObj = subBtnArr.getJSONObject(j);
						WxSubMenu wxSubMenu = new WxSubMenu();
						wxSubMenu.setAccountId(getSessionAccounts().getId());
						wxSubMenu.setMenuId(wxMenu.getId());
						wxSubMenu.setName(subBtnObj.getString("name"));
						wxSubMenu.setType(subBtnObj.getString("type"));
	    				if("click".equals(subBtnObj.getString("type"))){
	    					wxSubMenu.setKey(subBtnObj.getString("key"));
	    				}else if("view".equals(subBtnObj.getString("type"))){
	    					wxSubMenu.setUrl(btnObj.getString("url"));
	    				}else if("miniprogram".equals(subBtnObj.getString("type"))){
	    					wxSubMenu.setUrl(subBtnObj.getString("url"));
	    					wxSubMenu.setAppid(subBtnObj.getString("appid"));
	    					wxSubMenu.setPagepath(subBtnObj.getString("pagepath"));
	    				}
	    				wxSubMenuService.save(wxSubMenu);
					}
    			}
			}
    		mav.setViewName("wxMenu");
    	}else{
    		mav.addObject("errmsg", errmsg);
    		mav.setViewName("wxError");
			mav.addObject("urlStr", "/wx/users");
    	}
		return mav;
  	}
    
    //调用微信自定义菜单查询接口，更新托管平台自定义菜单数据
    @RequestMapping("/wx/menu/get")
  	public ModelAndView getMenu() {
    	ModelAndView mav = new ModelAndView("redirect:/wx/menu");
    	AccessTokenUtil.checkAccessToken(getSessionAccounts());
    	WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    	String menuJson=WxApiUtil.getMenu(param.getAccessToken());
		JSONObject jsonobj = JSONObject.fromObject(menuJson);
		if(jsonobj.has("menu")){
			JSONObject menuObj = jsonobj.getJSONObject("menu");
			JSONArray btnArr = menuObj.getJSONArray("button");
			wxMenuService.deleteList(getSessionAccounts().getId());
			wxSubMenuService.deleteList(getSessionAccounts().getId());
			for (int i = 0; i < btnArr.size(); i++) {
				JSONObject btnObj = btnArr.getJSONObject(i);
				JSONArray subBtnArr = btnObj.getJSONArray("sub_button");
				if(subBtnArr==null||subBtnArr.size()==0){
					WxMenu wxMenu = new WxMenu();
					wxMenu.setAccountId(getSessionAccounts().getId());
					wxMenu.setName(btnObj.getString("name"));
					wxMenu.setType(btnObj.getString("type"));
					if("click".equals(btnObj.getString("type"))){
						wxMenu.setKey(btnObj.getString("key"));
					}else if("view".equals(btnObj.getString("type"))){
						wxMenu.setUrl(btnObj.getString("url"));
					}else if("miniprogram".equals(btnObj.getString("type"))){
						wxMenu.setUrl(btnObj.getString("url"));
						wxMenu.setAppid(btnObj.getString("appid"));
						wxMenu.setPagepath(btnObj.getString("pagepath"));
					}
					wxMenuService.save(wxMenu);
				}else{
					WxMenu wxMenu = new WxMenu();
					wxMenu.setAccountId(getSessionAccounts().getId());
					wxMenu.setName(btnObj.getString("name"));
					wxMenuService.save(wxMenu);
					
					for (int j = 0; j < subBtnArr.size(); j++) {
						JSONObject subBtnObj = subBtnArr.getJSONObject(j);
						WxSubMenu wxSubMenu = new WxSubMenu();
						wxSubMenu.setAccountId(getSessionAccounts().getId());
						wxSubMenu.setMenuId(wxMenu.getId());
						wxSubMenu.setName(subBtnObj.getString("name"));
						wxSubMenu.setType(subBtnObj.getString("type"));
	    				if("click".equals(subBtnObj.getString("type"))){
	    					wxSubMenu.setKey(subBtnObj.getString("key"));
	    				}else if("view".equals(subBtnObj.getString("type"))){
	    					wxSubMenu.setUrl(subBtnObj.getString("url"));
	    				}else if("miniprogram".equals(subBtnObj.getString("type"))){
	    					wxSubMenu.setUrl(subBtnObj.getString("url"));
	    					wxSubMenu.setAppid(subBtnObj.getString("appid"));
	    					wxSubMenu.setPagepath(subBtnObj.getString("pagepath"));
	    				}
	    				wxSubMenuService.save(wxSubMenu);
					}
				}
			}
		}
		return mav;
  	}
    
    //删除自定义菜单
    @RequestMapping("/wx/menu/delete")
  	public ModelAndView deleteMenu() {
    	ModelAndView mav = new ModelAndView();
    	AccessTokenUtil.checkAccessToken(getSessionAccounts());
    	WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    	String errmsg=WxApiUtil.deleteMenu(param.getAccessToken());
    	if("ok".equals(errmsg)){
    		wxMenuService.deleteList(getSessionAccounts().getId());
    		wxSubMenuService.deleteList(getSessionAccounts().getId());
    		mav.setViewName("wxMenu");
    	}else{
    		mav.addObject("errmsg", errmsg);
    		mav.setViewName("wxError");
			mav.addObject("urlStr", "/wx/users");
    	}
		return mav;
  	}
    
} 