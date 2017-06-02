package com.lenovo.elk3.service;

import java.util.List;

import com.lenovo.elk3.beans.UserBean;

import net.sf.json.JSONObject;

public interface IUserService {
	
	int add(UserBean user) throws Exception;
	
	JSONObject getUserById(int id) throws Exception;
	
	JSONObject getUserByName(String userName) throws Exception;
	
	int delete(String uids) throws Exception;
	
	int update(UserBean user) throws Exception;
}
