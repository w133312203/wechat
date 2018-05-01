package com.hm.carService.dao;

import com.hm.base.dao.BaseDao;
import com.hm.carService.domain.Information;

public interface InformationDao extends BaseDao<Information>{
	
	/**
	 * 根据企业用户Id查找4S店信息
	 * @param euserId 小程序Id
	 * @return 4S店信息
	 */
	public Information findBySoftwareId(Integer softwareId);
	
}
