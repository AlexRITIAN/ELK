package com.lenovo.elk3.service;

import com.lenovo.elk3.beans.UserBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IndexService {
	JSONArray index(String index,String type,int from,int size) throws Exception;
	
	JSONObject single(String index,String type,String id) throws Exception;
	
	boolean match(int userId,String url) throws Exception;
	
	JSONArray settingUserInit() throws Exception;
	
	UserBean getUserById(int userId) throws Exception;
	
	JSONArray getRoleByUserId(int userId) throws Exception;
	
	JSONArray getRoleInit() throws Exception;
}
