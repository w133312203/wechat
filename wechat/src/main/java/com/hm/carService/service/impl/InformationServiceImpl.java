package com.hm.carService.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.carService.dao.InformationDao;
import com.hm.carService.domain.Car;
import com.hm.carService.domain.Information;
import com.hm.carService.service.CarService;
import com.hm.carService.service.InformationService;

@Service("informationService")
public class InformationServiceImpl implements
	InformationService {

	@Autowired
	InformationDao informationDao;
	
	@Override
	public void save(Information t) {
		informationDao.save(t);
	}

	@Override
	public void update(Information t) {
		informationDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		informationDao.deleteById(id);
	}

	@Override
	public Information findById(Integer id) {
		Information information = informationDao.findById(id);
		return information;
	}

	public Information findBySoftwareId(Integer softwareId) {
		return informationDao.findBySoftwareId(softwareId);
	}

}
