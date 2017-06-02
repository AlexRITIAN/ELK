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

import com.lenovo.elk3.beans.NotificationBean;
import com.lenovo.elk3.service.INotificationService;
import com.lenovo.elk3.utils.ParseJSON;

@Controller
public class NotificationController {
	@Autowired
	@Qualifier(value = "notificationservice")
	private INotificationService notificationservice;

	private Logger logger = Logger.getLogger(NotificationController.class);

	@RequestMapping("/notification/getMsg.do")
	public void getMsg(HttpServletResponse response,HttpServletRequest request,int from,int size,String flag) {
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		int user_id = 0;
		try {
			out = response.getWriter();
			if(session != null && session.getAttribute("userId") != null){
				user_id = (Integer) session.getAttribute("userId");
				if("show".equals(flag)){
					out.print(ParseJSON.getJSONArray(notificationservice.getMsgNoLimit(user_id)));
				}else if("cc".equals(flag)){
					out.print(ParseJSON.getJSONArray(notificationservice.getMsg(user_id, from, size)));
				}
			}else{
				out.print("{\"flag\":false}");
			}
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@RequestMapping("/notification/getContent.do")
	public void getContent(int id,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(ParseJSON.getJSON(notificationservice.getContent(id)));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/notification/add.do")
	public void add(String operation,String opDetail,String username,HttpServletResponse response,HttpServletRequest request){
		PrintWriter out = null;
		try {
			HttpSession session = request.getSession(false);
			if(username == null && session != null && session.getAttribute("username") != null){
				username = (String)session.getAttribute("username");
			}
			out = response.getWriter();
			out.print(notificationservice.add(operation, opDetail, username));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/notification/readed.do")
	public void readed(NotificationBean notification,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(notificationservice.read(notification));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/notification/getTotal.do")
	public void getTotal(HttpServletResponse response,HttpServletRequest request){
		PrintWriter out = null;
		try {
			HttpSession session = request.getSession(false);
			out = response.getWriter();
			if(session != null && session.getAttribute("username") != null){
				out.print("{\"total\":" + notificationservice.getTotal((Integer)session.getAttribute("userId"))+"}");
			}
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
