package com.hm.domain;

/**
 * 公众号自定义二级菜单表
 */

public class WxSubMenu {

	private Integer id;
	
	private Integer accountId;//微信公众号id
	
	private Integer menuId;//一级菜单id
	
	private String name;//菜单标题，不超过16个字节，子菜单不超过60个字节
	
	private String type;//菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型
	
	private String key;//click等点击类型必须填写,菜单KEY值，用于消息接口推送，不超过128字节
	
	private String url;//网页链接，用户点击菜单可打开链接，不超过1024字节。type为miniprogram时，不支持小程序的老版本客户端将打开本url。

	private String appid;//miniprogram类型必须填写，小程序的appid（仅认证公众号可配置）
	
	private String pagepath;//miniprogram类型必须填写，小程序的页面路径

	
	public Integer getId() {
		return id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getKey() {
		return key;
	}

	public String getUrl() {
		return url;
	}

	public String getAppid() {
		return appid;
	}

	public String getPagepath() {
		return pagepath;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
	
}
