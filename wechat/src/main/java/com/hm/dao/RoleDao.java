package com.hm.dao;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.Role;

public interface RoleDao{
	
	/**
	 * 根据角色ID查询角色信息
	 * @param id 角色ID
	 */
	public Role find(@Param("id") Integer id);
}
