package com.hm.carService.controller;  

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.carService.domain.Information;
import com.hm.carService.service.InformationService;
import com.hm.domain.OfficialAccounts;
   
@Controller
@RequestMapping("/carService")
public class InformationController extends BaseCotroller{ 
	
	@Autowired
	InformationService informationService;
	
	//4s店基本信息
	@RequestMapping("/home")
	public ModelAndView home(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView("/carService/home");
		Information information = informationService.findBySoftwareId(getSessionSoftware().getId());
		mv.addObject("information", information);
		return mv;
	}
	
	//4s店基本信息
	@RequestMapping("/edit")
	public ModelAndView edit(Information information) {
		ModelAndView mv = new ModelAndView("/carService/home");
		Information oldInformation = informationService.findBySoftwareId(getSessionSoftware().getId());
		information.setSoftwareId(getSessionSoftware().getId());
		if(oldInformation==null) {
			informationService.save(information);
		}else {
			informationService.update(information);
		}
		mv.addObject("information", information);
		return mv;
	}
 
}