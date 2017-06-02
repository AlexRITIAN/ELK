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

import com.lenovo.elk3.beans.UserNotificationRelationBean;
import com.lenovo.elk3.service.IUserNotificationRelationService;

@Controller
public class UserNotificationRelationController {

	@Autowired
	@Qualifier(value="usernotificationrelationservice")
	private IUserNotificationRelationService unservice;
	private Logger logger = Logger.getLogger(UserNotificationRelationController.class);
	
	@RequestMapping("/unr/add.do")
	public void add(String operationUrl,int notification_id,HttpServletResponse response,HttpServletRequest request){
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		try {
			if(session != null && session.getAttribute("username") != null){
				out = response.getWriter();
				out.print(unservice.add(operationUrl, notification_id,(Integer)session.getAttribute("userId")));
			}
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/unr/readed.do")
	public void readed(UserNotificationRelationBean unr,HttpServletRequest request,HttpServletResponse response){
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		try {
			out = response.getWriter();
			if(session != null && session.getAttribute("username") != null){
				unr.setUser_id((Integer)session.getAttribute("userId"));
				out.print(unservice.readed(unr));
			}else{
				out.print("login");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/unr/rmUNR.do")
	public void rmUNR(String userIds,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(unservice.rmunrelation(userIds));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
