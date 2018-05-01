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
import com.hm.carService.domain.Subdivision;
import com.hm.carService.service.CarService;
import com.hm.carService.service.SubdivisionService;
import com.hm.utils.StringUtil;
   
@Controller
@RequestMapping("/carService")
public class SubdivisionController extends BaseCotroller{ 
	
	@Autowired
	CarService carService;
	
	@Autowired
	SubdivisionService subdivisionService;
	
	//车辆细分列表页
	@RequestMapping("/subdivisionList")
	public ModelAndView list(Integer carId) {
		ModelAndView mv = new ModelAndView("/carService/subdivision");
		mv.addObject("carId",carId);
		return mv;
	}
	
	 //获取车辆细分列表json数据
    @ResponseBody
	@RequestMapping("/showSubdivisionListJson")
	public Map<String,Object> showListJson(Integer carId) {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map> carList = subdivisionService.findList(carId, search,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = subdivisionService.findCount(carId, search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", carList); 
		map.put("total", count);
		return map;
	}
    
    //编辑售卖车辆
    @RequestMapping("/editSubdivision")
    public ModelAndView edit(Subdivision subdivision) {
    	ModelAndView mv = new ModelAndView("redirect:/carService/subdivisionList?carId="+subdivision.getCarId());
    	if(subdivision.getId()==null) {
			subdivisionService.save(subdivision);
		}else {
			subdivisionService.update(subdivision);
		}
    	return mv;
    }
    
    //删除分组信息
    @RequestMapping("/deleteSubdivision")
    public ModelAndView delete(Integer carId, Integer id) {
    	ModelAndView mv = new ModelAndView("redirect:/carService/subdivisionList?carId="+carId);
    	subdivisionService.deleteById(id);
    	return mv;
    }
 
}