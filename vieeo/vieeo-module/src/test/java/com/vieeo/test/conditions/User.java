package com.vieeo.test.conditions;

import com.vieeo.core.domain.BaseEntity;

public class User extends BaseEntity{
	
	private static final long serialVersionUID = 7761946469911787359L;

	private String userName;
	
	private String passWord;
	
	private int age;

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
