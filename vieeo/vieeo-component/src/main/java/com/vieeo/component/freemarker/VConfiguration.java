package com.vieeo.component.freemarker;

import java.io.IOException;
import java.util.Locale;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class VConfiguration implements VTemplate{

	private Configuration configuration;

	public VConfiguration(){}

	public VConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public Configuration getConfiguration(){
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	@Override
	public Template getTemplate(String name)throws IOException {
		return configuration.getTemplate(name);
	}

	@Override
	public Template getTemplate(String name, Locale locale)throws IOException {
		return configuration.getTemplate(name, locale);
	}

	@Override
	public Template getTemplate(String name, Locale locale, String encoding)throws IOException {
		return configuration.getTemplate(name, locale, encoding);
	}

}
