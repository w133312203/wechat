package com.hm.domain;

import java.util.Date;

/**
 * 微信活动模板消息推送表
 */
public class WxEventTemplate {
	
	private Integer id;
	
	private Integer accountId;//微信公众号ID
	
	private Integer eventId;//活动ID
	
	private String templateId;//微信自身模板ID
	
	private String title;//模板标题
	
	private String content;//发送内容
	
	private String url;//模板跳转链接
	
	private Date runningTime;//执行时间
	
	private Integer type;//类型 0：未启动 1：已启动
	
	private Integer mark;//是否执行标记  0：未执行  1：已执行
	
	private String userIds;//指定发送用户ID字符串(,隔开)
	
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

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Date getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(Date runningTime) {
		this.runningTime = runningTime;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMark() {
		return mark;
	}
	
	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	
}
