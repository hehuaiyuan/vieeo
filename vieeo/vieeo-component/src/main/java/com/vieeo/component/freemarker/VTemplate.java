package com.vieeo.component.freemarker;

import java.io.IOException;
import java.util.Locale;

import freemarker.template.Template;

public interface VTemplate {

	public Template getTemplate(String name)throws IOException;

	public Template getTemplate(String name,Locale locale)throws IOException;

	public Template getTemplate(String name,Locale locale,String encoding)throws IOException;
}
