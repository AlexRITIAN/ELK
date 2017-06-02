package com.lenovo.elk3.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.dao.PermissionDao;
import com.lenovo.elk3.utils.ParseJSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(value="permissionService")
public class PermissionServiceImpl implements IPermissionService {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Autowired
	private PermissionDao perDao;
	
	public void setPerDao(PermissionDao perDao) {
		this.perDao = perDao;
	}

	
	@Override
	public JSONArray getAll() throws Exception {
		return ParseJSON.getJSONArray(perDao.selectAll());
	}


	@Override
	public int add(PermissionBean permission) throws Exception {
		permission.setCreateTime(dateFormat.format(new Date()));
		return perDao.insert(permission);
	}


	@Override
	public int delete(String permissionIds) throws Exception {
		List<PermissionBean> list = new ArrayList<>();
		String[] split = permissionIds.split(",");
		for (String id : split) {
			PermissionBean permission = new PermissionBean();
			permission.setId(Integer.valueOf(id));
			list.add(permission);
		}
		return perDao.delete(list);
	}


	@Override
	public JSONObject getById(int id) throws Exception {
		
		return ParseJSON.getJSON(perDao.selectByid(id));
	}


	@Override
	public int update(PermissionBean permission) throws Exception {
		return perDao.update(permission);
	}

}
