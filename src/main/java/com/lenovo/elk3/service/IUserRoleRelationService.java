package com.lenovo.elk3.service;


public interface IUserRoleRelationService {
	
	int deleteByUid(String userId) throws Exception;
	
	int add(int userId,String roleIds) throws Exception;
}
