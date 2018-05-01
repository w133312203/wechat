package com.hm.dao;

import com.hm.domain.WxMessage;

public interface WxMessageDao {
	
	/**
	 * 保存用户发送的消息
	 * @param wxMessage
	 */
	public void save(WxMessage wxMessage);
}
