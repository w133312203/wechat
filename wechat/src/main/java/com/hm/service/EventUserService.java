	package com.hm.service;

import java.util.List;
import java.util.Map;

public interface EventUserService {
	
	/**
	 * 根据活动ID查询所有用户信息
	 * @param eventId 活动ID
	 * @return
	 */
	public List<Map<String, Object>> findAllList(Integer eventId);
}
