package com.lenovo.elk3.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.RoleBean;
import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.dao.UserDao;
import com.lenovo.elk3.utils.AESUtil;
import com.lenovo.elk3.utils.ElasticsearchUtil;
import com.lenovo.elk3.utils.ParseHexUtil;
import com.lenovo.elk3.utils.ParseJSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(value = "indexservice")
public class IndexServiceImpl implements IndexService {
	Logger logger = Logger.getLogger(IndexServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public JSONArray index(String index,String type,int from,int size) throws ClientProtocolException, IOException {
		return ElasticsearchUtil.searchAll(index,type, from, size).getJSONObject("hits").getJSONArray("hits");
	}

	@Override
	public JSONObject single(String index, String type, String id) throws Exception {
		JSONObject singleJson = ElasticsearchUtil.single(index, type, id);
		if(!"kb1".equals(singleJson.getString("_index"))){
			String content = singleJson.getJSONObject("_source").getString("content");
		    content = content.replace("\r\n", "<br>");
//			StringEscapeUtils.unescapeHtml(content);
			logger.info("content-------->" + content);
			singleJson.getJSONObject("_source").put("content", content);
			logger.info("singJson------->" + singleJson.toString());
		}
		return singleJson;
	}

	@Override
	public boolean match(int userId, String url) throws Exception {
		boolean flag = true;
		List<PermissionBean> selectPermissionById = userDao.selectPermissionById(userId);
		PermissionBean selectPermissionByUrl = userDao.selectPermissionByUrl(url);
		while(flag){
			for (PermissionBean permissionBean : selectPermissionById) {
				if(permissionBean.getId() == selectPermissionByUrl.getId() || permissionBean.getId() == selectPermissionByUrl.getPid()){
					return flag;
				}
			}
			
			selectPermissionByUrl = userDao.selectPermissionByPid(selectPermissionByUrl.getPid());
			logger.info("pid-------------------------->" + selectPermissionByUrl.getPid());
			if(selectPermissionByUrl.getPid() == 0){
				flag = false;
			}
		
		}
		return flag;
	}

	@Override
	public JSONArray settingUserInit() throws Exception {
		StringBuffer buffer = new StringBuffer();
		List<UserBean> selectAllUser = userDao.selectAllUser();
		buffer.append("[");
		for (UserBean userBean : selectAllUser) {
			buffer.append(ParseJSON.getJSON(userBean).toString());
			buffer.append(",");
		}
		buffer.append("]");
		return ParseJSON.getJSONArray(buffer.toString());
	}

	@Override
	public UserBean getUserById(int userId) throws Exception {
		AESUtil aes = new AESUtil();
		UserBean userBean = userDao.selectUserById(userId);
		userBean.setPassword(new String(aes.Decryptor(ParseHexUtil.parseHexStr2Byte(userBean.getPassword()))));
		return userBean;
	}
	
	@Override
	public JSONArray getRoleByUserId(int userId) throws Exception {
		List<RoleBean> roles = userDao.selectRoleByUserId(userId);
		return ParseJSON.getJSONArray(roles);
	}

	@Override
	public JSONArray getRoleInit() throws Exception {
		List<RoleBean> roles = userDao.selectAllRole();
		for (RoleBean roleBean : roles) {
			logger.debug("Roles----------------->" + roleBean.getRemark());
		}
		return ParseJSON.getJSONArray(roles);
	}
	
	

}
