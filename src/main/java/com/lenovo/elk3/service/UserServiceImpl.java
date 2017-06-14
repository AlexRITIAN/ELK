package com.lenovo.elk3.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.RoleBean;
import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.dao.UserDao;
import com.lenovo.elk3.utils.AESUtil;
import com.lenovo.elk3.utils.ParseHexUtil;
import com.lenovo.elk3.utils.ParseJSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(value = "UserService")
public class UserServiceImpl implements IUserService {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public int add(UserBean user) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		AESUtil aes = new AESUtil();
		user.setPassword(ParseHexUtil.parseByte2HexStr(aes.Encrytor(user.getPassword())));
		user.setCreateTime(dateFormat.format(new Date()));
		return userDao.insert(user);
	}

	@Override
	public UserBean getUserById(int id) {
		return userDao.selectUserById(id);
	}

	@Override
	public JSONObject getUserByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String uids) {
		List<UserBean> list = new ArrayList<>();
		String[] split = uids.split(",");
		for (String id : split) {
			UserBean user = new UserBean();
			user.setId(Integer.valueOf(id));
			list.add(user);
		}
		return userDao.delete(list);
	}

	@Override
	public int update(UserBean user) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		AESUtil aes = new AESUtil();
		user.setPassword(ParseHexUtil.parseByte2HexStr(aes.Encrytor(user.getPassword())));
		user.setEditTime(dateFormat.format(new Date()));
		return userDao.update(user);
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
	public List<UserBean> getAllLimit(int from) throws Exception {
		return userDao.selectAllLimit(from);
	}


	/*@Override
	public JSONArray getRoleByUserId(int userId) {
		List<RoleBean> roles = userDao.selectRoleByUserId(userId);
		return ParseJSON.getJSONArray(roles);
	}*/


	/*@Override
	public JSONArray getRoleInit() {
		List<RoleBean> roles = userDao.selectAllRole();
		return ParseJSON.getJSONArray(roles);
	}*/

}
