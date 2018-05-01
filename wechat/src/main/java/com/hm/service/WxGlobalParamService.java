package com.hm.service;

import java.util.List;
import java.util.Map;

import com.hm.base.service.BaseService;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxGlobalParam;

public interface WxGlobalParamService extends BaseService<WxGlobalParam>{
	
	/**
	 * 添加公众号全局参数
	 * @param officialAccounts
	 */
	public boolean save(OfficialAccounts officialAccounts);
	
	/**
	 * 批量添加公众号全局参数
	 * @param paramList
	 */
	public void saveList(List<Map<String, Object>> paramList);
	
	/**
	 * 批量更新公众号全局参数
	 * @param paramList
	 */
	public void updateList(List<Map<String, Object>> paramList);

	/**
	 * 根据公众号ID查询公众号全局参数
	 * @param accountId 公众号ID
	 * @return
	 */
	public WxGlobalParam findByAccountId(Integer accountId);
	
	/**
	 * 获取公众号AccessToke
	 * @param accounts 公众号信息
	 * @return
	 */
	public WxGlobalParam getAccessToken(OfficialAccounts accounts);
}
