package com.hm.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.WxGlobalParamDao;
import com.hm.dao.WxTagDao;
import com.hm.dao.WxUserDao;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxTag;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxTagService;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.HttpClientUtil;
import com.hm.utils.StringUtil;
import com.hm.utils.WxApiUtil;

@Service("wxTagService")
public class WxTagServiceImpl implements WxTagService{
	
	@Autowired
	WxTagDao wxTagDao;
	
	@Autowired
	WxGlobalParamService wxGlobalParamService;
	
	@Autowired
	WxUserDao wxUserDao;
	
	@Override
	public void save(WxTag wxTag) {
		wxTagDao.save(wxTag);
	}

	@Override
	public void deleteById(Integer id) {

		wxTagDao.deleteById(id);
	}

	@Override
	public void update(WxTag wxTag) {
		
		wxTagDao.update(wxTag);
	}
	
	@Override
	public WxTag findById(Integer id) {
		
		return wxTagDao.findById(id);
	}
	
	@Override
	public WxTag findByTagId(Integer accountId, Integer tagId) {
		
		return wxTagDao.findByTagId(accountId, tagId);
	}
	
	@Override
	public List<WxTag> findList(Integer accountId) {
		
		return wxTagDao.findList(accountId);
	}

	@Override
	public void deleteList(Integer accountId) {

		wxTagDao.deleteList(accountId);
	}

	@Override
	public List<Map<String, Object>> findTagByName(String name) {
		// TODO Auto-generated method stub
		return wxTagDao.findTagByName(name);
	}
	
	@Override
	@Transactional
	public void deleteAndSaveList(List<WxTag> list) {
		deleteList(list.get(0).getAccountId());
		wxTagDao.saveList(list);
	}

	@Override
	public void batchUpdate(List<WxTag> list) {
		wxTagDao.batchUpdate(list);
	}

	@Override
	public void batchDelete(List<WxTag> list) {
		wxTagDao.batchDelete(list);
	}
	
	public List<WxTag> loadTagList(Integer accountId, String accessToken) {
		String resultJson = WxApiUtil.getTags(accessToken);
		JSONObject jsonobj = JSONObject.fromObject(resultJson);
		JSONArray jsonArray = JSONArray.fromObject(jsonobj.get("tags"));
		List<WxTag> tagList = new ArrayList<WxTag>();
        for(int i=0;i<jsonArray.size();i++) {
        	WxTag tag = new WxTag();
        	Map tagMap = (Map) jsonArray.get(i);
        	tag.setAccountId(accountId);
        	tag.setCount(Integer.parseInt(tagMap.get("count").toString()));
        	tag.setTagId(Integer.parseInt(tagMap.get("id").toString()));
        	tag.setTagName(tagMap.get("name").toString());
        	tagList.add(tag);
        }
        List<WxTag> oldTagList = findList(accountId);
        if(oldTagList!=null&&oldTagList.size()>0&&compareToTagList(tagList, oldTagList)){
	        return oldTagList;
		}else if(oldTagList==null||oldTagList.size()==0){
			saveList(tagList);
			return tagList;
		}else {
			deleteAndSaveList(tagList);
			return tagList;
		}
	}
	
	public boolean compareToTagList(List<WxTag> jsonList,List<WxTag> dataList) {
		if(dataList==null||dataList.size()==0||jsonList.size()!=dataList.size()) {
			return false;
		}else {
			Set<String> set = new HashSet<String>();
			for(int i=0;i<dataList.size();i++) {
				set.add(dataList.get(i).getTagName());
				if(set.add(jsonList.get(i).getTagName())) {
					return false;
				}
			}
			return true;
		}
	}
	
	public void addTag(String accessToken, String[] openIdArray, String[] tagIdArray) {
		for(int i=0;i<tagIdArray.length;i++) {
			Map map = new HashMap();
			map.put("openid_list", openIdArray);
			map.put("tagid", tagIdArray[i]);
			JSON json = JSONSerializer.toJSON(map);
			WxApiUtil.batchtagging(accessToken, json.toString());
		}
	}
	
	public void clearTag(String accessToken, String[] openIdArray, String[] tagIdArray) {
		for(int i=0;i<tagIdArray.length;i++) {
			Map map = new HashMap();
			map.put("openid_list", openIdArray);
			map.put("tagid", tagIdArray[i]);
			JSON json = JSONSerializer.toJSON(map);
			WxApiUtil.batchuntagging(accessToken, json.toString());
		}
	}
	
	public void updateTag(String tagIdArr,List<String> list) {
		wxUserDao.updateTag(tagIdArr, list);
	}
	
	public void updateSingleTag(String tagIdArr,String openId) {
		wxUserDao.updateSingleTag(tagIdArr, openId);
	}
	
	public boolean updateTagName(String accessToken, String name, WxTag oldTag) {
		if(!StringUtil.isEmpty(name)) {
			Map infoMap = new HashMap();
			Map tagMap = new HashMap();
			tagMap.put("id", oldTag.getTagId());
			tagMap.put("name", oldTag.getTagName());
			infoMap.put("tag", tagMap);
			JSON json = JSONSerializer.toJSON(infoMap);
			String resultJson=WxApiUtil.updateTag(accessToken, json.toString());
			JSONObject jsonb = JSONObject.fromObject(resultJson);
			if(jsonb.getInt("errcode")==0){
				oldTag.setTagName(name);
				update(oldTag);
				return true;
			}else{
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean addTagName(Integer accountId, String accessToken, String name) {
		if(!StringUtil.isEmpty(name)) {
			Map infoMap = new HashMap();
			Map tagMap = new HashMap();
			tagMap.put("name", name);
			infoMap.put("tag", tagMap);
			JSON json = JSONSerializer.toJSON(infoMap);
			String resultJson=WxApiUtil.addTag(accessToken, json.toString());
			JSONObject jsonb = JSONObject.fromObject(resultJson);
			if(jsonb.has("tag")){
				jsonb = jsonb.getJSONObject("tag");
				WxTag tag = new WxTag();
				tag.setAccountId(accountId);
				tag.setTagId(jsonb.getInt("id"));
				tag.setTagName(jsonb.getString("name"));
				tag.setCount(0);
				save(tag);
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean deleteTagName(String accessToken, Integer tagId) {
		if(tagId!=null) {
			WxTag tag = findById(tagId);
			Map infoMap = new HashMap();
			Map tagMap = new HashMap();
			tagMap.put("id", tag.getTagId());
			infoMap.put("tag", tagMap);
			JSON json = JSONSerializer.toJSON(infoMap);
			String resultJson=WxApiUtil.deleteTag(accessToken, json.toString());
			JSONObject jsonb = JSONObject.fromObject(resultJson);
			if(jsonb.getInt("errcode")==0){
				deleteById(tag.getId());
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}

	@Override
	public void saveList(List<WxTag> list) {
		wxTagDao.saveList(list);
	}
	
}
