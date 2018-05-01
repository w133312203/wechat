package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.domain.Result;
import com.hm.domain.WxChannel;

public interface WxChannelService {
    /**
     * 获取渠道列表
     * @param cid
     * @param name
     * @param first
     * @param max
     * @return
     */
	List<Map<String, Object>> findChannelList(String cid, String name, Integer first, Integer max);
     /**
      * 获取渠道列表总数
      * @param id
      * @param name
      * @return
      */
	Integer findChannelCount(String id, String name);
    /**
     * 通过id获取渠道列表展示二维码
     * @param channelId
     * @return
     */
	
	List findNameById(String channelId);
    /**
     * 保存
     * @param wxchannel
     */
	void save(WxChannel wxchannel);
   /**
    * 删除
    * @param id
    * @return
    */
	Integer del(String id);
    /**
     * 通过id获取渠道对象
     * @param channelId
     * @return
     */
	WxChannel find(String channelId);
    /**
     * 将url入库
     * @param wxChannel
     */
	void update(WxChannel wxChannel);
    /**
     * 更新状态
     * @param wxChannel
     */
	void updateStatus(WxChannel wxChannel);
	 /**
     * 依据名字统计新用户关注人数
     * @param name
     * @return
     */
	Integer newUserSubcount(String name);
	 /**
     * 依据名字统计新用户取消人数
     * @param name
     * @return
     */
	Integer newOldSubcount(String name);
	 /**
     * 依据名字统计老用户关注人数
     * @param name
     * @return
     */
	Integer newUserUnubcount(String name);
    /**
     * 依据名字统计老用户取消人数
     * @param name
     * @return
     */
	Integer newOldUnsubcount(String name);
    /**
     * 依据id查出数据导出到excel中
     * @param id
     * @return
     */
	List<Map<String, Object>> findChannelListById(Integer id);
    /**
     * 依据渠道名称获取渠道列表
     * @param name
     * @return
     */
	List<Map<String, Object>> findChannelListByName(String name);
	List<Result> findChannelListByDay(String id, int first, int max);
	List<Result> findChannelListByDayAndId(String id);
	

}
