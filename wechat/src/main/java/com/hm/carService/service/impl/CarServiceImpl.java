package com.hm.carService.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.carService.dao.CarDao;
import com.hm.carService.domain.Car;
import com.hm.carService.service.CarService;

@Service("carService")
public class CarServiceImpl implements
		CarService {

	@Autowired
	CarDao carDao;
	
	@Override
	public void save(Car t) {
		carDao.save(t);
	}

	@Override
	public void update(Car t) {
		carDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		carDao.deleteById(id);
	}

	@Override
	public Car findById(Integer id) {
		return carDao.findById(id);
	}
	
	@Override
	public Integer findCarCount(Integer softwareId, Integer groupId,
			String search) {
		return carDao.findCarCount(softwareId, groupId, search);
	}
	
	@Override
	public List<Map> findCarList(Integer softwareId, Integer groupId,
			String search, Integer first, Integer max) {
		return carDao.findCarList(softwareId, groupId, search, first, max);
	}

}
