package com.hm.carService.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.carService.domain.CarImage;

public interface CarImageService extends BaseService<CarImage>{

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
	public List<CarImage> findList(Integer carId, Integer first, Integer max);
	
	/**
	 * 按类型查找车辆图片列表
	 * @param carId 车辆Id
	 * @param first 首页
	 * @param max 每页页数
	 * @param type 车辆类型
	 * @return
	 */
	public List<CarImage> findListByType(Integer carId, Integer type, Integer first, Integer max);
	
	/**
	 * 查找车辆图片列表总数
	 * @param carId 车辆Id
	 * @return
	 */
	public Integer findCount(Integer carId);
}
