package com.hm.carService.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.carService.dao.ConfigureFieldDao;
import com.hm.carService.domain.ConfigureContext;
import com.hm.carService.domain.ConfigureField;
import com.hm.carService.service.ConfigureFieldService;

@Service("configureFieldService")
public class ConfigureFieldServiceImpl implements
	ConfigureFieldService {

	@Autowired
	ConfigureFieldDao configureFieldDao;

	@Override
	public void save(ConfigureField t) {
		configureFieldDao.save(t);
	}

	@Override
	public void update(ConfigureField t) {
		configureFieldDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		configureFieldDao.deleteById(id);
	}

	@Override
	public ConfigureField findById(Integer id) {
		return configureFieldDao.findById(id);
	}

	@Override
	public List<ConfigureField> findList(Integer softwareId, String search, Integer first,
			Integer max) {
		return configureFieldDao.findList(softwareId, search, first, max);
	}
	

	public List<ConfigureField> findAllList(Integer softwareId) {
		return configureFieldDao.findAllList(softwareId);
	}

	@Override
	public Integer findCount(Integer softwareId, String search) {
		return configureFieldDao.findCount(softwareId, search);
	}

	public List<ConfigureField> findNoConfigureField(Integer subdivisionId, Integer softwareId) {
		return configureFieldDao.findNoConfigureField(subdivisionId, softwareId);
	}
	
}
