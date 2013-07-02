package com.vieeo.core.condition.enums;

import org.apache.commons.lang.StringUtils;

public enum OrderEnum {

	ASC("ASC"),DESC("DESC");

	private String sortType;

	private OrderEnum(String sortType){
		this.sortType = sortType;
	}

	public String getSortType() {
		return sortType;
	}
	
	public static OrderEnum getOrder(String type) {
		if(StringUtils.isBlank(type)) return DESC;
		for (OrderEnum order : values()) {
			if(order.getSortType().toUpperCase().equals(StringUtils.trim(type).toUpperCase())) return order;
		}
		return DESC;
	}
}
