package com.vieeo.core.condition.enums;

public enum ConditionEnum {
	EQ("="),GE("<="),LE(">="),LIKE("like"),IN("in"),NOT_IN("not in"),NULL(" is null"),NOT_NULL(" is not null"),
	INNER_JOIN(" INNER JOIN "),LEFT_JOIN(" LEFT JOIN "),RIGHT_JOIN(" RIGHT JOIN "),
	BETWEEN("BETWEEN");

	private String value;

	private ConditionEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
