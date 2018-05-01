package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.domain.WxKeywords;

public interface WxKeywordsService {
	
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
	public WxKeywords findByKeywords(Integer accountId, String keywords);
	
	/**
	 * 根据公众号ID消息回复规则列表
	 * @param accountId 微信公众号id
	 * @return
	 */
	public List<Map<String, Object>> findList(Integer accountId);
	
	/**
	 * 分页查询消息回复规则列表
	 * @param accountId 微信公众号id
	 * @param type		回复类型：0:文本回复  1:图文回复  2:语音回复
	 * @param search	搜索字段
	 * @param first	 	首页
	 * @param max		最大显示数
	 * @return
	 */
	public List<Map<String, Object>> findKeywordsList(Integer accountId, Integer type, String search, Integer first, Integer max);
	
	/**
	 * 查询消息回复规则总数
	 * @param accountId 微信公众号id
	 * @param type		回复类型：0:文本回复  1:图文回复  2:语音回复
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findKeywordsCount(Integer accountId, Integer type, String search);
	
}
