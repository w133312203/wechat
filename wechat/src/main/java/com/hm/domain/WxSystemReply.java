package com.hm.domain;

/**
 * 公众号系统消息回复表
 */

public class WxSystemReply {

	private Integer id;
	
	private Integer accountId;//微信公众号ID
	
	private Integer type;//0:关注时回复  1:默认回复

	private String keywords;//关键字(回复规则)

	
	public Integer getId() {
		return id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public Integer getType() {
		return type;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
