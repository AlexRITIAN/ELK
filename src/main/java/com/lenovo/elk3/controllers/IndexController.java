package com.lenovo.elk3.controllers;

import java.io.IOException;
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

	@RequestMapping("/index/index.do")
	public void index(HttpServletRequest request, HttpServletResponse response, String index, String type, int from,
			int size) {
		HttpSession session = request.getSession(false);
		PrintWriter out = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		try {
			buffer.append("\"username\":\"" + session.getAttribute("username") + "\",\"blogs\":"
					+ indexService.index(index, type, from, size));
			if (session == null || session.getAttribute("username") == null) {
				buffer.append(",\"flag\":1");
			}
			buffer.append("}");
			out = response.getWriter();
			out.print(ParseJSON.getJSON(buffer.toString()));
			out.close();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/index/show.do")
	public String show() {
		return "index";
	}

	@RequestMapping("/index/single.do")
	public String single(Map<String, JSONObject> map, String index, String type, String id,
			HttpServletRequest request) {
		JSONObject json = null;
		HttpSession session = request.getSession(false);
		try {
			json = indexService.single(index, type, id);
			map.put("blog", json);
			if (session != null && session.getAttribute("username") != null) {
				map.put("username", ParseJSON.getJSON("{'username':'" + session.getAttribute("username") + "'}"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "single";
	}

	@RequestMapping("/index/edit.do")
	public String edit(Map<String, JSONObject> map, String index, String type, String id, HttpServletRequest request) {
		JSONObject json = null;
		HttpSession session = request.getSession(false);
		try {
			if (session != null && session.getAttribute("username") != null) {
				json = indexService.single(index, type, id);
				map.put("blog", json);
				map.put("username", ParseJSON.getJSON("{'username':'" + session.getAttribute("username") + "'}"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "edit";
	}

	@RequestMapping("/index/detail.do")
	public String delete(HttpServletRequest request, Map<String, String> map) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			return "detail";
		}
		map.put("from", "index/detail.do");
		return "login";
	}

	@RequestMapping("/index/setting.do")
	public String setting(HttpServletRequest request,Map<String,String> map) {
		HttpSession session = request.getSession(false);
		try {
			if (session != null && session.getAttribute("username") != null && indexService.match((int) session.getAttribute("userId"), "/index/setting.do")) {
				map.put("username", (String)session.getAttribute("username"));
				return "setting";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "add_false";
	}

	@RequestMapping("/index/settingUserInit.do")
	public void settingUserInit(HttpServletResponse response) {
		PrintWriter out = null;
		try {
			JSONArray settingUserInit = indexService.settingUserInit();
			out = response.getWriter();
			out.print(settingUserInit);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/index/indexUserDetail.do")
	public String indexUserDetail(HttpServletRequest request, Map<String, Integer> map, int userId) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			try {
				map.put("userId", userId);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			return "login";
		}
		return "settingUserDetail";
	}

	@RequestMapping("/index/settingUserDetail.do")
	public void settingUserDetail(HttpServletResponse response, int userId) {
		PrintWriter out = null;
		try {
			JSONArray roleByUserId = indexService.getRoleByUserId(userId);
			UserBean userById = indexService.getUserById(userId);
			JSONArray roleInit = indexService.getRoleInit();
			String result = "{\"userRole\":" + roleByUserId + ",\"user\":" + ParseJSON.getJSON(userById) + ",\"roles\":"
					+ roleInit + "}";
			out = response.getWriter();
			out.print(ParseJSON.getJSON(result));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/index/getAllRoles.do")
	public void getAllRoles(HttpServletResponse response) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			JSONArray roleInit = indexService.getRoleInit();
			out = response.getWriter();
			out.print(roleInit);
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/index/indexRoleDetail.do")
	public String indexRoleDetail(HttpServletRequest request, Map<String, Integer> map, int roleId) {
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

	@RequestMapping("/index/indexAddUser.do")
	public String indexAddUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
		} else {
			return "login";
		}
		return "addUser";
	}

	@RequestMapping("/index/indexAddRole.do")
	public String indexAddRole(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
		} else {
			return "login";
		}
		return "addRole";
	}

	@RequestMapping("/index/indexAddPermission.do")
	public String indexAddPermission(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
		} else {
			return "login";
		}
		return "addPermission";
	}

	@RequestMapping("/index/indexPermissionDetail.do")
	public String indexPermissionDetail(HttpServletRequest request, Map<String, Integer> map, int id) {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			try {
				map.put("permissionId", id);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} else {
			return "login";
		}
		return "settingPermissionDetail";
	}

	@RequestMapping("/index/match.do")
	public void match(HttpServletRequest request, HttpServletResponse response, String url) {
		HttpSession session = request.getSession(false);
		boolean flag = false;
		PrintWriter out = null;
		try {
			if (session != null && session.getAttribute("username") != null) {
				if (indexService.match((int) session.getAttribute("userId"), url)) {
					flag = true;
				}
			}
			out = response.getWriter();
			out.print(flag);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping("/index/notificationCenter.do")
	public String notificationCenter(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("username") != null){
			return "notificationCenter";
		}
		return "login";
		
	}
}
