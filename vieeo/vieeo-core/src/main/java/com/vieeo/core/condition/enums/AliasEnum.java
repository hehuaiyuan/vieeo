package com.vieeo.core.condition.enums;

public enum AliasEnum {
	INNER_JOIN("0"),LEFT_JOIN("1"),RIGHT_JOIN("2"),OUT_JOIN("3");

	private String code;

	private AliasEnum(String code){
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
