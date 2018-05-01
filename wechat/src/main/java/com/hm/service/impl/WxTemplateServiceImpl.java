package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hm.dao.WxTemplateDao;
import com.hm.domain.WxTemplate;
import com.hm.service.WxTemplateService;

@Service("wxTemplateService")
public class WxTemplateServiceImpl implements WxTemplateService{
	
	@Autowired
	WxTemplateDao wxTemplateDao;
	
	@Override
	public void delete(Integer id) {
		
		wxTemplateDao.delete(id);
	}

	@Override
	public WxTemplate find(Integer id) {
		
		return wxTemplateDao.find(id);
	}
	
	@Override
	public void deleteList(Integer accountId) {
		
		wxTemplateDao.deleteList(accountId);
	}
	
	@Override
	@Transactional
	public void saveList(Integer accountId, List<Map<String, Object>> templateList) {
		
		deleteList(accountId);
		wxTemplateDao.saveList(templateList);
	}

	@Override
	public List<WxTemplate> findList(Integer accountId) {
		
		return wxTemplateDao.findList(accountId);
	}

	@Override
	public WxTemplate findByTitle(Integer accountId, String title) {
		
		return wxTemplateDao.findByTitle(accountId, title);
	}

	@Override
	public WxTemplate findByTemplateId(String templateId) {
		
		return wxTemplateDao.findByTemplateId(templateId);
	}
}
