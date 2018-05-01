package com.hm.carService.controller;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.carService.domain.CarGroup;
import com.hm.carService.service.CarGroupService;
   
@Controller
@RequestMapping("/carService")
public class CarGroupController extends BaseCotroller{ 
	
	@Autowired
	CarGroupService carGroupService;
    
    //编辑分组信息
    @RequestMapping("/editGroup")
    public ModelAndView editGroup(CarGroup carGroup) {
    	ModelAndView mv = new ModelAndView("redirect:/carService/list");
    	if(carGroup!=null) {
    		if(carGroup.getId()==null) {
    			carGroup.setSoftwareId(getSessionSoftware().getId());
    			carGroupService.save(carGroup);
    		}else {
    			carGroupService.update(carGroup);
    		}
    	}
    	return mv;
    }
    
    //删除分组信息
    @RequestMapping("/deleteGroup")
    public ModelAndView deleteGroup(Integer id) {
    	ModelAndView mv = new ModelAndView("redirect:/carService/list");
    	carGroupService.deleteById(id);
    	return mv;
    }
 
}