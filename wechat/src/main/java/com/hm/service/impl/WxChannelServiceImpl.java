package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxChannelDao;
import com.hm.domain.Result;
import com.hm.domain.WxChannel;
import com.hm.service.WxChannelService;
@Service("wxChannelService")
public class WxChannelServiceImpl implements WxChannelService{
	@Autowired
	WxChannelDao wxChannelDao;
	@Override
	public List<Map<String, Object>> findChannelList(String id,String name,Integer first, Integer max) {
		List<Map<String, Object>> channelList=wxChannelDao.findChannelList(id,name,first,max);
		return channelList;
	}

	@Override
	public Integer findChannelCount(String id,String name) {
		return wxChannelDao.findChannelCount(id,name);
	}

	@Override
	public List findNameById(String channelId) {
		return wxChannelDao.findNameById(channelId);
	}

	@Override
	public void save(WxChannel wxchannel) {
		wxChannelDao.save(wxchannel);
	}

	@Override
	public Integer del(String id) {
		
		return wxChannelDao.del(id);
	}

	@Override
	public WxChannel find(String channelId) {
		// TODO Auto-generated method stub
		return wxChannelDao.find(channelId);
	}

	@Override
	public void update(WxChannel wxChannel) {
		// TODO Auto-generated method stub
		wxChannelDao.update(wxChannel);
	}

	@Override
	public void updateStatus(WxChannel wxChannel) {
		// TODO Auto-generated method stub
		wxChannelDao.updateStatus(wxChannel);
	}

	@Override
	public Integer newUserSubcount(String name) {
		// TODO Auto-generated method stub
		return wxChannelDao.newUserSubcount(name);
	}

	@Override
	public Integer newOldSubcount(String name) {
		// TODO Auto-generated method stub
		return wxChannelDao.newOldSubcount(name);
	}

	@Override
	public Integer newUserUnubcount(String name) {
		// TODO Auto-generated method stub
		return wxChannelDao.newUserUnubcount(name);
	}

	@Override
	public Integer newOldUnsubcount(String name) {
		// TODO Auto-generated method stub
		return wxChannelDao.newOldUnsubcount(name);
	}

	@Override
	public List<Map<String, Object>> findChannelListById(Integer id) {
		// TODO Auto-generated method stub
		return wxChannelDao.findChannelListById(id);
	}

	@Override
	public List<Map<String, Object>> findChannelListByName(String name) {
		// TODO Auto-generated method stub
		return wxChannelDao.findChannelListByName(name);
	}

	@Override
	public List<Result> findChannelListByDay(String id, int first, int max) {
		// TODO Auto-generated method stub
		return wxChannelDao.findChannelListByDay(id,first,max);
	}

	@Override
	public List<Result> findChannelListByDayAndId(String id) {
		// TODO Auto-generated method stub
		return wxChannelDao.findChannelListByDayAndId(id);
	}


	
}
