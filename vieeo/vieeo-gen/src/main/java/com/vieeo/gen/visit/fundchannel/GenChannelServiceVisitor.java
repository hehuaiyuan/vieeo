package com.vieeo.gen.visit.fundchannel;

import java.util.HashMap;
import java.util.Map;

import com.vieeo.gen.config.fundchannel.ChannelServiceConfig;
import com.vieeo.gen.context.TemplateContext;
import com.vieeo.gen.service.TemplateService;
import com.vieeo.gen.visit.AbstractTemplateVisitor;

public class GenChannelServiceVisitor extends AbstractTemplateVisitor{

	private ChannelServiceConfig channelServiceConfig;

	private String opType;

	@Override
	public Map<String, Object> process(TemplateContext context) {
		TemplateService service = context.getTemplateService();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("domain", channelServiceConfig);
		result.put("opType", opType);

		//生成Header
		service.process(result, channelServiceConfig.getHeaderTemplateFile(),"/bean/header/TransactionHeader.java");
		//生成rquestBean
		service.process(result, channelServiceConfig.getRequestTemplateFile(),"/bean/"+channelServiceConfig.getRequestClassName()+".java");
		//生成responseBean
		service.process(result, channelServiceConfig.getResponseTemplateFile(),"/bean/"+channelServiceConfig.getResponseClassName()+".java");
		//生成Processor
		service.process(result, channelServiceConfig.getProcessorTemplateFile(),"/processor/"+opType+"Processor.java");
		return result;
	}

	public ChannelServiceConfig getChannelServiceConfig() {
		return channelServiceConfig;
	}

	public void setChannelServiceConfig(ChannelServiceConfig channelServiceConfig) {
		this.channelServiceConfig = channelServiceConfig;
	}

	public String getOpType() {
		return opType;
	}

	public void setOpType(String opType) {
		this.opType = opType;
	}

}
