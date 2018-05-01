package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.EventUserDao;
import com.hm.service.EventUserService;

@Service("eventUserService")
public class EventUserServiceImpl implements EventUserService{
	
	@Autowired
	EventUserDao userDao;

	@Override
	public List<Map<String, Object>> findAllList(Integer eventId) {
		
		return userDao.findAllList(eventId);
	}
	
}
