package com.hm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxEventTemplateDao;
import com.hm.domain.WxEventTemplate;
import com.hm.service.WxEventTemplateService;

@Service("wxEventTemplateService")
public class WxEventTemplateServiceImpl implements WxEventTemplateService{
	
	@Autowired
	WxEventTemplateDao wxEventTemplateDao;

	@Override
	public List<WxEventTemplate> findList(Integer type, Integer mark) {
		
		return wxEventTemplateDao.findList(type, mark);
	}

	@Override
	public void update(WxEventTemplate wxEventTemplate) {
		
		wxEventTemplateDao.update(wxEventTemplate);
	}
	
}
