package com.lenovo.elk3.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import com.lenovo.elk3.beans.BlogBean;

import net.sf.json.JSONObject;

public class ElasticsearchUtil {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	private static Logger logger = Logger.getLogger(ElasticsearchUtil.class);

	public static JSONObject select(String index, String type) throws ClientProtocolException, IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String string = HttpClientUtil.get(lc.getProperty("Elasticsearch_url") + index + "/" + type + "/_search");
		return ParseJSON.getJSON(string);
	}

	public static JSONObject add(BlogBean blog, String index, String type) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		blog.setDate(dateFormat.format(new Date()));
		String postData = ParseJSON.getJSON(blog).toString();
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/" + index + "/" + type, postData);
		return ParseJSON.getJSON(post);
	}

	public static JSONObject update(String index, String type, String id, BlogBean blog) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		blog.setDate(dateFormat.format(new Date()));
		String postData = ParseJSON.getJSON(blog).toString();
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/" + index + "/" + type + "/" + id,
				postData);
		return ParseJSON.getJSON(post);
	}

	public static JSONObject term(String index, String type, String parm, String matchStr,int from,int size) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String postData = "{\"query\":{\"term\":{\"" + parm + "\":\"" + matchStr + "\"}},\"from\":"+from+ ",\"size\":"+size+"}";
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/_search",
				postData);
		return ParseJSON.getJSON(post);
	}

	public static JSONObject add(String index, String type, String tag, String username) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String postData = "{\"tag\":\"" + tag + "\",\"author\":\"" + username + "\"}";
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/" + index + "/" + type, postData);
		return ParseJSON.getJSON(post);
	}

	public static JSONObject single(String index, String type, String id) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String string = HttpClientUtil.get(lc.getProperty("Elasticsearch_url") + index + "/" + type + "/" + id);
		return ParseJSON.getJSON(string);
	}

	public static JSONObject searchAll(String index, String type, int from, int size) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String postData = "{\"sort\": { \"date\": { \"order\": \"desc\" }},\"from\":" + from + ",\"size\":" + size
				+ "}";
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + index + "/" + type + "/_search",
				postData);
		return ParseJSON.getJSON(post);
	}

	public static JSONObject updateTag(String index, String type, String id, String tag, String author)
			throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String postData = "{\"tag\":\"" + tag + "\",\"author\":\"" + author + "\"}";
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/" + index + "/" + type + "/" + id,
				postData);
		return ParseJSON.getJSON(post);
	}

	public static JSONObject bool(String index, String type, String tag, String author) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String postData = "{\"query\":{\"bool\":{\"must\":[{\"match\":{\"author\":\"" + author
				+ "\"}},{\"term\":{\"tag\":\"" + tag + "\"}}]}}}";
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/" + index + "/" + type + "/_search",
				postData);
		return ParseJSON.getJSON(post);
	}

	public static JSONObject delete(String index, String type, String id) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String delete = HttpClientUtil
				.delete(lc.getProperty("Elasticsearch_url") + "/" + index + "/" + type + "/" + id);
		return ParseJSON.getJSON(delete);
	}

	public static JSONObject match(String index, String type, String parm, String matchStr) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String postData = "{\"query\":{\"match\":{\"" + parm + "\":\"" + matchStr + "\"}}}";
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/" + index + "/" + type + "/_search",
				postData);
		return ParseJSON.getJSON(post);
	}

	public static JSONObject searchText(String index, String type, String text) throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		String postData = "{\"query\":{\"multi_match\":{\"query\":\"" + text
				+ "\",\"fields\":[\"title\",\"text\",\"tag\",\"author\",\"description\"]}},\"highlight\":{\"pre_tags\":[\"<em class='highlight_em'>\"],\"post_tags\":[\"</em>\"],\"fields\":{\"title\":{},\"description\":{},\"tag\":{},\"text\":{}}}}";
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/" + index + "/" + type + "/_search",
				postData);
		return ParseJSON.getJSON(post);
	}

	public static String searchIndex() throws IOException {
		LoadConfig lc = new LoadConfig("conf.properties");
		return HttpClientUtil.get(lc.getProperty("Elasticsearch_url") + "/_cat/indices");
	}
	
	public static JSONObject searchFullText(String index,String text,int from,int size) throws IOException{
		LoadConfig lc = new LoadConfig("conf.properties");
		String postData = "{\"query\":{\"multi_match\":{\"query\":\"" + text
				+ "\",\"fields\":[\"title\",\"content\",\"tag\",\"author\",\"description\",\"content_search\"]}},\"highlight\":{\"pre_tags\":[\"<em class='highlight_em'>\"],\"post_tags\":[\"</em>\"],\"fields\":{\"title\":{},\"description\":{},\"tag\":{},\"content\":{},\"content_search\":{}}},\"from\":"+from+ ",\"size\":"+size+"}";
		String post = HttpClientUtil.post(lc.getProperty("Elasticsearch_url") + "/" + index + "/_search", postData);
		return ParseJSON.getJSON(post);
	}

}
