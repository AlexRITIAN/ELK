package com.lenovo.elk3.utils;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
/**
 * HttpClient
 * @author yuhao5
 *
 */
public class HttpClientUtil {
	private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String get(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse result = client.execute(get);
		HttpEntity entity = result.getEntity();
		String entityStr = EntityUtils.toString(entity, "UTF-8");
		client.close();
		return entityStr;
	}
	
	/**
	 * post请求
	 * @param url
	 * @param postData
	 * 				请求体,json格式字符串
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String post(String url,String postData) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.addHeader("Content-type","application/json; charset=utf-8");  
        post.setHeader("Accept", "application/json");  
		post.setEntity(new StringEntity(postData, Charset.forName("UTF-8")));
		CloseableHttpResponse response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		String entityStr = EntityUtils.toString(entity, "UTF-8");
		logger.info(entityStr);
		httpClient.close();
		return entityStr;
	}
	
	public static String delete(String url) throws ClientProtocolException, IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete delete = new HttpDelete(url);
		CloseableHttpResponse execute = httpClient.execute(delete);
		HttpEntity entity = execute.getEntity();
		String entityStr = EntityUtils.toString(entity);
		logger.info(entityStr);
		httpClient.close();
		return entityStr;
	}
}
