package com.hm.domain;

/**
 * 公众号消息回复关键词表(消息回复规则)
 */

public class WxKeywords {

	private Integer id;
	
	private Integer accountId;//微信公众号id
	
	private String keywords;//关键词(多个关键词请用|格开,例如: 美丽|漂亮|好看)
	
	private Integer type;//0:文本回复  1:图文回复  2:语音回复

	private Integer status;//0:完全匹配(用户输入的和此关键词一样才会触发)	1:包含匹配 (只要用户输入的文字包含本关键词就触发)

	
	public Integer getId() {
		return id;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public String getKeywords() {
		return keywords;
	}

	public Integer getType() {
		return type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
