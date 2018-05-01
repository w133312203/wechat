package com.hm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxTextDao;
import com.hm.domain.WxText;
import com.hm.service.WxTextService;

@Service("wxTextService")
public class WxTextServiceImpl implements WxTextService{
	
	@Autowired
	WxTextDao wxTextDao;

	@Override
	public void save(WxText wxText) {

		wxTextDao.save(wxText);		
	}

	@Override
	public void delete(Integer id) {
		
		wxTextDao.delete(id);
	}

	@Override
	public void update(WxText wxText) {

		wxTextDao.update(wxText);
	}

	@Override
	public WxText find(Integer id) {
		
		return wxTextDao.find(id);
	}

	@Override
	public WxText findBykeywordsId(Integer keywordsId) {
		
		return wxTextDao.findBykeywordsId(keywordsId);
	}
	
}
