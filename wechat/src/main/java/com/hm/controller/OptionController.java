package com.hm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.EnterpriseUserInfo;
import com.hm.utils.WxApiUtil;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

@Controller
public class OptionController extends BaseCotroller{
	@ResponseBody
	@RequestMapping("/option")
    public String save(){
    	EnterpriseUserInfo euserInfo = (EnterpriseUserInfo) request.getSession().getAttribute("euserInfo");
    	String contents = request.getParameter("contents");
    	Map map = new HashMap();
    	map.put("euserId", euserInfo.getId().toString());
    	map.put("contents", contents);
    	map.put("stage", "4");
    	JSON json = JSONSerializer.toJSON(map);
 //   	String saveOption = WxApiUtil.saveOption(json.toString());
		return "";
    	
    }
}
