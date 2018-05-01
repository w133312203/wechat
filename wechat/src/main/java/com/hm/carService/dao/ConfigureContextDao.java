package com.hm.carService.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseBatchDao;
import com.hm.base.dao.BaseDao;
import com.hm.carService.domain.ConfigureContext;

public interface ConfigureContextDao extends BaseDao<ConfigureContext>,BaseBatchDao<ConfigureContext>{
	
	/**
	 * 查找配置字段列表
	 * @param subdivisionId 车辆分类Id
	 * @param search 字段名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<Map> findList(@Param("subdivisionId")Integer subdivisionId, @Param("search")String search, @Param("first")Integer first, @Param("max")Integer max);
	
	/**
	 * 查找配置字段列表
	 * @param subdivisionId 车辆分类Id
	 * @return
	 */
	public List<Map> findAllList(@Param("subdivisionId")Integer subdivisionId, @Param("softwareId")Integer softwareId);
	
	/**
	 * 查找最大排序等级
	 * @param subdivisionId 车辆分类Id
	 * @return
	 */
	public Integer findMaxLevel(Integer subdivisionId);
	
	/**
	 * 更新排序级别
	 * @param subdivisionId 车辆分类Id
	 * @param level 序号级别
	 * @return
	 */
	public void updateLevel(@Param("subdivisionId")Integer subdivisionId, @Param("level")Integer level);
	
	/**
	 * 查找排序级别
	 * @param subdivisionId 车辆分类Id
	 * @param level 序号级别
	 * @return
	 */
	public List<Integer> findLevel(@Param("subdivisionId")Integer subdivisionId, @Param("level")Integer level);
	
	/**
	 * 查找配置字段列表数量
	 * @param subdivisionId 车辆分类Id
	 * @param search 字段名称
	 * @return
	 */
	public Map<String,Integer> findCount(@Param("subdivisionId")Integer subdivisionId, @Param("search")String search);
	
}
