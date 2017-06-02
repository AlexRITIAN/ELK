package com.lenovo.elk3.dao;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.RoleBean;

public interface RoleDao {
	
	RoleBean selectById(int id);
	
	int update(RoleBean role);
	
	List<PermissionBean> selectPermissionByRoleId(int roleId);
	
	List<RoleBean> selectAll();
	
	int insert(RoleBean role);
	
	int delete(List<RoleBean> list);
}
