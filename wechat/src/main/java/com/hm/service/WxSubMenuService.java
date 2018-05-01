package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.domain.WxSubMenu;

public interface WxSubMenuService {
	
	/**
	 * 保存自定义二级级菜单信息
	 * @param wxSubMenu
	 */
	public void save(WxSubMenu wxSubMenu);
	
	/**
	 * 根据一级菜单ID查询二级级菜单列表
	 * @param menuId
	 * @return
	 */
	public List<Map<String, Object>> findList(Integer menuId);
	
	/**
	 * 根据accountId删除该公众号下所有二级菜单信息
	 * @param accoutId 公众号ID
	 */
	public void deleteList(Integer accoutId);
}
