	package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.domain.WxMaterialNews;

public interface WxMaterialNewsService {
	
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
	 * @param accountId 后台微信公众号ID
	 * @param newsList	图文信息素材集合
	 * @param type	保存方式 默认0：保存前先删除此公众号下所有图文信息素材	1：保存前不删除此公众号下所有图文信息素材
	 */
	public void saveList(Integer accountId, List<Map<String, Object>> newsList, Integer type);
	
	/**
	 * 根据mediaId删除该条素材
	 * @param mediaId 微信素材ID
	 */
	public void deleteByMediaId(String mediaId);
	
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
	public List<Map<String, Object>> findNewsList(Integer accountId, String search, Integer first, Integer max);
	
	/**
	 * 查询图文信息素材总数
	 * @param accountId 后台微信公众号ID
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findNewsCount(Integer accountId, String search);
	
	/**
	 * 根据图文消息素材ID查询图文信息素材列表
	 * @param accountId 后台微信公众号ID
	 * @param mediaId 图文消息素材ID
	 * @param alias 是否添加字段别名 0：否 (默认为0)	 1：是(编辑图文素材时使用)
	 * @return
	 */
	public List<Map<String, Object>> findListByMediaId(Integer accountId, String mediaId, Integer alias);
}
