package com.hm.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.hm.base.service.BaseBatchService;
import com.hm.base.service.BaseService;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxTag;

public interface WxTagService extends BaseService<WxTag>, BaseBatchService<WxTag>{
	
	/**
	 * 根据微信内标签id查询标签信息
	 * @param tagId
	 * @return
	 */
	public WxTag findByTagId(Integer accountId, Integer tagId);
	
	/**
	 * 根据公众号id查询标签列表
	 * @param accountId
	 * @return
	 */
	public List<WxTag> findList(Integer accountId);
	
	/**
	 * 删除此公众号下所有用户标签
	 * @param accountId 微信公众号id
	 */
	public void deleteList(Integer accountId);
	
     /**
      * 依据名称判断是否重复
      * @param name
      * @return
      */
	public List<Map<String, Object>> findTagByName(String name);
	
	/**
	 * 加载标签
	 * @param accountId 微信公众号Id
	 * @param accessToken 全局accessToken
	 * @return
	 */
	public List<WxTag> loadTagList(Integer accountId, String accessToken);
	
	/**
	 * 重新保存标签库
	 * @param tagList 标签列表
	 * @return
	 */
	public void deleteAndSaveList(List<WxTag> tagList);
	
	/**
	 * 比较标签列表的一致性
	 * @param jsonList 微信标签列表
	 * @param dataList 数据库标签列表
	 * @return
	 */
	public boolean compareToTagList(List<WxTag> jsonList,List<WxTag> dataList);
	
	/**
	 * 添加标签
	 * @param accounts
	 * @param openIdArray
	 * @param tagId
	 * @return
	 */
	public void addTag(String accessToken, String[] openIdArray, String[] tagIdArray);
	
	/**
	 * 清除标签
	 * @param accounts
	 * @param openIdArray
	 * @param tagId
	 * @return
	 */
	public void clearTag(String accessToken, String[] openIdArray, String[] tagIdArray);
	
	/**
	 * 更新用户标签
	 * @param tagIdArr 更新的标签组
	 * @param list openId列表
	 */
	public void updateTag(String tagIdArr,List<String> list);
	
	/**
	 * 更新单个用户标签
	 * @param tagIdArr 更新的标签组
	 * @param openId openId
	 */
	public void updateSingleTag(String tagIdArr, String openId);
	
	/**
	 * 更新标签名称
	 * @param accessToken 全局accessToken
	 * @param name 更新名称
	 * @param tag 标签信息
	 */
	public boolean updateTagName(String accessToken, String name, WxTag oldTag);
	
	/**
	 * 新增标签名称
	 * @param accountId 微信公众号Id
	 * @param accessToken 全局accessToken
	 * @param name 更新名称
	 */
	public boolean addTagName(Integer accountId, String accessToken, String name);
	
	/**
	 * 删除标签
	 * @param accessToken 全局accessToken
	 * @param tagId 标签ID
	 */
	public boolean deleteTagName(String accessToken, Integer tagId);
	
}
