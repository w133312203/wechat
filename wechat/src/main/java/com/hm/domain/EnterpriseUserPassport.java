package com.hm.domain;
import java.util.Date;

/**
 * 企业用户表
 * @author magic
 */
public class EnterpriseUserPassport {

	private Integer id;
	
	private String email;//邮箱
	
	private String mobileNum;//手机号
	
	private String password;//密码
	
	private Integer activatEmail=0;//邮箱是否激活 0 未激活 1 激活
	
	private Integer activatMobileNum=0;//手机是否激活 0 未激活 1 激活
	
	private Integer roleId;//角色关联字段
	
	private String leaderId;//所属主帐号ID
	
	private Date expirationTime;//账户过期时间
	
	private Integer status;//0 禁用 1 启用
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getActivatEmail() {
		return activatEmail;
	}

	public void setActivatEmail(Integer activatEmail) {
		this.activatEmail = activatEmail;
	}
	
	public String getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	
	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Integer getActivatMobileNum() {
		return activatMobileNum;
	}

	public void setActivatMobileNum(Integer activatMobileNum) {
		this.activatMobileNum = activatMobileNum;
	}
	
}
