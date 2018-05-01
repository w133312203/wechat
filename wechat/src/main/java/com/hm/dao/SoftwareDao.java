package com.hm.dao;

import org.apache.ibatis.annotations.Param;

import com.hm.base.dao.BaseDao;
import com.hm.domain.Software;

public interface SoftwareDao extends BaseDao<Software>{
	
	/**
	 * 根据企业用户Id查找小程序信息
	 * @param euserId 企业用户Id
	 * @param type 小程序类型
	 * @return 
	 */
	public Software findByEuserId(@Param("euserId")Integer euserId, @Param("type")Integer type);
	
}
