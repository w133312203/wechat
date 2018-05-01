package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxKeywordsDao;
import com.hm.domain.WxKeywords;
import com.hm.service.WxKeywordsService;

@Service("wxKeywordsService")
public class WxKeywordsServiceImpl implements WxKeywordsService{
	
	@Autowired
	WxKeywordsDao wxKeywordsDao;

	
	@Override
	public void save(WxKeywords wxKeywords) {

		wxKeywordsDao.save(wxKeywords);
	}

	@Override
	public void delete(Integer id) {

		wxKeywordsDao.delete(id);
	}

	@Override
	public void update(WxKeywords wxNewsKey) {

		wxKeywordsDao.update(wxNewsKey);
	}

	@Override
	public WxKeywords find(Integer id) {
		
		return wxKeywordsDao.find(id);
	}

	@Override
	public WxKeywords findByKeywords(Integer accountId, String keywords) {
		
		return wxKeywordsDao.findByKeywords(accountId, keywords);
	}
	
	@Override
	public List<Map<String, Object>> findList(Integer accountId) {
		
		return wxKeywordsDao.findList(accountId);
	}
	
	@Override
	public List<Map<String, Object>> findKeywordsList(Integer accountId,
			Integer type, String search, Integer first, Integer max) {
		
		return wxKeywordsDao.findKeywordsList(accountId, type, search, first, max);
	}

	@Override
	public Integer findKeywordsCount(Integer accountId, Integer type,
			String search) {
		
		return wxKeywordsDao.findKeywordsCount(accountId, type, search);
	}
	
}
