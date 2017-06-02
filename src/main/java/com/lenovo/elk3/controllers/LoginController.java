package com.lenovo.elk3.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.service.ILoginService;



@Controller
public class LoginController {
	@Autowired
	@Qualifier(value = "loginservice")
	private ILoginService loginservice;

	private Logger logger = Logger.getLogger(LoginController.class);

	@RequestMapping("login/login.do")
	public String login(Map<String,String> map ,String from) {
		map.put("from", from);
		return "login";
	}

	@RequestMapping("login/match.do")
	public void match(HttpServletRequest request, HttpServletResponse response, UserBean user) {
		PrintWriter out = null;
		int id = 0;
		try {
			id = loginservice.match(user);
			if (id != 0) {
				HttpSession session = request.getSession();
				session.setAttribute("username", user.getName());
				session.setAttribute("userId", id);
			}
			out = response.getWriter();
			out.println(id);
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}

	}
	
	@RequestMapping("login/logout.do")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		try {
			if(session != null && session.getAttribute("username") != null)	{
				session.setAttribute("username", null);
				session.setAttribute("userId", null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return "index";
	}
}
