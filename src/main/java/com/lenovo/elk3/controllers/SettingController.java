package com.lenovo.elk3.controllers;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.service.ISettingService;

@Controller
public class SettingController {
	
	@Autowired
	@Qualifier(value = "settingService")
	private ISettingService settingService;

	@RequestMapping("/setting/show.do")
	public String setting() {
		return "setting";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/setting/match.do")
	public void match(HttpServletRequest request, HttpServletResponse response, String url) {
		boolean flag = true;
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		try {
			flag = settingService.match(url, (List<PermissionBean>)session.getAttribute("permission"));
			out = response.getWriter();
			out.print(flag);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
