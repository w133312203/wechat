package com.hm.service;

import java.util.List;

import com.hm.domain.WxChannelUser;

public interface WxChannelUserService {
    /**
     * 根据微信状态将openid与二维码id保存
     * @param channelUser
     */
	void save(WxChannelUser channelUser);

      /**
       * 依据openid查找
       * @param fromUserName
       * @return
       */
	List<WxChannelUser> find(String fromUserName);
  /**
   * 依据channelid更改状态
   * @param channelUser
   */
	void updteStatus(WxChannelUser channelUser);
	

}
