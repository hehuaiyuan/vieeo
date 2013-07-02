package com.vieeo.gen.build.impl;

import java.util.List;

import com.vieeo.gen.build.Builder;
import com.vieeo.gen.config.TemplateConfig;
import com.vieeo.gen.context.TemplateContext;
import com.vieeo.gen.context.support.DefaultTemplateContext;
import com.vieeo.gen.service.TemplateService;
import com.vieeo.gen.visit.Visitor;
import com.vieeo.util.ListUtils;

public class GenBuilder implements Builder{

	private List<Visitor> visitors;

	private TemplateConfig config;

	private TemplateService templateService;

	@Override
	public void build() {
		TemplateContext context = new DefaultTemplateContext(config,templateService);
		if(ListUtils.isEmpty(visitors)){
			throw new RuntimeException("未配置对应的生成器!");
		}
		//生成文件
		for (Visitor visitor : visitors) {
			visitor.visit(context);
		}
	}

	public List<Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}

	public TemplateConfig getConfig() {
		return config;
	}

	public void setConfig(TemplateConfig config) {
		this.config = config;
	}

	public TemplateService getTemplateService() {
		return templateService;
	}

	public void setTemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}

}
