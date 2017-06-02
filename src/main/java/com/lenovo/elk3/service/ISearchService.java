package com.lenovo.elk3.service;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ISearchService {
	JSONObject searchAll(String index,String type,int from,int size) throws Exception;
	
	JSONObject termSearch(String index,String type,String parm,String matchStr,int from,int size) throws Exception;
	
	JSONArray matchSearch(String index,String type,String parm,String matchStr) throws Exception;
	
	JSONArray mutilMatchSearch(String index,String type,String text) throws Exception;
	
	JSONObject searchFullText(String index,String text,int from,int size) throws Exception;
	
	JSONArray searchIndex() throws Exception;
	
	JSONArray allSearchIndex() throws Exception;
	
	JSONObject esHealth() throws Exception;
	
	JSONObject setWhiteList(String whiteList) throws Exception;
}
