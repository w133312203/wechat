package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.WxNews;

public interface WxNewsDao {
	
	/**
	 * 保存图文内容
	 * @param wxNews
	 */
	public void save(WxNews wxNews);
	
	/**
	 * 根据图文内容ID删除该条数据
	 * @param id 用户ID
	 */
	public void delete(Integer id);
	
	/**
	 * 更新图文内容
	 * @param wxNews
	 */
	public void update(WxNews wxNews);
	
	/**
	 * 根据图文内容ID查询该条信息
	 * @param id
	 * @return
	 */
	public WxNews find(Integer id);
	
	/**
	 * 查询图文内容列表
	 * @param keywordsId 图文规则ID
	 * @return
	 */
	public List<WxNews> findList(@Param("keywordsId") Integer keywordsId);
	
	/**
	 * 分页查询图文内容列表
	 * @param keywordsId 图文规则ID
	 * @param search	搜索字段
	 * @param first	 	首页
	 * @param max		最大显示数
	 * @return
	 */
	public List<Map<String, Object>> findNewsList(@Param("keywordsId") Integer keywordsId, @Param("search") String search, @Param("first") Integer first, @Param("max") Integer max);
	
	/**
	 * 查询图文内容总数
	 * @param keywordsId 图文规则ID
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findNewsCount(@Param("keywordsId") Integer keywordsId, @Param("search") String search);
	
}
