package com.lenovo.elk3.dao;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.RoleBean;
import com.lenovo.elk3.beans.UserBean;

public interface UserDao {
	int insert(UserBean user);
	
	UserBean selectByName(String name);
	
	List<PermissionBean> selectPermissionById(int id);
	
	PermissionBean selectPermissionByUrl(String url);
	
	PermissionBean selectPermissionByPid(int pid);
	
	List<UserBean> selectAllUser();
	
	UserBean selectUserById(int userId);
	
	List<RoleBean> selectRoleByUserId(int userId);
	
	List<RoleBean> selectAllRole();
	
	int update(UserBean user);
	
	int delete(List<UserBean> list);
	
}
