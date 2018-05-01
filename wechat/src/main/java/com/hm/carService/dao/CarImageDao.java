package com.hm.carService.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.carService.domain.CarImage;

public interface CarImageDao extends BaseDao<CarImage>{
	
	/**
	 * 根据售车Id查找车辆图片列表
	 * @param carId 车辆Id
	 */
	public List<CarImage> findByCarId(Integer carId);
	
	/**
	 * 查找车辆图片列表
	 * @param carId 车辆Id
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<CarImage> findList(@Param("carId")Integer carId, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 按类型查找车辆图片列表
	 * @param carId 车辆Id
	 * @param first 首页
	 * @param max 每页页数
	 * @param type 车辆类型
	 * @return
	 */
	public List<CarImage> findListByType(@Param("carId")Integer carId, @Param("type")Integer type, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查找车辆图片列表总数
	 * @param carId 车辆Id
	 * @return
	 */
	public Integer findCount(@Param("carId")Integer carId);
	
}
