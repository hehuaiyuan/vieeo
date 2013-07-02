package com.vieeo.module.util;

import org.apache.commons.lang.StringUtils;

/**
 * 别名生成助手类
 * @author roy.he
 *
 */
public class AliasUtils {
	
	public static String createAliasByClass(Class<?> clazz){
		String alias = clazz.getSimpleName();
		alias = alias.replaceFirst(alias.substring(0,1), alias.substring(0,1).toLowerCase());
		return "_"+alias;
	}
	
	public static boolean isAliasExists(String propertyName){
		if(StringUtils.isBlank(propertyName)) return false;
		return StringUtils.indexOf(propertyName, ".") >0 ? true : false;
	}

}
