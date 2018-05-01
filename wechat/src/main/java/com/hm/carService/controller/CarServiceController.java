package com.hm.carService.controller;  

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.carService.domain.Car;
import com.hm.carService.domain.CarGroup;
import com.hm.carService.service.CarGroupService;
import com.hm.carService.service.CarService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.ImageUtil;
import com.hm.utils.StringUtil;
   
@Controller
@RequestMapping("/carService")
public class CarServiceController extends BaseCotroller{ 
	
	@Autowired
	CarService carService;
	
	@Autowired
	CarGroupService carGroupService;
	
	//售卖车辆列表页
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("/carService/carList");
		List<CarGroup> groupList = carGroupService.findList(getSessionSoftware().getId());
		mv.addObject("groupList", groupList);
		return mv;
	}
	
	//获取售卖车辆列表json数据
    @ResponseBody
	@RequestMapping("/showCarListJson")
	public Map<String,Object> showListJson(Integer groupId) {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<Map> carList = carService.findCarList(getSessionSoftware().getId(), groupId, search,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = carService.findCarCount(getSessionSoftware().getId(), groupId, search);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", carList); 
		map.put("total", count);
		return map;
	}
    
    //编辑售卖车辆
    @RequestMapping("/editCar")
    public ModelAndView editCar(MultipartFile imageFile, Car car) {
    	ModelAndView mv = new ModelAndView("redirect:/carService/list");
    	if(car!=null) {
    		if(imageFile!=null&&!StringUtil.isEmpty(imageFile.getOriginalFilename())) {
    			String fileName = ImageUtil.uploadImage(imageFile);
    			car.setHeadImage(ApplicationUtil.IMAGE_PREFIX+fileName);
    		}
    		if(car.getId()==null) {
    			car.setSoftwareId(getSessionSoftware().getId());
    			carService.save(car);
    		}else {
    			Car oldCar = carService.findById(car.getId());
    			car.setVideoImage(oldCar.getVideoImage());
    			car.setVideoUrl(oldCar.getVideoUrl());
    			carService.update(car);
    		}
    	}
    	return mv;
    }
    
    //编辑售卖车辆
    @RequestMapping("/editVideo")
    public ModelAndView editVideo(MultipartFile imageFile, Car car) {
    	ModelAndView mv = new ModelAndView("redirect:/carService/list");
    	if(car!=null) {
    		if(imageFile!=null&&!StringUtil.isEmpty(imageFile.getOriginalFilename())) {
    			String fileName = ImageUtil.uploadImage(imageFile);
    			car.setVideoImage(ApplicationUtil.IMAGE_PREFIX+fileName);
    		}
    		if(car.getId()==null) {
    			car.setSoftwareId(getSessionSoftware().getId());
    			carService.save(car);
    		}else {
    			if(!StringUtil.isEmpty(car.getVideoImage())||!StringUtil.isEmpty(car.getVideoUrl())) {
    				Car oldCar = carService.findById(car.getId());
    				if(!StringUtil.isEmpty(car.getVideoImage())) {
    					oldCar.setVideoImage(car.getVideoImage());
    				}
    				if(!StringUtil.isEmpty(car.getVideoUrl())) {
    					oldCar.setVideoUrl(car.getVideoUrl());
    				}
        			carService.update(oldCar);
    			}
    		}
    	}
    	return mv;
    }
    
    //删除分组信息
    @RequestMapping("/deleteCar")
    public ModelAndView delete(Integer id) {
    	ModelAndView mv = new ModelAndView("redirect:/carService/list");
    	carService.deleteById(id);
    	return mv;
    }
	
 
}