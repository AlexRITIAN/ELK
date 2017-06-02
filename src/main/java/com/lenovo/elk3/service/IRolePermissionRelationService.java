package com.lenovo.elk3.service;



public interface IRolePermissionRelationService {
	
	int delete(String roleIds) throws Exception;
	
	int add(String permissionIds,int roleId) throws Exception;

}
