package com.hm.controller;  

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.StringUtil;
import com.hm.base.controller.BaseCotroller;
import com.hm.domain.Software;
import com.hm.service.SoftwareService;
   
@Controller
@RequestMapping("/software")
public class SoftwareController extends BaseCotroller{ 
	
	@Autowired
	SoftwareService softwareService;
	
	@RequestMapping("/home")
	public ModelAndView home(HttpSession httpSession) {
		ModelAndView mv = new ModelAndView("/page/softwareHome");
		return mv;
	}
	
	@RequestMapping("/toSoftware")
	public ModelAndView toSoftware(HttpSession httpSession, String name, Integer type) {
		ModelAndView mv = new ModelAndView();
		if(!StringUtil.isEmpty(name)&&name.equals("carService")) {
			Software software = softwareService.findByEuserId(getSessionPassport().getId(), type);
			if(software!=null&&software.getEuserId().equals(getSessionPassport().getId())){
				httpSession.setAttribute("software", software);
				mv.setViewName("redirect:/carService/home");
			}else if(software==null) {
				software = new Software();
				software.setType(type);
				software.setEuserId(getSessionPassport().getId());
				softwareService.save(software);
				httpSession.setAttribute("software", software);
				mv.setViewName("redirect:/carService/home");
			}else {
				mv.setViewName("redirect:/software/home");
			}
		}else {
			mv.setViewName("redirect:/software/home");
		}
		return mv;
	}
 
}