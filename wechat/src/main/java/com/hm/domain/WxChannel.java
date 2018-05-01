package com.hm.domain;

import java.util.Date;

public class WxChannel {
private int id; // 渠道表id
private  int newuser;//新粉丝
private  int olduser;//老粉丝
private String name; //渠道名称
private int status; //状态 1 新用户取消关注   2新用户关注  3老用户取消关注   4老用户关注
private Date createTime = new Date();//创建时间
private String url;//二维码保存路径
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public int getNewuser() {
	return newuser;
}
public void setNewuser(int newuser) {
	this.newuser = newuser;
}
public int getOlduser() {
	return olduser;
}
public void setOlduser(int olduser) {
	this.olduser = olduser;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public Date getCreateTime() {
	return createTime;
}
public void setCreateTime(Date createTime) {
	this.createTime = createTime;
}



}
