package com.lenovo.elk3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.utils.MatchUtil;

@Service(value="settingService")
public class SettingServiceImpl implements ISettingService {

	@Override
	public boolean match(String url, List<PermissionBean> permissionList) {
		return MatchUtil.matchPermission(url, permissionList);
		
	}

}
