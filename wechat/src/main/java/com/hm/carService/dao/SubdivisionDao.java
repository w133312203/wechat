package com.hm.carService.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.carService.domain.Subdivision;

public interface SubdivisionDao extends BaseDao<Subdivision>{
	
	/**
	 * 查找售卖车辆列表
	 * @param carId 车辆Id
	 * @param search 车辆名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<Map> findList(@Param("carId")Integer carId, @Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查找售卖车辆列表数量
	 * @param carId 车辆Id
	 * @param search 车辆名称
	 * @return
	 */
	public Integer findCount(@Param("carId")Integer carId, @Param("search")String search);
	
	/**
	 * 查找售卖车辆列表
	 * @param carId 车辆Id
	 * @return
	 */
	public List<Subdivision> findListByOBJ(@Param("carId")Integer carId);
	
	
}
