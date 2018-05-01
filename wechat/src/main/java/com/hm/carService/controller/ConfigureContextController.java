package com.hm.carService.controller;  

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.carService.domain.ConfigureContext;
import com.hm.carService.domain.ConfigureField;
import com.hm.carService.domain.Information;
import com.hm.carService.service.ConfigureContextService;
import com.hm.carService.service.ConfigureFieldService;
import com.hm.carService.service.InformationService;
import com.hm.domain.OfficialAccounts;
import com.hm.utils.StringUtil;
   
@Controller
@RequestMapping("/carService")
public class ConfigureContextController extends BaseCotroller{ 
	
	@Autowired
	ConfigureContextService configureContextService;
	
	@Autowired
	ConfigureFieldService configureFieldService;
	
	//车辆字段页面
	@RequestMapping("/configure")
	public ModelAndView configure(Integer subdivisionId) {
		ModelAndView mv = new ModelAndView("/carService/configure");
		List<ConfigureField> configureList = configureFieldService.findAllList(getSessionSoftware().getId());
		mv.addObject("subdivisionId",subdivisionId);
		mv.addObject("configureList",configureList);
		return mv;
	}
	
	//获取车辆字段配置列表json数据
    @ResponseBody
	@RequestMapping("/showConfigureListJson")
	public Map<String,Object> showListJson(Integer subdivisionId) {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map> fieldList = configureContextService.findList(subdivisionId, search,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = 0;
		Map infoMap = configureContextService.findCount(subdivisionId, search);
		count = Integer.valueOf(infoMap.get("count").toString());
		Integer level = 0;
		if(infoMap.get("level")!=null) {
			level = Integer.valueOf(infoMap.get("level").toString());
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", fieldList); 
		map.put("total", count);
		map.put("level", level);
		return map;
	}
	
	//编辑字段配置
	@RequestMapping("/editConfigure")
	public ModelAndView edit(ConfigureContext context, Integer type) {
		ModelAndView mv = new ModelAndView("redirect:/carService/configure?subdivisionId="+context.getSubdivisionId());
		if(type==1) {
			context.setContext("");
		}
		if(context.getId()==null) {
			configureContextService.save(context);
		}else {
			configureContextService.update(context);
		}
		return mv;
	}
	
	//删除字段配置
	@RequestMapping("/deleteConfigure")
	public ModelAndView delete(Integer subdivisionId, Integer id) {
		ModelAndView mv = new ModelAndView("redirect:/carService/configure?subdivisionId="+subdivisionId);
		configureContextService.deleteById(id);
		return mv;
	}
	
	//同步配置字段
	@RequestMapping("/synConfigureField")
	public ModelAndView synConfigureField(Integer subdivisionId) {
		ModelAndView mv = new ModelAndView("redirect:/carService/configure?subdivisionId="+subdivisionId);
		configureContextService.synConfigureField(subdivisionId, getSessionSoftware().getId());
		return mv;
	}
}