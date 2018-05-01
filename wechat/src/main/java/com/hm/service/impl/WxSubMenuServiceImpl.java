package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxSubMenuDao;
import com.hm.domain.WxSubMenu;
import com.hm.service.WxSubMenuService;

@Service("wxSubMenuService")
public class WxSubMenuServiceImpl implements WxSubMenuService{
	
	@Autowired
	WxSubMenuDao wxSubMenuDao;
	
	@Override
	public void save(WxSubMenu wxSubMenu) {

		wxSubMenuDao.save(wxSubMenu);
	}
	
	@Override
	public List<Map<String, Object>> findList(Integer menuId) {
		
		return wxSubMenuDao.findList(menuId);
	}
	
	@Override
	public void deleteList(Integer accoutId) {
			
		wxSubMenuDao.deleteList(accoutId);
	}
	
}
