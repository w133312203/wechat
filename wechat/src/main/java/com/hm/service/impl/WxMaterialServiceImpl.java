package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.WxMaterialDao;
import com.hm.domain.WxMaterial;
import com.hm.service.WxMaterialService;

@Service("wxMaterialService")
public class WxMaterialServiceImpl implements WxMaterialService{
	
	@Autowired
	WxMaterialDao wxMaterialDao;

	@Override
	public void save(WxMaterial material) {

		wxMaterialDao.save(material);
	}
	
	@Override
	public void update(WxMaterial material) {

		wxMaterialDao.update(material);
	}
	
	@Override
	public WxMaterial find(Integer id) {
		
		return wxMaterialDao.find(id);
	}
	
	@Override
	@Transactional
	public void saveList(Integer accountId, Integer type,
			List<Map<String, Object>> materialList) {
		deleteList(accountId, type);
		wxMaterialDao.saveList(materialList);
	}
	
	@Override
	public void deleteByMediaId(String mediaId) {

		wxMaterialDao.deleteByMediaId(mediaId);
	}
	
	@Override
	public void deleteList(Integer accountId, Integer type) {

		wxMaterialDao.deleteList(accountId, type);
	}

	@Override
	public List<Map<String, Object>> findMaterialList(Integer accountId,
			Integer type, String search, Integer first, Integer max) {
		
		return wxMaterialDao.findMaterialList(accountId, type, search, first, max);
	}

	@Override
	public Integer findMaterialCount(Integer accountId, Integer type,
			String search) {
		
		return wxMaterialDao.findMaterialCount(accountId, type, search);
	}
	
}
