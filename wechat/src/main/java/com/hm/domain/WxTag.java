package com.hm.domain;

/**
 * 公众号用户标签表
 */

public class WxTag {

	private Integer id;
	
	private Integer accountId;//微信公众号id
	
	private Integer tagId;//微信标签id
	
	private String tagName;//标签名称
	
	private Integer count;//此标签下粉丝数

	
	public Integer getId() {
		return id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public Integer getCount() {
		return count;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
}
