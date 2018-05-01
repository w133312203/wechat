package com.hm.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hm.dao.OfficialAccountsDao;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxGlobalParam;
import com.hm.service.OfficialAccountsService;
import com.hm.service.WxGlobalParamService;
import com.hm.utils.AccessTokenUtil;
import com.hm.utils.ApplicationUtil;
import com.hm.utils.HttpClientUtil;
import com.hm.utils.SHA1;
import com.hm.utils.ServletContextUtil;
import com.hm.utils.StringUtil;

@Service("officialAccountsService")
public class OfficialAccountsServiceImpl implements OfficialAccountsService{
	
	@Autowired
	OfficialAccountsDao officialAccountsDao;
	
	@Autowired
	WxGlobalParamService wxGlobalParamService;
	
	public String code(String AppId, String appSecret) {
		String token = AppId + appSecret;
		token = SHA1.hexSHA1(token);
		return token;
	}
	
	public void deleteByEuserId(Integer Id, Integer euserId) {
		
		officialAccountsDao.deleteByEuserId(Id, euserId);
	}

	@Override
	public void save(OfficialAccounts officialAccounts) {
		officialAccounts.setCode(code(officialAccounts.getAppId(),officialAccounts.getAppSecret()));
		officialAccounts.setServerUrl(ApplicationUtil.MAIN_HTTP+"/wechat/api/auth?code="+officialAccounts.getCode());
		officialAccountsDao.save(officialAccounts);
	}
	
	@Override
	public void update(OfficialAccounts officialAccounts) {

		officialAccountsDao.update(officialAccounts);
	}
	
	@Override
	public OfficialAccounts findByCode(String code) {
		
		return officialAccountsDao.findByCode(code);
	}
	
	@Override
	public OfficialAccounts findByAppId(String appId) {
		
		return officialAccountsDao.findByAppId(appId);
	}
	
	@Override
	public OfficialAccounts findByOriginalId(String originalId) {
		
		return officialAccountsDao.findByOriginalId(originalId);
	}
	
	@Override
	public List<OfficialAccounts> findListByEuserId(Integer euserId) {
		
		return officialAccountsDao.findListByEuserId(euserId);
	}

	@Override
	public List<OfficialAccounts> findAllList() {
		
		return officialAccountsDao.findAllList();
	}

	@Override
	public List<Map<String, Object>> findList(Integer euserId) {
		
		return officialAccountsDao.findList(euserId);
	}

	@Override
	public void deleteById(Integer id) {
		
		officialAccountsDao.deleteById(id);
	}

	@Override
	public OfficialAccounts findById(Integer id) {
		return officialAccountsDao.findById(id);
	}

}
