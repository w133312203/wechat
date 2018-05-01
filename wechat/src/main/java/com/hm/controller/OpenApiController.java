package com.hm.controller;  
  
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hm.base.controller.BaseCotroller;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxGlobalParam;
import com.hm.domain.WxTemplate;
import com.hm.service.OfficialAccountsService;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxTemplateService;
import com.hm.utils.AccessTokenUtil;
import com.hm.utils.StringUtil;
import com.hm.utils.WxApiUtil;
   

@Controller
@RequestMapping("/api")
public class OpenApiController extends BaseCotroller{ 

    @Resource  
    private OfficialAccountsService officialAccountsService;
    
    @Resource
    private WxGlobalParamService wxGlobalParamService;
    
    @Resource
    private WxTemplateService wxTemplateService;
    
    private static Logger errorlogger = Logger.getLogger("errorinfo");
	private static Logger infologger = Logger.getLogger("dayinfo");
	
	/**
	 * 发送模板消息
	 * @return
	 */
    @ResponseBody
    @RequestMapping(value = "/sendTemplate",method = RequestMethod.POST, produces="application/json")
	public  Map<String, Object> sendTemplate() {
    	Map<String, Object> map=new HashMap<String, Object>();
    	StringBuffer info=new StringBuffer();
		InputStream in;
		try {
			in = request.getInputStream();
			BufferedInputStream buf=new BufferedInputStream(in);
			byte[] buffer=new byte[1024];     
			int iRead;    
			while((iRead=buf.read(buffer))!=-1) {   
				info.append(new String(buffer,0,iRead,"UTF-8"));    
			}
			if(!info.toString().equals("")){
				JSONObject jsonObject= JSONObject.fromObject(info.toString());
				if(jsonObject.has("wxcode")&&jsonObject.get("wxcode")!=null){
					String code = jsonObject.getString("wxcode");
					OfficialAccounts accounts = null;
					if(StringUtil.isNumber(code)){
						accounts = officialAccountsService.findById(Integer.parseInt(code));
					}else{
						accounts = officialAccountsService.findByCode(code);
					}
					AccessTokenUtil.checkAccessToken(accounts);
    				WxGlobalParam param=wxGlobalParamService.findByAccountId(accounts.getId());
    				String result = WxApiUtil.sendTemplate(param.getAccessToken(), info.toString());
    				JSONObject resultObj= JSONObject.fromObject(result);
    				if(resultObj.has("errcode")&&resultObj.getInt("errcode")==0){
    					map.put("code", 1);
    					map.put("msg", "发送成功");
    				}
				}else{
					map.put("code", -1);
					map.put("msg", "wxcode不存在");
				}
			}else{
				map.put("code", 0);
				map.put("msg", "JSON数据不能为空");
			}
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw, true));
			String str = sw.toString();
			errorlogger.error ( "sendTemplate请求JSON数据异常_IOException : " + str ) ;
		}
			
		return map;
	}
    
    /**
     * 获取模板消息ID接口
     * @param wxcode 微信公众号在ehub内唯一标识
     * @param type 发送类型 0：用户注册成功提醒 1：用户审核通过提醒
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getTemplateId",method = RequestMethod.GET, produces="application/json")
	public  Map<String, Object> getTemplateId(@RequestParam String wxcode, @RequestParam Integer type) {
    	Map<String, Object> map=new HashMap<String, Object>();
    	if(!StringUtil.isEmpty(wxcode)&&type!=null){
    		String templateId="";
    		OfficialAccounts accounts = null;
    		if(StringUtil.isNumber(wxcode)){
    			accounts = officialAccountsService.findById(Integer.parseInt(wxcode));
    		}else{
    			accounts = officialAccountsService.findByCode(wxcode);
    		}
    		AccessTokenUtil.checkAccessToken(accounts);
			WxGlobalParam param=wxGlobalParamService.findByAccountId(accounts.getId());
    		if(type!=null&&type==0){
    			WxTemplate wxTemplate = wxTemplateService.findByTitle(accounts.getId(), "注册成功提醒");
        		if(wxTemplate==null){
        			String result = WxApiUtil.addTemplate(param.getAccessToken(), "OPENTM410088419");
        			JSONObject resultObj= JSONObject.fromObject(result);
        			if(resultObj.has("errcode")&&resultObj.getInt("errcode")==0){
        				templateId =  resultObj.getString("template_id");
        				List<Map<String, Object>> wxTemplateList = WxApiUtil.getTemplates(accounts.getId(), param.getAccessToken());
        				if(wxTemplateList!=null&&wxTemplateList.size()>0){
        					wxTemplateService.saveList(accounts.getId(), wxTemplateList);
        				}
        			}
        		}else{
        			templateId = wxTemplate.getTemplateId();
        		}
        		if(!StringUtil.isEmpty(templateId)){
        			map.put("templateId", templateId);
        			map.put("code", 1);
            		map.put("msg", "获取成功");
        		}else{
        			map.put("code", -1);
            		map.put("msg", "模版ID为空");
        		}
    		}else if(type!=null&&type==1){
    			WxTemplate wxTemplate = wxTemplateService.findByTitle(accounts.getId(), "用户资料审核通过通知");
        		if(wxTemplate==null){
        			String result = WxApiUtil.addTemplate(param.getAccessToken(), "OPENTM402105220");
        			JSONObject resultObj= JSONObject.fromObject(result);
        			if(resultObj.has("errcode")&&resultObj.getInt("errcode")==0){
        				templateId =  resultObj.getString("template_id");
        				List<Map<String, Object>> wxTemplateList = WxApiUtil.getTemplates(accounts.getId(), param.getAccessToken());
        				if(wxTemplateList!=null&&wxTemplateList.size()>0){
        					wxTemplateService.saveList(accounts.getId(), wxTemplateList);
        				}
        			}
        		}else{
        			templateId = wxTemplate.getTemplateId();
        		}
        		if(!StringUtil.isEmpty(templateId)){
        			map.put("templateId", templateId);
        			map.put("code", 1);
            		map.put("msg", "获取成功");
        		}else{
        			map.put("code", -1);
            		map.put("msg", "模版ID为空");
        		}
    		}
    	}else{
    		map.put("code", 0);
    		map.put("msg", "参数不正确");
    	}
		return map;
	}
    
} 