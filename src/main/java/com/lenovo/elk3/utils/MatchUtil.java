package com.lenovo.elk3.utils;

import java.util.List;

import org.apache.log4j.Logger;

import com.lenovo.elk3.beans.PermissionBean;

public class MatchUtil {
	private static Logger logger = Logger.getLogger(MatchUtil.class);
	
	public static boolean matchPermission(String url, List<PermissionBean> permissionList) {
		boolean flag = false;
		for (PermissionBean permissionBean : permissionList) {
			logger.debug("======>url: " + url + "<========");
			if (url.equals(permissionBean.getOperationUrl())) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
