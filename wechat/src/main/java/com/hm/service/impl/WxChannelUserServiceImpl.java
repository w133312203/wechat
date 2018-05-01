package com.hm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxChannelDao;
import com.hm.dao.WxChannelUserDao;
import com.hm.domain.WxChannelUser;
import com.hm.service.WxChannelUserService;
@Service("wxChannelUserService")
public class WxChannelUserServiceImpl implements WxChannelUserService {
	@Autowired
	WxChannelUserDao wxChannelUserDao;
	@Override
	public void save(WxChannelUser channelUser) {
		// TODO Auto-generated method stub
		wxChannelUserDao.save(channelUser);
	}
	/*@Override
	public Integer del(String fromUserName) {
		// TODO Auto-generated method stub
		return wxChannelUserDao.del(fromUserName);
	}*/
	@Override
	public List<WxChannelUser> find(String openid) {
		// TODO Auto-generated method stub
		return wxChannelUserDao.find(openid);
	}
	@Override
	public void updteStatus(WxChannelUser channelUser) {
		// TODO Auto-generated method stub
		wxChannelUserDao.updteStatus(channelUser);
	}

}
