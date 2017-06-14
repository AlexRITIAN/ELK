package com.lenovo.elk3.controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SettingController {

	@RequestMapping("/setting/show.do")
	public String setting() {
		return "setting";
	}
	
	@RequestMapping("/setting/match.do")
	public void match(HttpServletRequest request, HttpServletResponse response, String url) {
		boolean flag = true;
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(flag);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
