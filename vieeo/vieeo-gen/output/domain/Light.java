package com.vieeo.test.domain;

import com.vieeo.core.domain.BaseEntity;

/**
 *
 * @author roy.he
 *
 */
@SuppressWarnings("unchecked")
public class Light extends BaseEntity{

	private static String[] ignorePropertiesInUpdate = new String[]{"id",
		"dateCreated","dateLastModified","userCreated","userLastModified"};

	private Integer age;

	private String address;


	public static String[] getIgnoreProperties(){
		return ignorePropertiesInUpdate;
	}

	public Integer getAge(){
		return this.age;
	}

	public void setAge(Integer age){
		this.age = age;
	}
	public String getAddress(){
		return this.address;
	}

	public void setAddress(String address){
		this.address = address;
	}

}
