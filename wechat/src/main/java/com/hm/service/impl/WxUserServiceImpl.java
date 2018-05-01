package com.hm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hm.dao.WxUserDao;
import com.hm.domain.OfficialAccounts;
import com.hm.domain.WxTag;
import com.hm.domain.WxUser;
import com.hm.service.OfficialAccountsService;
import com.hm.service.WxGlobalParamService;
import com.hm.service.WxTagService;
import com.hm.service.WxUserService;
import com.hm.utils.CalculatorUtil;
import com.hm.utils.EmojiFilterUtils;
import com.hm.utils.StringUtil;
import com.hm.utils.WxApiUtil;

@Service("wxUserService")
public class WxUserServiceImpl implements WxUserService{
	
	@Autowired
	WxUserDao wxUserDao;
	
	@Autowired
	WxTagService wxTagService;
	
	@Autowired
	WxGlobalParamService wxGlobalParamService;
	
	@Autowired
	OfficialAccountsService officialAccountsService;
	
	@Override
	public void save(WxUser wxUser) {

		wxUserDao.save(wxUser);
	}

	@Override
	public void deleteById(Integer id) {
		
		wxUserDao.deleteById(id);
	}

	@Override
	public void update(WxUser wxUser) {

		wxUserDao.update(wxUser);
	}

	@Override
	public WxUser findById(Integer id) {
		
		return wxUserDao.findById(id);
	}

	@Override
	public WxUser findMax(Integer accountId) {
		
		return wxUserDao.findMax(accountId);
	}

	@Override
	public List<WxUser> findList(Integer accountId) {
		
		return wxUserDao.findList(accountId);
	}

	@Override
	public void saveAllList(List<Map<String, Object>> userList) {

		wxUserDao.saveAllList(userList);
	}

	@Override
	public void deleteList(Integer accountId) {

		wxUserDao.deleteList(accountId);
	}

	@Override
	public List<Map<String, Object>> findUserList(Integer accountId, Integer tagId, String search,
			Integer first, Integer max) {
		List<Map<String, Object>> userList = wxUserDao.findUserList(accountId, tagId, search, first, max);
		for (Map<String, Object> map : userList) {
			String tagNames="";
			if(map.get("tagIds")!=null){
				String[] tagIds = map.get("tagIds").toString().split(",");
				for (String tagid : tagIds) {
					if(!StringUtil.isEmpty(tagid)){
						WxTag wxTag = wxTagService.findByTagId(accountId, Integer.parseInt(tagid));
						tagNames += wxTag.getTagName()+",";
					}
				}
			}
			if(!StringUtil.isEmpty(tagNames)){
				map.put("tagNames", tagNames.substring(0, tagNames.length()-1));
			}else{
				map.put("tagNames", "暂无标签");
			}
		}
		return userList;
	}

	@Override
	public Integer findUserCount(Integer accountId, Integer tagId, String search) {
		
		return wxUserDao.findUserCount(accountId, tagId, search);
	}

	@Override
	public void updateList(List<Map<String, Object>> userList) {
		
		wxUserDao.updateList(userList);
	}

	@Override
	public WxUser findByOpenid(String openid) {
		
		return wxUserDao.findByOpenid(openid);
	}

	@Override
	public List findListByChannelId(Integer cid, int first, int max) {
		// TODO Auto-generated method stub
		return wxUserDao.findListByChannelId(cid,first,max);
	}

	@Override
	public List findChannelListById(Integer id) {
		return wxUserDao.findChannelListById(id);
	}

	@Override
	public PageInfo<Map<String, Object>> findList(Integer cid, int first, int max) throws Exception {
		 PageHelper.startPage(first,max);
		 List<Map<String,Object>> alist= wxUserDao.findListById(cid);
		
		 PageInfo<Map<String,Object>> pageInfo=new PageInfo<Map<String,Object>>(alist);
		 return pageInfo;
	}
	
	
	public boolean checkNewUser(Integer accountId, String accessToken) {
		WxUser maxUser = findMax(accountId);
		String resultJson="";
		if(maxUser!=null){
			resultJson = WxApiUtil.getUsers(accessToken, maxUser.getOpenid());
		}else{
			resultJson = WxApiUtil.getUsers(accessToken, "");
		}
		JSONObject jsonObject = JSONObject.fromObject(resultJson);
		if(jsonObject.has("count")){
			Integer total = jsonObject.getInt("total");
			if(findUserCount(accountId, null, null)<total) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public void loadUser(Integer accountId, String accessToken) {
		WxUser maxUser = findMax(accountId);
		String resultJson="";
		if(maxUser!=null){
			resultJson = WxApiUtil.getUsers(accessToken, maxUser.getOpenid());
		}else{
			resultJson = WxApiUtil.getUsers(accessToken, "");
		}
		JSONObject jsonObject = JSONObject.fromObject(resultJson);
		if(jsonObject.has("total")){
			Integer total = jsonObject.getInt("total");
			String next_openid = "";
			if(maxUser!=null){
				next_openid = maxUser.getOpenid();
			}
			int num = CalculatorUtil.division(total, 10000);
			List<Map> openIdList = new ArrayList<Map>();
			for (int i = 0; i < num; i++) {
				if(i>0) {
					resultJson = WxApiUtil.getUsers(accessToken, next_openid);
					jsonObject = JSONObject.fromObject(resultJson);
				}
				next_openid = jsonObject.getString("next_openid");
				jsonObject = jsonObject.getJSONObject("data");
				List<String> list = jsonObject.getJSONArray("openid");
	        	for(String s:list) {
	        		Map map = new HashMap();
	        		map.put("openid", s);
	        		openIdList.add(map);
	        	}
				for (int j = 0; j < openIdList.size(); j+=100) {
					List<Map> subOpenIdList = openIdList.subList(j, j+100);
					Map map = new HashMap();
					map.put("user_list", subOpenIdList);
					JSON object = JSONSerializer.toJSON(map);
					resultJson = WxApiUtil.batchGetUserInfo(accessToken, object.toString());
					JSONObject userInfoJson = JSONObject.fromObject(resultJson);
					List<Map> mapList = userInfoJson.getJSONArray("user_info_list");
					List<WxUser> userInfoList = new ArrayList();
					for(Map userInfoMap:mapList) {
						WxUser wxUser = createUser(userInfoMap, accountId);
						userInfoList.add(wxUser);
					}
					saveList(userInfoList);
				}
			}
		}
	}
	
	public WxUser createUser(Map userInfoMap, Integer accountId) {
		WxUser wxUser = new WxUser();
		wxUser.setAccountId(accountId);
		wxUser.setSubscribe(Integer.parseInt(userInfoMap.get("subscribe").toString()));
		wxUser.setOpenid(userInfoMap.get("openid").toString());
		if(wxUser.getSubscribe()==1) {
			wxUser.setNickname(EmojiFilterUtils.filterEmoji(userInfoMap.get("nickname").toString()));
			wxUser.setSex(Integer.parseInt(userInfoMap.get("sex").toString()));
			wxUser.setLanguage(userInfoMap.get("language").toString());
			wxUser.setCity(userInfoMap.get("city").toString());
			wxUser.setProvince(userInfoMap.get("province").toString());
			wxUser.setCountry(userInfoMap.get("country").toString());
			wxUser.setHeadimgurl(userInfoMap.get("headimgurl").toString());
			wxUser.setRemark(userInfoMap.get("remark").toString());
    		wxUser.setGroupid(userInfoMap.get("groupid").toString());
    		Date date = new Date((long)userInfoMap.get("subscribe_time")*1000);
    		wxUser.setSubscribetime(date);
    		if(userInfoMap.get("unionid")!=null){
    			wxUser.setUnionid(userInfoMap.get("unionid").toString());
    		}
    		List tagIdList = (List)userInfoMap.get("tagid_list");
    		if(tagIdList!=null&&tagIdList.size()>0) {
    			StringBuilder tagIdStr = new StringBuilder();
    			for (int m = 0; m < tagIdList.size(); m++) {
    				tagIdStr.append(tagIdList.get(m)+",");
				}
    			wxUser.setTagIds(tagIdStr.toString().substring(0,tagIdStr.length()-1));
    		}
		}
		return wxUser;
	}

	@Override
	public void saveList(List<WxUser> list) {
		wxUserDao.saveList(list);
	}

	@Override
	public void batchUpdate(List<WxUser> list) {
		wxUserDao.batchUpdate(list);
	}

	@Override
	public void batchDelete(List<WxUser> list) {
		wxUserDao.batchDelete(list);
	}
}
