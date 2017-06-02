package com.lenovo.elk3.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.service.IUserRoleRelationService;
import com.lenovo.elk3.service.UserRoleRelationServiceImpl;

@Controller
public class UserRoleRelationController {
	
	@Autowired
	@Qualifier(value = "UserRoleRelationService")
	private IUserRoleRelationService urrService;
	private Logger logger = Logger.getLogger(UserRoleRelationController.class);

	@RequestMapping("/urr/deleteByUid.do")
	public void deleteByUid(String userId,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(urrService.deleteByUid(userId));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/urr/addMore.do")
	public void addMore(int userId,String roleIds,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(urrService.add(userId, roleIds));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}

}
