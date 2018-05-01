package com.hm.carService.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.carService.dao.CarImageDao;
import com.hm.carService.dao.InformationDao;
import com.hm.carService.domain.Car;
import com.hm.carService.domain.CarImage;
import com.hm.carService.domain.Information;
import com.hm.carService.service.CarImageService;
import com.hm.carService.service.CarService;
import com.hm.carService.service.InformationService;

@Service("carImageService")
public class CarImageServiceImpl implements
	CarImageService {

	@Autowired
	CarImageDao carImageDao;

	@Override
	public void save(CarImage t) {
		carImageDao.save(t);
	}

	@Override
	public void update(CarImage t) {
		carImageDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		carImageDao.deleteById(id);
	}

	@Override
	public CarImage findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CarImage> findByCarId(Integer carId) {
		return carImageDao.findByCarId(carId);
	}

	@Override
	public List<CarImage> findList(Integer carId, Integer first, Integer max) {
		return carImageDao.findList(carId, first, max);
	}
	
	public List<CarImage> findListByType(Integer carId, Integer type, Integer first, Integer max) {
		return carImageDao.findListByType(carId, type, first, max);
	}

	@Override
	public Integer findCount(Integer carId) {
		return carImageDao.findCount(carId);
	}

}
