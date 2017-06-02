package com.lenovo.elk3.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlParseUtil {
	public static String getText(String html){
		Document doc = Jsoup.parse(html);
		String text = doc.text();
		return text;
	}
}
