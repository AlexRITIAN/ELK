package com.lenovo.elk3.controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.service.IRolePermissionRelationService;

@Controller
public class RolePermissionRelationController {

	@Autowired
	@Qualifier(value = "rolepermissionrelationservice")
	private IRolePermissionRelationService rprService;
	
	private Logger logger = Logger.getLogger(RolePermissionRelationController.class);
	
	@RequestMapping("/rpr/delete.do")
	public void delete(String roleIds,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(rprService.delete(roleIds));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/rpr/add.do")
	public void add(String permissionIds,int roleId,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(rprService.add(permissionIds, roleId));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
