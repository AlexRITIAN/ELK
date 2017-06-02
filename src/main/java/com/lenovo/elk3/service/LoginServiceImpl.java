package com.lenovo.elk3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.dao.LoginDao;
import com.lenovo.elk3.dao.UserDao;
import com.lenovo.elk3.utils.AESUtil;
import com.lenovo.elk3.utils.ParseHexUtil;

@Service(value = "loginservice")
public class LoginServiceImpl implements ILoginService {
	@Autowired
	private UserDao dao;

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
			flag = userBean.getId();
		}
		return flag;
	}

}
