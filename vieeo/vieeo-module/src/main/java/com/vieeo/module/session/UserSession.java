package com.vieeo.module.session;

/**
 * 保存当前线程user信息
 * @author roy.he
 *
 */
public class UserSession {
	
	private static ThreadLocal<String> local = new ThreadLocal<String>();
	
	public static void put(String user) {
		if(local.get()!= null) local.remove();
		local.set(user);
	}
	
	public static String get() {
		return (local.get()== null) ? "" : local.get();
	}
	
	public static void remove(){
		local.remove();
	}

}
