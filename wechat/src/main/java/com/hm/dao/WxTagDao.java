package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseBatchDao;
import com.hm.base.dao.BaseDao;
import com.hm.domain.WxTag;

public interface WxTagDao extends BaseDao<WxTag>,BaseBatchDao<WxTag>{
	
	/**
	 * 根据微信内标签id查询标签信息
	 * @param tagId
	 * @return
	 */
	public WxTag findByTagId(@Param("accountId") Integer accountId, @Param("tagId") Integer tagId);
	
	/**
	 * 根据公众号id查询标签列表
	 * @param accountId
	 * @return
	 */
	public List<WxTag> findList(Integer accountId);
	
	/**
	 * 删除此公众号下所有用户标签
	 * @param accountId 微信公众号id
	 */
	public void deleteList(Integer accountId);

	public List<Map<String, Object>> findTagByName(String name);
}
