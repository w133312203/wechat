package com.hm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxGlobalParam;
import com.hm.service.OfficialAccountsService;
import com.hm.service.WxGlobalParamService;

public class AccessTokenUtil {
	
	private static OfficialAccountsService officialAccountsService;
	
	private static WxGlobalParamService wxGlobalParamService;
	
	private static Logger errorlogger = Logger.getLogger("errorinfo");
	private static Logger infologger = Logger.getLogger("dayinfo");
	
	/**
	 *设置access_token 
	 */
    public static void initAndSetAccessToken(ApplicationContext ac) {
    	officialAccountsService = (OfficialAccountsService) ac.getBean("officialAccountsService");
    	wxGlobalParamService = (WxGlobalParamService) ac.getBean("wxGlobalParamService");
    	infologger.info("execute initAndSetAccessToken Start:{"+System.currentTimeMillis()+"}");
    	List<OfficialAccounts> accounts=officialAccountsService.findAllList();
    	String access_token="";
    	List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
    	List<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();
    	for (OfficialAccounts account : accounts) {
        	access_token=getAccessToken(account.getAppId(), account.getAppSecret());
            if(!StringUtil.isEmpty(access_token)) {
            	WxGlobalParam param = wxGlobalParamService.findByAccountId(account.getId());
            	Map<String, Object> paramMap = new HashMap<String, Object>();
            	paramMap.put("euserId", account.getEuserId());
            	paramMap.put("accountId", account.getId());
            	paramMap.put("accessToken", access_token);
            	if(param!=null){
            		updateList.add(paramMap);
            	}else{
            		addList.add(paramMap);
            	}
            }
		}
    	if(addList!=null&&addList.size()>0){
    		wxGlobalParamService.saveList(addList);
    	}
    	if(updateList!=null&&updateList.size()>0){
    		wxGlobalParamService.updateList(updateList);
    	}
    	infologger.info("execute initAndSetAccessToken End:{"+System.currentTimeMillis()+"}");  
    }
	
	/** 
     * 获取access_token
     * @param appid 凭证 
     * @param appsecret 密钥 
     * @return 
     */  
    public static String getAccessToken(String appid, String appsecret) {
        String access_token_url = ApplicationUtil.ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
        String access_json=HttpClientUtil.getInstance().doGetRequest(access_token_url);
        JSONObject jsonobj = JSONObject.fromObject(access_json);
        String access_token = null;
        if(jsonobj.has("access_token")){
        	access_token = jsonobj.getString("access_token");
        	infologger.info("获取access_token成功，sucMsg:"+access_json);
        }else{
        	errorlogger.error("获取access_token失败，errMsg:"+access_json);
        }
        return access_token;
    }
    
    /** 
     * 验证access_token是否有效，无效则获取最新access_token
     * @param appid 凭证 
     * @param appsecret 密钥 
     * @return 
     */  
    public static void checkAccessToken(OfficialAccounts accounts) {
    	ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletContextUtil.get());
    	wxGlobalParamService = (WxGlobalParamService) ac.getBean("wxGlobalParamService");
    	WxGlobalParam param=wxGlobalParamService.findByAccountId(accounts.getId());
		String tags_list_url = ApplicationUtil.TAGS_LIST_URL.replace("ACCESS_TOKEN", param.getAccessToken());
        String tags_json=HttpClientUtil.getInstance().doGetRequest(tags_list_url);
        JSONObject jsonobj = JSONObject.fromObject(tags_json);
        if(!jsonobj.has("tags")){
        	String accessToken = getAccessToken(accounts.getAppId(), accounts.getAppSecret());
			if(!StringUtil.isEmpty(accessToken)){
				param.setAccessToken(accessToken);
				wxGlobalParamService.update(param);
			}
        }
    }
}
