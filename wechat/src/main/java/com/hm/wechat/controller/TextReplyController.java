package com.hm.wechat.controller;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.WxKeywords;
import com.hm.domain.WxText;
import com.hm.service.WxKeywordsService;
import com.hm.service.WxTextService;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/reply")
public class TextReplyController extends BaseCotroller{ 
	
	@Autowired
	WxKeywordsService wxKeywordsService;
	
	@Autowired
	WxTextService wxTextService;
	
	//跳转图文回复规则列表
    @RequestMapping("/textReply")
  	public ModelAndView textReply() {
    	ModelAndView mav = new ModelAndView("/page/wxTextReply");
    	return mav;
  	}
    
    //获取消息回复规则列表json数据
    @ResponseBody
	@RequestMapping("/showTextReplyListJson")
	public Map<String,Object> showTextReplyListJson() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map<String, Object>> wxKeywordsList = wxKeywordsService.findKeywordsList(getSessionAccounts().getId(), 0, search,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = wxKeywordsService.findKeywordsCount(getSessionAccounts().getId(), 0, search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", wxKeywordsList); 
		map.put("total", count);
		return map;
	}
    
    //添加/修改消息回复规则
    @RequestMapping("/editTextReply")
	public ModelAndView editTextReply(WxKeywords wxKeywords, String content) {
		ModelAndView mav = new ModelAndView("redirect:/reply/textReply");
		wxKeywords.setAccountId(getSessionAccounts().getId());
		wxKeywords.setType(0);
		if(wxKeywords.getId()!=null) {
			wxKeywordsService.update(wxKeywords);
		}else {
			wxKeywordsService.save(wxKeywords);	
		}
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
		return mav; 
	}
    
    //删除消息回复规则数据
    @RequestMapping("/deleteTextReply")
	public ModelAndView deleteTextReply(Integer id) {
    	ModelAndView mav = new ModelAndView("redirect:/reply/textReply");
    	wxKeywordsService.delete(id);
    	return mav;
	}
    
    //查询关键字是否存在
    @ResponseBody
    @RequestMapping("/findKeywords")
    public Map<String,Object> findKeywords(Integer id,String keywords) {
		HashMap<String, Object> map = new HashMap<String, Object>();
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
    	return map;
    }
    
} 