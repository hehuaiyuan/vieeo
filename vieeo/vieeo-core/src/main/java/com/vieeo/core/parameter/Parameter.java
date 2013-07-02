package com.vieeo.core.parameter;

import java.io.Serializable;
import java.util.Map;

/**
 *	参数对象接口,可使用于多参数传递时封装
 * @author roy
 */
public interface Parameter extends Serializable{

	/**
	 * 按照属性名:值方式把字段封装成Map返回
	 * @return
	 */
	public Map<String,Object> getParamsMap();
}
