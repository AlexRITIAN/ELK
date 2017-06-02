package com.lenovo.elk3.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

//import org.apache.log4j.Logger;

public class LoadConfig {
//	private Logger logger = Logger.getLogger(LoadConfig.class);
	Properties pro;
	String confName;

	public LoadConfig(String confName) throws IOException {
		this.confName = confName;
	}

	public String getProperty(String key) throws IOException {
		String property = "";
		FileInputStream in = new FileInputStream(this.getClass().getClassLoader().getResource(confName).getPath());
		pro = new Properties();
		pro.load(in);
		property = pro.getProperty(key);
		in.close();
		return property;
	}
	
	public void setProperty(String key,String value) throws IOException{
		FileInputStream in = new FileInputStream(this.getClass().getClassLoader().getResource(confName).getPath());
		pro = new Properties();
		pro.load(in);
		in.close();
		FileOutputStream out = new FileOutputStream(this.getClass().getClassLoader().getResource(confName).getPath());
		pro.setProperty(key, value);
		pro.store(out, "Update Seach_index");
		out.close();
	}
}
