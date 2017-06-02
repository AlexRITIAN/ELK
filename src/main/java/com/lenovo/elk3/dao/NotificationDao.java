package com.lenovo.elk3.dao;

import java.util.List;

import com.lenovo.elk3.beans.NotificationBean;

public interface NotificationDao {
	
	int insert(NotificationBean notification);
	
	int updateStatusById(int id,int status);
	
	int delete(List<NotificationBean> list);
	
	List<NotificationBean> selectByUserId(int user_id,int from,int size);
	
	List<NotificationBean> selectByUserIdNolimit(int user_id);
	
	List<NotificationBean> selectByUserIdAll(int user_id);
	NotificationBean selectById(int id);
	
	int updateDelmark(NotificationBean notification);
	
	int updateStatus(NotificationBean notification);
	
	
	
}
