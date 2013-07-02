package com.vieeo.util.env;

public class JavaEnvUtils {

	public static String getUserDir(){
		return getValue("user.dir");
	}

	public static String getFileEncoding(){
		return getValue("file.encoding");
	}

	public static String getValue(String name){
		return System.getProperty(name);
	}

}
