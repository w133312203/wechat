package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.domain.WxMenu;

public interface WxMenuService {
	
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
	public List<Map<String, Object>> findList(Integer accountId);
	
	/**
	 * 根据accountId删除该公众号下所有一级菜单信息
	 * @param accoutId 公众号ID
	 */
	public void deleteList(Integer accoutId);
	
}
