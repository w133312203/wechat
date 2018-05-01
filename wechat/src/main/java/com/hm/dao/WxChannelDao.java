package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.Result;
import com.hm.domain.WxChannel;

public interface WxChannelDao {

	List<Map<String, Object>> findChannelList(@Param("id")String id, @Param("name") String name ,@Param("first") Integer first, @Param("max")Integer max);


	Integer findChannelCount(@Param("id")String id,@Param("name")String name);


	List findNameById(String channelId);


	void save(WxChannel wxchannel);


	Integer del(String id);


	WxChannel find(String channelId);


	void update(WxChannel wxChannel);


	void updateStatus(WxChannel wxChannel);


	Integer newUserSubcount(String name);


	Integer newOldSubcount(String name);


	Integer newUserUnubcount(String name);


	Integer newOldUnsubcount(String name);


	List<Map<String, Object>> findChannelListById(@Param("id")Integer id);


	List<Map<String, Object>> findChannelListByName(String name);


	List<Result> findChannelListByDay(@Param("id") String id, @Param("first") int first, @Param("max") int max);


	List<Result> newOldUnsubcount1(String nameCount);


	List<Result> newUserUnubcount1(String nameCount);


	List<Result> newOldSubcount1(String nameCount);


	List<Result> newUserSubcount1(String nameCount);


	List<Result> findChannelListByDayAndId(String id);

}
