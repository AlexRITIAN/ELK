package com.lenovo.elk3.dao;

import com.lenovo.elk3.beans.UserBean;

public interface LoginDao {
	int selectByUser(UserBean user);
}
