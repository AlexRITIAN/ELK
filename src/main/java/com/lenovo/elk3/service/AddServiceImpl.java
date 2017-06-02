package com.lenovo.elk3.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.beans.BlogBean;
import com.lenovo.elk3.beans.RoleBean;
import com.lenovo.elk3.beans.UserBean;
import com.lenovo.elk3.dao.UserDao;
import com.lenovo.elk3.utils.AESUtil;
import com.lenovo.elk3.utils.ElasticsearchUtil;
import com.lenovo.elk3.utils.HtmlParseUtil;
import com.lenovo.elk3.utils.ParseHexUtil;
import com.lenovo.elk3.utils.ParseJSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(value = "addservice")
public class AddServiceImpl implements IAddService {
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	@Autowired
	private UserDao dao;
	
	public void setDao(UserDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean add(BlogBean blog,String index,String type) throws Exception {
		blog.setContent_search((HtmlParseUtil.getText(blog.getContent_show())));
		JSONObject addJson = ElasticsearchUtil.add(blog,index,type);
		return addJson.getBoolean("created");
	}

	@Override
	public JSONObject selectTag(String tag) throws Exception {
		return null;
	}

	@Override
	public boolean addTag(String tag, String author) throws Exception {
		JSONObject add = ElasticsearchUtil.add("website", "tag", tag, author);
		return add.getBoolean("created");
	}

	@Override
	public JSONObject updateTag(String index, String type, String id, String tag, String author) throws Exception {

		return ElasticsearchUtil.updateTag(index, type, id, tag, author);
	}

	@Override
	public JSONObject boolTagAuthor(String index, String type, String tag, String author) throws Exception {
		return ElasticsearchUtil.bool(index, type, tag, author);
	}

	@Override
	public boolean update(String index, String type, String id, BlogBean blog) throws Exception {
		blog.setContent_search((HtmlParseUtil.getText(blog.getContent_show())));
		JSONObject addJson = ElasticsearchUtil.update(index, type, id, blog);
		return addJson.getBoolean("created");
	}

	@Override
	public int addUsero(UserBean user) throws Exception {
		AESUtil aes = new AESUtil();
		user.setPassword(ParseHexUtil.parseByte2HexStr((aes.Encrytor(user.getPassword()))));
		user.setCreateTime(dateFormat.format(new Date()));
		return dao.insert(user);
		
	}

	

}
