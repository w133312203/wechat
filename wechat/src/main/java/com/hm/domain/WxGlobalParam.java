package com.hm.domain;

import java.util.Date;

/**
 * 公众号全局参数表
 */

public class WxGlobalParam {

	private Integer id;
	
	private Integer euserId;//后台管理员id
	
	private Integer accountId;//公众号id
	
	private String accessToken;//公众号的全局唯一接口调用凭据
	
	private Date expirationTime;//token过期时间

	public Integer getId() {
		return id;
	}

	public Integer getEuserId() {
		return euserId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEuserId(Integer euserId) {
		this.euserId = euserId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}
	
}
