package com.hm.carService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.carService.dao.CarGroupDao;
import com.hm.carService.domain.CarGroup;
import com.hm.carService.service.CarGroupService;

@Service("carGroupService")
public class CarGroupServiceImpl implements
	CarGroupService {
	
	@Autowired
	CarGroupDao carGroupDao;

	@Override
	public void save(CarGroup t) {
		carGroupDao.save(t);
	}

	@Override
	public void update(CarGroup t) {
		carGroupDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		carGroupDao.deleteById(id);
	}

	@Override
	public CarGroup findById(Integer id) {
		return carGroupDao.findById(id);
	}
	
	public List<CarGroup> findList(Integer softwareId) {
		return carGroupDao.findList(softwareId);
	}

}
