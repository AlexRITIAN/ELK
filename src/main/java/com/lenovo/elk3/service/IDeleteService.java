package com.lenovo.elk3.service;

import net.sf.json.JSONObject;

public interface IDeleteService {
	JSONObject delete(String index,String type,String id) throws Exception;
}
