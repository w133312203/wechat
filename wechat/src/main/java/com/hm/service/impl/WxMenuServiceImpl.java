package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxMenuDao;
import com.hm.domain.WxMenu;
import com.hm.service.WxMenuService;

@Service("wxMenuService")
public class WxMenuServiceImpl implements WxMenuService{
	
	@Autowired
	WxMenuDao wxMenuDao;
	
	@Override
	public void save(WxMenu wxMenu) {

		wxMenuDao.save(wxMenu);
	}
	
	@Override
	public List<Map<String, Object>> findList(Integer accountId) {
		
		return wxMenuDao.findList(accountId);
	}
	
	@Override
	public void deleteList(Integer accoutId) {
			
		wxMenuDao.deleteList(accoutId);
	}
	
}
