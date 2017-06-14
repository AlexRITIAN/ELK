package com.lenovo.elk3.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.NotificationBean;
import com.lenovo.elk3.dao.NotificationDao;

@Service(value = "notificationservice")
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	private NotificationDao notificationdao;
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public void setNotificationdao(NotificationDao notificationdao) {
		this.notificationdao = notificationdao;
	}

	@Override
	public int add(String operation,String opDetail,String username) throws Exception {
		NotificationBean notification = new NotificationBean();
		String[] split = opDetail.split("!@");
		StringBuffer contentBuffer = new StringBuffer();
		for (String string : split) {
			contentBuffer.append("<p>"+string+"</p>");
		}
		notification.setMsg(username + "进行了" + operation + "操作");
		notification.setContent(contentBuffer.toString());
		notification.setTime(dateFormat.format(new Date()));
		notificationdao.insert(notification);
		return notification.getId();
	}

	@Override
	public int changeStatus(int id, int status) throws Exception {
		return notificationdao.updateStatusById(id, status);
	}

	@Override
	public int pdelete(String ids) throws Exception {
		List<NotificationBean> list = new ArrayList<>();
		String[] split = ids.split(",");
		for (String id : split) {
			NotificationBean notification = new NotificationBean();
			notification.setId(Integer.valueOf(id));
			list.add(notification);
		}
		return notificationdao.delete(list);
	}

	@Override
	public List<NotificationBean> getMsg(int user_id,int from,int size) throws Exception {
		return notificationdao.selectByUserId(user_id,from,size);
	}
	
	@Override
	public List<NotificationBean> getMsgNoLimit(int user_id) throws Exception{
		return notificationdao.selectByUserIdNolimit(user_id);
	}

	@Override
	public NotificationBean getContent(int id) throws Exception {
		return notificationdao.selectById(id);
	}

	@Override
	public int mdelete(NotificationBean notificationBean) throws Exception {
		return notificationdao.updateDelmark(notificationBean);
	}

	@Override
	public int read(NotificationBean notificationBean) throws Exception {
		return notificationdao.updateStatus(notificationBean);
	}

	@Override
	public int getTotal(int user_id) throws Exception {
		return notificationdao.selectByUserIdAll(user_id);
	}

}
