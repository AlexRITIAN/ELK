package com.lenovo.elk3.service;

import org.springframework.stereotype.Service;

import com.lenovo.elk3.utils.ElasticsearchUtil;

import net.sf.json.JSONObject;

@Service(value="deleteservice")
public class DeleteServiceImpl implements IDeleteService {

	@Override
	public JSONObject delete(String index, String type, String id) throws Exception {
		
		return ElasticsearchUtil.delete(index, type, id);
	}

}
