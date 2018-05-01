package com.hm.carService.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseBatchService;
import com.hm.base.service.BaseService;
import com.hm.carService.domain.ConfigureContext;
import com.hm.carService.domain.ConfigureField;

public interface ConfigureContextService extends BaseService<ConfigureContext>,BaseBatchService<ConfigureContext>{

	/**
	 * 查找配置字段列表
	 * @param subdivisionId 车辆分类Id
	 * @param search 字段名称
	 * @param first 首页
	 * @param max 每页页数
	 * @return
	 */
	public List<Map> findList(Integer subdivisionId, String search, Integer first, Integer max);
	
	/**
	 * 查找配置字段列表
	 * @param subdivisionId 车辆分类Id
	 * @return
	 */
	public List<Map> findAllList(Integer subdivisionId, Integer softwareId);
	
	/**
	 * 查找最大排序等级
	 * @param subdivisionId 车辆分类Id
	 * @return
	 */
	public Integer findMaxLevel(Integer subdivisionId);
	
	/**
	 * 查找配置字段列表数量
	 * @param subdivisionId 车辆分类Id
	 * @param search 字段名称
	 * @return
	 */
	public Map findCount(Integer subdivisionId, String search);
	
	/**
	 * 同步未添加的字段
	 */
	public void synConfigureField(Integer subdivisionId, Integer softwareId);
	
	/**
	 * 更新排序级别
	 * @param subdivisionId 车辆分类Id
	 * @param level 序号级别
	 * @return
	 */
	public void updateLevel(Integer subdivisionId, Integer level);
	
	/**
	 * 查找排序级别
	 * @param subdivisionId 车辆分类Id
	 * @param level 序号级别
	 * @return
	 */
	public List<Integer> findLevel(Integer subdivisionId, Integer level);
	
	/**
	 * 查找更新排序级别
	 * @param subdivisionId 车辆分类Id
	 * @param level 序号级别
	 * @return
	 */
	public void findUpdateLevel(Integer subdivisionId, Integer level, Integer contextId);
}
