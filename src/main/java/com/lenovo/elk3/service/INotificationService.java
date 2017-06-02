package com.lenovo.elk3.service;


import java.util.List;

import com.lenovo.elk3.beans.NotificationBean;

public interface INotificationService {

	int add(String option,String opDetail,String username) throws Exception;
	
	int changeStatus(int id,int status) throws Exception;
	
	int pdelete(String ids) throws Exception;
	
	List<NotificationBean> getMsg(int user_id,int from,int size) throws Exception;

	List<NotificationBean> getMsgNoLimit(int user_id) throws Exception;
	
	int getTotal(int user_id) throws Exception;
	
	NotificationBean getContent(int id) throws Exception;
	
	int mdelete(NotificationBean notificationBean) throws Exception;
	
	int read(NotificationBean notificationBean) throws Exception;
}
