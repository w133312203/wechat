package com.hm.carService.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.carService.dao.SubdivisionDao;
import com.hm.carService.domain.Subdivision;
import com.hm.carService.service.SubdivisionService;

@Service("subdivisionService")
public class SubdivisionServiceImpl implements
	SubdivisionService {

	@Autowired
	SubdivisionDao subdivisionDao;
	
	@Override
	public Integer findCount(Integer carId,
			String search) {
		return subdivisionDao.findCount(carId, search);
	}
	
	@Override
	public List<Map> findList(Integer carId,
			String search, Integer first, Integer max) {
		return subdivisionDao.findList(carId, search, first, max);
	}
	
	@Override
	public List<Subdivision> findListByOBJ(Integer carId) {
		return subdivisionDao.findListByOBJ(carId);
	}
	

	@Override
	public void save(Subdivision t) {
		subdivisionDao.save(t);
	}

	@Override
	public void update(Subdivision t) {
		subdivisionDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		subdivisionDao.deleteById(id);
	}

	@Override
	public Subdivision findById(Integer id) {
		return subdivisionDao.findById(id);
	}

}
