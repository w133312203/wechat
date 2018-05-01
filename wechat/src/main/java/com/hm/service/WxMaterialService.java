	package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.domain.WxMaterial;

public interface WxMaterialService {
	
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
	 * 批量保存其他素材(保存前先删除此公众号该类型下所有其他素材)
	 * @param accountId 后台微信公众号ID
	 * @param type 素材类型
	 * @param materialList	其他素材集合
	 */
	public void saveList(Integer accountId, Integer type, List<Map<String, Object>> materialList);
	
	/**
	 * 根据mediaId删除该条素材
	 * @param mediaId 微信素材ID
	 */
	public void deleteByMediaId(String mediaId);
	
	/**
	 * 删除此公众号该类型下所有其他素材
	 * @param accountId 后台微信公众号ID
	 * @param type 素材类型
	 */
	public void deleteList(Integer accountId, Integer type);
	
	/**
	 * 分页查询图文信息素材列表
	 * @param accountId 后台微信公众号ID
	 * @param type 		素材类型
	 * @param search	搜索字段
	 * @param first	 	首页
	 * @param max		最大显示数
	 * @return
	 */
	public List<Map<String, Object>> findMaterialList(Integer accountId, Integer type, String search, Integer first, Integer max);
	
	/**
	 * 查询其他素材总数
	 * @param accountId 后台微信公众号ID
	 * @param type 		素材类型
	 * @param search	搜索字段
	 * @return
	 */
	public Integer findMaterialCount(Integer accountId, Integer type, String search);

}
