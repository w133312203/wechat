package com.hm.service;

import com.hm.domain.WxMessage;

public interface WxMessageService {
	
	/**
	 * 保存用户发送的消息
	 * @param wxMessage
	 */
	public void save(WxMessage wxMessage);
}
