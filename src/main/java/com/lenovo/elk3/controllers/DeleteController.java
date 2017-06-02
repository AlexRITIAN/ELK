package com.lenovo.elk3.controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenovo.elk3.service.IDeleteService;

import net.sf.json.JSONObject;

@Controller
public class DeleteController {
	@Autowired
	@Qualifier(value = "deleteservice")
	private IDeleteService deleteService;

	@RequestMapping("/delete/delete.do")
	public void delete(String index, String type, String id, HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			JSONObject delete = deleteService.delete(index, type, id);
			out.print("{\"flag\":" + delete.getBoolean("found") + "}");
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
