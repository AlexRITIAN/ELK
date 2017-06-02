package com.lenovo.elk3.utils;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ParseJSON {
	public static JSONObject getJSON(Object obj){
		JSONObject jsonObj = JSONObject.fromObject(obj);
		return jsonObj;
	}
	
	public static JSONArray getJSONArray(Object obj){
		return JSONArray.fromObject(obj);
	}

}
