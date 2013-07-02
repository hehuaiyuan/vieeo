package com.vieeo.gen.visit.fundchannel;

import java.util.HashMap;
import java.util.Map;

import com.vieeo.gen.config.fundchannel.ChannelServiceConfig;
import com.vieeo.gen.context.TemplateContext;
import com.vieeo.gen.visit.AbstractTemplateVisitor;

public class GenSpringConfigVisitor extends AbstractTemplateVisitor{

	private ChannelServiceConfig channelServiceConfig;

	private String channelCode;

	@Override
	public Map<String, Object> process(TemplateContext context) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("domain", channelServiceConfig);
		result.put("channelCode", channelCode);
		return result;
	}

	public ChannelServiceConfig getChannelServiceConfig() {
		return channelServiceConfig;
	}

	public void setChannelServiceConfig(ChannelServiceConfig channelServiceConfig) {
		this.channelServiceConfig = channelServiceConfig;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
}
