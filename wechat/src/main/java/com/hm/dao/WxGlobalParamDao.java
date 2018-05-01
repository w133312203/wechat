package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.WxGlobalParam;

public interface WxGlobalParamDao extends BaseDao<WxGlobalParam>{
	/**
	 * 批量添加公众号全局参数
	 * @param paramList
	 */
	public void saveList(@Param("paramList") List<Map<String, Object>> paramList);
	
	/**
	 * 更新公众号信息
	 * @param paramList
	 */
	public void updateList(@Param("paramList") List<Map<String, Object>> paramList);
	
	/**
	 * 根据公众号ID查询公众号全局参数
	 * @param accountId 公众号ID
	 * @return
	 */
	public WxGlobalParam findByAccountId(Integer accountId);
}
