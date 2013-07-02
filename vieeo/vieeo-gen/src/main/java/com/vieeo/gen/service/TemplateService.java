package com.vieeo.gen.service;

import java.util.Map;

/**
 * 模版操作服务接口
 * @author hehuaiyuan.roy
 *
 */
public interface TemplateService {

	/**
	 * 处理模版输出
	 * @param template
	 * @param paramsRoot
	 */
	public void process(Map<String,Object> params,String templateFile,String outputFileName);

}
