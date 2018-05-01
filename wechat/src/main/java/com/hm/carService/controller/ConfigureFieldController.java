package com.hm.carService.controller;  

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.carService.domain.ConfigureField;
import com.hm.carService.service.ConfigureFieldService;
import com.hm.utils.StringUtil;
   
@Controller
@RequestMapping("/carService")
public class ConfigureFieldController extends BaseCotroller{ 
	
	@Autowired
	ConfigureFieldService configureFieldService;
	
	//车辆字段配置
	@RequestMapping("/fieldList")
	public ModelAndView fieldList() {
		ModelAndView mv = new ModelAndView("/carService/fieldList");
		return mv;
	}
	
	//获取车辆字段配置列表json数据
    @ResponseBody
	@RequestMapping("/showFieldListJson")
	public Map<String,Object> showListJson() {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<ConfigureField> fieldList = configureFieldService.findList(getSessionSoftware().getId(), search,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = configureFieldService.findCount(getSessionSoftware().getId(), search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", fieldList); 
		map.put("total", count);
		return map;
	}
	
	//编辑字段配置
	@RequestMapping("/editField")
	public ModelAndView edit(ConfigureField field) {
		ModelAndView mv = new ModelAndView("redirect:/carService/fieldList");
		field.setSoftwareId(getSessionSoftware().getId());
		if(field.getId()==null) {
			configureFieldService.save(field);
		}else {
			configureFieldService.update(field);
		}
		mv.addObject("configureField", field);
		return mv;
	}
	
	//删除字段配置
	@RequestMapping("/deleteField")
	public ModelAndView delete(Integer id) {
		ModelAndView mv = new ModelAndView("redirect:/carService/fieldList");
		configureFieldService.deleteById(id);
		return mv;
	}
 
}