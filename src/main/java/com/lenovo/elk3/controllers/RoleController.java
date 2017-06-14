package com.lenovo.elk3.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			out = response.getWriter();
			out.print(ParseJSON.getJSON(roleService.getRoleByid(roleId)));
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
	
	@RequestMapping("/role/getRoleByUid.do")
	public void getRoleByUid(int uid,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(ParseJSON.getJSONArray(roleService.getRoleByUid(uid)));
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	@RequestMapping("/role/detailPage.do")
	public String detailPage(HttpServletRequest request, Map<String, Integer> map, int roleId) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			try {
				map.put("roleId", roleId);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			return "login";
		}
		return "settingRoleDetail";
	}
	
	@RequestMapping("/role/addRolePage.do")
	public String indexAddRole(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
		} else {
			return "login";
		}
		return "addRole";
	}
	
	@RequestMapping("/role/getAllLimit.do")
	public void getAllLimit(HttpServletResponse response,int from){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(ParseJSON.getJSONArray(roleService.getAllLimit(from)));
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
	}
}
