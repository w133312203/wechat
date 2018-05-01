package com.hm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxMessageDao;
import com.hm.domain.WxMessage;
import com.hm.service.WxMessageService;

@Service("wxMessageService")
public class WxMessageServiceImpl implements WxMessageService{
	
	@Autowired
	WxMessageDao wxMessageDao;
	
	@Override
	public void save(WxMessage wxMessage) {

		wxMessageDao.save(wxMessage);
	}
	
}
