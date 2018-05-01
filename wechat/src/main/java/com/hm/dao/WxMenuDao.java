package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.WxMenu;

public interface WxMenuDao {
	
	/**
	 * 保存自定义一级菜单信息
	 * @param wxMenu
	 */
	public void save(WxMenu wxMenu);
	
	/**
	 * 根据公众号id查询一级菜单列表
	 * @param accountId
	 * @return
	 */
	public List<Map<String, Object>> findList(@Param("accountId") Integer accountId);
	
	/**
	 * 根据accountId删除该公众号下所有一级菜单信息
	 * @param accoutId 公众号ID
	 */
	public void deleteList(Integer accoutId);
}
