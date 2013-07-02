package com.vieeo.gen.visit.vieeo;

import java.util.HashMap;
import java.util.Map;

import com.vieeo.gen.config.bean.RepositoryConfig;
import com.vieeo.gen.context.TemplateContext;
import com.vieeo.gen.visit.AbstractTemplateVisitor;

public class GenRepositoryVisitor extends AbstractTemplateVisitor{

	private RepositoryConfig repositoryConfig;

	@Override
	public Map<String, Object> process(TemplateContext context) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("className", repositoryConfig.getDomainName());
		return result;
	}

	public RepositoryConfig getRepositoryConfig() {
		return repositoryConfig;
	}

	public void setRepositoryConfig(RepositoryConfig repositoryConfig) {
		this.repositoryConfig = repositoryConfig;
	}

}
