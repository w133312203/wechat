package com.hm.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.hm.base.controller.BaseCotroller;
import com.hm.domain.Result;
import com.hm.domain.WxChannel;
import com.hm.domain.WxGlobalParam;
import com.hm.service.WxChannelService;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxTagService;
import com.hm.service.WxUserService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.HttpRequestUtil;
import com.hm.utils.StringUtil;
import com.hm.utils.WxApiUtil;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

@Controller
@RequestMapping("/wx/channel")
public class WxChannelController extends BaseCotroller{
	 
	@Resource  
    private WxGlobalParamService wxGlobalParamService;

    @Resource  
    private WxChannelService wxChannelService;
    
    @Resource
    private WxUserService  wxUserService;
    
    @Resource
    private WxTagService wxTagService;
    
    

     /**
      * menu菜单跳转页面
      * @return
      * @throws Exception
      */
    @RequestMapping("/qrCode")
  	public ModelAndView showQrcode() throws Exception{
    	ModelAndView mav = new ModelAndView("/wxQrcode");
    		return mav;
    }
      /**获取用户列表json数据
       * 
       * @param name 渠道名称
       * @param id   渠道id
       * @return
       * @throws Exception
       */
    @ResponseBody
	@RequestMapping("showWxChannelListJson")
	public Map<String,Object> showChannelpListJson() throws Exception {
    	String name = request.getParameter("search");
		if(StringUtil.isEmpty(name)) {
			name="";
		}else{
			name= new String(name.getBytes("iso-8859-1"), "utf-8");
			name="%"+name+"%";
		}
    	String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String id = request.getParameter("id");
		if(StringUtil.isEmpty(offset)||StringUtil.isEmpty(maxresult)){
			maxresult="10";
			offset="0";
		}
		 Map qMap = new HashMap();
		 List<Map<String,Object>> wxChannelList=wxChannelService.findChannelList(id,name,Integer.parseInt(offset), Integer.parseInt(maxresult));
		 for (Map<String, Object> map : wxChannelList) {
			String nameCount = map.get("name").toString();
			Integer newUserSubcount=wxChannelService.newUserSubcount(nameCount);
			Integer OldSubcount=wxChannelService.newOldSubcount(nameCount);
			Integer newUserUnubcount=wxChannelService.newUserUnubcount(nameCount);
			Integer OldUnsubcount=wxChannelService.newOldUnsubcount(nameCount);
			String newuser = newUserSubcount+"/"+newUserUnubcount;
			String oldUser = OldSubcount+"/"+OldUnsubcount;
			map.put("newuser", newuser);
			map.put("olduser", oldUser);
			map.put("newsubuser", newUserSubcount);
			map.put("newunsubuser", newUserUnubcount);
			map.put("oldsubuser", OldSubcount);
			map.put("oldunsubuser", OldUnsubcount);
		 }
		 Integer count=wxChannelService.findChannelCount(id,name);
		 Map map1 = new HashMap();
		 map1.put("rows", wxChannelList); 
		 map1.put("total", count);
		 return map1;
     }
    /**
     * 依据渠道id从数据库获取二维码
     * @return
     * @throws Exception
     */
    @RequestMapping("getQrCode")
    @ResponseBody
  	public String getQrcode() throws Exception{
//    	String scene_str=null;
    	String channelId = request.getParameter("id");
//    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
//    		Integer id = getSessionAccounts().getId();
//    		WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    		List<Map<String,Object>> channelList=wxChannelService.findNameById(channelId);
//    		for (Map<String, Object> map : channelList) {
//    			scene_str=map.get("name").toString();
//			}
//    		Map map = new HashMap<>();
//    		map.put("action_name", "QR_LIMIT_SCENE");
//    		Map map1 = new HashMap<>();
//    		map1.put("scene_id",Integer.valueOf(channelId));
//    		Map map2 = new HashMap<>();
//    		map2.put("scene", map1);
//    		map.put("action_info", map2);
//    		JSON qrcodeJson =JSONSerializer.toJSON(map);
//    		String persistQrcode = WxApiUtil.getPersistQrcode(param.getAccessToken(), qrcodeJson.toString());
//    		JSONObject newsObj = JSONObject.fromObject(persistQrcode);
//    		String ticket = newsObj.get("ticket").toString();
//    		Map mapQ=new HashMap();
//    		String showQrcode = showQrcode(ticket,ApplicationUtil.GETQRCODE_TICKET_URL);
//    		System.out.println(showQrcode);
//    		mapQ.put("showQrcode", showQrcode);
//    		WxChannel wxChannel=wxChannelService.find(channelId);
//    		 wxChannel.setUrl(showQrcode);
//    		 wxChannelService.update(wxChannel);
    		return JSONSerializer.toJSON(channelList.get(0)).toString();
//    	}
//		return null;
    }
     //获取图片路径
    public String showQrcode(String ticket,String showqrcode_path){  
        Map<String,String> params = new TreeMap<String,String>();  
        params.put("ticket", HttpRequestUtil.urlEncode(ticket, HttpRequestUtil.DEFAULT_CHARSET));  
        try {  
            showqrcode_path = HttpRequestUtil.setParmas(params,showqrcode_path,"");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return showqrcode_path;  
    } 
    
    /**
     * 依据渠道名称判断是否存在
     * @return
     */
    @RequestMapping("findInfoByName")
    @ResponseBody
    public Map<String,Object>  findInfoByName(){
    	 Map map = new HashMap();
    	String name = request.getParameter("name");
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			List<Map<String,Object>> findChannelList = wxChannelService.findChannelListByName(name);
    			List<Map<String,Object>> tagList =wxTagService.findTagByName(name);
    			if(findChannelList.size()>0||tagList.size()>0){
    				 map.put("code", "00"); 
    				 return map;
    			}
    			 map.put("code", "01");
    		}
    	}
    	return map;
    }
    /**
     * 依据渠道名称入库并获取id取得永久二维码
     * @return
     * @throws Exception
     */
    @RequestMapping("addQrcode")
    public ModelAndView addQrcode() throws Exception{
    	String name = request.getParameter("name");
    	name= new String(name.getBytes("iso-8859-1"), "utf-8");
    	ModelAndView mav = new ModelAndView();
    	String scene_str=null;
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			Integer id = getSessionAccounts().getId();
        		WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    			WxChannel wxChannel=new WxChannel();
    			wxChannel.setName(name);
    			wxChannel.setNewuser(0);
    			wxChannel.setOlduser(0);
    			wxChannel.setStatus(0);
    			wxChannel.setCreateTime(new Date());
    			wxChannelService.save(wxChannel);
    			List<Map<String,Object>> findChannelList = wxChannelService.findChannelListByName(name);
    			String channelId = findChannelList.get(0).get("id").toString();
    			Map map = new HashMap<>();
        		map.put("action_name", "QR_LIMIT_SCENE");
        		Map map1 = new HashMap<>();
        		map1.put("scene_id",channelId);
        		Map map2 = new HashMap<>();
        		map2.put("scene", map1);
        		map.put("action_info", map2);
        		JSON qrcodeJson =JSONSerializer.toJSON(map);
        		String persistQrcode = WxApiUtil.getPersistQrcode(param.getAccessToken(), qrcodeJson.toString());
        		JSONObject newsObj = JSONObject.fromObject(persistQrcode);
        		String ticket = newsObj.get("ticket").toString();
        		Map mapQ=new HashMap();
        		String showQrcode = showQrcode(ticket,ApplicationUtil.GETQRCODE_TICKET_URL);
        		WxChannel wxChannel1=wxChannelService.find(channelId);
       		    wxChannel1.setUrl(showQrcode);
       		    wxChannelService.update(wxChannel1);
    		}
    	}
    	 mav.setViewName("redirect:/wx/channel/qrCode");
    	 return mav;
    }
//    依据id删除渠道列表
    @RequestMapping("delQrcode")
    @ResponseBody
    public ModelAndView delQrcode(){
    	ModelAndView mav = new ModelAndView();
    	String id = request.getParameter("id");
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    		  Integer i = wxChannelService.del(id);
    		  mav.setViewName("redirect:/wx/channel/qrCode");
    		}
    	}	
    	return mav;
    }
    /**
     * 依据渠道二维码获取粉丝列表
     * @return
     * @throws Exception 
     * @throws NumberFormatException 
     */
    @ResponseBody
  	@RequestMapping("showWxUserListJson")
  	public JSON showUserpListJson() throws NumberFormatException, Exception {
      	String maxresult = request.getParameter("pageSize");
  		String offset = request.getParameter("pageNum");
  		String id = request.getParameter("id");
  		Integer cid = Integer.valueOf(id);
  		if(StringUtil.isEmpty(offset)||StringUtil.isEmpty(maxresult)){
  			maxresult="10";
  			offset="0";
  		}
  		Map pMap = new HashMap();
  		PageInfo<Map<String, Object>> records= wxUserService.findList(cid,Integer.parseInt(offset), Integer.parseInt(maxresult));
  		List<Map<String, Object>> list = records.getList();
  		for (Map<String, Object> map : list) {
			String subscribetime = map.get("subscribetime").toString();
			System.out.println(subscribetime);
				map.put("time", subscribetime);
		}
  		
  	    List findChannelListById = wxUserService.findChannelListById(cid);
  	    int count = findChannelListById.size();
  		pMap.put("records", list);
		pMap.put("total", findChannelListById.size());
		pMap.put("pageCount", records.getPages());
  		
  		return JSONSerializer.toJSON(pMap);
      }
    
  

	/**
     * 依据下载列表到本地
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
  	@RequestMapping("downloadExcel")
  	public void downloadExcel(HttpServletResponse response) throws Exception  {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	String id = request.getParameter("id");
    	Integer cid = Integer.valueOf(id);
    	OutputStream os; 
		WritableWorkbook book;
		try {
			os= response.getOutputStream();
			List<Map<String,Object>> wxChannelList=wxChannelService.findChannelListById(cid);
			List<Result> reslist = wxChannelService.findChannelListByDayAndId(id);
			String fileName=wxChannelList.get(0).get("name").toString();
			response.setCharacterEncoding("utf-8");
	    	response.setContentType("application/vnd.ms-excel");
	    	response.setHeader("Content-Disposition", "attachment; filename=\""+ new String( fileName.getBytes( "gb2312" ), "ISO8859-1" )+ ".xls" + "\"");
			book=Workbook.createWorkbook(os);
			WritableSheet sheet=book.createSheet("渠道数据表",0);
			 sheet.addCell(new Label(0,0,"日期"));
		     sheet.addCell(new Label(1,0,"新粉丝"));
		     sheet.addCell(new Label(1,1,"关注人数"));
		     sheet.addCell(new Label(2,1,"取消人数"));
		     sheet.addCell(new Label(3,0,"老粉丝"));
		     sheet.addCell(new Label(3,1,"关注人数"));
		     sheet.addCell(new Label(4,1,"取消人数"));
		     sheet.mergeCells(0,0,0,1);
		     sheet.mergeCells(1,0,2,0);
		     sheet.mergeCells(3,0,4,0);
		     WritableSheet  sheet1=book.createSheet("渠道粉丝表",1); 
		     sheet1.addCell(new Label(0,0,"头像"));
		     sheet1.addCell(new Label(1,0,"粉丝昵称"));
		     sheet1.addCell(new Label(2,0,"粉丝地域"));
		     sheet1.addCell(new Label(3,0,"关注时间"));
		     if(reslist.size()>0){
		    	 for (int i = 0; i < reslist.size(); i++) {
		    		 sheet.addCell(new Label(0,i+2, reslist.get(i).getDays().toString()));
		    		 sheet.addCell(new Label(1,i+2,String.valueOf(reslist.get(i).getCount2())));
		    		 sheet.addCell(new Label(2,i+2,String.valueOf(reslist.get(i).getCount1())));
		    		 sheet.addCell(new Label(3,i+2,String.valueOf(reslist.get(i).getCount4())));
		    		 sheet.addCell(new Label(4,i+2,String.valueOf(reslist.get(i).getCount3()))); 
		    	 }
		     }
			List<Map> list=wxUserService.findChannelListById(cid);
			if(list.size()>0) {	
				  for (int j = 0; j < list.size(); j++) {
					  sheet1.addCell(new Label(0,j+1,list.get(j).get("headimgurl").toString()));
					  sheet1.addCell(new Label(1,j+1,list.get(j).get("nickname").toString()));
					  sheet1.addCell(new Label(2,j+1,list.get(j).get("province").toString()+" "+list.get(j).get("city").toString()));
					  sheet1.addCell(new Label(3,j+1,list.get(j).get("subscribetime").toString()));
				}
			}
			book.write();
			book.close();
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    /**
     * 根据id统计不同状态下的数据，并按天分组
     * @return
     * @throws Exception
     */
    @ResponseBody
   	@RequestMapping("showWxChannelListByDay")
   	public Map<String,Object> showChannelpListByDay() throws Exception {
    	String maxresult = request.getParameter("limit");
		String offset = request.getParameter("offset");
  		if(StringUtil.isEmpty(offset)||StringUtil.isEmpty(maxresult)){
  			maxresult="10";
  			offset="0";
  		}
   		String id = request.getParameter("id");
   		Map map1 = new HashMap();
   		List<Result> list = wxChannelService.findChannelListByDay(id,Integer.parseInt(offset), Integer.parseInt(maxresult));
   		List<Result> reslist = wxChannelService.findChannelListByDayAndId(id);
   	       map1.put("rows",list);
   		   map1.put("total", reslist.size());
   	    return map1;
   }
    
}
