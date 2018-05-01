package com.hm.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseService;
import com.hm.domain.OfficialAccounts;

public interface OfficialAccountsService extends BaseService<OfficialAccounts>{
	
	/**
	 * 生成公众号识别码
	 * @param AppId 公众号APPId
	 * @param appSecret 公众号密钥
	 * @return
	 */
	public String code(String AppId, String appSecret);
	
	/**
	 * 根据公众号ID及企业用户ID删除公众号
	 * @param Id 公众号Id
	 * @param euserId 企业用户ID
	 * @return
	 */
	public void deleteByEuserId(Integer Id, Integer euserId);
	
	/**
	 * 根据公众号唯一编码查询公众号信息
	 * @param code 公众号唯一编码
	 * @return
	 */
	public OfficialAccounts findByCode(String code);
	
	/**
	 * 根据appId查询公众号信息
	 * @param appId
	 * @return
	 */
	public OfficialAccounts findByAppId(String appId);
	
	/**
	 * 根据原始ID查询公众号信息
	 * @param originalId 原始ID
	 * @return
	 */
	public OfficialAccounts findByOriginalId(String originalId);
	
	/**
	 * 根据企业用户ID查询公众号列表
	 * @param euserId 企业用户ID
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
	
}
