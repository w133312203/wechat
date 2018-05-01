package com.hm.carService.service;

import java.util.List;

import com.hm.base.service.BaseService;
import com.hm.carService.domain.CarGroup;

public interface CarGroupService extends BaseService<CarGroup>{

	/**
	 * 根据小程序ID查找组别信息
	 * @param softwareId 小程序Id
	 * @return
	 */
	public List<CarGroup> findList(Integer softwareId);
}
