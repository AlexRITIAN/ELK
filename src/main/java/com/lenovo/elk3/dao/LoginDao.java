package com.lenovo.elk3.dao;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.UserBean;

public interface LoginDao {
	UserBean selectByUser(UserBean user);
	
	List<PermissionBean> selectPermission(int userID);
}
