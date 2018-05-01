package com.hm.carService.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.carService.domain.CarGroup;
import com.hm.carService.domain.ConfigureContext;
import com.hm.carService.domain.ConfigureField;
import com.hm.carService.domain.Information;

public interface ConfigureFieldService extends BaseService<ConfigureField>{

	/**
	 * 查找配置字段列表
	 * @param softwareId 小程序Id
	 * @param search 字段名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<ConfigureField> findList(Integer softwareId, String search, Integer first, Integer max);
	
	/**
	 * 查找未配置的字段列表
	 * @param subdivisionId 车辆分类Id
	 * @return
	 */
	public List<ConfigureField> findNoConfigureField(Integer subdivisionId, Integer softwareId);
	
	/**
	 * 查找配置字段列表
	 * @param softwareId 小程序Id
	 * @return
	 */
	public List<ConfigureField> findAllList(Integer softwareId);
	
	/**
	 * 查找配置字段列表数量
	 * @param softwareId 小程序Id
	 * @param search 字段名称
	 * @return
	 */
	public Integer findCount(Integer softwareId, String search);
}
