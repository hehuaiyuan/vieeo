package com.vieeo.util;

import java.util.List;

/**
 * 集合助手类
 * @author roy.he
 *
 */
public class ListUtils {
	
	public static boolean isEmpty(List<?> data) {
		return data==null || data.size()<=0;
	}
	
	public static boolean isNotEmpty(List<?> data) {
		return !isEmpty(data);
	}

}
