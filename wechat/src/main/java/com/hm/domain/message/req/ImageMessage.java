
package com.hm.domain.message.req;

/**
 * 图片消息
 * 
 */
public class ImageMessage extends BaseMessage {
	
	private String PicUrl;//图片链接

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
}

