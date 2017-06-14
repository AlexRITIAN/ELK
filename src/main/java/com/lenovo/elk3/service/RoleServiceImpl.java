package com.lenovo.elk3.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.RoleBean;
import com.lenovo.elk3.dao.RoleDao;
import com.lenovo.elk3.utils.ParseJSON;

import net.sf.json.JSONObject;

@Service(value="roleService")
public class RoleServiceImpl implements IRoleService {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Autowired
	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public JSONObject getRoleByid(int id) {
		return ParseJSON.getJSON(roleDao.selectById(id));
	}

	@Override
	public int updateRole(RoleBean role) {
		role.setEditTime(dateFormat.format(new Date()));
		return roleDao.update(role);
	}

	/*@Override
	public List<PermissionBean> getPermissionByRoleId(int roleId) {
		return roleDao.selectPermissionByRoleId(roleId);
	}*/

	@Override
	public List<RoleBean> getAll() throws Exception {
		return roleDao.selectAll();
	}

	@Override
	public int add(RoleBean role) throws Exception {
		role.setCreateTime(dateFormat.format(new Date()));
		return roleDao.insert(role);
	}

	@Override
	public int delete(String roleIds) throws Exception {
		List<RoleBean> list = new ArrayList<>();
		String[] split = roleIds.split(",");
		for (String id : split) {
			RoleBean role = new RoleBean();
			role.setId(Integer.valueOf(id));
			list.add(role);
		}
		return roleDao.delete(list);
	}

	@Override
	public List<RoleBean> getRoleByUid(int uid) throws Exception {
		return roleDao.getRoleByUid(uid);
	}

	@Override
	public List<RoleBean> getAllLimit(int from) {
		return roleDao.selectAllLimit(from);
	}

}
