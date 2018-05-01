package com.hm.carService.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.carService.domain.Car;

public interface CarDao extends BaseDao<Car>{
	
	/**
	 * 查找售卖车辆列表
	 * @param softwareId 小程序Id
	 * @param groupId 组别Id
	 * @param carName 车辆名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<Map> findCarList(@Param("softwareId")Integer softwareId, @Param("groupId")Integer groupId, @Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查找售卖车辆列表数量
	 * @param softwareId 小程序Id
	 * @param groupId 组别Id
	 * @param carName 车辆名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public Integer findCarCount(@Param("softwareId")Integer softwareId, @Param("groupId")Integer groupId, @Param("search")String search);
	
	
}
