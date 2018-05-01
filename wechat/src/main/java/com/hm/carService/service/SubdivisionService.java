package com.hm.carService.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.carService.domain.Subdivision;

public interface SubdivisionService extends BaseService<Subdivision>{

	/**
	 * 查找售卖车辆列表
	 * @param carId 车辆Id
	 * @param search 车辆名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<Map> findList(Integer carId, String search, Integer first, Integer max);
	
	/**
	 * 查找售卖车辆列表数量
	 * @param carId 车辆Id
	 * @param search 车辆名称
	 * @return
	 */
	public Integer findCount(Integer carId, String search);
	
	/**
	 * 查找售卖车辆列表
	 * @param carId 车辆Id
	 * @return
	 */
	public List<Subdivision> findListByOBJ(Integer carId);
	
}
