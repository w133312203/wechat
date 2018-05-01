package com.hm.carService.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.carService.dao.ConfigureContextDao;
import com.hm.carService.dao.ConfigureFieldDao;
import com.hm.carService.dao.InformationDao;
import com.hm.carService.domain.Car;
import com.hm.carService.domain.ConfigureContext;
import com.hm.carService.domain.ConfigureField;
import com.hm.carService.domain.Information;
import com.hm.carService.service.CarService;
import com.hm.carService.service.ConfigureContextService;
import com.hm.carService.service.ConfigureFieldService;
import com.hm.carService.service.InformationService;

@Service("configureContextService")
public class ConfigureContextServiceImpl implements
	ConfigureContextService {

	@Autowired
	ConfigureContextDao configureContextDao;
	
	@Autowired
	ConfigureFieldDao configureFieldDao;
	
	

	@Override
	public void save(ConfigureContext t) {
		findUpdateLevel(t.getSubdivisionId(), t.getLevel(), t.getId());
		configureContextDao.save(t);
	}

	@Override
	public void update(ConfigureContext t) {
		findUpdateLevel(t.getSubdivisionId(), t.getLevel(), t.getId());
		configureContextDao.update(t);
	}

	@Override
	public void deleteById(Integer id) {
		configureContextDao.deleteById(id);
	}

	@Override
	public ConfigureContext findById(Integer id) {
		return configureContextDao.findById(id);
	}

	@Override
	public List<Map> findList(Integer subdivisionId, String search,
			Integer first, Integer max) {
		return configureContextDao.findList(subdivisionId, search, first, max);
	}
	
	public List<Map> findAllList(Integer subdivisionId, Integer softwareId) {
		return configureContextDao.findAllList(subdivisionId, softwareId);
	}

	@Override
	public Map findCount(Integer subdivisionId, String search) {
		return configureContextDao.findCount(subdivisionId, search);
	}

	@Override
	public void saveList(List<ConfigureContext> list) {
		if(list.size()>0) {
			configureContextDao.saveList(list);
		}
	}
	
	public void synConfigureField(Integer subdivisionId, Integer softwareId) {
		List<ConfigureField> fieldList = configureFieldDao.findNoConfigureField(subdivisionId, softwareId);
		List<ConfigureContext> contextList = new ArrayList<ConfigureContext>();
		Integer maxLevel = findMaxLevel(subdivisionId);
		if(maxLevel==null) {
			maxLevel = 0;
		}
		for(ConfigureField field:fieldList) {
			maxLevel++;
			ConfigureContext ct = new ConfigureContext();
			ct.setSubdivisionId(subdivisionId);
			if(field.getType()==1) {
				ct.setContext("");
			}else {
				ct.setContext("无");
			}
			ct.setFieldId(field.getId());
			ct.setLevel(maxLevel);
			contextList.add(ct);
		}
		saveList(contextList);
	}
	
	public List<Integer> findLevel(Integer subdivisionId, Integer level) {
		List<Integer> list = configureContextDao.findLevel(subdivisionId, level);
		return list;
		
	}
	
	public void findUpdateLevel(Integer subdivisionId, Integer level, Integer contextId) {
		if(level!=null&&level!=0) {
			List<Integer> list = findLevel(subdivisionId, level);
			if(list.size()==1) {
				if(list.get(0)!=null&&!list.get(0).equals(contextId)) {
					updateLevel(subdivisionId, level);
				}
			}else if(list.size()>1) {
				updateLevel(subdivisionId, level);
			}
		}
	}
	
	public void updateLevel(Integer subdivisionId, Integer level) {
		configureContextDao.updateLevel(subdivisionId, level);
	}

	public Integer findMaxLevel(Integer subdivisionId) {
		return configureContextDao.findMaxLevel(subdivisionId);
	}

	@Override
	public void batchUpdate(List<ConfigureContext> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void batchDelete(List<ConfigureContext> list) {
		// TODO Auto-generated method stub
		
	}
}
