package com.lenovo.elk3.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.beans.RoleBean;
import com.lenovo.elk3.service.IRoleService;
import com.lenovo.elk3.utils.ParseJSON;

@Controller
public class RoleController {
	
	@Autowired
	@Qualifier(value = "roleService")
	private IRoleService roleService;
	
	private Logger logger = Logger.getLogger(RoleController.class);
	
	@RequestMapping("/role/update.do")
	public void update(RoleBean role,HttpServletResponse response,HttpServletRequest request){
		response.setContentType("text/hmtl;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			logger.debug("remark-------------------------------->" + role.getRemark());
			out.print(roleService.updateRole(role));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/role/getRoleById.do")
	public void getRoleById(int roleId,HttpServletResponse response){
		PrintWriter out = null;
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{\"role\":" + roleService.getRoleByid(roleId) + ",\"rolePermission\":" + ParseJSON.getJSONArray(roleService.getPermissionByRoleId(roleId)) + "}");
			out = response.getWriter();
			logger.debug("buffer------------------------->" + buffer.toString());
			out.print(ParseJSON.getJSON(buffer.toString()));
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/role/getAll.do")
	public void getAll(HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(ParseJSON.getJSONArray(roleService.getAll()));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/role/add.do")
	public void add(RoleBean role,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			roleService.add(role);
			out.print(role.getId());
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/role/delete.do")
	public void delete(String roleIds,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(roleService.delete(roleIds));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
