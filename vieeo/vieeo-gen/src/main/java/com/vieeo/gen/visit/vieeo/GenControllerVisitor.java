package com.vieeo.gen.visit.vieeo;

import java.util.HashMap;
import java.util.Map;

import com.vieeo.gen.config.bean.ControllerConfig;
import com.vieeo.gen.context.TemplateContext;
import com.vieeo.gen.service.TemplateService;
import com.vieeo.gen.visit.AbstractTemplateVisitor;

public class GenControllerVisitor extends AbstractTemplateVisitor{

	private ControllerConfig controllerConfig;

	@Override
	public Map<String, Object> process(TemplateContext context) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", controllerConfig);

		TemplateService service = context.getTemplateService();
		service.process(result,controllerConfig.getViewBeanTemplateFileName(),controllerConfig.getViewBeanOutputFileName());
		return result;
	}

	public ControllerConfig getControllerConfig() {
		return controllerConfig;
	}

	public void setControllerConfig(ControllerConfig controllerConfig) {
		this.controllerConfig = controllerConfig;
	}

}
