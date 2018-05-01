package com.hm.domain;
import java.util.Date;

/**
 * 公众号表
 */

public class OfficialAccounts {

	private Integer id;
	
	private String code;//公众号识别码
	
	private Integer euserId;//后台管理员id
	
	private String headImage;//头像
	
	private String name;//公众号名称
	
	private String account;//公众号账号
	
	private String originalId;//原始id
	
	private Integer level;//级别	0：普通订阅号	1：普通服务号	2：认证订阅号	3：认证服务号/认证媒体/政府订阅号
	
	private String appId;//应用id
	
	private String appSecret;//应用密钥
	
	private String serverUrl;//服务器地址
	
	private String token;//token令牌
	
	private String qrcode;//二维码
	
	private String summary;//描述
	
	private Integer status = 1;//状态 1：正常	0：删除
	
	private Date createTime = new Date();//创建时间

	
	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public Integer getEuserId() {
		return euserId;
	}

	public String getName() {
		return name;
	}

	public String getAccount() {
		return account;
	}

	public String getOriginalId() {
		return originalId;
	}

	public Integer getLevel() {
		return level;
	}

	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public String getToken() {
		return token;
	}

	public String getQrcode() {
		return qrcode;
	}

	public String getSummary() {
		return summary;
	}

	public Integer getStatus() {
		return status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setEuserId(Integer euserId) {
		this.euserId = euserId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

}
