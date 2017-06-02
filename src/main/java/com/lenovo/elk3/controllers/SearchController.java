package com.lenovo.elk3.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.service.ISearchService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class SearchController {
	@Autowired
	@Qualifier(value = "searchservice")
	private ISearchService searchService;
	private Logger logger = Logger.getLogger(SearchController.class);

	@RequestMapping("/search/test.do")
	public void test() {
		try {
			logger.info(searchService.searchAll("website", "blog", 0, 5));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/search/show.do")
	public String show() {
		return "search";
	}

	@RequestMapping("/search/searchAll.do")
	public void searchAll(String index, String type, int from, int size, HttpServletResponse response,
			HttpServletRequest request) {
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		try {
			StringBuffer buffer = new StringBuffer();
			JSONObject searchAll = searchService.searchAll(index, type, from, size);
			buffer.append("{");
			buffer.append("\"username\":\"" + session.getAttribute("username") + "\",\"blogs\":"
					+ searchAll.getJSONArray("hits") + ",\"total\":" + searchAll.getInt("total"));
			if (session == null || session.getAttribute("username") == null) {
				buffer.append(",\"flag\":1");
			}

			buffer.append("}");
			out = response.getWriter();
			out.print(buffer.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/search/search.do")
	public void search(String index, String type, String parm, String matchStr, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append("\"username\":\"" + session.getAttribute("username") + "\",\"blogs\":"
					+ searchService.matchSearch(index, type, parm, matchStr));
			if (session == null || session.getAttribute("username") == null) {
				buffer.append(",\"flag\":1");
			}
			buffer.append("}");
			out = response.getWriter();
			out.print(buffer.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/search/mutilsearch.do")
	public void mutilSearch(String index, String type, String text, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("{");
			buffer.append("\"username\":\"" + session.getAttribute("username") + "\",\"blogs\":"
					+ searchService.mutilMatchSearch(index, type, text));
			if (session == null || session.getAttribute("username") == null) {
				buffer.append(",\"flag\":1");
			}
			buffer.append("}");
			out = response.getWriter();
			out.print(buffer.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/search/searchfulltext.do")
	public void searchFullText(String index, String text, int from, int size, HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		try {
			StringBuffer buffer = new StringBuffer();
			JSONObject searchFullText = searchService.searchFullText(index, text, from, size);
			buffer.append("{");
			buffer.append("\"username\":\"" + session.getAttribute("username") + "\",\"blogs\":"
					+ searchFullText.getJSONArray("hits") + ",\"total\":" + searchFullText.getInt("total"));
			if (session == null || session.getAttribute("username") == null) {
				buffer.append(",\"flag\":1");
			}
			buffer.append("}");
			out = response.getWriter();
			out.print(buffer.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.close();
		}
	}

	@RequestMapping("/search/index.do")
	public void searchIndex(HttpServletResponse response, HttpServletRequest request) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
				out.println("{\"blogs\":" + searchService.searchIndex() + ",\"allIndex\":"
						+ searchService.allSearchIndex() + "}");
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/search/termsearch.do")
	public void termSeach(String index, String type, String parm,int from,int size, HttpServletResponse response,
			HttpServletRequest request) {
		PrintWriter out = null;
		HttpSession session = request.getSession(false);
		StringBuffer strBuffer = new StringBuffer();
		String username = null;
		try {
			out = response.getWriter();
			if (session != null && session.getAttribute("username") != null) {
				username = (String) session.getAttribute("username");
				if ("admin".equals(username)) {
					JSONObject searchAll = searchService.searchAll(index, type, from, size);
					out.print("{\"blogs\":"
							+ searchAll.getJSONArray("hits")
							+ ",\"username\":\"" + session.getAttribute("username") + "\",\"total\":" + searchAll.getInt("total") + "}");
				}else{
					JSONObject termSearch = searchService.termSearch(index, type, parm, (String) session.getAttribute("username"),from,size);
					out.print("{\"blogs\":"
							+ termSearch.getJSONArray("hits")
							+ ",\"username\":\"" + session.getAttribute("username") + "\",\"total\":" + termSearch.getInt("total") + "}");
				}
			} else {
				out.print("{\"flag\":true}");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@RequestMapping("/search/esStatus.do")
	public void esStatus(HttpServletResponse response, HttpServletRequest request) {
		PrintWriter out = null;
		JSONObject esHealth = null;
		HttpSession session = request.getSession(false);
		String username = null;
		StringBuffer buffer = new StringBuffer();
		try {
			if (session != null && session.getAttribute("username") != null) {
				username = (String) session.getAttribute("username");
			}
			esHealth = searchService.esHealth();
			out = response.getWriter();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		buffer.append("{\"esHealth\":" + esHealth + ",\"username\":\"" + username + "\"}");
		out.print(buffer.toString());
		out.close();
	}

	@RequestMapping("/search/updateSeachIndex.do")
	public void updateSeachIndex(HttpServletResponse response, String whiteList) {
		PrintWriter out = null;
		try {
			searchService.setWhiteList(whiteList);
			out = response.getWriter();
		} catch (Exception e) {
			out.print("{'flag':'" + false + "'}");
			logger.error(e.getMessage(), e);
		}
		out.print(true);
	}
}
