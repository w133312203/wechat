package com.hm.utils;

public class ApplicationUtil {
	
	/**
	 * 图片前缀
	 */
	public static String IMAGE_PREFIX = (String) SpringPropertiesUtil.getContextProperty("image_prefix");
	
	/**
	 * 主域名
	 */
	public static String MAIN_HTTP = (String) SpringPropertiesUtil.getContextProperty("main_http");
	
	/**
	 * 获取用户详细信息
	 */
	public static String USER_INFO_URL = (String) SpringPropertiesUtil.getContextProperty("user_info_url");
	
	/**
	 * 批量获取用户详细信息
	 */
	public static String BATCH_USER_INFO_URL = (String) SpringPropertiesUtil.getContextProperty("batch_user_info_url");
	
	
	
	
	
	/**
	 * 易会云域名
	 */
	public static String EHUI_HTTP_HEAD = (String) SpringPropertiesUtil.getContextProperty("ehui_http_head");
	
	/**
	 * eHub域名
	 */
	public static String EHUB_HTTP_HEAD = (String) SpringPropertiesUtil.getContextProperty("ehub_http_head");
	
	/**
	 * 微信公众号appid
	 */
	public static String WX_APPID = (String) SpringPropertiesUtil.getContextProperty("wx_appid");
	
	/**
	 * 微信公众号appSecret
	 */
	public static String WX_APPSECRET = (String) SpringPropertiesUtil.getContextProperty("wx_appSecret");
	
	/**
	 * 图片服务器域名
	 */
	public static String PIC_HEAD_URL = (String) SpringPropertiesUtil.getContextProperty("pic_head_url");
	
	/**
	 * 获取access_token
	 */
	public static String ACCESS_TOKEN_URL = (String) SpringPropertiesUtil.getContextProperty("access_token_url");
	
	/**
	 * 获取用户标签列表
	 */
	public static String TAGS_LIST_URL = (String) SpringPropertiesUtil.getContextProperty("tags_list_url");
	
	/**
	 * 添加用户标签
	 */
	public static String ADD_TAG_URL = (String) SpringPropertiesUtil.getContextProperty("add_tag_url");
	
	/**
	 * 编辑用户标签
	 */
	public static String UPDATE_TAG_URL = (String) SpringPropertiesUtil.getContextProperty("update_tag_url");
	
	/**
	 * 删除用户标签
	 */
	public static String DELETE_TAG_URL = (String) SpringPropertiesUtil.getContextProperty("delete_tag_url");
	
	/**
	 * 获取用户列表
	 */
	public static String USER_LIST_URL = (String) SpringPropertiesUtil.getContextProperty("user_list_url");
	
	/**
	 * 批量为用户打标签
	 */
	public static String BATCHTAGGING_URL = (String) SpringPropertiesUtil.getContextProperty("batchtagging_url");
	
	/**
	 * 批量为用户取消标签
	 */
	public static String BATCHUNTAGGING_URL = (String) SpringPropertiesUtil.getContextProperty("batchuntagging_url");
	
	/**
	 * 自定义菜单创建
	 */
	public static String CREATE_MENU_URL = (String) SpringPropertiesUtil.getContextProperty("create_menu_url");
	
	/**
	 * 自定义菜单查询
	 */
	public static String GET_MENU_URL = (String) SpringPropertiesUtil.getContextProperty("get_menu_url");
	
	/**
	 * 自定义菜单删除
	 */
	public static String DELETE_MENU_URL = (String) SpringPropertiesUtil.getContextProperty("delete_menu_url");
	
	/**
	 * 发送客服消息
	 */
	public static String SEND_CUSTOM_MESSAGE_URL = (String) SpringPropertiesUtil.getContextProperty("send_custom_message_url");
	
	/**
	 * 获取素材总数
	 */
	public static String MATERIALCOUNT_URL = (String) SpringPropertiesUtil.getContextProperty("materialcount_url");
	
	/**
	 * 获取素材列表
	 */
	public static String BATCHGET_MATERIAL_URL = (String) SpringPropertiesUtil.getContextProperty("batchget_material_url");
	
	/**
	 * 上传图文消息内的图片获取URL
	 */
	public static String UPLOADIMG_URL = (String) SpringPropertiesUtil.getContextProperty("uploadimg_url");
	
	/**
	 * 新增其他类型永久素材
	 */
	public static String ADD_MATERIAL_URL = (String) SpringPropertiesUtil.getContextProperty("add_material_url");
	
	/**
	 * 新增永久图文素材
	 */
	public static String ADD_NEWS_URL = (String) SpringPropertiesUtil.getContextProperty("add_news_url");
	
	/**
	 * 修改永久图文素材
	 */
	public static String UPDATE_NEWS_URL = (String) SpringPropertiesUtil.getContextProperty("update_news_url");
	
	/**
	 * 删除永久素材
	 */
	public static String DEL_MATERIAL_URL = (String) SpringPropertiesUtil.getContextProperty("del_material_url");
	
	/**
	 * 获取永久素材
	 */
	public static String GET_MATERIAL_URL = (String) SpringPropertiesUtil.getContextProperty("get_material_url");
	
	/**
	 * 获取模板列表
	 */
	public static String GET_TEMPLATE_URL = (String) SpringPropertiesUtil.getContextProperty("get_template_url");
	
	/**
	 * 删除模板
	 */
	public static String DELETE_TEMPLATE_URL = (String) SpringPropertiesUtil.getContextProperty("delete_template_url");
	
	/**
	 * 发送模板消息
	 */
	public static String SEND_TEMPLATE_URL = (String) SpringPropertiesUtil.getContextProperty("send_template_url");
	
	/**
	 * 获得模板ID
	 */
	public static String ADD_TEMPLATE_URL = (String) SpringPropertiesUtil.getContextProperty("add_template_url");

    public static String PERSIST_MATERIAL_URL = (String) SpringPropertiesUtil.getContextProperty("persist_mateial_url");
	
	/**
	 * 获取永久二维码
	 */
	public static String PERSIST_QRCODE_URL = (String)SpringPropertiesUtil.getContextProperty("get_persist_qrcode_url");
	
	/**
	 * 根据ticket获取二维码
	 */
	public static String GETQRCODE_TICKET_URL = (String)SpringPropertiesUtil.getContextProperty("getQrcode_ticket_url");
	
	/**
	 * 存储标签
	 */
	public static String TAG_URL = (String)SpringPropertiesUtil.getContextProperty("tag_url");
	
	/**
	 * 获得文件后缀
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileSuffix(String filename) {
		return filename.substring(filename.lastIndexOf(".") + 1);
	}
	
	/**
	 * 获取意见反馈的url
	 * 
	 */
	
	public static String OPTION_URL = (String)SpringPropertiesUtil.getContextProperty("option_url");
}
