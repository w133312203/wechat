package com.hm.carService.service;

import com.hm.base.service.BaseService;
import com.hm.carService.domain.CarGroup;
import com.hm.carService.domain.Information;

public interface InformationService extends BaseService<Information>{

	/**
	 * 根据企业用户Id查找4S店信息
	 * @param softwareId 小程序Id
	 * @return 4S店信息
	 */
	public Information findBySoftwareId(Integer softwareId);
}
