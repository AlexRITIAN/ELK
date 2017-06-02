package com.lenovo.elk3.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.service.IUserService;

@Controller
public class UserController {

	@Autowired
	@Qualifier(value = "UserService")
	private IUserService userService;
	private Logger logger = Logger.getLogger(AddController.class);

	@RequestMapping("/user/updateUser.do")
	public void updateUser(UserBean user, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			int update = userService.update(user);
			out = response.getWriter();
			out.print(update);
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@RequestMapping("/user/add.do")
	public void add(UserBean user,HttpServletResponse response){
		PrintWriter out = null;
		try {
			userService.add(user);
			logger.debug("id---------------------------------->" + user.getId());
			out = response.getWriter();
			out.print("{\"id\":" + user.getId() + "}");
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/user/delete.do")
	public void delete(String uids,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(userService.delete(uids));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/user/getUsername.do")
	public void getUsername(HttpServletRequest request,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			HttpSession session = request.getSession(false);
			if(session != null && session.getAttribute("username") != null){
				out.print("{\"username\":\"" + session.getAttribute("username") + "\"}");
			}else{
				out.print("{\"username\":null}");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
