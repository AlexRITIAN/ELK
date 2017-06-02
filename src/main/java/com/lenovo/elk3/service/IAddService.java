package com.lenovo.elk3.service;

import com.lenovo.elk3.beans.BlogBean;
import com.lenovo.elk3.beans.UserBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface IAddService {
	boolean add(BlogBean blog,String index,String type) throws Exception;
	
	JSONObject selectTag(String tag) throws Exception;
	
	boolean addTag(String tag,String author) throws Exception;
	
	JSONObject updateTag(String index,String type,String id,String tag,String author) throws Exception;
	
	JSONObject boolTagAuthor(String index,String type,String tag,String author) throws Exception;
	
	
	boolean update(String index,String type,String id,BlogBean blog) throws Exception;
	
	int addUsero(UserBean user) throws Exception;
}
