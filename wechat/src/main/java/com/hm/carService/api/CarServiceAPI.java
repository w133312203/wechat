package com.hm.carService.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.base.controller.BaseCotroller;
import com.hm.carService.domain.Car;
import com.hm.carService.domain.CarImage;
import com.hm.carService.domain.Information;
import com.hm.carService.domain.Subdivision;
import com.hm.carService.service.CarImageService;
import com.hm.carService.service.CarService;
import com.hm.carService.service.ConfigureContextService;
import com.hm.carService.service.InformationService;
import com.hm.carService.service.SubdivisionService;
import com.hm.utils.BaseUtil;
import com.hm.utils.HttpClientUtil;
import com.hm.utils.StringUtil;

@Controller
@RequestMapping("/carService/api")
public class CarServiceAPI extends BaseCotroller{
	
	@Autowired
	SubdivisionService subdivisionService;
	
	@Autowired
	CarService carService;
	
	@Autowired
	ConfigureContextService configureContextService;
	
	@Autowired
	CarImageService carImageService;
	
	@Autowired
	InformationService informationService;

	//价格计算器API
    @ResponseBody
	@RequestMapping("/priceList")
    public Map<String,Object> priceList() {
    	Map map = new HashMap();
    	try {
    		String subdivisionId = request.getParameter("subdivisionId");
        	Subdivision subdivision = subdivisionService.findById(Integer.parseInt(subdivisionId));
        	Double barePrice = subdivision.getBarePrice();//裸车价
        	Integer displacement = subdivision.getDisplacement();//排量
        	Integer seatNum = subdivision.getSeatNum();//座位数
        	Integer carType = subdivision.getCarType();//车辆类型
        	map.put("lcj", BaseUtil.round(barePrice));
        	//购置税
        	map.put("gzs", BaseUtil.round(barePrice/1.17*0.1));
        	//交强险
        	if(seatNum<6) {
        		map.put("jqx", 950);
        	}else {
        		map.put("jqx", 1100);
        	}
        	//车船使用税
        	if(0==displacement){
        		map.put("ccsys", 300);
    		}else if(displacement == 1){
    			map.put("ccsys", 420);
    		}else if(displacement == 2){
    			map.put("ccsys", 480);
    		}else if(displacement == 3){
    			map.put("ccsys", 900);
    		}else if(displacement == 4){
    			map.put("ccsys", 1920);
    		}else if(displacement == 5){
    			map.put("ccsys", 3480);
    		}else if(displacement == 6){
    			map.put("ccsys", 5280);
    		}
        	//上牌费用
        	map.put("spf", 500);
        	//第三者责任险(5, 10, 20, 50, 100)
        	map.put("dszzrx1", 710);
        	map.put("dszzrx2", 1026);
        	map.put("dszzrx3", 1270);
        	map.put("dszzrx4", 1721);
        	map.put("dszzrx5", 2242);
        	//车辆损失险 = 裸车价*0.009 + 342
        	map.put("clssx", BaseUtil.round(barePrice*0.009+342));
        	//不计免赔 = 第三者责任*0.2+车辆损失险*0.2
        	map.put("bjmp", 0);
        	//全车盗抢险 = 裸车价*0.0045 + 120
        	map.put("qcdqx", BaseUtil.round(barePrice*0.0045+120));
        	//玻璃单独破碎险
        	//国产玻璃损失险种 = 裸车价*0.0015
        	//进口玻璃损失险种 = 裸车价*0.0025
        	if(carType==0) {
        		map.put("blx1", BaseUtil.round(barePrice*0.0015));
        	}else {
        		map.put("blx2", BaseUtil.round(barePrice*0.0025));
        	}
        	//自燃损失险 = 裸车价*0.0015;
        	map.put("zrssx", BaseUtil.round(barePrice*0.0015));
        	//涉水险 = 车损险*5%
        	map.put("ssx", BaseUtil.round((barePrice*0.009+342)*0.005));
        	//划痕险
        	if(barePrice<30) {
        		map.put("hhx1", 400);
        		map.put("hhx2", 570);
        		map.put("hhx3", 760);
        		map.put("hhx4", 1140);
        	}else if(barePrice<50&&barePrice>30) {
        		map.put("hhx1", 585);
        		map.put("hhx2", 900);
        		map.put("hhx3", 1170);
        		map.put("hhx4", 1780);
        	}else {
        		map.put("hhx1", 850);
        		map.put("hhx2", 1100);
        		map.put("hhx3", 1500);
        		map.put("hhx4", 2250);
        	}
        	//司机/乘客责任险
        	Double jsy = 0.0d;
        	Double ck = 0.0d;
        	if(subdivision.getSeatNum()<6) {
        		jsy = subdivision.getSeatNum()*0.0042;	
        		ck = subdivision.getSeatNum()*0.0027;
        		map.put("jsy1", BaseUtil.round(jsy*10000));
        		map.put("ck1", BaseUtil.round(ck*10000));
        		map.put("jsy2", BaseUtil.round(jsy*20000));
        		map.put("ck2", BaseUtil.round(ck*20000));
        		map.put("jsy3", BaseUtil.round(jsy*30000));
        		map.put("ck3", BaseUtil.round(ck*30000));
        		map.put("jsy4", BaseUtil.round(jsy*40000));
        		map.put("ck4", BaseUtil.round(ck*40000));
        		map.put("jsy5", BaseUtil.round(jsy*50000));
        		map.put("ck5", BaseUtil.round(ck*50000));
        	}else if(subdivision.getSeatNum()<=10&&subdivision.getSeatNum()>=6) {
        		jsy = subdivision.getSeatNum()*0.0040;
        		ck = subdivision.getSeatNum()*0.0026;
        		map.put("jsy1", BaseUtil.round(jsy*10000));
        		map.put("ck1", BaseUtil.round(ck*10000));
        		map.put("jsy2", BaseUtil.round(jsy*20000));
        		map.put("ck2", BaseUtil.round(ck*20000));
        		map.put("jsy3", BaseUtil.round(jsy*30000));
        		map.put("ck3", BaseUtil.round(ck*30000));
        		map.put("jsy4", BaseUtil.round(jsy*40000));
        		map.put("ck4", BaseUtil.round(ck*40000));
        		map.put("jsy5", BaseUtil.round(jsy*50000));
        		map.put("ck5", BaseUtil.round(ck*50000));
        	}else {
        		jsy = subdivision.getSeatNum()*0.0040;
        		ck = subdivision.getSeatNum()*0.0026;
        		map.put("jsy1", BaseUtil.round(jsy*10000));
        		map.put("ck1", BaseUtil.round(ck*10000));
        		map.put("jsy2", BaseUtil.round(jsy*20000));
        		map.put("ck2", BaseUtil.round(ck*20000));
        		map.put("jsy3", BaseUtil.round(jsy*30000));
        		map.put("ck3", BaseUtil.round(ck*30000));
        		map.put("jsy4", BaseUtil.round(jsy*40000));
        		map.put("ck4", BaseUtil.round(ck*40000));
        		map.put("jsy5", BaseUtil.round(jsy*50000));
        		map.put("ck5", BaseUtil.round(ck*50000));
        	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return map;
    }
    
    //售车列表
    @ResponseBody
	@RequestMapping("/carList")
    public Map<String,Object> carList() {
    	String softwareId = request.getParameter("softwareId");
    	Information information = informationService.findBySoftwareId(Integer.parseInt(softwareId));
    	List<Map> list = carService.findCarList(Integer.parseInt(softwareId), null, "", 0, 100);
    	String groupName = "";
    	List<Map> newList = new ArrayList<Map>();
    	Map carMap = new HashMap();
    	carMap.put("groupName", groupName);
    	carMap.put("list", new ArrayList<Map>());
    	for(int i=0;i<list.size();i++) {
    		List<Map> carList = (List<Map>) carMap.get("list");
    		if(list.get(i).get("groupName")==null||StringUtil.isEmpty(list.get(i).get("groupName").toString())) {
    			if(StringUtil.isEmpty(carMap.get("groupName").toString())) {
    				carList.add(list.get(i));
    			}else {
    				newList.add(carMap);
    				carMap = new HashMap();
    		    	carMap.put("groupName", "");
    		    	carList = new ArrayList();
    		    	carList.add(list.get(i));
    		    	carMap.put("list", carList);
    			}
    		}else {
    			if(carMap.get("groupName").toString().equals(list.get(i).get("groupName").toString())) {
    				carList.add(list.get(i));
    			}else {
    				newList.add(carMap);
    				carMap = new HashMap();
    		    	carMap.put("groupName", list.get(i).get("groupName").toString());
    		    	carList = new ArrayList();
    		    	carList.add(list.get(i));
    		    	carMap.put("list", carList);
    			}
    		}
    	}
    	newList.add(carMap);
    	Map map = new HashMap();
    	map.put("information", information);
    	map.put("list", newList);
    	return map;
    }
    
    //车辆详细分类
    @ResponseBody
	@RequestMapping("/carInfoList")
    public Map<String,Object> carInfoList() {
    	String carId = request.getParameter("carId");
    	Car car = carService.findById(Integer.parseInt(carId));
    	List<CarImage> imgList = carImageService.findByCarId(Integer.parseInt(carId));
    	List<Map> list = subdivisionService.findList(Integer.parseInt(carId), "", 0, 100);
    	String horsepower = "";
    	for(Map map:list) {
    		if(map.get("horsepower")!=null) {
    			if(!horsepower.equals(map.get("horsepower").toString())) {
    				horsepower = map.get("horsepower").toString();
    			}else {
    				map.put("horsepower", "");
    			}
    		}
    	}
    	Map map = new HashMap();
    	map.put("list", list);
    	
    	
    	String videoUrl = car.getVideoUrl();
    	if(!StringUtil.isEmpty(videoUrl)) {
    		int spotIndex = videoUrl.lastIndexOf(".");
    		int gIndex = videoUrl.lastIndexOf("/");
    		videoUrl = videoUrl.substring(gIndex+1, spotIndex);
    		
    		HttpClientUtil httpUtil = HttpClientUtil.getInstance();
    		String resultJson = httpUtil.doGetRequest("http://vv.video.qq.com/getinfo?vids="+videoUrl+"&platform=101001&charge=0&otype=json");
    		resultJson = resultJson.replace("QZOutputJson=", "");
    		resultJson = resultJson.substring(0,resultJson.length()-1);
    		JSONObject jsob = JSONObject.fromObject(resultJson);
    		Map objectMap = (Map) jsob.get("vl");
    		List objectList = (List) objectMap.get("vi");
    		objectMap = (Map) objectList.get(0);
    		String fn = (String) objectMap.get("fn");
    		String fvkey = (String) objectMap.get("fvkey");
    		objectMap = (Map) objectMap.get("ul");
    		objectList = (List) objectMap.get("ui");
    		objectMap = (Map) objectList.get(0);
    		String url = (String) objectMap.get("url");
    		videoUrl = url+fn+"?vkey="+fvkey;
    	}else {
    		videoUrl = "";
    	}
    	car.setVideoUrl(videoUrl);
    	map.put("car", car);
    	if(imgList.size()>0) {
    		map.put("img",imgList.get(0));
    	}else {
    		map.put("img","");
    	}
    	return map;
    }
    
    //车辆配置信息
    @ResponseBody
	@RequestMapping("/carConfigure")
    public Map<String,Object> carConfigure() {
    	String carId = request.getParameter("carId");
//    	String subdivisionId = request.getParameter("subdivisionId");
//    	String softwareId = request.getParameter("softwareId");
//    	List<Map> list = configureContextService.findAllList(Integer.parseInt(subdivisionId),Integer.parseInt(softwareId));
    	List<CarImage> imgList = new ArrayList<CarImage>();
    	if(StringUtil.isNumber(carId)) {
    		imgList = carImageService.findListByType(Integer.parseInt(carId), 1, 0, 100);
    	}
    	Map map = new HashMap();
//    	map.put("list", list);
    	map.put("imgList", imgList);
    	return map;
    }
    
    //车辆图片列表
    @ResponseBody
	@RequestMapping("/carImageList")
    public Map<String,Object> carImageList() {
    	String carId = request.getParameter("carId");
    	List<CarImage> imgList = carImageService.findByCarId(Integer.parseInt(carId));
    	Map map = new HashMap();
    	map.put("list", imgList);
    	return map;
    }
    
    //价格快速修改
//    @ResponseBody
//	@RequestMapping("/test")
//    public Map<String,Object> test() {
//    	String carId = request.getParameter("carId");
//    	String num = request.getParameter("num");
//    	List<Subdivision> list = subdivisionService.findListByOBJ(Integer.parseInt(carId));
//    	for(Subdivision s:list) {
//    		String guidancePrice = s.getGuidancePrice();
//    		guidancePrice = guidancePrice.replace("万", "");
//    		Double d = Double.parseDouble(guidancePrice);
//    		d = d*10000;
//    		d = d-Integer.parseInt(num);
//    		s.setBarePrice(d);
//    		d = d/10000;
//    		String newPrice = d.toString();
//    		int index = newPrice.indexOf(".") + 3;
//    		newPrice = newPrice.substring(0,index);
//    		newPrice = newPrice+"万起";
//    		s.setPrice(newPrice);
//    		subdivisionService.update(s);
//    	}
//    	Map map = new HashMap();
//    	return map;
//    }
	
}
