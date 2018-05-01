package com.hm.base.dao;

import java.util.List;

public interface BaseBatchDao<T> {
	
	/**
	 * 批量增加
	 * @param t
	 */
	void saveList(List<T> list);
	
	/**
	 * 批量修改
	 * @param t
	 */
	void batchUpdate(List<T> list); 
	
	/**
	 * 批量删除
	 * @param t
	 */
	void batchDelete(List<T> list); 
	
}
