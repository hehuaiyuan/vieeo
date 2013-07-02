package com.vieeo.util.json;

import java.util.Map;

public class JsonUtils {
	
	//生成错误消息json字符串
	public static String genJsonErrorResponse(String msg,String errCode) {
		StringBuilder result = new StringBuilder("{");
		result.append("\"errorCode\": \"").append(errCode)
		.append("\", \"msg\": \"").append(msg).append("\"}");
		return result.toString();
	}
	
	//生成成功消息json字符串
	public static String genJsonResponse(boolean isSucc,String msg) {
		StringBuilder result = new StringBuilder("{");
		result.append("\"success\": ").append(isSucc)
		.append(", \"msg\": \"").append(msg).append("\"}");
		return result.toString();
	}
	
	//生成成功消息json字符串,附加参数
	public static String genJsonResponse(boolean isSucc,String msg,Map<String,Object> params) {
		StringBuilder result = new StringBuilder("{");
		result.append("\"success\": ").append(isSucc)
		.append(", \"msg\": \"").append(msg).append("\"");
		if(params != null && params.size()>0) {
			for (String key : params.keySet()) {
				result.append(", \"").append(key).append("\": \"").append(params.get(key)).append("\"");
			}
		}
		result.append("}");
		return result.toString();
	}

}
