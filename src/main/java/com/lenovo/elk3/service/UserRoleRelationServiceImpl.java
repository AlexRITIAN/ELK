package com.lenovo.elk3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.beans.UserRoleRelationBean;
import com.lenovo.elk3.dao.UserRoleRelationDao;

@Service(value="UserRoleRelationService")
public class UserRoleRelationServiceImpl implements IUserRoleRelationService {
	
	@Autowired
	private UserRoleRelationDao urrDao;

	public void setUrrDao(UserRoleRelationDao urrDao) {
		this.urrDao = urrDao;
	}

	@Override
	public int deleteByUid(String userId) throws Exception {
		List<UserBean> list = new ArrayList<>();
		String[] split = userId.split(",");
		for (String id : split) {
			UserBean user = new UserBean();
			user.setId(Integer.valueOf(id));
			list.add(user);
		}
		return urrDao.deleteByUid(list);
	}

	@Override
	public int add(int userId, String roleIds) throws Exception {
		String[] roleIdArray = roleIds.split(",");
		List<UserRoleRelationBean> urrList = new ArrayList<>();
		for (String roleId : roleIdArray) {
			UserRoleRelationBean urr = new UserRoleRelationBean();
			urr.setUser_id(userId);
			urr.setRole_id(Integer.valueOf(roleId));
			urrList.add(urr);
		}
		return urrDao.insertByBatch(urrList);
	}

}
