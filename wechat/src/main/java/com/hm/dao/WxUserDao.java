package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseBatchDao;
import com.hm.base.dao.BaseDao;
import com.hm.domain.WxUser;

public interface WxUserDao extends BaseDao<WxUser>, BaseBatchDao<WxUser>{
	
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
	List<WxUser> findList(Integer accountId);
	
	/**
	 * 批量保存用户(保存详细信息)
	 * @param userList
	 */
	public void saveAllList(@Param("userList") List<Map<String, Object>> userList);
	
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
	public List<Map<String, Object>> findUserList(@Param("accountId") Integer accountId, @Param("tagId") Integer tagId, @Param("search") String search, @Param("first") Integer first, @Param("max") Integer max);
	
	/**
	 * 查询用户总数
	 * @param accountId 微信公众号id
	 * @param tagId 	标签id
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findUserCount(@Param("accountId") Integer accountId, @Param("tagId") Integer tagId, @Param("search") String search);
	
	/**
	 * 批量更新用户(根据openid)
	 * @param userList
	 */
	public void updateList(@Param("userList") List<Map<String, Object>> userList);
	
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
	public List findListByChannelId(@Param("cid")Integer cid, @Param("first")int first, @Param("max")int max);
	 /**
	    * 根据渠道二维码id获取用户列表
	    * @param cid
	 * @return 
	    */
	public List findChannelListById(@Param("cid")Integer cid);

	public List<Map<String, Object>> findListById(Integer cid);
	
	public void updateTag(@Param("tagIdArr")String tagIdArr,@Param("list")List list);
	
	public void updateSingleTag(@Param("tagIdArr")String tagIdArr, @Param("openId")String openId);
	
}
