package com.vieeo.util.string;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串辅助操作
 * @author roy.he
 *
 */
public class StringOthersUtils {

	private static String spaces = "                                                                                    ";

	/**
	 * 构建一个like条件的值
	 * @param value
	 * @return
	 */
	public static String buildLikeValue(String value) {
		if(StringUtils.isBlank(value)) return null;
		StringBuilder result = new StringBuilder("%");
		result.append(value).append("%");
		return result.toString();
	}

	/**
	 * 构建一个like条件的值
	 * @param value
	 * @return
	 */
	public static String buildBeforeLikeValue(String value) {
		if(StringUtils.isBlank(value)) return null;
		StringBuilder result = new StringBuilder("%");
		result.append(value);
		return result.toString();
	}

	/**
	 * 构建一个like条件的值
	 * @param value
	 * @return
	 */
	public static String buildAfterLikeValue(String value) {
		if(StringUtils.isBlank(value)) return null;
		StringBuilder result = new StringBuilder(value);
		result.append("%");
		return result.toString();
	}

	/**
	 * 判断值是否超过最大长度限制，如果超过，根据指定的截取方式截取maxSize位数返回
	 * @param value
	 * @param maxSize
	 * @param isLeft
	 */
	public static String subStringWithLimit(String value,int maxSize,boolean isLeft){
	    if(StringUtils.isBlank(value) || value.length()<= maxSize) return value;
	    if(isLeft){
	        return StringUtils.substring(value, 0, maxSize);
	    }else {
	        return StringUtils.substring(value, (value.length()-maxSize), value.length());
	    }
	}

	/**
	 * 判断指定值的长度是否小于指定的最大长度，如果小于，把剩余部分补充空格
	 * @param value
	 * @param maxSize
	 * @return
	 */
	public static String appendSpace(String value,int maxSize){
	    //如果值为空，返回要求长度的空格
	    if(StringUtils.isBlank(value)) return StringUtils.substring(spaces, 0, maxSize);
	    if(value.length()>=maxSize) return value;
	    int size = maxSize - value.length();
	    String appendSpaces = StringUtils.substring(spaces, 0, size);
	    return (value+appendSpaces);
	}
}
