
package com.hm.domain;

/**
 * 素材表（其他类型（图片、语音、视频））
 * 
 */
public class WxMaterial {
	
	private Integer id;
	
	private Integer accountId;//后台微信公众号ID
	
	private String mediaId;//素材ID
	
	private String name;//素材名称
	
	private String url;//URL
	
	private String picUrl;//图片OOS存储路径
	
	private long updateTime;//素材的最后更新时间
	
	private Integer type;//0:图片 1:语音 2:视频

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}

