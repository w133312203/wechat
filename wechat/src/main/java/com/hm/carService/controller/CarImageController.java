package com.hm.carService.controller;  

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
import com.hm.carService.domain.CarImage;
import com.hm.carService.service.CarImageService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.ImageUtil;
import com.hm.utils.StringUtil;
   
@Controller
@RequestMapping("/carService")
public class CarImageController extends BaseCotroller{ 
	
	@Autowired
	CarImageService carImageService;
	
	//车辆字段配置
	@RequestMapping("/imageList")
	public ModelAndView imageList(Integer carId) {
		ModelAndView mv = new ModelAndView("/carService/imageList");
		mv.addObject("carId", carId);
		return mv;
	}
	
	//获取车辆字段配置列表json数据
    @ResponseBody
	@RequestMapping("/showImageListJson")
	public Map<String,Object> showListJson(Integer carId) {
		String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String search = request.getParameter("search");
		if(StringUtil.isEmpty(search)) {
			search="";
		}else{
			search="%"+search+"%";
		}
		List<CarImage> imageList = carImageService.findList(carId,Integer.parseInt(offset), Integer.parseInt(maxresult));
		Integer count = carImageService.findCount(carId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rows", imageList); 
		map.put("total", count);
		return map;
	}
	
	//编辑字段配置
	@RequestMapping("/editImage")
	public ModelAndView edit(MultipartFile imageFile, CarImage carImage) {
		ModelAndView mv = new ModelAndView("redirect:/carService/imageList?carId="+carImage.getCarId());
		if(imageFile!=null) {
			String fileName = ImageUtil.uploadImage(imageFile);
			carImage.setHeadImage(ApplicationUtil.IMAGE_PREFIX+fileName);
			carImageService.save(carImage);
		}
		return mv;
	}
	
	//删除字段配置
	@RequestMapping("/deleteImage")
	public ModelAndView delete(Integer carId,Integer id) {
		ModelAndView mv = new ModelAndView("redirect:/carService/imageList?carId="+carId);
		carImageService.deleteById(id);
		return mv;
	}
 
}