	package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.domain.WxMaterial;

public interface WxMaterialDao {
	
	/**
	 * 保存其他素材
	 * @param material
	 */
	public void save(WxMaterial material);
	
	/**
	 * 更新其他素材
	 * @param material
	 */
	public void update(WxMaterial material);
	
	/**
	 * 根据其他素材ID查询其他素材信息
	 * @param id
	 * @return
	 */
	public WxMaterial find(Integer id);
	
	/**
	 * 批量保存其他素材
	 * @param materialList
	 */
	public void saveList(@Param("materialList") List<Map<String, Object>> materialList);
	
	/**
	 * 根据mediaId删除该条素材
	 * @param mediaId 微信素材ID
	 */
	public void deleteByMediaId(@Param("mediaId") String mediaId);
	
	/**
	 * 删除此公众号该类型下所有其他素材
	 * @param accountId 后台微信公众号ID
	 * @param type 素材类型
	 */
	public void deleteList(@Param("accountId") Integer accountId, @Param("type") Integer type);
	
	/**
	 * 分页查询图文信息素材列表
	 * @param accountId 后台微信公众号ID
	 * @param type 		素材类型
	 * @param search	搜索字段
	 * @param first	 	首页
	 * @param max		最大显示数
	 * @return
	 */
	public List<Map<String, Object>> findMaterialList(@Param("accountId") Integer accountId, @Param("type") Integer type, @Param("search") String search, @Param("first") Integer first, @Param("max") Integer max);
	
	/**
	 * 查询其他素材总数
	 * @param accountId 后台微信公众号ID
	 * @param type 		素材类型
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findMaterialCount(@Param("accountId") Integer accountId, @Param("type") Integer type, @Param("search") String search);

}
