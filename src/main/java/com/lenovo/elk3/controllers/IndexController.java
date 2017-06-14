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

import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.service.IndexService;
import com.lenovo.elk3.utils.ParseJSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class IndexController {
	@Autowired
	@Qualifier(value = "indexservice")
	private IndexService indexService;

	private Logger logger = Logger.getLogger(IndexController.class);

	@RequestMapping("/index/show.do")
	public String show() {
		return "index";
	}
}
