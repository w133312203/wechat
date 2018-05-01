package com.hm.dao;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.EnterpriseUserInfo;

public interface EnterpriseUserInfoDao {
	
	/**
	 * 查找企业用户信息
	 * @param passportId 账号ID
	 */
	public EnterpriseUserInfo findEnterpriseUserInfoByPassportId(@Param("passportId")Integer passportId);
	
	/**
	 * 更新企业用户详细信息
	 * @param enterpriseUserInfo
	 */
	public void update(EnterpriseUserInfo enterpriseUserInfo);
	
	/**
	 * 保存企业用户详细信息
	 * @param enterpriseUserInfo
	 */
	public void save(EnterpriseUserInfo enterpriseUserInfo);
	
}
