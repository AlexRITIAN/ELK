package com.lenovo.elk3.dao;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;

public interface PermissionDao {
	
	int insert(PermissionBean perBean);
	
	int delete(List<PermissionBean> list);
	
	int update(PermissionBean perBean);
	
	PermissionBean selectByid(int id);
	
	
	List<PermissionBean> selectAll();
	
	List<PermissionBean> selectAllLimit(int from);
	
	List<PermissionBean> getByRoleId(int roleId);
	
	List<PermissionBean> selectPermissionByUserId(int userId);
	
	PermissionBean selectPermissionByUrl(String url);
	
	
}
