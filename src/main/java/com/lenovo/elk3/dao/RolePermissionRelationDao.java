package com.lenovo.elk3.dao;

import java.util.List;

import com.lenovo.elk3.beans.RoleBean;
import com.lenovo.elk3.beans.RolePermissionRelationBean;

public interface RolePermissionRelationDao {

	List<RolePermissionRelationBean> selectByRoleId(int roleId);
	
	int deleteByRid(List<RoleBean> list);
	
	int insert(List<RolePermissionRelationBean> list);
	

}
