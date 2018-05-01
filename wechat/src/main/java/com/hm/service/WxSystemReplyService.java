package com.hm.service;

import com.hm.domain.WxSystemReply;

public interface WxSystemReplyService {
	
	/**
	 * 保存系统回复信息
	 * @param wxSystemReply
	 */
	public void save(WxSystemReply wxSystemReply);
	
	/**
	 * 更新系统回复信息
	 * @param wxSystemReply
	 */
	public void update(WxSystemReply wxSystemReply);
	
	/**
	 * 根据后台微信公众号ID和回复类型查询系统回复信息
	 * @param accountId	微信公众号ID
	 * @param type	0:关注时回复  1:默认回复
	 * @return
	 */
	public WxSystemReply find(Integer accountId, Integer type);
	
}
