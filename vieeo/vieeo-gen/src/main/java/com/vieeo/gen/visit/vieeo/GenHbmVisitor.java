package com.vieeo.gen.visit.vieeo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.vieeo.gen.config.bean.DomainConfig;
import com.vieeo.gen.config.util.DomainXmlUtils;
import com.vieeo.gen.context.TemplateContext;
import com.vieeo.gen.visit.AbstractTemplateVisitor;

public class GenHbmVisitor extends AbstractTemplateVisitor{

	private DomainConfig domainConfig;

	@Override
	public Map<String, Object> process(TemplateContext context) {
		Map<String, Object> result = new HashMap<String, Object>();
		DomainConfig config = DomainXmlUtils.parseDomainConfig(context.getConfig().getRootPath()+domainConfig.getDomainConfigPath());
		if(StringUtils.isBlank(config.getPackageName())){
			config.setPackageName(context.getConfig().getRootPackage()+"."+domainConfig.getPackageName());
		}
		if(StringUtils.isBlank(config.getClassName())){
			config.setClassName(context.getConfig().getRootDomain());
		}
		result.put("domain", config);
		return result;
	}

	public DomainConfig getDomainConfig() {
		return domainConfig;
	}

	public void setDomainConfig(DomainConfig domainConfig) {
		this.domainConfig = domainConfig;
	}

}
