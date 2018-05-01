package com.hm.wechat.controller;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.WxKeywords;
import com.hm.domain.WxNews;
import com.hm.domain.WxText;
import com.hm.service.WxKeywordsService;
import com.hm.service.WxNewsService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.ImageUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/reply")
public class ImageReplyController extends BaseCotroller{ 
	
	@Autowired
	WxKeywordsService wxKeywordsService;
	
	@Autowired
	WxNewsService wxNewsService;
	
	//跳转图文回复规则列表
    @RequestMapping("/imageReply")
  	public ModelAndView imageReply() {
    	ModelAndView mav = new ModelAndView("/page/wxImageReply");
    	return mav;
  	}
    
    //获取消息回复规则列表json数据
    @ResponseBody
	@RequestMapping("/showImageReplyListJson")
	public Map<String,Object> showImageReplyListJson() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map<String, Object>> wxKeywordsList = wxKeywordsService.findKeywordsList(getSessionAccounts().getId(), 1, search,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = wxKeywordsService.findKeywordsCount(getSessionAccounts().getId(), 1, search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", wxKeywordsList); 
		map.put("total", count);
		return map;
	}
    
    //添加/修改消息回复规则
    @RequestMapping("/editImageKeyword")
	public ModelAndView editImageKeyword(WxKeywords wxKeywords) {
		ModelAndView mav = new ModelAndView("redirect:/reply/imageReply");
		wxKeywords.setAccountId(getSessionAccounts().getId());
		wxKeywords.setType(1);
		if(wxKeywords.getId()!=null) {
			wxKeywordsService.update(wxKeywords);
		}else {
			wxKeywordsService.save(wxKeywords);	
		}
		return mav; 
	}
    
    //删除消息回复规则数据
    @RequestMapping("/deleteImageKeyword")
	public ModelAndView deleteImageKeyword(Integer id) {
    	ModelAndView mav = new ModelAndView("redirect:/reply/imageReply");
    	wxKeywordsService.delete(id);
    	return mav;
	}
    
    //跳转图文回复内容列表
    @RequestMapping("/imageReplyInfo")
  	public ModelAndView imageReplyInfo(Integer wxKeywordsId) {
    	ModelAndView mav = new ModelAndView("/page/wxNews");
    	if(wxKeywordsId==null) {
    		mav.setViewName("redirect:/reply/imageReply");
    	}else {
    		List<WxNews> newsList = wxNewsService.findList(wxKeywordsId);
    		mav.addObject("newsList", newsList);
    		mav.addObject("wxKeywordsId", wxKeywordsId);
    	}
    	return mav;
  	}
    
    //添加/修改图文回复规则
    @RequestMapping("/editReplyInfo")
	public ModelAndView editReplyInfo(MultipartFile imageFile, WxNews wxNews) {
		ModelAndView mav = new ModelAndView("redirect:/reply/imageReplyInfo");
		if(imageFile!=null) {
			String fileName = ImageUtil.uploadImage(imageFile);
			wxNews.setPicUrl(ApplicationUtil.IMAGE_PREFIX+fileName);
		}
		if(wxNews.getId() != null) {
			wxNewsService.update(wxNews);
		}else {
			wxNewsService.save(wxNews);	
		}
		mav.addObject("wxKeywordsId", wxNews.getKeywordsId());
		return mav; 
	}
    
    //删除图文内容数据
    @RequestMapping("/deleteReplyInfo")
	public ModelAndView deleteReplyInfo(Integer id, Integer wxKeywordsId) {
		ModelAndView mav = new ModelAndView("redirect:/reply/imgaeReplyInfo");
		mav.addObject("wxKeywordsId", wxKeywordsId);
		wxNewsService.delete(id);
    	return mav;
	}
    
    //根据id查询图文内容数据
    @ResponseBody
    @RequestMapping("/findImageReply")
    public Map<String,Object> findImageReply(Integer id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		WxNews wxNews=wxNewsService.find(id);
		if(wxNews!=null){
			map.put("news", wxNews);
			map.put("CODE", "200");
		}else{
			map.put("CODE", "10001");
		}
    	return map;
    }
    
} 