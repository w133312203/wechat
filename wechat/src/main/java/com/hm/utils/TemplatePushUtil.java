package com.hm.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxEventTemplate;
import com.hm.domain.WxTemplate;
import com.hm.service.EventUserService;
import com.hm.service.OfficialAccountsService;
import com.hm.service.WxEventTemplateService;
import com.hm.service.WxTemplateService;

public class TemplatePushUtil {
	
	private static Logger errorlogger = Logger.getLogger("errorinfo");
	private static Logger infologger = Logger.getLogger("dayinfo");
	
	/**
	 *推送微信活动模板消息 
	 * @throws InterruptedException 
	 */
    public static void push(ApplicationContext ac) {
    	final OfficialAccountsService officialAccountsService = (OfficialAccountsService) ac.getBean("officialAccountsService");
    	final WxEventTemplateService wxEventTemplateService = (WxEventTemplateService) ac.getBean("wxEventTemplateService");
    	final WxTemplateService wxTemplateService = (WxTemplateService) ac.getBean("wxTemplateService");
    	final EventUserService eventUserService = (EventUserService) ac.getBean("eventUserService");
    	final List<WxEventTemplate> templateList = wxEventTemplateService.findList(1, 0);
    	ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new Thread(){
			public void run() {
				for (final WxEventTemplate wxEventTemplate : templateList) {
					long runningTime = wxEventTemplate.getRunningTime().getTime();
					long newTime = new Date().getTime();
					if(runningTime>=newTime){
						if(wxEventTemplate.getMark()==0){
							final List<Map<String, Object>> userList = eventUserService.findAllList(wxEventTemplate.getEventId());
				    		WxTemplate wxTemplate = wxTemplateService.findByTemplateId(wxEventTemplate.getTemplateId());
				    		if(wxTemplate!=null){
				    			final OfficialAccounts accounts = officialAccountsService.findById(wxTemplate.getAccountId());
				    			String content = wxTemplate.getContent();
				    			String[] strArray = content.split("\n");
				    			final List<String> list = new ArrayList<String>();
				    			for (String str : strArray) {
				    				String[] sArray = str.split("：");
				    				if(sArray.length==1){
				    					if(sArray[0].indexOf("first")!=-1){
				    						list.add("first");
				    					}else if(sArray[0].indexOf("remark")!=-1){
				    						list.add("remark");
				    					}else if(sArray[0].indexOf("content")!=-1){
				    						list.add("content");
				    					}
				    				}else{
				    					list.add(sArray[1].substring(2, sArray[1].length()-7));
				    				}
				    			}
				    			Timer timer = new Timer();
				                timer.schedule(new TimerTask() {
				    				
				    				@SuppressWarnings("unchecked")
									@Override
				    				public void run() {
				    					if(wxEventTemplate.getType()==1){
				    						String content = wxEventTemplate.getContent();
					    					if(!StringUtil.isEmpty(content)){
					    						JSONObject  jsonObject = JSONObject.fromObject(content);
					    						Map<String, Object> map = (Map<String, Object>)jsonObject;
					    						for (Map<String, Object> userMap : userList) {
					    							Map<String, Object> templateMap = new HashMap<String, Object>();
					            					Map<String, Object> miniprogramMap = new HashMap<String, Object>();
					            					Map<String, Object> dataMap = new HashMap<String, Object>();
					            					templateMap.put("wxcode", accounts.getCode());
					            					templateMap.put("touser", userMap.get("openid"));
					            					templateMap.put("template_id", wxEventTemplate.getTemplateId());
					            					templateMap.put("url", wxEventTemplate.getUrl());
					            					miniprogramMap.put("appid", "");
					            					miniprogramMap.put("pagepath", "");
					            					templateMap.put("miniprogram", miniprogramMap);
					            					for (String str : list) {
					            						Map<String, Object> caseMap = new HashMap<String, Object>();
					            						caseMap.put("value", map.get(str));
					            						caseMap.put("color", "#173177");
					            						dataMap.put(str, caseMap);
					        						}
					            					templateMap.put("data", dataMap);
					            					JSON templateJson=JSONSerializer.toJSON(templateMap);
					            					String result = HttpUtils.doPost(ApplicationUtil.MAIN_HTTP+"api/sendTemplate", templateJson.toString(), "UTF-8");
													JSONObject jsonObj= JSONObject.fromObject(result);
													if(jsonObj.getInt("code")==1){
														wxEventTemplate.setMark(1);
														wxEventTemplateService.update(wxEventTemplate);
													}
												}
					    					}
				    					}
				    				}
				    			}, wxEventTemplate.getRunningTime());
				    		}
						}
					}
				}
			}
		});
    }
    
}
