package com.vieeo.gen.visit;

import java.util.Map;

import com.vieeo.gen.context.TemplateContext;
import com.vieeo.gen.service.TemplateService;

public abstract class AbstractTemplateVisitor implements Visitor{

	private String templateFileName;

	private String outputFileName;

	@Override
	public void visit(TemplateContext context) {
		TemplateService service = context.getTemplateService();
		Map<String,Object> rootMap = process(context);
		service.process(rootMap,templateFileName,outputFileName);
	}

	public abstract Map<String,Object> process(TemplateContext context);

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public String getOutputFileName() {
		return outputFileName;
	}

	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}

}
