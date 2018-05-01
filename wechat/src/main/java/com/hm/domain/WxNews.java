
package com.hm.domain;

/**
 * 图文内容表
 * 
 */
public class WxNews {
	
	private Integer id;
	
	private Integer keywordsId;//回复规则ID
	
	private String title;//图文消息名称
	
	private String description;//图文消息描述
	
	private String picUrl;//图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
	
	private String url;//点击图文消息跳转链接
	
	private Integer sortLevel;//排序 越小越靠前

	public Integer getId() {
		return id;
	}

	public Integer getKeywordsId() {
		return keywordsId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public String getUrl() {
		return url;
	}

	public Integer getSortLevel() {
		return sortLevel;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setKeywordsId(Integer keywordsId) {
		this.keywordsId = keywordsId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setSortLevel(Integer sortLevel) {
		this.sortLevel = sortLevel;
	}
	
}

