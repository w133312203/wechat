package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.WxKeywords;

public interface WxKeywordsDao {
	
	/**
	 * 保存消息回复规则
	 * @param wxKeywords
	 */
	public void save(WxKeywords wxKeywords);
	
	/**
	 * 根据消息回复规则ID删除该条数据
	 * @param id 用户ID
	 */
	public void delete(Integer id);
	
	/**
	 * 更新消息回复规则
	 * @param wxKeywords
	 */
	public void update(WxKeywords wxNewsKey);
	
	/**
	 * 根据消息回复规则ID查询该条信息
	 * @param id
	 * @return
	 */
	public WxKeywords find(Integer id);
	
	/**
	 * 根据消息回复关键字查询该条信息
	 * @param wxKeywords
	 * @return
	 */
	public WxKeywords findByKeywords(@Param("accountId") Integer accountId, @Param("keywords") String keywords);
	
	/**
	 * 根据公众号ID消息回复规则列表
	 * @param accountId 微信公众号id
	 * @return
	 */
	public List<Map<String, Object>> findList(@Param("accountId") Integer accountId);
	
	/**
	 * 分页查询消息回复规则列表
	 * @param accountId 微信公众号id
	 * @param type		回复类型：0:文本回复  1:图文回复  2:语音回复
	 * @param search	搜索字段
	 * @param first	 	首页
	 * @param max		最大显示数
	 * @return
	 */
	public List<Map<String, Object>> findKeywordsList(@Param("accountId") Integer accountId, @Param("type") Integer type, @Param("search") String search, @Param("first") Integer first, @Param("max") Integer max);
	
	/**
	 * 查询消息回复规则总数
	 * @param accountId 微信公众号id
	 * @param type		回复类型：0:文本回复  1:图文回复  2:语音回复
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findKeywordsCount(@Param("accountId") Integer accountId, @Param("type") Integer type, @Param("search") String search);
	
}
