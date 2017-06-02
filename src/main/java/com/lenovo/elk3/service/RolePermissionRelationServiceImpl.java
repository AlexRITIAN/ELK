package com.lenovo.elk3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.RoleBean;
import com.lenovo.elk3.beans.RolePermissionRelationBean;
import com.lenovo.elk3.dao.RolePermissionRelationDao;

@Service(value = "rolepermissionrelationservice")
public class RolePermissionRelationServiceImpl implements IRolePermissionRelationService {
	@Autowired
	private RolePermissionRelationDao rprDao;

	public void setRprDao(RolePermissionRelationDao rprDao) {
		this.rprDao = rprDao;
	}

	@Override
	public int delete(String roleIds) throws Exception {
		String[] split = roleIds.split(",");
		List<RoleBean> list = new ArrayList<>();
		for (String roleId : split) {
			RoleBean role = new RoleBean();
			role.setId(Integer.valueOf(roleId));
			list.add(role);
		}
		return rprDao.deleteByRid(list);
	}

	@Override
	public int add(String permissionIds, int roleId) throws Exception {
		String[] split = permissionIds.split(",");
		List<RolePermissionRelationBean> list = new ArrayList<>();
		for (String permissionId : split) {
			RolePermissionRelationBean rpr = new RolePermissionRelationBean();
			rpr.setRole_id(roleId);
			rpr.setPermission_id(Integer.valueOf(permissionId));
			list.add(rpr);
		}
		return rprDao.insert(list);
	}

}
