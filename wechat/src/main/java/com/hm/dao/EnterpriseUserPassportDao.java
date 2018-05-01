package com.hm.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.EnterpriseUserPassport;

public interface EnterpriseUserPassportDao extends BaseDao<EnterpriseUserPassport>{
	
	/**
	 * 查找企业用户通行证
	 * @param email 邮箱
	 * @param mobileNum 电话
	 * @param password 密码
	 * @return 企业用户账户信息
	 */
	public EnterpriseUserPassport findEnterpriseUserPassport(@Param("email")String email, @Param("mobileNum")String mobileNum, @Param("password")String password);
	
	/**
	 * 根据企业用户id查询用户信息
	 * @param id 企业用户id
	 * @return
	 */
	public Map<String, Object> findInfo(Integer id);
	
}
