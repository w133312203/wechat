package com.hm.carService.dao;

import java.util.List;

import com.hm.base.dao.BaseDao;
import com.hm.carService.domain.CarGroup;
import com.hm.domain.WxTag;

public interface CarGroupDao extends BaseDao<CarGroup>{
	
	/**
	 * 根据小程序ID查找组别信息
	 * @param softwareId 小程序Id
	 * @return
	 */
	public List<CarGroup> findList(Integer softwareId);
	
}
