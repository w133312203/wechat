package com.hm.controller;  
  
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.WxGlobalParam;
import com.hm.domain.WxMaterial;
import com.hm.domain.WxMaterialNews;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxMaterialNewsService;
import com.hm.service.WxMaterialService;
import com.hm.service.WxTagService;
import com.hm.service.WxUserService;
import com.hm.utils.AccessTokenUtil;
import com.hm.utils.CalculatorUtil;
import com.hm.utils.HttpUtils;
import com.hm.utils.StringUtil;
import com.hm.utils.WxApiUtil;
   

@Controller
@RequestMapping("/wx/material")
public class WxMaterialController extends BaseCotroller{ 
	
    @Resource  
    private WxGlobalParamService wxGlobalParamService;
    
    @Resource  
    private WxTagService wxTagService;
    
    @Resource  
    private WxUserService wxUserService;
    
    @Resource  
    private WxMaterialService wxMaterialService;
    
    @Resource  
    private WxMaterialNewsService wxMaterialNewsService;
    
    private static Logger infologger = Logger.getLogger("dayinfo");
    
    //获取微信图片素材列表
    @RequestMapping("/image")
  	public ModelAndView materialImage(Integer pageIndex) {
    	String search = request.getParameter("search");
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("search", search);
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			Integer maxResult=9;
    			Integer pageCount;
    			if(pageIndex==null) {
    				pageIndex = 0;
    			}
    			if(StringUtil.isEmpty(search)) {
    				search="";
    			}else{
    				search="%"+search+"%";
    			}
    			List<Map<String, Object>> wxMaterialList = wxMaterialService.findMaterialList(getSessionAccounts().getId(), 0, search, pageIndex*maxResult, maxResult);
    			Integer count = wxMaterialService.findMaterialCount(getSessionAccounts().getId(), 0, search);
    			if(count==0){
    				AccessTokenUtil.checkAccessToken(getSessionAccounts());
    				WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
        			Map<String, Integer> countMap = WxApiUtil.getMaterialCount(param.getAccessToken());
        			Integer imageCount = countMap.get("image_count");
        			if(imageCount>0){
        				List<Map<String, Object>> imageList = new ArrayList<Map<String, Object>>();
        				int imageSize = CalculatorUtil.division(imageCount, 20);
        				for (int i = 0; i < imageSize; i++) {
        					Map<String, Object> materialMap = new HashMap<String, Object>();
                			materialMap.put("type", "image");
                			materialMap.put("offset", i*20);
                			materialMap.put("count", 20);
                			JSON materialJson =JSONSerializer.toJSON(materialMap);
                			String result = WxApiUtil.batchGetMaterial(param.getAccessToken(), materialJson.toString());
                			JSONObject newsObj = JSONObject.fromObject(result);
                			JSONArray itemArr = newsObj.getJSONArray("item");
							
							for (int j = 0; j < itemArr.size(); j++) {
                				Map<String, Object> imageMap = new HashMap<String, Object>();
                				JSONObject jsonb = itemArr.getJSONObject(j);
                				imageMap.put("accountId", getSessionAccounts().getId());
                				imageMap.put("mediaId", jsonb.getString("media_id"));
                				imageMap.put("name", jsonb.getString("name"));
                				imageMap.put("url", jsonb.getString("url"));
                				imageMap.put("updateTime", jsonb.getLong("update_time"));
                				imageMap.put("type", 0);
                				imageList.add(imageMap);
    						}
							
    					}
        				if(imageList!=null&&imageList.size()>0){
        					wxMaterialService.saveList(getSessionAccounts().getId(), 0, imageList);
        				}
        			}
        			wxMaterialList = wxMaterialService.findMaterialList(getSessionAccounts().getId(), 0, search, pageIndex*maxResult, maxResult);
        			count = wxMaterialService.findMaterialCount(getSessionAccounts().getId(), 0, search);
    			}
    			long time=System.currentTimeMillis();
    			ServletContext sc=request.getServletContext();
				String dirPath=sc.getRealPath("/")+"upload/"+time+"/";
				File dirFile=new File(dirPath);
				if(!dirFile.exists()){
					dirFile.mkdirs();
				}
    			for (Map<String, Object> map : wxMaterialList) {
    				if(map.get("picUrl")==null){
    					String picUrl = HttpUtils.downloadImage(map.get("url").toString(), dirPath);
        				WxMaterial material = wxMaterialService.find(Integer.parseInt(map.get("id").toString()));
        				material.setPicUrl(picUrl);
        				wxMaterialService.update(material);
        				map.put("picUrl", picUrl);
    				}
    				
				}
    			deleteFile(dirFile);
    			if(count==0) {
    				pageCount = 1;
    			}else {
    				if(count%maxResult==0) {
    					pageCount = count/maxResult; 
    				}else {
    					pageCount = count/maxResult+1; 
    				}
    			}
    			mav.addObject("wxImageList", wxMaterialList);
    			mav.addObject("count", count);
    			mav.addObject("pageIndex", pageIndex);
    			mav.addObject("pageCount", pageCount);
    			mav.addObject("maxResult", maxResult);
    			mav.setViewName("/wxMaterialImage");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //添加图片素材
    @RequestMapping("/image/add")
  	public ModelAndView uploadPic(MultipartFile imageFile) throws Exception {
        ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			AccessTokenUtil.checkAccessToken(getSessionAccounts());
    			WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    			String fileName = imageFile.getOriginalFilename();
    			/*CommonsMultipartFile cf= (CommonsMultipartFile)imageFile; 
    	        DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
    	        File f = fi.getStoreLocation();*/
    	        long time=System.currentTimeMillis();
    	        ServletContext sc=request.getServletContext();
    			String dirPath=sc.getRealPath("/")+"upload/"+time+"/";
    			File dirFile=new File(dirPath);
    			if(!dirFile.exists()){
    				dirFile.mkdirs();
    			}
    			String filePath = dirPath+fileName;
    			File file=new File(filePath);
    			imageFile.transferTo(file);
    	        String result = WxApiUtil.addMaterial(param.getAccessToken(), "image", filePath);
    	        deleteFile(dirFile);
    	        JSONObject jsonobj = JSONObject.fromObject(result);
    	        String rootPath = sc.getRealPath("/");
    	        String picUrl = HttpUtils.downloadImage(jsonobj.getString("url"), rootPath);
    	        WxMaterial material = new WxMaterial();
    	        material.setAccountId(getSessionAccounts().getId());
    	        material.setMediaId(jsonobj.getString("media_id"));
    	        material.setName(fileName);
    	        material.setUrl(jsonobj.getString("url"));
    	        material.setPicUrl(picUrl);
    	        material.setUpdateTime(System.currentTimeMillis());
    	        material.setType(0);
    	        wxMaterialService.save(material);
    			mav.setViewName("redirect:/wx/material/image");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //同步所有微信图片素材
    @RequestMapping("/synchronizeImages")
  	public ModelAndView synchronizeImages() {
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			AccessTokenUtil.checkAccessToken(getSessionAccounts());
    			WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    			Map<String, Integer> countMap = WxApiUtil.getMaterialCount(param.getAccessToken());
    			if(countMap.containsKey("image_count")){
    				Integer imageCount = countMap.get("image_count");
        			if(imageCount>0){
        				List<Map<String, Object>> imageList = new ArrayList<Map<String, Object>>();
        				int imageSize = CalculatorUtil.division(imageCount, 20);
        				for (int i = 0; i < imageSize; i++) {
        					Map<String, Object> materialMap = new HashMap<String, Object>();
                			materialMap.put("type", "image");
                			materialMap.put("offset", i*20);
                			materialMap.put("count", 20);
                			JSON materialJson =JSONSerializer.toJSON(materialMap);
                			String result = WxApiUtil.batchGetMaterial(param.getAccessToken(), materialJson.toString());
                			JSONObject newsObj = JSONObject.fromObject(result);
                			JSONArray itemArr = newsObj.getJSONArray("item");
							for (int j = 0; j < itemArr.size(); j++) {
								Map<String, Object> imageMap = new HashMap<String, Object>();
                				JSONObject jsonb = itemArr.getJSONObject(j);
                				imageMap.put("accountId", getSessionAccounts().getId());
                				imageMap.put("mediaId", jsonb.getString("media_id"));
                				imageMap.put("name", jsonb.getString("name"));
                				imageMap.put("url", jsonb.getString("url"));
                				imageMap.put("updateTime", jsonb.getLong("update_time"));
                				imageMap.put("type", 0);
                				imageList.add(imageMap);
    						}
    					}
        				if(imageList!=null&&imageList.size()>0){
        					wxMaterialService.saveList(getSessionAccounts().getId(), 0, imageList);
        				}
        			}
        			mav.setViewName("redirect:/wx/material/image");
    			}else{
    				mav.addObject("errmsg", "access_token is invalid or not latest hint");
    				mav.setViewName("wxError");
    				mav.addObject("urlStr", "/wx/users");
    				return mav;
    			}
    			
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //获取微信图文素材列表
    @RequestMapping("/news")
  	public ModelAndView materialNews(Integer pageIndex) {
    	String search = request.getParameter("search");
    	ModelAndView mav = new ModelAndView();
    	mav.addObject("search", search);
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			Integer maxResult=9;
    			Integer pageCount;
    			if(pageIndex==null) {
    				pageIndex = 0;
    			}
    			if(StringUtil.isEmpty(search)) {
    				search="";
    			}else{
    				search="%"+search+"%";
    			}
    			List<Map<String, Object>> wxNewsList = wxMaterialNewsService.findNewsList(getSessionAccounts().getId(), search, pageIndex*maxResult, maxResult);
    			Integer count = wxMaterialNewsService.findNewsCount(getSessionAccounts().getId(), search);
    			if(count==0){
    				AccessTokenUtil.checkAccessToken(getSessionAccounts());
    				WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
        			Map<String, Integer> countMap = WxApiUtil.getMaterialCount(param.getAccessToken());
        			Integer newsCount = countMap.get("news_count");
        			if(newsCount>0){
        				int newsSize = CalculatorUtil.division(newsCount, 20);
        				List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
        				for (int i = 0; i < newsSize; i++) {
        					Map<String, Object> materialMap = new HashMap<String, Object>();
                			materialMap.put("type", "news");
                			materialMap.put("offset", i*20);
                			materialMap.put("count", 20);
                			JSON materialJson =JSONSerializer.toJSON(materialMap);
                			String result = WxApiUtil.batchGetMaterial(param.getAccessToken(), materialJson.toString());
                			JSONObject newsObj = JSONObject.fromObject(result);
                			JSONArray itemArr = newsObj.getJSONArray("item");
                			for (int j = 0; j < itemArr.size(); j++) {
                				JSONObject jsonb = itemArr.getJSONObject(j);
                				String mediaId = jsonb.getString("media_id");
                				long updateTime = jsonb.getLong("update_time");
                				JSONObject jsonContent = jsonb.getJSONObject("content");
                				long createTime = jsonContent.getLong("create_time");
                				JSONArray itemNewsArr = jsonContent.getJSONArray("news_item");
                				for (int k = 0; k < itemNewsArr.size(); k++) {
                					JSONObject JsonItemNews = itemNewsArr.getJSONObject(k);
                					Map<String, Object> itemNewsMap = new HashMap<String, Object>();
                					itemNewsMap.put("accountId", getSessionAccounts().getId());
                					itemNewsMap.put("mediaId", mediaId);
                					itemNewsMap.put("title", JsonItemNews.getString("title"));
                					itemNewsMap.put("author", JsonItemNews.getString("author"));
                					itemNewsMap.put("showCoverPic", JsonItemNews.getString("show_cover_pic"));
                					itemNewsMap.put("digest", JsonItemNews.getString("digest"));
                					itemNewsMap.put("content", JsonItemNews.getString("content"));
                					itemNewsMap.put("contentSourceUrl", JsonItemNews.getString("content_source_url"));
                					itemNewsMap.put("thumbMediaId", JsonItemNews.getString("thumb_media_id"));
                					itemNewsMap.put("url", JsonItemNews.getString("url"));
                					itemNewsMap.put("thumbUrl", JsonItemNews.getString("thumb_url"));
                					itemNewsMap.put("createTime", createTime);
                					itemNewsMap.put("updateTime", updateTime);
                					itemNewsMap.put("sortLevel", k+1);
                    				newsList.add(itemNewsMap);
								}
    						}
    					}
        				if(newsList!=null&&newsList.size()>0){
        					wxMaterialNewsService.saveList(getSessionAccounts().getId(), newsList, 0);
        				}
        			}
        			wxNewsList = wxMaterialNewsService.findNewsList(getSessionAccounts().getId(), search, 0*20, 20);
        			count = wxMaterialNewsService.findNewsCount(getSessionAccounts().getId(), search);
    			}
    			long time=System.currentTimeMillis();
    			ServletContext sc=request.getServletContext();
				String dirPath=sc.getRealPath("/")+"upload/"+time+"/";
				File dirFile=new File(dirPath);
				if(!dirFile.exists()){
					dirFile.mkdirs();
				}
    			for (Map<String, Object> map : wxNewsList) {
					List<Map<String, Object>> newsList = wxMaterialNewsService.findListByMediaId(getSessionAccounts().getId(), map.get("mediaId").toString(), 0);
					for (Map<String, Object> newsMap : newsList) {
						if(newsMap.get("thumbUrl")!=null&&newsMap.get("picUrl")==null){
							String picUrl = HttpUtils.downloadImage(newsMap.get("thumbUrl").toString(), dirPath);
	        				WxMaterialNews materialNews = wxMaterialNewsService.find(Integer.parseInt(map.get("id").toString()));
	        				materialNews.setPicUrl(picUrl);
	        				wxMaterialNewsService.update(materialNews);
	        				newsMap.put("picUrl", picUrl);
						}
					}
					map.put("itemNews", newsList);
				}
    			deleteFile(dirFile);
    			if(count==0) {
    				pageCount = 1;
    			}else {
    				if(count%maxResult==0) {
    					pageCount = count/maxResult; 
    				}else {
    					pageCount = count/maxResult+1; 
    				}
    			}
    			mav.addObject("wxNewsList", wxNewsList);
    			mav.addObject("count", count);
    			mav.addObject("pageIndex", pageIndex);
    			mav.addObject("pageCount", pageCount);
    			mav.addObject("maxResult", maxResult);
    			mav.setViewName("/wxMaterialNews");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //跳转到编辑微信图文素材页面
    @RequestMapping("/news/edit")
  	public ModelAndView showEditMaterialNews(HttpSession httpSession) {
    	String mediaId = request.getParameter("mediaId");
    	String mark = request.getParameter("mark");
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			mav.addObject("mediaId", mediaId);
    			mav.addObject("mark", mark);
    			mav.setViewName("/editMaterialNews");
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    @ResponseBody
    @RequestMapping(value = "/getNewsJson" ,method = RequestMethod.POST)
  	public String getNewsJson(HttpSession httpSession) {
    	String mediaId = request.getParameter("mediaId");
    	String mark = request.getParameter("mark");
    	if("create".equals(mark)){
			httpSession.setAttribute("mediaId", "");
		}else if("update".equals(mark)){
			httpSession.setAttribute("mediaId", mediaId);
		}
		if(StringUtil.isEmpty(mediaId)){
			mediaId = (String) httpSession.getAttribute("mediaId");
		}
		List<Map<String, Object>> newsList = wxMaterialNewsService.findListByMediaId(getSessionAccounts().getId(), mediaId, 1);
		JSON newsJson =JSONSerializer.toJSON(newsList);
		return newsJson.toString();
  	}
    
    //编辑微信图文素材
    @RequestMapping("/editNews")
  	public ModelAndView editMaterialNews() {
    	String mediaId = request.getParameter("mediaId");
    	String newsJsonStr = request.getParameter("newsJsonStr");
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			AccessTokenUtil.checkAccessToken(getSessionAccounts());
    			WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    			if(StringUtil.isEmpty(mediaId)){
    				String result = WxApiUtil.addNews(param.getAccessToken(), newsJsonStr);
        			JSONObject jsonobj = JSONObject.fromObject(result);
        			if(jsonobj.has("media_id")){
        				JSONObject jsonb = JSONObject.fromObject(newsJsonStr);
            			JSONArray newsArray = jsonb.getJSONArray("articles");
            			List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
            			for (int i = 0; i < newsArray.size(); i++) {
            				JSONObject newsJson = newsArray.getJSONObject(i);
        					Map<String, Object> itemNewsMap = new HashMap<String, Object>();
        					itemNewsMap.put("accountId", getSessionAccounts().getId());
        					itemNewsMap.put("mediaId", jsonobj.getString("media_id"));
        					itemNewsMap.put("title", newsJson.getString("title"));
        					itemNewsMap.put("author", newsJson.getString("author"));
        					itemNewsMap.put("showCoverPic", newsJson.getString("show_cover_pic"));
        					itemNewsMap.put("digest", newsJson.getString("digest"));
        					itemNewsMap.put("content", newsJson.getString("content"));
        					itemNewsMap.put("contentSourceUrl", newsJson.getString("content_source_url"));
        					itemNewsMap.put("thumbMediaId", newsJson.getString("thumb_media_id"));
        					itemNewsMap.put("url", "");
        					itemNewsMap.put("thumbUrl", newsJson.getString("thumb_url"));
        					itemNewsMap.put("createTime", System.currentTimeMillis());
        					itemNewsMap.put("updateTime", System.currentTimeMillis());
        					itemNewsMap.put("sortLevel", newsJson.getInt("sortLevel"));
            				newsList.add(itemNewsMap);
        				}
            			if(newsList!=null&&newsList.size()>0){
        					wxMaterialNewsService.saveList(getSessionAccounts().getId(), newsList, 1);
        				}
            			mav.setViewName("redirect:/wx/material/news");
        			}else{
        				mav.setViewName("wxError");
        				mav.addObject("urlStr", "/wx/material/news");
    					mav.addObject("errmsg", jsonobj.getString("errmsg"));
        			}
    			}else{
    				JSONObject jsonb = JSONObject.fromObject(newsJsonStr);
        			JSONArray newsArray = jsonb.getJSONArray("articles");
        			List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
        			for (int i = 0; i < newsArray.size(); i++) {
        				JSONObject newsJson = newsArray.getJSONObject(i);
    					Map<String, Object> newsMap = new HashMap<String, Object>();
    					Map<String, Object> articlesMap = new HashMap<String, Object>();
    					newsMap.put("media_id", mediaId);
    					newsMap.put("index", i);
    					articlesMap.put("accountId", getSessionAccounts().getId());
    					articlesMap.put("mediaId", mediaId);
    					articlesMap.put("title", newsJson.getString("title"));
    					articlesMap.put("author", newsJson.getString("author"));
    					articlesMap.put("showCoverPic", newsJson.getString("show_cover_pic"));
    					articlesMap.put("show_cover_pic", newsJson.getString("show_cover_pic"));
    					articlesMap.put("digest", newsJson.getString("digest"));
    					articlesMap.put("content", newsJson.getString("content"));
    					articlesMap.put("contentSourceUrl", newsJson.getString("content_source_url"));
    					articlesMap.put("content_source_url", newsJson.getString("content_source_url"));
    					articlesMap.put("thumbMediaId", newsJson.getString("thumb_media_id"));
    					articlesMap.put("thumb_media_id", newsJson.getString("thumb_media_id"));
    					articlesMap.put("url", "");
    					articlesMap.put("thumbUrl", newsJson.getString("thumb_url"));
    					articlesMap.put("thumb_url", newsJson.getString("thumb_url"));
    					articlesMap.put("createTime", System.currentTimeMillis());
    					articlesMap.put("updateTime", System.currentTimeMillis());
    					articlesMap.put("sortLevel", newsJson.getInt("sortLevel"));
    					newsMap.put("articles", articlesMap);
    					JSON newsjson = JSONSerializer.toJSON(newsMap);
    					String result = WxApiUtil.updateNews(param.getAccessToken(), newsjson.toString());
    					JSONObject jsonobj = JSONObject.fromObject(result);
    					if(jsonobj.getInt("errcode")==0){
    						newsList.add(articlesMap);
            				if(newsList!=null&&newsList.size()>0){
            					if(i==0){
            						wxMaterialNewsService.deleteByMediaId(mediaId);
            					}
            					wxMaterialNewsService.saveList(getSessionAccounts().getId(), newsList, 1);
            				}
    					}
    				}
        			mav.setViewName("redirect:/wx/material/news");
    			}
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //编辑图文素材时，上传图片素材
    @ResponseBody
    @RequestMapping(value = "/uploadImage" ,method = RequestMethod.POST)
  	public String uploadImage(MultipartFile imageFile) throws IOException {
    	AccessTokenUtil.checkAccessToken(getSessionAccounts());
    	WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
		String fileName = imageFile.getOriginalFilename();
        long time=System.currentTimeMillis();
        ServletContext sc=request.getServletContext();
		String dirPath=sc.getRealPath("/")+"upload/"+time+"/";
		File dirFile=new File(dirPath);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String filePath = dirPath+fileName;
		File file=new File(filePath);
		imageFile.transferTo(file);
        String result = WxApiUtil.addMaterial(param.getAccessToken(), "image", filePath);
        deleteFile(dirFile);
        JSONObject jsonobj = JSONObject.fromObject(result);
        WxMaterial material = new WxMaterial();
        material.setAccountId(getSessionAccounts().getId());
        material.setMediaId(jsonobj.getString("media_id"));
        material.setName(fileName);
        material.setUrl(jsonobj.getString("url"));
        material.setUpdateTime(System.currentTimeMillis());
        material.setType(0);
        wxMaterialService.save(material);
  		return "<script>window.parent.uploadSuccess('"+jsonobj.getString("media_id")+"','"+jsonobj.getString("url")+"');</script>";
  	}
    
    //同步所有微信图文素材
    @RequestMapping("/synchronizeNews")
  	public ModelAndView synchronizeNews() {
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			AccessTokenUtil.checkAccessToken(getSessionAccounts());
    			WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    			Map<String, Integer> countMap = WxApiUtil.getMaterialCount(param.getAccessToken());
    			if(countMap.containsKey("news_count")){
    				Integer newsCount = countMap.get("news_count");
        			if(newsCount>0){
        				int newsSize = CalculatorUtil.division(newsCount, 20);
        				List<Map<String, Object>> newsList = new ArrayList<Map<String, Object>>();
        				for (int i = 0; i < newsSize; i++) {
        					Map<String, Object> materialMap = new HashMap<String, Object>();
                			materialMap.put("type", "news");
                			materialMap.put("offset", i*20);
                			materialMap.put("count", 20);
                			JSON materialJson =JSONSerializer.toJSON(materialMap);
                			String result = WxApiUtil.batchGetMaterial(param.getAccessToken(), materialJson.toString());
                			JSONObject newsObj = JSONObject.fromObject(result);
                			JSONArray itemArr = newsObj.getJSONArray("item");
                			for (int j = 0; j < itemArr.size(); j++) {
                				JSONObject jsonb = itemArr.getJSONObject(j);
                				String mediaId = jsonb.getString("media_id");
                				long updateTime = jsonb.getLong("update_time");
                				JSONObject jsonContent = jsonb.getJSONObject("content");
                				long createTime = jsonContent.getLong("create_time");
                				JSONArray itemNewsArr = jsonContent.getJSONArray("news_item");
                				for (int k = 0; k < itemNewsArr.size(); k++) {
                					JSONObject JsonItemNews = itemNewsArr.getJSONObject(k);
                					Map<String, Object> itemNewsMap = new HashMap<String, Object>();
                					itemNewsMap.put("accountId", getSessionAccounts().getId());
                					itemNewsMap.put("mediaId", mediaId);
                					itemNewsMap.put("title", JsonItemNews.getString("title"));
                					itemNewsMap.put("author", JsonItemNews.getString("author"));
                					itemNewsMap.put("showCoverPic", JsonItemNews.getString("show_cover_pic"));
                					itemNewsMap.put("digest", JsonItemNews.getString("digest"));
                					itemNewsMap.put("content", JsonItemNews.getString("content"));
                					itemNewsMap.put("contentSourceUrl", JsonItemNews.getString("content_source_url"));
                					itemNewsMap.put("thumbMediaId", JsonItemNews.getString("thumb_media_id"));
                					itemNewsMap.put("url", JsonItemNews.getString("url"));
                					itemNewsMap.put("thumbUrl", JsonItemNews.getString("thumb_url"));
                					itemNewsMap.put("createTime", createTime);
                					itemNewsMap.put("updateTime", updateTime);
                					itemNewsMap.put("sortLevel", k+1);
                    				newsList.add(itemNewsMap);
    							}
    						}
    					}
        				if(newsList!=null&&newsList.size()>0){
        					wxMaterialNewsService.saveList(getSessionAccounts().getId(), newsList, 0);
        				}
        			}
        			mav.setViewName("redirect:/wx/material/news");
    			}else{
    				mav.addObject("errmsg", "access_token is invalid or not latest hint");
    				mav.setViewName("wxError");
    				mav.addObject("urlStr", "/wx/users");
    				return mav;
    			}
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    //删除微信素材
    @RequestMapping("/deleteMaterial")
  	public ModelAndView deleteMaterial() {
    	String mediaId = request.getParameter("mediaId");
    	String type = request.getParameter("type");
    	ModelAndView mav = new ModelAndView();
    	if(getSessionPassport()!=null&&getSessionPassport().getStatus()==1) {
    		if(getSessionAccounts()!=null) {
    			AccessTokenUtil.checkAccessToken(getSessionAccounts());
    			WxGlobalParam param=wxGlobalParamService.findByAccountId(getSessionAccounts().getId());
    			Map<String, Object> mediaIdMap = new HashMap<String, Object>();
    			mediaIdMap.put("media_id", mediaId);
    			JSON mediaIdJson = JSONSerializer.toJSON(mediaIdMap);
    			String result = WxApiUtil.delMaterial(param.getAccessToken(), mediaIdJson.toString());
    			JSONObject jsonobj = JSONObject.fromObject(result);
    			if(jsonobj.getInt("errcode")==0){
    				if("news".equals(type)){
    					wxMaterialNewsService.deleteByMediaId(mediaId);
    					mav.setViewName("redirect:/wx/material/news");
    				}else if("image".equals(type)){
    					wxMaterialService.deleteByMediaId(mediaId);
    					mav.setViewName("redirect:/wx/material/image");
    				}
    			}else{
    				mav.setViewName("wxError");
    				mav.addObject("urlStr", "/wx/material/image");
					mav.addObject("errmsg", jsonobj.getString("errmsg"));
    			}
    		}else{
    			mav.setViewName("redirect:/wx/home");
    		}
		}else {
			mav.setViewName("redirect:/login");
		}
    	return mav;
  	}
    
    /**
     * 删除文件夹下的所有文件
     * @param oldPath
     */
    public void deleteFile(File oldPath) {
	    if (oldPath.isDirectory()) {
		    File[] files = oldPath.listFiles();
		    for (File file : files) {
		     deleteFile(file);
		    }
	    }
	    oldPath.delete();
    }
    
} 