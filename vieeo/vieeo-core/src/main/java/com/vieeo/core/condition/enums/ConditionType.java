package com.vieeo.core.condition.enums;

public enum ConditionType {
	BODY(""),AND("AND"),OR("OR"),ORDER("ORDER BY"),ALIAS("");

	private String code;

	private ConditionType(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
