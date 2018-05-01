package com.hm.controller;  
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.WxKeywords;
import com.hm.domain.WxNews;
import com.hm.domain.WxSystemReply;
import com.hm.domain.WxText;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxKeywordsService;
import com.hm.service.WxNewsService;
import com.hm.service.WxSystemReplyService;
import com.hm.service.WxTextService;
import com.hm.utils.StringUtil;
   

@Controller
public class WxMessageReplyController extends BaseCotroller{ 
	
    @Resource  
    private WxGlobalParamService wxGlobalParamService;
    
    @Resource
    private WxKeywordsService wxKeywordsService;
    
    @Resource
    private WxNewsService wxNewsService;
    
    @Resource
    private WxTextService wxTextService;
    
    @Resource
    private WxSystemReplyService wxSystemReplyService;
    
    private static Logger infologger = Logger.getLogger("dayinfo");
    
    //跳转图文回复规则列表
    @RequestMapping("/wx/textkey")
  	public ModelAndView wxTextKey() {
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			mav.addObject("type", 0);
    			mav.setViewName("/wxKeywords");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //跳转图文回复规则列表
    @RequestMapping("/wx/newskey")
  	public ModelAndView wxNewsKey() {
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			mav.addObject("type", 1);
    			mav.setViewName("/wxKeywords");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //获取消息回复规则列表json数据
    @ResponseBody
	@RequestMapping("showKeywordsListJson")
	public Map<String,Object> showNewsKeyListJson() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		String type = request.getParameter("type");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map<String, Object>> wxKeywordsList = wxKeywordsService.findKeywordsList(getSessionAccounts().getId(), Integer.parseInt(type), search,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = wxKeywordsService.findKeywordsCount(getSessionAccounts().getId(), Integer.parseInt(type), search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", wxKeywordsList); 
		map.put("total", count);
		return map;
	}
    
    //添加/修改消息回复规则
    @RequestMapping("/editKeywords")
	public ModelAndView editNewsKey(WxKeywords wxKeywords, String content) {
		ModelAndView mav = new ModelAndView();
		if(wxKeywords.getId() != null) {
			wxKeywords.setAccountId(getSessionAccounts().getId());
			wxKeywordsService.update(wxKeywords);
			if(!StringUtil.isEmpty(content)){
				WxText wxText = wxTextService.findBykeywordsId(wxKeywords.getId());
				if(wxText!=null){
					wxText.setContent(content);
					wxTextService.update(wxText);
				}else{
					wxText = new WxText();
					wxText.setKeywordsId(wxKeywords.getId());
					wxText.setContent(content);
					wxTextService.save(wxText);
				}
			}
		}else {
			wxKeywords.setAccountId(getSessionAccounts().getId());
			wxKeywordsService.save(wxKeywords);	
			if(!StringUtil.isEmpty(content)){
				WxText wxText = wxTextService.findBykeywordsId(wxKeywords.getId());
				if(wxText!=null){
					wxText.setContent(content);
					wxTextService.update(wxText);
				}else{
					wxText = new WxText();
					wxText.setKeywordsId(wxKeywords.getId());
					wxText.setContent(content);
					wxTextService.save(wxText);
				}
			}
		}
		if(wxKeywords.getType()==0){
			mav.setViewName("redirect:/wx/textkey");
		}else if(wxKeywords.getType()==1){
			mav.setViewName("redirect:/wx/newskey");
		}else if(wxKeywords.getType()==2){
			//TODO
		}
		return mav; 
	}
    
    //删除消息回复规则数据
    @RequestMapping("/deleteKeywords")
	public ModelAndView deleteKeywords(Integer id,Integer type) {
    	ModelAndView mav = new ModelAndView();
		if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
				wxKeywordsService.delete(id);
				if(type==0){
					mav.setViewName("redirect:/wx/textkey");
				}else if(type==1){
					mav.setViewName("redirect:/wx/newskey");
				}else if(type==2){
					//TODO
				}
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
	}
    
    //跳转图文回复内容列表
    @RequestMapping("/wx/newskey/news")
  	public ModelAndView wxNews(Integer wxKeywordsId, HttpSession httpSession) {
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			if(wxKeywordsId!=null){
    				httpSession.setAttribute("wxKeywordsId", wxKeywordsId);
    			}else{
    				wxKeywordsId = (Integer) httpSession.getAttribute("wxKeywordsId");
    			}
    			List<WxNews> newsList = wxNewsService.findList(wxKeywordsId);
    			mav.addObject("newsList", newsList);
				mav.setViewName("/wxNews");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //添加/修改图文回复规则
    @RequestMapping("/editNews")
	public ModelAndView editNews(WxNews wxNews, HttpSession httpSession) {
		ModelAndView mav = new ModelAndView("redirect:/wx/newskey/news");
		if(wxNews.getId() != null) {
			wxNews.setKeywordsId((Integer) httpSession.getAttribute("wxKeywordsId"));
			wxNewsService.update(wxNews);
		}else {
			wxNews.setKeywordsId((Integer) httpSession.getAttribute("wxKeywordsId"));
			wxNewsService.save(wxNews);	
		}
		return mav; 
	}
    
    //删除图文内容数据
    @RequestMapping("/deleteNews")
	public ModelAndView deleteNews(Integer id) {
		ModelAndView mav = new ModelAndView("redirect:/wx/newskey/news");
		if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			wxNewsService.delete(id);
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
	}
    
    //根据id查询图文内容数据
    @ResponseBody
    @RequestMapping("/findNewsById")
    public Map<String,Object> findTagById(Integer id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			WxNews wxNews=wxNewsService.find(id);
    			if(wxNews!=null){
    				map.put("news", wxNews);
    				map.put("code", "success");
    			}else{
    				map.put("code", "fail");
    			}
    		}else{
    			map.put("code", "fail");
    		}
		}else{
			map.put("code", "fail");
		}
    	return map;
    }
    
    //跳转系统回复列表
    @RequestMapping("/wx/reply")
  	public ModelAndView wxReply() {
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			WxSystemReply joinReply = wxSystemReplyService.find(getSessionAccounts().getId(), 0);
    			WxSystemReply defaultReply = wxSystemReplyService.find(getSessionAccounts().getId(), 1);
    			if(joinReply==null){
    				mav.addObject("joinKey", "");
    			}else{
    				mav.addObject("joinKey", joinReply.getKeywords());
    			}
    			if(defaultReply==null){
    				mav.addObject("defaultKey", "");
    			}else{
    				mav.addObject("defaultKey", defaultReply.getKeywords());
    			}
    			List<Map<String, Object>> keywordsList = wxKeywordsService.findList(getSessionAccounts().getId());
    			mav.addObject("keywordsList", keywordsList);
    			mav.setViewName("/wxReply");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //编辑系统回复信息
    @RequestMapping("/editReply")
  	public ModelAndView editReply() {
    	String joinKey = request.getParameter("joinKey");
    	String defaultKey = request.getParameter("defaultKey");
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			WxSystemReply joinReply = wxSystemReplyService.find(getSessionAccounts().getId(), 0);
    			WxSystemReply defaultReply = wxSystemReplyService.find(getSessionAccounts().getId(), 1);
    			if(joinReply==null){
    				joinReply = new WxSystemReply();
    				joinReply.setAccountId(getSessionAccounts().getId());
    				joinReply.setType(0);
    				joinReply.setKeywords(joinKey);
    				wxSystemReplyService.save(joinReply);
    			}else{
    				joinReply.setKeywords(joinKey);
    				wxSystemReplyService.update(joinReply);
    			}
    			if(defaultReply==null){
    				defaultReply = new WxSystemReply();
    				defaultReply.setAccountId(getSessionAccounts().getId());
    				defaultReply.setType(1);
    				defaultReply.setKeywords(defaultKey);
    				wxSystemReplyService.save(defaultReply);
    			}else{
    				defaultReply.setKeywords(defaultKey);
    				wxSystemReplyService.update(defaultReply);
    			}
    			mav.setViewName("redirect:/wx/reply");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //查询关键字是否存在
    @ResponseBody
    @RequestMapping("/findInfoByKeywords")
    public Map<String,Object> findInfoByKeywords(Integer id,String keywords) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			WxKeywords wxKeywords = wxKeywordsService.findByKeywords(getSessionAccounts().getId(), keywords.trim());
    			if(wxKeywords!=null){
    				if(wxKeywords.getId().equals(id)){
    					map.put("code", "N");
    				}else{
    					map.put("code", "Y");
    				}
    			}else{
    				map.put("code", "N");
    			}
    		}else{
    			map.put("code", "fail");
    		}
		}else{
			map.put("code", "fail");
		}
    	return map;
    }
    
} 