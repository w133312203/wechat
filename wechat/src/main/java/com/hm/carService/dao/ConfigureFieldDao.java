package com.hm.carService.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.carService.domain.ConfigureContext;
import com.hm.carService.domain.ConfigureField;

public interface ConfigureFieldDao extends BaseDao<ConfigureField>{
	
	/**
	 * 查找配置字段列表
	 * @param softwareId 小程序Id
	 * @param search 字段名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<ConfigureField> findList(@Param("softwareId")Integer softwareId, @Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查找未配置的字段列表
	 * @param subdivisionId 车辆分类Id
	 * @return
	 */
	public List<ConfigureField> findNoConfigureField(@Param("subdivisionId")Integer subdivisionId, @Param("softwareId")Integer softwareId);
	
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
	public Integer findCount(@Param("softwareId")Integer softwareId, @Param("search")String search);
	
}
