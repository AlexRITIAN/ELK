package com.lenovo.elk3.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.lenovo.elk3.beans.PermissionBean;
import com.lenovo.elk3.utils.MatchUtil;

public class HandlerInterceptor implements org.springframework.web.servlet.HandlerInterceptor {
	
	private Logger logger = Logger.getLogger(HandlerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("========>HandlerInterceptor Running<==============");
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("username") == null){
			response.sendRedirect("/elk3/login/login.do");
			return false;
		}else if(session.getAttribute("permission") == null || request.getParameter("url") == null){
			logger.debug("=======>url: " + request.getParameter("url") + "<==========");
			request.getRequestDispatcher("/WEB-INF/jsp/add_false.jsp").forward(request, response);
			return false;
		}else{
			String url = (String) request.getParameter("url");
			@SuppressWarnings("unchecked")
			List<PermissionBean> permissionList = (List<PermissionBean>) session.getAttribute("permission");
			if(MatchUtil.matchPermission(url, permissionList)){
				return true;
			}else{
				request.getRequestDispatcher("/WEB-INF/jsp/add_false.jsp").forward(request, response);
				return false;
			}
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
