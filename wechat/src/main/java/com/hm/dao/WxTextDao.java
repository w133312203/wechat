package com.hm.dao;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.WxText;

public interface WxTextDao {
	
	/**
	 * 保存文本内容
	 * @param wxText
	 */
	public void save(WxText wxText);
	
	/**
	 * 根据文本内容ID删除该条数据
	 * @param id 用户ID
	 */
	public void delete(Integer id);
	
	/**
	 * 更新文本内容
	 * @param wxText
	 */
	public void update(WxText wxText);
	
	/**
	 * 根据文本内容ID查询该条信息
	 * @param id
	 * @return
	 */
	public WxText find(Integer id);
	
	/**
	 * 根据文本规则ID查询文本内容
	 * @param keywordsId 文本规则ID
	 * @return
	 */
	public WxText findBykeywordsId(@Param("keywordsId") Integer keywordsId);
	
}
