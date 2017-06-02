package com.lenovo.elk3.dao;

import java.util.List;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.beans.UserNotificationRelationBean;

public interface UserNotificationRelationDao {

	int insert(List<UserNotificationRelationBean> list);
	
	List<UserNotificationRelationBean> selectByUserId(int userId);
	
	int delete(List<UserNotificationRelationBean> list);
	
	int deleteByUserID(List<UserNotificationRelationBean> list);
	
	List<UserBean> selectUserIdByPermissionId(List<PermissionBean> list);
	
	PermissionBean selectPermissionidByoperationUrl(String operationUrl);
	
	PermissionBean selectPermissionPidByid(int id);
	
	int updateStatus(UserNotificationRelationBean urn);
}
