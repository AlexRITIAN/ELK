package com.lenovo.elk3.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.service.IPermissionService;

@Controller
public class PermissionController {
	
	@Autowired
	@Qualifier(value = "permissionService")
	private IPermissionService perService;
	private Logger logger = Logger.getLogger(PermissionController.class);
	
	@RequestMapping("/permission/getAll.do")
	public void getAll(HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(perService.getAll());
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/permission/add.do")
	public void add(PermissionBean permission,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(perService.add(permission));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/permission/delete.do")
	public void delete(String permissionIds,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(perService.delete(permissionIds));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/permission/getById.do")
	public void getById(int permissionId,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(perService.getById(permissionId));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/permission/update.do")
	public void update(PermissionBean permission,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(perService.update(permission));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
