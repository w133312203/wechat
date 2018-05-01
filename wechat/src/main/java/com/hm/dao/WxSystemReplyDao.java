package com.hm.dao;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.WxSystemReply;

public interface WxSystemReplyDao {
	
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
	public WxSystemReply find(@Param("accountId") Integer accountId, @Param("type") Integer type);
	
}
