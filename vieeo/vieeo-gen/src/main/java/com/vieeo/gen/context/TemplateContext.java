package com.vieeo.gen.context;

import com.vieeo.gen.config.TemplateConfig;
import com.vieeo.gen.service.TemplateService;

/**
 * 模版上下文接口
 * @author hehuaiyuan.roy
 *
 */
public interface TemplateContext {

	public TemplateConfig getConfig();

	public TemplateService getTemplateService();

}
