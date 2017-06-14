package com.lenovo.elk3.service;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.RoleBean;

import net.sf.json.JSONObject;

public interface IRoleService {

	JSONObject getRoleByid(int id);
	
	int updateRole(RoleBean role);
	
//	List<PermissionBean> getPermissionByRoleId(int roleId);
	
	List<RoleBean> getAll() throws Exception;
	
	int add(RoleBean role) throws Exception;
	
	int delete(String roleIds) throws Exception;
	
	List<RoleBean> getRoleByUid(int uid) throws Exception;
	
	List<RoleBean> getAllLimit(int from);
	
}
