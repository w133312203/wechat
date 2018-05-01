package com.hm.service;

import com.hm.base.service.BaseService;
import com.hm.domain.EnterpriseUserInfo;


public interface EnterpriseUserInfoService extends BaseService<EnterpriseUserInfo>{

	/**
	 * 查找企业用户信息
	 * @param passportId 账号ID
	 */
	public EnterpriseUserInfo findEnterpriseUserInfoByPassportId(Integer passportId);
	
	/**
	 * 更新企业用户最后一次登陆时间
	 * @param passportId 账号ID
	 */
	public EnterpriseUserInfo updateLastLoginTime(Integer passportId);
	
}
