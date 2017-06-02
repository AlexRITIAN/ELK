package com.lenovo.elk3.service;

import com.lenovo.elk3.beans.UserBean;

public interface ILoginService {
	int match(UserBean user) throws Exception;
}
