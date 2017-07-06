package com.lenovo.elk3.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.dao.UserDao;
import com.lenovo.elk3.utils.AESUtil;
import com.lenovo.elk3.utils.ParseHexUtil;

@Service(value = "loginservice")
public class LoginServiceImpl implements ILoginService {
	@Autowired
	private UserDao dao;
	private Logger logger = Logger.getLogger(LoginServiceImpl.class);

	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public int match(UserBean user) throws Exception {
		int flag = 0;
		UserBean userBean = dao.selectByName(user.getName());
		AESUtil aes = new AESUtil();
		byte[] decPasswordByte = aes.Decryptor(ParseHexUtil.parseHexStr2Byte(userBean.getPassword()));
		if(user.getPassword().equals(new String(decPasswordByte))){
			if(userBean.getLockStatus() == 0){
				flag = userBean.getId();
			}else{
				flag = -1;
			}
		}
		return flag;
	}

	@Override
	public List<PermissionBean> getPermission(int userID) {
		return dao.selectPermission(userID);
	}

}
