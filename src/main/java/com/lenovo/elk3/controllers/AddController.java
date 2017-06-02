package com.lenovo.elk3.controllers;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.beans.BlogBean;
import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.service.IAddService;


@Controller
public class AddController {
	@Autowired
	@Qualifier(value = "addservice")
	private IAddService addService;
	private Logger logger = Logger.getLogger(AddController.class);

	@RequestMapping("/add/add.do")
	public void add(String index,String type,BlogBean blog,HttpServletRequest request,HttpServletResponse response) {
//		logger.info("blog-text: " + request.getParameter("text"));
//		blog.setText(request.getParameter("text"));
		try {
			PrintWriter out = response.getWriter();
			if (addService.add(blog,index,type)) {
				out.print(1);
			}else{
				out.print(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/add/show.do")
	public String show(Map<String, String> map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			map.put("from", "add/show.do");
			return "login";
		}
		map.put("author", (String)session.getAttribute("username"));
		return "add_blog";
	}
	
	@RequestMapping("/add/edit.do")
	public void edit(String index,String type,String id,BlogBean blog,HttpServletResponse response){
		PrintWriter out = null;
		try {
				addService.update(index, type, id, blog);
				out = response.getWriter();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		out.print(1);
	}
	
	@RequestMapping("/add/user.do")
	public void user(UserBean user,HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(addService.addUsero(user));
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
}
