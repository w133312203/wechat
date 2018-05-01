package com.hm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.SoftwareDao;
import com.hm.domain.Software;
import com.hm.service.SoftwareService;

@Service("softwareService")
public class SoftwareServiceImpl implements
	SoftwareService {

	@Autowired
	SoftwareDao softwareDao;

	public Software findByEuserId(Integer euserId, Integer type) {
		return softwareDao.findByEuserId(euserId, type);
	}

	@Override
	public void save(Software t) {
		softwareDao.save(t);
	}

	@Override
	public void update(Software t) {
		softwareDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		softwareDao.deleteById(id);
	}

	@Override
	public Software findById(Integer id) {
		return softwareDao.findById(id);
	}

}
