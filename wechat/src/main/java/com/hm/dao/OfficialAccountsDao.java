package com.hm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.OfficialAccounts;

public interface OfficialAccountsDao extends BaseDao<OfficialAccounts>{
	
	/**
	 * 根据公众号唯一编码查询公众号信息
	 * @param code 公众号唯一编码
	 * @return
	 */
	public OfficialAccounts findByCode(@Param("code") String code);
	
	/**
	 * 根据appId查询公众号信息
	 * @param appId
	 * @return
	 */
	public OfficialAccounts findByAppId(@Param("appId") String appId);
	
	/**
	 * 根据原始ID查询公众号信息
	 * @param originalId 原始ID
	 * @return
	 */
	public OfficialAccounts findByOriginalId(@Param("originalId") String originalId);
	
	/**
	 * 根据企业用户ID查询公众号列表
	 * @param euserId
	 * @return
	 */
	public List<OfficialAccounts> findListByEuserId(Integer euserId);
	
	/**
	 * 查询平台全部公众号列表
	 * @return
	 */
	public List<OfficialAccounts> findAllList();
	
	/**
	 * 根据企业用户ID查询公众号map列表
	 * @param euserId 企业用户ID
	 * @return
	 */
	public List<Map<String, Object>> findList(Integer euserId);
	
	/**
	 * 根据公众号ID及企业用户ID删除公众号
	 * @param Id 公众号Id
	 * @param euserId 企业用户ID
	 * @return
	 */
	public void deleteByEuserId(@Param("Id")Integer Id, @Param("euserId")Integer euserId);
}
