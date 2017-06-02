package com.lenovo.elk3.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lenovo.elk3.utils.ConversionUtil;
import com.lenovo.elk3.utils.ElasticsearchUtil;
import com.lenovo.elk3.utils.LoadConfig;
import com.lenovo.elk3.utils.ParseJSON;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(value = "searchservice")
public class SearchServiceImpl implements ISearchService {

	private Logger logger = Logger.getLogger(SearchServiceImpl.class);

	@Override
	public JSONObject searchAll(String index, String type, int from, int size) throws Exception {
		return ElasticsearchUtil.searchAll(index, type, from, size).getJSONObject("hits");
	}

	@Override
	public JSONObject termSearch(String index, String type, String parm, String matchStr,int from,int size) throws Exception {
		return ElasticsearchUtil.term(index, type, parm, matchStr,from,size).getJSONObject("hits");
	}

	@Override
	public JSONArray matchSearch(String index, String type, String parm, String matchStr) throws Exception {
		return ElasticsearchUtil.match(index, type, parm, matchStr).getJSONObject("hits").getJSONArray("hits");
	}

	@Override
	public JSONArray mutilMatchSearch(String index, String type, String text) throws Exception {
		return ElasticsearchUtil.searchText(index, type, text).getJSONObject("hits").getJSONArray("hits");
	}

	@Override
	public JSONObject searchFullText(String index, String text, int from, int size) throws Exception {
		return ElasticsearchUtil.searchFullText(index, text, from, size).getJSONObject("hits");
	}

	@Override
	public JSONArray searchIndex() throws Exception {
		StringBuffer buffer = new StringBuffer();
		String[] split = ElasticsearchUtil.searchIndex().split("\\s* ");
		LoadConfig lc = new LoadConfig("conf.properties");
		String[] ableIndexs = lc.getProperty("Search_index").split(",");
		buffer.append("[");
		for (int i = 2; i < split.length; i += 9) {
			boolean flag = false;
			for(int n = 0;n < ableIndexs.length;n++){
				if(split[i].equals(ableIndexs[n])){
					flag = true;
					break;
				}
			}
			if(flag){
				buffer.append("{\"index\":\"" + split[i] + "\"},");
			}
		}
		buffer.append("{\"index\":\"end\"}]");
		return ParseJSON.getJSONArray(buffer.toString());
	}
	
	

	@Override
	public JSONObject esHealth() throws Exception {
		String[] split = ElasticsearchUtil.searchIndex().replace("\n", " ").split("\\s* ");
		StringBuffer buffer = new StringBuffer();
		int healthGreen = 0;
		int healthYellow = 0;
		int healthRed = 0;
		int indexNum = 0;
		int docNum = 0;
		double sizeNum = 0;
		double kbNum = 0.00;
		double mbNum = 0.00;
		double gbNum = 0.00;
		for (int i = 0; i < split.length; i++) {
			if (i % 10 == 0) {
				if ("green".equals(split[i])) {
					healthGreen++;
				} else if ("yellow".equals(split[i])) {
					healthYellow++;
				} else if ("red".equals(split[i])) {
					healthRed++;
				}
			} else if (i % 10 == 2) {
				indexNum++;
			} else if (i % 10 == 6) {
				docNum += Integer.valueOf(split[i]);
			} else if (i % 10 == 8) {
				if ("gb".equals(split[i].substring(split[i].length() - 2, split[i].length()))) {
					gbNum += Double.valueOf((split[i].substring(0, split[i].length() - 2)));
				} else if ("mb".equals(split[i].substring(split[i].length() - 2, split[i].length()))) {
					mbNum += Double.valueOf((split[i].substring(0, split[i].length() - 2)));
				} else if ("kb".equals(split[i].substring(split[i].length() - 2, split[i].length()))) {
					kbNum += Double.valueOf((split[i].substring(0, split[i].length() - 2)));
				}
			}
		}
		buffer.append("{\"indexNum\":" + indexNum);
		sizeNum = gbNum * 1024 * 1024 + mbNum * 1024 + kbNum;
		String[] units = { "G", "M", "K" };
		buffer.append(",\"size\":\"" + ConversionUtil.converToBig(sizeNum, "K", units, 1024, 2) + "\"");
		String[] docNumUnits = { "B", "M", "K", " " };
		buffer.append(",\"docNum\":\"" + ConversionUtil.converToBig(docNum, " ", docNumUnits, 1000, 2) + "\"");
		if (healthRed != 0) {
			buffer.append(",\"health\":\"red\",\"healthNum\":" + healthRed + "}");
		} else if (healthYellow != 0) {
			buffer.append(",\"health\":\"yellow\",\"healthNum\":" + healthYellow + "}");
		} else if (healthGreen != 0) {
			buffer.append(",\"health\":\"green\",\"healthNum\":" + healthGreen + "}");
		}
		return ParseJSON.getJSON(buffer.toString());
	}

	@Override
	public JSONArray allSearchIndex() throws Exception {
		StringBuffer buffer = new StringBuffer();
		String[] split = ElasticsearchUtil.searchIndex().split("\\s* ");
		
		buffer.append("[");
		for (int i = 2; i < split.length; i += 9) {
			buffer.append("{\"index\":\"" + split[i] + "\"},");
		}
		buffer.append("{\"index\":\"end\"}]");
		return ParseJSON.getJSONArray(buffer.toString());
	}

	@Override
	public JSONObject setWhiteList(String whiteList) throws Exception {
		LoadConfig lc = new LoadConfig("conf.properties");
		lc.setProperty("Search_index", whiteList);
		return null;
	}

}
