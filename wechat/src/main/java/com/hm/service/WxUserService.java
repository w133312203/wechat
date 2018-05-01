package com.hm.service;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.github.pagehelper.PageInfo;
import com.hm.base.service.BaseBatchService;
import com.hm.base.service.BaseService;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxUser;

public interface WxUserService extends BaseService<WxUser>,BaseBatchService<WxUser>{
	
	/**
	 * 根据公众号id查询最大id的用户
	 * @param accountId
	 * @return
	 */
	public WxUser findMax(Integer accountId);
	
	/**
	 * 根据公众号id查询用户列表
	 * @param accountId
	 * @return
	 */
	public List<WxUser> findList(Integer accountId);
	
	/**
	 * 批量保存用户(保存详细信息)
	 * @param userList
	 */
	public void saveAllList(List<Map<String, Object>> userList);
	
	/**
	 * 删除此公众号下所有用户
	 * @param accountId 微信公众号id
	 */
	public void deleteList(Integer accountId);
	
	/**
	 * 分页查询用户列表
	 * @param accountId 微信公众号id
	 * @param tagId 	标签id
	 * @param search	搜索字段
	 * @param first	 	首页
	 * @param max		最大显示数
	 * @return
	 */
	public List<Map<String, Object>> findUserList(Integer accountId, Integer tagId, String search, Integer first, Integer max);
	
	/**
	 * 查询用户总数
	 * @param accountId 微信公众号id
	 * @param tagId 	标签id
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findUserCount(Integer accountId, Integer tagId, String search);
	
	/**
	 * 批量更新用户(根据openid)
	 * @param userList
	 */
	public void updateList(List<Map<String, Object>> userList);
	
	/**
	 * 根据openid查询用户信息
	 * @param openid 微信用户唯一标识
	 * @return
	 */
	public WxUser findByOpenid(String openid);
	
   /**
    * 根据渠道二维码id获取用户列表
    * @param cid
    * @param first
    * @param max
 * @return 
    */
	public List findListByChannelId(Integer cid, int first, int max);
    /**
     * 根据渠道二维码id获取用户列表
     * @param id
     * @return
     */
    public List findChannelListById(Integer id);

	public PageInfo<Map<String, Object>> findList(Integer cid, int first, int max) throws Exception;
	
	/**
	 * 检查是否用新用户
	 * @param accountId 微信公众号Id
	 * @param accessToken 全局accessToken
	 * @return
	 */
	public boolean checkNewUser(Integer accountId, String accessToken);
	
	/**
	 * 加载微信用户
	 * @param accountId 微信公众号Id
	 * @param accessToken 全局accessToken
	 * @return
	 */
	public void loadUser(Integer accountId, String accessToken);
	
	/**
	 * 创建新的微信用户
	 * @param userInfoMap
	 * @param accountId
	 * @return
	 */
	public WxUser createUser(Map userInfoMap, Integer accountId);

}
