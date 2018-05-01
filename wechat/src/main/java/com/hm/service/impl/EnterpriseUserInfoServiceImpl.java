package com.hm.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.EnterpriseUserInfoDao;
import com.hm.domain.EnterpriseUserInfo;
import com.hm.service.EnterpriseUserInfoService;

@Service("euserInfoService")
public class EnterpriseUserInfoServiceImpl implements
		EnterpriseUserInfoService {

	@Autowired
	EnterpriseUserInfoDao euserInfoDao;

	public EnterpriseUserInfo findEnterpriseUserInfoByPassportId(
			Integer passportId) {
		return euserInfoDao.findEnterpriseUserInfoByPassportId(passportId);
	}
	
	public EnterpriseUserInfo updateLastLoginTime(Integer passportId) {
		EnterpriseUserInfo info = euserInfoDao.findEnterpriseUserInfoByPassportId(passportId);
		if(info==null) {
			info = new EnterpriseUserInfo();
			info.setPassportId(passportId);
			info.setLastLoginTime(new Date());
			euserInfoDao.save(info);
		}else {
			info.setLastLoginTime(new Date());
			euserInfoDao.update(info);
		}
		return info;
	}

	@Override
	public void save(EnterpriseUserInfo t) {
		euserInfoDao.save(t);
	}

	@Override
	public void update(EnterpriseUserInfo t) {
		euserInfoDao.update(t);
	}

	@Override
	public void deleteById(Integer Id) {}

	@Override
	public EnterpriseUserInfo findById(Integer Id) {
		return null;
	}

}
