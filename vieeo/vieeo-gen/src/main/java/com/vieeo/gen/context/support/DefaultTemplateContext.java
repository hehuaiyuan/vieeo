package com.vieeo.gen.context.support;

import com.vieeo.gen.config.TemplateConfig;
import com.vieeo.gen.context.TemplateContext;
import com.vieeo.gen.service.TemplateService;

public class DefaultTemplateContext implements TemplateContext{

	private TemplateConfig config;

	private TemplateService templateService;

	public DefaultTemplateContext(TemplateConfig config,TemplateService templateService){
		this.config = config;
		this.templateService = templateService;
	}

	public TemplateConfig getConfig(){
		return config;
	}

	@Override
	public TemplateService getTemplateService() {
		return templateService;
	}

}
