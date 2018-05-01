package com.hm.carService.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.carService.domain.Car;

public interface CarService extends BaseService<Car>{

	/**
	 * 查找售卖车辆列表
	 * @param softwareId 小程序Id
	 * @param groupId 组别Id
	 * @param search 车辆名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<Map> findCarList(Integer softwareId, Integer groupId, String search, Integer first, Integer max);
	
	/**
	 * 查找售卖车辆列表数量
	 * @param softwareId 小程序Id
	 * @param groupId 组别Id
	 * @param search 车辆名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public Integer findCarCount(Integer softwareId, Integer groupId, String search);
	
}
