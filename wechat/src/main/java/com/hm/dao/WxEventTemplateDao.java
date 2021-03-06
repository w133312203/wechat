package com.hm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.WxEventTemplate;

public interface WxEventTemplateDao {
	
	/**
	 * 更新微信活动模板信息
	 * @param wxEventTemplate
	 */
	public void update(WxEventTemplate wxEventTemplate);
	
	/**
	 * 根据type和mark查询weixin系统内所有微信活动模板消息列表
	 * @param type 类型 0：未启动 1：已启动
	 * @param mark 是否执行标记  0：未执行  1：已执行
	 * @return
	 */
	public List<WxEventTemplate> findList(@Param("type") Integer type, @Param("mark") Integer mark);
	
}
