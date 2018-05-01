package com.hm.domain;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业用户详细信息表
 * @author magic
 */
public class EnterpriseUserInfo {

	private Integer id;
	
	private Integer passportId;//账号关联字段
	
	private String headImage;//头像
	
	private String realName;//真实姓名
	
	private String companyName;//公司
	
	private String position;//职位
	
	private Date regTime = new Date();//注册时间
	
	private Date lastLoginTime;//最后登陆时间
	
	private BigDecimal moneys = new BigDecimal(0);//余额
	
	private Integer unreadMsgNum=0;//消息未读数
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getPassportId() {
		return passportId;
	}

	public void setPassportId(Integer passportId) {
		this.passportId = passportId;
	}
	
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public BigDecimal getMoneys() {
		return moneys;
	}

	public void setMoneys(BigDecimal moneys) {
		this.moneys = moneys;
	}

	public Integer getUnreadMsgNum() {
		return unreadMsgNum;
	}

	public void setUnreadMsgNum(Integer unreadMsgNum) {
		this.unreadMsgNum = unreadMsgNum;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
