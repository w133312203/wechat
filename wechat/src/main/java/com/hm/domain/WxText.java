
package com.hm.domain;

/**
 * 文本内容表
 * 
 */
public class WxText {
	
	private Integer id;
	
	private Integer keywordsId;//回复规则ID
	
	private String content;//文本消息内容

	
	public Integer getId() {
		return id;
	}

	public Integer getKeywordsId() {
		return keywordsId;
	}

	public String getContent() {
		return content;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setKeywordsId(Integer keywordsId) {
		this.keywordsId = keywordsId;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

