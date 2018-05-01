package com.hm.service;

import com.hm.domain.Role;

public interface RoleService{
	
	
	/**
	 * 根据角色ID查询角色信息
	 * @param id 角色ID
	 */
	public Role find(Integer id);
}
