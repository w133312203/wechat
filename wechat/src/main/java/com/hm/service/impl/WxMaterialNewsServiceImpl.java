package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.WxMaterialNewsDao;
import com.hm.domain.WxMaterialNews;
import com.hm.service.WxMaterialNewsService;

@Service("wxMaterialNewsService")
public class WxMaterialNewsServiceImpl implements WxMaterialNewsService{
	
	@Autowired
	WxMaterialNewsDao wxMaterialNewsDao;

	@Override
	public void update(WxMaterialNews materialNews) {
		
		wxMaterialNewsDao.update(materialNews);
	}

	@Override
	public WxMaterialNews find(Integer id) {
		
		return wxMaterialNewsDao.find(id);
	}
	
	@Override
	@Transactional
	public void saveList(Integer accountId, List<Map<String, Object>> newsList, Integer type) {
		if(type==0){
			deleteList(accountId);
		}
		wxMaterialNewsDao.saveList(newsList);
	}

	@Override
	public void deleteByMediaId(String mediaId) {

		wxMaterialNewsDao.deleteByMediaId(mediaId);
	}
	
	@Override
	public void deleteList(Integer accountId) {

		wxMaterialNewsDao.deleteList(accountId);
	}

	@Override
	public List<Map<String, Object>> findNewsList(Integer accountId,
			String search, Integer first, Integer max) {
		
		return wxMaterialNewsDao.findNewsList(accountId, search, first, max);
	}

	@Override
	public Integer findNewsCount(Integer accountId, String search) {
		
		return wxMaterialNewsDao.findNewsCount(accountId, search);
	}

	@Override
	public List<Map<String, Object>> findListByMediaId(Integer accountId, String mediaId, Integer alias) {
		
		return wxMaterialNewsDao.findListByMediaId(accountId, mediaId, alias);
	}
	
}
