package com.lenovo.elk3.service;

import com.lenovo.elk3.beans.UserBean;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;

public interface ILoginService {
	int match(UserBean user) throws Exception;
	
	List<PermissionBean> getPermission(int userID);
}
