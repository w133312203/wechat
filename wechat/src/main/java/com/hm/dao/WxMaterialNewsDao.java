	package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.WxMaterialNews;

public interface WxMaterialNewsDao {
	
	/**
	 * 更新单条图文素材
	 * @param materialNews
	 */
	public void update(WxMaterialNews materialNews);
	
	/**
	 * 根据单条图文素材ID查询图文素材信息
	 * @param id
	 * @return
	 */
	public WxMaterialNews find(Integer id);
	
	/**
	 * 批量保存图文信息素材
	 * @param newsList
	 */
	public void saveList(@Param("newsList") List<Map<String, Object>> newsList);
	
	/**
	 * 根据mediaId删除该条素材
	 * @param mediaId 微信素材ID
	 */
	public void deleteByMediaId(@Param("mediaId") String mediaId);
	
	/**
	 * 删除此公众号下所有图文信息素材
	 * @param accountId 后台微信公众号ID
	 */
	public void deleteList(Integer accountId);
	
	/**
	 * 分页查询图文信息素材列表
	 * @param accountId 后台微信公众号ID
	 * @param search	搜索字段
	 * @param first	 	首页
	 * @param max		最大显示数
	 * @return
	 */
	public List<Map<String, Object>> findNewsList(@Param("accountId") Integer accountId, @Param("search") String search, @Param("first") Integer first, @Param("max") Integer max);
	
	/**
	 * 查询图文信息素材总数
	 * @param accountId 后台微信公众号ID
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findNewsCount(@Param("accountId") Integer accountId, @Param("search") String search);
	
	/**
	 * 根据图文消息素材ID查询图文信息素材列表
	 * @param accountId 后台微信公众号ID
	 * @param mediaId 图文消息素材ID
	 * @return
	 */
	public List<Map<String, Object>> findListByMediaId(@Param("accountId") Integer accountId, @Param("mediaId") String mediaId, @Param("alias") Integer alias);
}
