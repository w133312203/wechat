package com.hm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxSystemReplyDao;
import com.hm.domain.WxSystemReply;
import com.hm.service.WxSystemReplyService;

@Service("wxSystemReplyService")
public class WxSystemReplyServiceImpl implements WxSystemReplyService{
	
	@Autowired
	WxSystemReplyDao wxSystemReplyDao;

	
	@Override
	public void save(WxSystemReply wxSystemReply) {

		wxSystemReplyDao.save(wxSystemReply);
	}

	@Override
	public void update(WxSystemReply wxSystemReply) {

		wxSystemReplyDao.update(wxSystemReply);
	}

	@Override
	public WxSystemReply find(Integer accountId, Integer type) {
		
		return wxSystemReplyDao.find(accountId, type);
	}
	
}
