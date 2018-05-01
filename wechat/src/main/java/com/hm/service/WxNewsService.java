package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.domain.WxNews;

public interface WxNewsService {
	
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
	public List<WxNews> findList(Integer keywordsId);
	
	/**
	 * 分页查询图文内容列表
	 * @param keywordsId 图文规则ID
	 * @param search	搜索字段
	 * @param first	 	首页
	 * @param max		最大显示数
	 * @return
	 */
	public List<Map<String, Object>> findNewsList(Integer keywordsId, String search, Integer first, Integer max);
	
	/**
	 * 查询图文内容总数
	 * @param keywordsId 图文规则ID
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findNewsCount(Integer keywordsId, String search);
	
}
