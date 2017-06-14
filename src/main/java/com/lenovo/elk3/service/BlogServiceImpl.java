package com.lenovo.elk3.service;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.BlogBean;
import com.lenovo.elk3.utils.ElasticsearchUtil;
import com.lenovo.elk3.utils.HtmlParseUtil;

import net.sf.json.JSONObject;

@Service(value="BlogService")
public class BlogServiceImpl implements IBlogService {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public Boolean add(BlogBean blog, String index, String type) throws IOException {
		blog.setContent_search((HtmlParseUtil.getText(blog.getContent_show())));
		JSONObject addJson = ElasticsearchUtil.add(blog,index,type);
		return addJson.getBoolean("created");
	}

	@Override
	public JSONObject delete(String index, String type, String id) throws IOException {
		return ElasticsearchUtil.delete(index, type, id);
	}
	
	@Override
	public Boolean edit(BlogBean blog, String index, String type,String id) throws IOException {
		blog.setContent_search((HtmlParseUtil.getText(blog.getContent_show())));
		JSONObject addJson = ElasticsearchUtil.update(index, type, id, blog);
		return addJson.getBoolean("created");
	}

	@Override
	public JSONObject single(String index, String type, String id) throws Exception {
		JSONObject singleJson = ElasticsearchUtil.single(index, type, id);
		if(!"kb1".equals(singleJson.getString("_index"))){
			String content = singleJson.getJSONObject("_source").getString("content");
		    content = content.replace("\r\n", "<br>");
			singleJson.getJSONObject("_source").put("content", content);
		}
		return singleJson;
	}

}
