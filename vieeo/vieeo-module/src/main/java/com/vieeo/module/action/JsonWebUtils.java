package com.vieeo.module.action;

import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONWriter;

import com.vieeo.util.json.JsonUtils;


public class JsonWebUtils {
	
	protected static final String DEFAULT_DATA_NAME="rows";
	
	public static String genJsonResponse(String data,int page,int total,String dataName){
		String result = null;
		try {
			JSONWriter json = new JSONStringer().object()
			.key("page").value(page).key("total").value(total);
			json.key(dataName).value(data);
			json.endObject();
			result = json.toString();
		} catch (JSONException e) {
			e.printStackTrace();
			result = JsonUtils.genJsonErrorResponse("系统异常,请联系管理人员!",null);
		}
		return result;
	}
	
	public static String genJsonResponse(String data,int page,int total){
		return genJsonResponse(data,page,total,DEFAULT_DATA_NAME);
	}
	
}
