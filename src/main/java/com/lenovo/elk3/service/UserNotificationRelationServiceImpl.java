package com.lenovo.elk3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.beans.UserNotificationRelationBean;
import com.lenovo.elk3.dao.UserNotificationRelationDao;

@Service(value = "usernotificationrelationservice")
public class UserNotificationRelationServiceImpl implements IUserNotificationRelationService {

	@Autowired
	UserNotificationRelationDao unrdao;

	public void setUnrdap(UserNotificationRelationDao unrdao) {
		this.unrdao = unrdao;
	}

	@Override
	public int add(String operationUrl, int notifiction_id, int user_id) throws Exception {
		PermissionBean pb = unrdao.selectPermissionidByoperationUrl(operationUrl);
		List<PermissionBean> idList = new ArrayList<>();
		idList.add(pb);
		while (true) {
			pb = unrdao.selectPermissionPidByid(pb.getPid());
			idList.add(pb);
			if (pb.getPid() == 0) {
				break;
			}
		}
		List<UserBean> userList = unrdao.selectUserIdByPermissionId(idList);
		List<UserNotificationRelationBean> unrList = new ArrayList<>();
		for (UserBean userBean : userList) {
			UserNotificationRelationBean unr = new UserNotificationRelationBean();
			if(userBean.getId() != user_id){
				unr.setUser_id(userBean.getId());
				unr.setNotification_id(notifiction_id);
				unr.setStatus(0);
				unrList.add(unr);
			}
		}
		return unrdao.insert(unrList);
	}

	@Override
	public int readed(UserNotificationRelationBean unr) throws Exception {
		return unrdao.updateStatus(unr);
	}

	@Override
	public int rmunrelation(String userIds) throws Exception {
		List<UserNotificationRelationBean> list = new ArrayList<>();
		String[] split = userIds.split(",");
		for (String userid : split) {
			UserNotificationRelationBean unr = new UserNotificationRelationBean();
			unr.setUser_id(Integer.valueOf(userid));
			list.add(unr);
		}
		return unrdao.deleteByUserID(list);
	}

}
