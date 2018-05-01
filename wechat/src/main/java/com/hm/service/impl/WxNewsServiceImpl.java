package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxNewsDao;
import com.hm.domain.WxNews;
import com.hm.service.WxNewsService;

@Service("wxNewsService")
public class WxNewsServiceImpl implements WxNewsService{
	
	@Autowired
	WxNewsDao wxNewsDao;

	@Override
	public void save(WxNews wxNews) {

		wxNewsDao.save(wxNews);
	}

	@Override
	public void delete(Integer id) {
		
		wxNewsDao.delete(id);
	}

	@Override
	public void update(WxNews wxNews) {

		wxNewsDao.update(wxNews);	
	}

	@Override
	public WxNews find(Integer id) {
		
		return wxNewsDao.find(id);
	}

	@Override
	public List<WxNews> findList(Integer keywordsId) {
		
		return wxNewsDao.findList(keywordsId);
	}
	
	@Override
	public List<Map<String, Object>> findNewsList(Integer keywordsId,
			String search, Integer first, Integer max) {
		
		return wxNewsDao.findNewsList(keywordsId, search, first, max);
	}

	@Override
	public Integer findNewsCount(Integer keywordsId, String search) {
		
		return wxNewsDao.findNewsCount(keywordsId, search);
	}
	
}
