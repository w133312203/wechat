package com.hm.service;

import java.util.Map;

import com.hm.base.service.BaseService;
import com.hm.domain.EnterpriseUserPassport;


public interface EnterpriseUserPassportService extends BaseService<EnterpriseUserPassport>{
	
	/**
	 * 查找企业用户通行证
	 * @param email 邮箱
	 * @param mobilenum 电话
	 * @param password 密码
	 * @return 企业用户账户信息
	 */
	public EnterpriseUserPassport findEnterpriseUserPassport(String email, String mobilenum, String password);
	
	/**
	 * 查询企业用户信息
	 * @param id 企业用户id
	 * @return
	 */
	public Map<String, Object> findInfo(Integer id);

}
