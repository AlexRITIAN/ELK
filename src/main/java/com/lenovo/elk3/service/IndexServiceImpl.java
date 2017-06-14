package com.lenovo.elk3.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.dao.PermissionDao;

@Service(value = "indexservice")
public class IndexServiceImpl implements IndexService {
	Logger logger = Logger.getLogger(IndexServiceImpl.class);

	@Autowired
	private PermissionDao dao;

	public void setUserDao(PermissionDao userDao) {
		this.dao = userDao;
	}

	@Override
	public boolean match(int userId, String url) throws Exception {
		boolean flag = true;
		/*List<PermissionBean> selectPermissionById = dao.selectPermissionByUserId(userId);
		PermissionBean selectPermissionByUrl = dao.selectPermissionByUrl(url);
		while (flag) {
			for (PermissionBean permissionBean : selectPermissionById) {
				if (permissionBean.getId() == selectPermissionByUrl.getId()
						|| permissionBean.getId() == selectPermissionByUrl.getPid()) {
					return flag;
				}
			}
			selectPermissionByUrl = dao.selectPermissionByPid(selectPermissionByUrl.getPid());
			if (selectPermissionByUrl.getPid() == 0) {
				flag = false;
			}
		}*/
		return flag;
	}
}
