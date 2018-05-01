package com.hm.base.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hm.domain.EnterpriseUserPassport;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxChannel;
import com.hm.domain.WxChannelUser;
import com.hm.domain.WxGlobalParam;
import com.hm.domain.WxKeywords;
import com.hm.domain.WxNews;
import com.hm.domain.WxSystemReply;
import com.hm.domain.WxTag;
import com.hm.domain.WxText;
import com.hm.domain.WxUser;
import com.hm.domain.message.resp.Article;
import com.hm.domain.message.resp.NewsMessage;
import com.hm.domain.message.resp.TextMessage;
import com.hm.service.OfficialAccountsService;
import com.hm.service.WxChannelService;
import com.hm.service.WxChannelUserService;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxKeywordsService;
import com.hm.service.WxNewsService;
import com.hm.service.WxSystemReplyService;
import com.hm.service.WxTagService;
import com.hm.service.WxTextService;
import com.hm.service.WxUserService;
import com.hm.utils.AccessTokenUtil;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.EmojiFilterUtils;
import com.hm.utils.HttpUtils;
import com.hm.utils.MessageUtil;
import com.hm.utils.ServletContextUtil;
import com.hm.utils.StringUtil;
import com.hm.utils.WxApiUtil;

/**
 * 核心服务类
 * 
 */
public class CoreService {
     
    private static Logger errorlogger = Logger.getLogger("errorinfo");
	private static Logger infologger = Logger.getLogger("dayinfo");
	
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletContextUtil.get());
		//WxMessageService wxMessageService = (WxMessageService) ac.getBean("wxMessageService");
		WxKeywordsService wxKeywordsService = (WxKeywordsService) ac.getBean("wxKeywordsService");
		WxTextService wxTextService = (WxTextService) ac.getBean("wxTextService");
		WxNewsService wxNewsService = (WxNewsService) ac.getBean("wxNewsService");
		WxSystemReplyService wxSystemReplyService = (WxSystemReplyService) ac.getBean("wxSystemReplyService");
		OfficialAccountsService officialAccountsService = (OfficialAccountsService) ac.getBean("officialAccountsService");
		WxGlobalParamService wxGlobalParamService = (WxGlobalParamService) ac.getBean("wxGlobalParamService");
		WxUserService wxUserService = (WxUserService) ac.getBean("wxUserService");
		WxChannelUserService wxChannelUserService=(WxChannelUserService)ac.getBean("wxChannelUserService");
		WxChannelService wxChannelService=(WxChannelService)ac.getBean("wxChannelService");
		WxTagService wxTagService = (WxTagService) ac.getBean("wxTagService");
		// 默认返回的文本消息内容
		String responseMessage = "success";	
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.xmlToMap(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息id，64位整型
			String msgId = requestMap.get("MsgId");
			// 事件KEY值，与自定义菜单接口中KEY值对应
			String eventKey = requestMap.get("EventKey");
			
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//保存用户发来的文本消息
				/*WxMessage wxMessage = new WxMessage();
				wxMessage.setToUserName(toUserName);
				wxMessage.setFromUserName(fromUserName);
				wxMessage.setMsgId(Long.parseLong(msgId));
				wxMessage.setMsgType(0);
				wxMessage.setContent(requestMap.get("Content"));
				wxMessage.setCreateTime(new Date());
				wxMessageService.save(wxMessage);*/
				
				OfficialAccounts accounts = officialAccountsService.findByOriginalId(toUserName);
				WxSystemReply defaultReply = wxSystemReplyService.find(accounts.getId(), 1);
				if(defaultReply!=null&&!StringUtil.isEmpty(defaultReply.getKeywords())){
					WxKeywords wxKeywords = wxKeywordsService.findByKeywords(accounts.getId(), defaultReply.getKeywords());
					if(wxKeywords!=null&&wxKeywords.getType()==0){
						WxText wxText = wxTextService.findBykeywordsId(wxKeywords.getId());
						TextMessage textMessage = new TextMessage();
			            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			            textMessage.setToUserName(fromUserName);
			            textMessage.setFromUserName(toUserName);
			            textMessage.setCreateTime(System.currentTimeMillis());
			            textMessage.setContent(wxText.getContent());
			            responseMessage = MessageUtil.textMessageToXml(textMessage);
					}else if(wxKeywords!=null&&wxKeywords.getType()==1){
						List<WxNews> wxNewsList = wxNewsService.findList(wxKeywords.getId());
						if(wxNewsList!=null&&wxNewsList.size()>0){
							List<Article> articles = new ArrayList<Article>();
							for (WxNews wxNews : wxNewsList) {
								Article article = new Article();
								article.setTitle(wxNews.getTitle());
								article.setDescription(wxNews.getDescription());
								article.setPicUrl(wxNews.getPicUrl());
								article.setUrl(wxNews.getUrl());
								articles.add(article);
							}
							NewsMessage newsMessage = new NewsMessage();
							newsMessage.setToUserName(fromUserName);
							newsMessage.setFromUserName(toUserName);
							newsMessage.setCreateTime(System.currentTimeMillis());
							newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
							newsMessage.setArticleCount(wxNewsList.size());
							newsMessage.setArticles(articles);
							responseMessage = MessageUtil.newsMessageToXml(newsMessage);
						}
					}
				}
			}else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				// 事件类型
				String eventType = requestMap.get("Event");
				
				if(eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){
					OfficialAccounts accounts = officialAccountsService.findByOriginalId(toUserName);
					WxSystemReply joinReply = wxSystemReplyService.find(accounts.getId(), 0);
					if(joinReply!=null&&!StringUtil.isEmpty(joinReply.getKeywords())){
						WxKeywords wxKeywords = wxKeywordsService.findByKeywords(accounts.getId(), joinReply.getKeywords());
						if(wxKeywords!=null&&wxKeywords.getType()==0){
							WxText wxText = wxTextService.findBykeywordsId(wxKeywords.getId());
							TextMessage textMessage = new TextMessage();
				            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				            textMessage.setToUserName(fromUserName);
				            textMessage.setFromUserName(toUserName);
				            textMessage.setCreateTime(System.currentTimeMillis());
				            textMessage.setContent(wxText.getContent());
				            responseMessage = MessageUtil.textMessageToXml(textMessage);
						}else if(wxKeywords!=null&&wxKeywords.getType()==1){
							List<WxNews> wxNewsList = wxNewsService.findList(wxKeywords.getId());
							if(wxNewsList!=null&&wxNewsList.size()>0){
								List<Article> articles = new ArrayList<Article>();
								for (WxNews wxNews : wxNewsList) {
									Article article = new Article();
									article.setTitle(wxNews.getTitle());
									article.setDescription(wxNews.getDescription());
									article.setPicUrl(wxNews.getPicUrl());
									article.setUrl(wxNews.getUrl());
									articles.add(article);
								}
								NewsMessage newsMessage = new NewsMessage();
								newsMessage.setToUserName(fromUserName);
								newsMessage.setFromUserName(toUserName);
								newsMessage.setCreateTime(System.currentTimeMillis());
								newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
								newsMessage.setArticleCount(wxNewsList.size());
								newsMessage.setArticles(articles);
								responseMessage = MessageUtil.newsMessageToXml(newsMessage);
							}
						}
					}
					AccessTokenUtil.checkAccessToken(accounts);
					WxGlobalParam param = wxGlobalParamService.findByAccountId(accounts.getId());
					//根据openid判断是否为新老粉丝,更新状态
					if(!StringUtil.isEmpty(eventType)){
						int status=0;
						List<WxChannelUser> list = wxChannelUserService.find(fromUserName);
						if(list.size()>=2){
							status=4;
						}else{
							status=2;
						}
						WxChannelUser channelUser = new WxChannelUser();
						channelUser.setOpenid(fromUserName);
						String[] split = eventKey.split("_");
						Integer channelid = Integer.valueOf(split[1]);
						channelUser.setChannelid(channelid);
						channelUser.setStatus(status);
						channelUser.setCreateTime(new Date());
						wxChannelUserService.save(channelUser);
						String name =null;
						List<Map<String,Object>> findChannelListById = wxChannelService.findChannelListById(channelid);
						for (Map<String, Object> map : findChannelListById) {
							 name = map.get("name").toString();
						}
						Map tagMap = new HashMap<String, Object>();
						Map nameMap = new HashMap<String, Object>();
						nameMap.put("name", name);
						tagMap.put("tag", nameMap);
						JSON tagJson=JSONSerializer.toJSON(tagMap);
						String tagStr=WxApiUtil.addTag(param.getAccessToken(), tagJson.toString());
						JSONObject jsonobj = JSONObject.fromObject(tagStr);
						if(jsonobj.has("tag")){
							EnterpriseUserPassport sessionPassport = (EnterpriseUserPassport) request.getSession().getAttribute("euserPassport");
							Integer id = sessionPassport.getId();
							String saveTag = WxApiUtil.saveTag(name,id);
							WxTag wxTag = new WxTag();
							JSONObject jsontag = jsonobj.getJSONObject("tag");
							wxTag.setAccountId(accounts.getId());
							wxTag.setTagId(jsontag.getInt("id"));
							wxTag.setTagName(jsontag.getString("name"));
							wxTag.setCount(0);
							wxTagService.save(wxTag);
						}
					}
					
					//根据openid获取用户基本信息，保存到wx_user中
					String userInfoJson = WxApiUtil.getUserInfo(param.getAccessToken(), fromUserName);
	        		JSONObject userInfo = JSONObject.fromObject(userInfoJson);
					WxUser wxUser =wxUserService.findByOpenid(fromUserName);
					if(wxUser==null){
						wxUser = new WxUser();
						wxUser.setAccountId(accounts.getId());
						wxUser.setOpenid(fromUserName);
						if(userInfo.get("subscribe")!=null&&StringUtil.isNumber(userInfo.get("subscribe").toString())){
							wxUser.setSubscribe(userInfo.getInt("subscribe"));
						}else{
							wxUser.setSubscribe(null);
						}
						wxUser.setNickname(EmojiFilterUtils.filterEmoji(userInfo.getString("nickname")));
						wxUser.setSex(userInfo.getInt("sex"));
						wxUser.setLanguage(userInfo.getString("language"));
						wxUser.setCity(userInfo.getString("city"));
						wxUser.setProvince(userInfo.getString("province"));
						wxUser.setCountry(userInfo.getString("country"));
						wxUser.setHeadimgurl(userInfo.getString("headimgurl"));
		        		long subscribe_time = userInfo.getLong("subscribe_time");
		        		Date date = new Date(subscribe_time*1000);
		        		wxUser.setSubscribetime(date);
		        		if(userInfo.has("unionid")){
		        			wxUser.setUnionid(userInfo.getString("unionid"));
		        		}else{
		        			wxUser.setUnionid("");
		        		}
		        		wxUser.setRemark(userInfo.getString("remark"));
		        		wxUser.setGroupid(userInfo.getString("groupid"));
		        		JSONArray tagArray=userInfo.getJSONArray("tagid_list");
		        		String tagIdStr="";
		        		if(tagArray!=null&&tagArray.size()>0){
		        			for (int m = 0; m < tagArray.size(); m++) {
			        			tagIdStr+=tagArray.getInt(m)+",";
							}
		        			tagIdStr=tagIdStr.substring(0, tagIdStr.length()-1);
		        		}
		        		//wxTag.setTagId(Integer.valueOf(tagIdStr));
		        		wxUser.setTagIds(tagIdStr);
		        		wxUserService.save(wxUser);
					}
			
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)){
					//根据openid删除wx_user中的用户
					WxUser wxUser =wxUserService.findByOpenid(fromUserName);
					if(wxUser!=null){
						wxUserService.deleteById(wxUser.getId());
					}
					int status=0;
					List<WxChannelUser> list = wxChannelUserService.find(fromUserName);
					for (WxChannelUser wxChannelUser : list) {
						int channelid = wxChannelUser.getChannelid();
						if(list.size()>=2){
							status=3;
						}else{
							status=1;
						}
						WxChannelUser channelUser = new WxChannelUser();
						channelUser.setStatus(status);
						channelUser.setChannelid(channelid);
						wxChannelUserService.updteStatus(channelUser);
					}
					
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
					OfficialAccounts accounts = officialAccountsService.findByOriginalId(toUserName);
					WxKeywords wxKeywords = wxKeywordsService.findByKeywords(accounts.getId(), eventKey);
					List<WxNews> wxNewsList = wxNewsService.findList(wxKeywords.getId());
					if(wxNewsList!=null&&wxNewsList.size()>0){
						List<Article> articles = new ArrayList<Article>();
						for (WxNews wxNews : wxNewsList) {
							Article article = new Article();
							article.setTitle(wxNews.getTitle());
							article.setDescription(wxNews.getDescription());
							article.setPicUrl(wxNews.getPicUrl());
							article.setUrl(wxNews.getUrl());
							articles.add(article);
						}
						NewsMessage newsMessage = new NewsMessage();
						newsMessage.setToUserName(fromUserName);
						newsMessage.setFromUserName(toUserName);
						newsMessage.setCreateTime(System.currentTimeMillis());
						newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
						newsMessage.setArticleCount(wxNewsList.size());
						newsMessage.setArticles(articles);
						responseMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
				}else if(eventType.equals(MessageUtil.EVENT_TYPE_VIEW)){
					//TODO
				}
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return responseMessage;
	}

	
}
