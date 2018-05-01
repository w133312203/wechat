package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.domain.WxTemplate;

public interface WxTemplateService {
	
	/**
	 * 根据ID删除模板消息
	 * @param id 模板ID
	 */
	public void delete(Integer id);
	
	/**
	 * 根据ID查询模板消息
	 * @param id
	 * @return
	 */
	public WxTemplate find(Integer id);
	
	/**
	 * 删除此公众号下所有模板消息
	 * @param accountId 微信公众号ID
	 */
	public void deleteList(Integer accountId);
	
	/**
	 * 批量保存模板消息(保存前先删除此公众号下所有模板消息)
	 * @param accountId 微信公众号ID
	 * @param templateList 模板消息集合
	 */
	public void saveList(Integer accountId, List<Map<String, Object>> templateList);
	
	/**
	 * 根据公众号ID查询模板消息列表
	 * @param accountId
	 * @return
	 */
	public List<WxTemplate> findList(Integer accountId);
	
	/**
	 * 根据模板标题查询模板消息
	 * @param accountId 微信公众号ID
	 * @param title 模板标题
	 * @return
	 */
	public WxTemplate findByTitle(Integer accountId, String title);
	
	/**
	 * 根据微信公众号模板ID查找微信模板消息
	 * @param templateId 微信公众号模板ID
	 * @return
	 */
	public WxTemplate findByTemplateId(String templateId);
}
