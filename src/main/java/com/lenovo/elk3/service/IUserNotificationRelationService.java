package com.lenovo.elk3.service;

import com.lenovo.elk3.beans.UserNotificationRelationBean;

public interface IUserNotificationRelationService {
	
	int add(String operationUrl,int notifiction_id,int user_id) throws Exception;
	
	int readed(UserNotificationRelationBean unr) throws Exception;
	
	int rmunrelation(String userIds) throws Exception;
}
