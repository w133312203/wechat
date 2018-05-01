package com.hm.base.service;

public interface BaseService<T> {
	
	/**
	 * 增加
	 * @param t
	 */
	void save(T t);
	
	/**
	 * 修改
	 * @param t
	 */
	void update(T t);
	
	/**
	 * 按Id删除
	 * @param t
	 */
	void deleteById(Integer id);
	
	/**
	 * 按Id查找
	 * @param Id
	 */
	T findById(Integer id);
}
