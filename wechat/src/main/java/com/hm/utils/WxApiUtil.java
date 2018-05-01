package com.hm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

/**
 * 调用微信api工具类
 *
 */
public class WxApiUtil {
	
	/** 
     * 添加用户标签
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String addTag(String access_token, String tagJson) {
        String add_tag_url = ApplicationUtil.ADD_TAG_URL.replace("ACCESS_TOKEN", access_token);
        String tags_json = HttpUtils.doPost(add_tag_url, tagJson, "UTF-8");
        return tags_json;
    }
    
	/** 
     * 修改用户标签
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String updateTag(String access_token, String tagJson) {
        String update_tag_url = ApplicationUtil.UPDATE_TAG_URL.replace("ACCESS_TOKEN", access_token);
        String tags_json = HttpUtils.doPost(update_tag_url, tagJson, "UTF-8");
        return tags_json;
    }
	
	/** 
     * 删除用户标签
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String deleteTag(String access_token, String tagJson) {
        String delete_tag_url = ApplicationUtil.DELETE_TAG_URL.replace("ACCESS_TOKEN", access_token);
        String tags_json = HttpUtils.doPost(delete_tag_url, tagJson, "UTF-8");
        return tags_json;
    }
	
	/**
     * 获取用户标签列表
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String getTags(String access_token) {
        String tags_list_url = ApplicationUtil.TAGS_LIST_URL.replace("ACCESS_TOKEN", access_token);
        String tags_json=HttpClientUtil.getInstance().doGetRequest(tags_list_url);
        return tags_json;
    }
	
	/**
     * 获取用户列表
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String getUsers(String access_token, String next_openid) {
        String user_list_url = ApplicationUtil.USER_LIST_URL.replace("ACCESS_TOKEN", access_token).replace("NEXT_OPENID", next_openid);
        String users_json=HttpClientUtil.getInstance().doGetRequest(user_list_url);
        return users_json;
    }
	
	/**
     * 获取用户信息
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String getUserInfo(String access_token, String openid) {
        String user_info_url = ApplicationUtil.USER_INFO_URL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
        String user_info_json=HttpClientUtil.getInstance().doGetRequest(user_info_url);
        return user_info_json;
    }
	
	/**
     * 批量获取用户信息
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String batchGetUserInfo(String access_token,String opendIdJson) {
        String batch_user_info_url = ApplicationUtil.BATCH_USER_INFO_URL.replace("ACCESS_TOKEN", access_token);  
        String batch_user_info_json=HttpUtils.doPost(batch_user_info_url, opendIdJson, "UTF-8");
        return batch_user_info_json;
    }
	
	/** 
     * 批量为用户打标签
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String batchtagging(String access_token, String tagJson) {
        String batchtagging_url = ApplicationUtil.BATCHTAGGING_URL.replace("ACCESS_TOKEN", access_token);
        String result_json = HttpUtils.doPost(batchtagging_url, tagJson, "UTF-8");
        return result_json;
    }
	
	/** 
     * 批量为用户取消标签
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String batchuntagging(String access_token, String tagJson) {
        String batchuntagging_url = ApplicationUtil.BATCHUNTAGGING_URL.replace("ACCESS_TOKEN", access_token);
        String result_json = HttpUtils.doPost(batchuntagging_url, tagJson, "UTF-8");
        return result_json;
    }
	
	/** 
     * 自定义菜单创建
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String createMenu(String access_token, String menuJson) {
        String create_menu_url = ApplicationUtil.CREATE_MENU_URL.replace("ACCESS_TOKEN", access_token);
        String result_json = HttpUtils.doPost(create_menu_url, menuJson, "UTF-8");
        JSONObject jsonobj = JSONObject.fromObject(result_json);
        String errmsg = jsonobj.getString("errmsg");
        return errmsg;
    }
	
	/** 
     * 自定义菜单查询
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String getMenu(String access_token) {
        String get_menu_url = ApplicationUtil.GET_MENU_URL.replace("ACCESS_TOKEN", access_token);
        String menu_json = HttpClientUtil.getInstance().doGetRequest(get_menu_url);
        return menu_json;
    }
	
	/** 
     * 自定义菜单删除
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String deleteMenu(String access_token) {
        String delete_menu_url = ApplicationUtil.DELETE_MENU_URL.replace("ACCESS_TOKEN", access_token);
        String result_json = HttpClientUtil.getInstance().doGetRequest(delete_menu_url);
        JSONObject jsonobj = JSONObject.fromObject(result_json);
        String errmsg = jsonobj.getString("errmsg");
        return errmsg;
    }
	
	/** 
     * 发送客服消息
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String sendCustomMessage(String access_token, String messageJson) {
        String send_custom_message_url = ApplicationUtil.SEND_CUSTOM_MESSAGE_URL.replace("ACCESS_TOKEN", access_token);
        String message_json = HttpUtils.doPost(send_custom_message_url, messageJson, "UTF-8");
        return message_json;
    }
	
	/** 
     * 获取素材总数
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static Map<String, Integer> getMaterialCount(String access_token) {
        String materialcount_url = ApplicationUtil.MATERIALCOUNT_URL.replace("ACCESS_TOKEN", access_token);
        String result_json = HttpClientUtil.getInstance().doGetRequest(materialcount_url);
        JSONObject jsonobj = JSONObject.fromObject(result_json);
        Map<String, Integer> map = new HashMap<String, Integer>();
        if(jsonobj.has("image_count")){
        	map.put("voice_count", jsonobj.getInt("voice_count"));
            map.put("video_count", jsonobj.getInt("video_count"));
            map.put("image_count", jsonobj.getInt("image_count"));
            map.put("news_count", jsonobj.getInt("news_count"));
        }else{
        	map.put("access_token_invalid", 0);
        }
        return map;
    }
	
	/** 
     * 获取素材列表
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String batchGetMaterial(String access_token, String materialJson) {
        String batchget_material_url = ApplicationUtil.BATCHGET_MATERIAL_URL.replace("ACCESS_TOKEN", access_token);
        String materia_json = HttpUtils.doPost(batchget_material_url, materialJson, "UTF-8");
        return materia_json;
    }
	
	/** 
     * 上传图文消息内的图片获取URL
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String uploadImg(String access_token, String filePath) {
        String uploadimg_url = ApplicationUtil.UPLOADIMG_URL.replace("ACCESS_TOKEN", access_token);
        String imgUrl = null;
		try {
			imgUrl = FileUpload.send(uploadimg_url, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return imgUrl;
    }
	
	/** 
     * 新增其他类型永久素材
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String addMaterial(String access_token, String type, String filePath) {
        String add_material_url = ApplicationUtil.ADD_MATERIAL_URL.replace("ACCESS_TOKEN", access_token).replace("TYPE", type);
        String result = null;
		try {
			result = FileUpload.send(add_material_url, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return result;
    }
	
	/** 
     * 新增永久图文素材
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String addNews(String access_token, String materialNewsJson) {
        String add_news_url = ApplicationUtil.ADD_NEWS_URL.replace("ACCESS_TOKEN", access_token);
        String news_json = HttpUtils.doPost(add_news_url, materialNewsJson, "UTF-8");
        return news_json;
    }
	
	/** 
     * 修改永久图文素材
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String updateNews(String access_token, String materialNewsJson) {
        String update_news_url = ApplicationUtil.UPDATE_NEWS_URL.replace("ACCESS_TOKEN", access_token);
        String news_json = HttpUtils.doPost(update_news_url, materialNewsJson, "UTF-8");
        return news_json;
    }
	
	/** 
     * 删除永久素材
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String delMaterial(String access_token, String mediaIdJson) {
        String del_material_url = ApplicationUtil.DEL_MATERIAL_URL.replace("ACCESS_TOKEN", access_token);
        String news_json = HttpUtils.doPost(del_material_url, mediaIdJson, "UTF-8");
        return news_json;
    }
	
	/** 
     * 获取模板消息列表
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	@SuppressWarnings("rawtypes")
	public static List<Map<String, Object>> getTemplates(Integer accountId, String access_token) {
        String get_template_url = ApplicationUtil.GET_TEMPLATE_URL.replace("ACCESS_TOKEN", access_token);
        String result_json = HttpClientUtil.getInstance().doGetRequest(get_template_url);
        JSONObject jsonobj = JSONObject.fromObject(result_json);
        List<Map<String, Object>> templateList = null;
        if(jsonobj.has("template_list")){
        	templateList = new ArrayList<Map<String, Object>>();
        	JSONArray jsona = JSONArray.fromObject(jsonobj.get("template_list"));
            for(int i=0;i<jsona.size();i++) {
            	Map m = (Map) jsona.get(i);
            	Map<String, Object> templateMap = new HashMap<String, Object>();
            	templateMap.put("accountId", accountId);
            	templateMap.put("templateId", m.get("template_id").toString());
            	templateMap.put("title", m.get("title").toString());
            	templateMap.put("primaryIndustry", m.get("primary_industry").toString());
            	templateMap.put("deputyIndustry", m.get("deputy_industry").toString());
            	templateMap.put("content", m.get("content").toString());
            	templateMap.put("example", m.get("example").toString());
            	templateList.add(templateMap);
            }
        }
        return templateList;
    }
	
	/** 
     * 删除模板消息
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String deleteTemplate(String access_token, String templateJson) {
        String delete_template_url = ApplicationUtil.DELETE_TEMPLATE_URL.replace("ACCESS_TOKEN", access_token);
        String result_json = HttpUtils.doPost(delete_template_url, templateJson, "UTF-8");
        return result_json;
    }
	
	/** 
     * 发送模板消息
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String sendTemplate(String access_token, String templateJson) {
        String send_template_url = ApplicationUtil.SEND_TEMPLATE_URL.replace("ACCESS_TOKEN", access_token);
        String result_json = HttpUtils.doPost(send_template_url, templateJson, "UTF-8");
        return result_json;
    }
	
	/** 
     * 获得模板ID
     * @param access_token 公众号的全局唯一接口调用凭据 
     * @return 
     */
	public static String addTemplate(String access_token, String templateIdShort) {
        String add_template_url = ApplicationUtil.ADD_TEMPLATE_URL.replace("ACCESS_TOKEN", access_token);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("template_id_short", templateIdShort);
        JSON json=JSONSerializer.toJSON(map);
        String result_json = HttpUtils.doPost(add_template_url, json.toString(), "UTF-8");
        return result_json;
    }
	/**
	 * 获取永久素材
	 * @param access_token
	 * @param mediaIdJson
	 * @return
	 */
	public static String getPersistMaterial(String access_token, String mediaIdJson) {
        String persist_material_url = ApplicationUtil.PERSIST_MATERIAL_URL.replace("ACCESS_TOKEN", access_token);
        String news_json = HttpUtils.doPost(persist_material_url, mediaIdJson, "UTF-8");
        return news_json;
    }
	
	/**
	 * 获取永久二维码
	 * @param token
	 * @param mediaIdJson
	 * @return
	 */
	public static String getPersistQrcode(String token, String mediaIdJson) {
        String persist_qrcode_url = ApplicationUtil.PERSIST_QRCODE_URL.replace("TOKEN", token);
        String news_json = HttpUtils.doPost(persist_qrcode_url, mediaIdJson, "UTF-8");
        return news_json;
    }
	
	/**
	 * 根据ticket获取二维码
	 * @param ticket
	 * @return
	 * @throws Exception
	 */
	public static String getQrcodeByTicket(String ticket) throws Exception {
        String get_qrcode_url = ApplicationUtil.GETQRCODE_TICKET_URL.replace("TICKET", ticket);
        String news_json = HttpUtils.doPost(get_qrcode_url, "", "UTF-8");
        return news_json;
    }
	
	/** 
     * 存储ehub客户标签
     * @param 
     * @return 
     */
	public static String saveTag(String name,Integer id) {
        String tag_url =(ApplicationUtil.EHUB_HTTP_HEAD+ ApplicationUtil.TAG_URL).replace("NAME", name).replace("ID", String.valueOf(id));
        String tags_json = HttpUtils.doPost(tag_url,"", "UTF-8");
        return tags_json;
    }
	
}
