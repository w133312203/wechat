package com.hm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.RoleDao;
import com.hm.domain.Role;
import com.hm.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleDao roleDao;
	
	@Override
	public Role find(Integer id) {
		
		return roleDao.find(id);
	}
	
}
