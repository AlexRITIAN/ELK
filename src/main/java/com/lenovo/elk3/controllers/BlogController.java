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
import com.lenovo.elk3.service.IBlogService;
import com.lenovo.elk3.utils.ParseJSON;

import net.sf.json.JSONObject;

@Controller
public class BlogController {

	@Autowired
	@Qualifier(value="BlogService")
	private IBlogService service;
	
	Logger logger = Logger.getLogger(BlogController.class);
	
	@RequestMapping("/blog/add.do")
	public void add(BlogBean blog,String index,String type,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			if (service.add(blog,index,type)) {
				out.print(1);
			}else{
				out.print(0);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@RequestMapping("/blog/delete.do")
	public void delete(String index,String type,String id,HttpServletResponse response){
		try {
			PrintWriter out = response.getWriter();
			JSONObject delete = service.delete(index, type, id);
			out.print("{\"flag\":" + delete.getBoolean("found") + "}");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/blog/edit.do")
	public void edit(BlogBean blog,String index,String type,String id,HttpServletResponse response){
		PrintWriter out = null;
		try {
				service.edit(blog,index,type,id);
				out = response.getWriter();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		out.print(1);
	}
	
	@RequestMapping("/blog/show.do")
	public String show(Map<String, String> map, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			map.put("from", "add/show.do");
			return "login";
		}
		map.put("author", (String)session.getAttribute("username"));
		return "add_blog";
	}
	
	@RequestMapping("/blog/single.do")
	public String single(Map<String, JSONObject> map, String index, String type, String id,
			HttpServletRequest request) {
		JSONObject json = null;
		HttpSession session = request.getSession(false);
		try {
			json = service.single(index, type, id);
			map.put("blog", json);
			if (session != null && session.getAttribute("username") != null) {
				map.put("username", ParseJSON.getJSON("{'username':'" + session.getAttribute("username") + "'}"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "single";
	}
	
	@RequestMapping("/blog/editPage.do")
	public String editPage(Map<String, JSONObject> map, String index, String type, String id, HttpServletRequest request) {
		JSONObject json = null;
		HttpSession session = request.getSession(false);
		try {
			if (session != null && session.getAttribute("username") != null) {
				json = service.single(index, type, id);
				map.put("blog", json);
				map.put("username", ParseJSON.getJSON("{'username':'" + session.getAttribute("username") + "'}"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "edit";
	}
}
