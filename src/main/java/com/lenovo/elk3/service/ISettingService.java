package com.lenovo.elk3.service;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;

public interface ISettingService {
	boolean match(String url,List<PermissionBean> permissionList);
}
