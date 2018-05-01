package com.hm.dao;

import java.util.List;

import com.hm.domain.WxChannelUser;

public interface WxChannelUserDao {

	public void save(WxChannelUser channelUser);

	public Integer del(String fromUserName);

	public List<WxChannelUser> find(String openid);

	public void updteStatus(WxChannelUser channelUser);

}
