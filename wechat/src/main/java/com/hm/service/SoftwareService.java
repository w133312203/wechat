package com.hm.service;

import com.hm.base.service.BaseService;
import com.hm.domain.Software;

public interface SoftwareService extends BaseService<Software>{

	/**
	 * 根据企业用户Id查找小程序信息
	 * @param euserId 企业用户Id
	 * @param type 小程序类型
	 * @return 
	 */
	public Software findByEuserId(Integer euserId, Integer type);
}
