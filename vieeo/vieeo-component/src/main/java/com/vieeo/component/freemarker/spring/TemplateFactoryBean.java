package com.vieeo.component.freemarker.spring;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;

public class TemplateFactoryBean implements FactoryBean<Template>,InitializingBean {

	private Configuration configuration;

	private String fileName;

	private String path;

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public Template getObject() throws Exception {
		return configuration.getTemplate(fileName);
	}

	@Override
	public Class<?> getObjectType() {
		return Template.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if(configuration == null) {
			configuration = new Configuration();
			configuration.setDefaultEncoding("UTF-8");
		}

		this.configuration.setObjectWrapper(ObjectWrapper.DEFAULT_WRAPPER);
		if(!StringUtils.isBlank(path)) {
			configuration.setClassForTemplateLoading(TemplateFactoryBean.class, path);
		}
	}

}
