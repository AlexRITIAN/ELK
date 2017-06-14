package com.lenovo.elk3.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.service.ILoginService;
import com.lenovo.elk3.utils.ParseJSON;



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
				List<PermissionBean> permission = loginservice.getPermission(id);
				session.setAttribute("username", user.getName());
				session.setAttribute("userId", id);
				session.setAttribute("permission", permission);
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
	
	@RequestMapping("login/getUsername.do")
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
			logger.error(e.getMessage(),e);
		}
	}
}
