	package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface EventUserDao {
	
	/**
	 * 根据活动ID查询所有用户信息
	 * @param eventId 活动ID
	 * @return
	 */
	public List<Map<String, Object>> findAllList(@Param("eventId") Integer eventId);
	
}
