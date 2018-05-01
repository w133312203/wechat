package com.hm.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.dao.WxGlobalParamDao;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxGlobalParam;
import com.hm.service.WxGlobalParamService;
import com.hm.utils.AccessTokenUtil;
import com.hm.utils.DateUtil;

@Service("wxGlobalParamService")
public class WxGlobalParamServiceImpl implements WxGlobalParamService{
	
	@Autowired
	WxGlobalParamDao wxGlobalParamDao;

	@Override
	public boolean save(OfficialAccounts officialAccounts) {
		String accessToken = AccessTokenUtil.getAccessToken(officialAccounts.getAppId(), officialAccounts.getAppSecret());
		if(accessToken!=null) {
			WxGlobalParam wxGlobalParam=new WxGlobalParam();
			wxGlobalParam.setEuserId(officialAccounts.getEuserId());
			wxGlobalParam.setAccountId(officialAccounts.getId());
			wxGlobalParam.setAccessToken(accessToken);
			wxGlobalParamDao.save(wxGlobalParam);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void saveList(List<Map<String, Object>> paramList) {
		wxGlobalParamDao.saveList(paramList);
	}
	
	@Override
	public void updateList(List<Map<String, Object>> paramList) {
		wxGlobalParamDao.updateList(paramList);
	}
	
	@Override
	public WxGlobalParam findByAccountId(Integer accountId) {
		return wxGlobalParamDao.findByAccountId(accountId);
	}

	@Override
	public void save(WxGlobalParam t) {
		wxGlobalParamDao.save(t);
	}

	@Override
	public void deleteById(Integer id) {
		wxGlobalParamDao.deleteById(id);
	}

	@Override
	public WxGlobalParam findById(Integer id) {
		return wxGlobalParamDao.findById(id);
	}

	@Override
	public void update(WxGlobalParam t) {
		wxGlobalParamDao.update(t);
	}
	
	public WxGlobalParam getAccessToken(OfficialAccounts accounts) {
		WxGlobalParam param=findByAccountId(accounts.getId());
		if(param==null) {
			param = new WxGlobalParam();
			param.setAccountId(accounts.getId());
			param.setEuserId(accounts.getEuserId());
			String accessToken = AccessTokenUtil.getAccessToken(accounts.getAppId(), accounts.getAppSecret());
			if(accessToken!=null) {
				param.setAccessToken(accessToken);
				param.setExpirationTime(DateUtil.calculationTime(new Date(), 10*60, 0));
				save(param);
			}
		}else if(param.getExpirationTime()==null||new Date().compareTo(param.getExpirationTime())>-1) {
			String accessToken = AccessTokenUtil.getAccessToken(accounts.getAppId(), accounts.getAppSecret());
			param.setAccessToken(accessToken);
			param.setExpirationTime(DateUtil.calculationTime(new Date(), 10*60, 0));
			update(param);
		}
		return param;
	}

}
