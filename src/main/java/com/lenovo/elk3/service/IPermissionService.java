package com.lenovo.elk3.service;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IPermissionService {
	
	JSONArray getAll() throws Exception;
	
	int add(PermissionBean permission) throws Exception;
	
	int delete(String permissionIds) throws Exception;
	
	JSONObject getById(int id) throws Exception;
	
	int update(PermissionBean permission) throws Exception;
}
