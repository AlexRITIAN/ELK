package com.lenovo.elk3.dao;

import java.util.List;

import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.beans.UserRoleRelationBean;

public interface UserRoleRelationDao {
	
	int deleteByUid(List<UserBean> list);
	
	int insert(UserRoleRelationBean urr);
	
	int insertByBatch(List<UserRoleRelationBean> urrList);
}
