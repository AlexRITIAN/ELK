package com.lenovo.elk3.service;

import java.io.IOException;

import com.lenovo.elk3.beans.BlogBean;

import net.sf.json.JSONObject;

public interface IBlogService {
	Boolean add(BlogBean blog,String index,String type) throws IOException;
	
	JSONObject delete(String index, String type, String id) throws IOException;
	
	Boolean edit(BlogBean blog,String index,String type,String id) throws IOException;

	JSONObject single(String index, String type, String id) throws Exception;
	
}
