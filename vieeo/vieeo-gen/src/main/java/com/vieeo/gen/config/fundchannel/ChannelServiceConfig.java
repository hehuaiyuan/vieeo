package com.vieeo.gen.config.fundchannel;

public class ChannelServiceConfig {

	private String processorTemplateFile;

	private String headerTemplateFile;

	private String requestTemplateFile;

	private String responseTemplateFile;

	private String requestClassName;

	private String responseClassName;

	public String getHeaderTemplateFile() {
		return headerTemplateFile;
	}

	public void setHeaderTemplateFile(String headerTemplateFile) {
		this.headerTemplateFile = headerTemplateFile;
	}

	public String getProcessorTemplateFile() {
		return processorTemplateFile;
	}

	public void setProcessorTemplateFile(String processorTemplateFile) {
		this.processorTemplateFile = processorTemplateFile;
	}

	public String getRequestTemplateFile() {
		return requestTemplateFile;
	}

	public void setRequestTemplateFile(String requestTemplateFile) {
		this.requestTemplateFile = requestTemplateFile;
	}

	public String getResponseTemplateFile() {
		return responseTemplateFile;
	}

	public void setResponseTemplateFile(String responseTemplateFile) {
		this.responseTemplateFile = responseTemplateFile;
	}

	public String getRequestClassName() {
		return requestClassName;
	}

	public void setRequestClassName(String requestClassName) {
		this.requestClassName = requestClassName;
	}

	public String getResponseClassName() {
		return responseClassName;
	}

	public void setResponseClassName(String responseClassName) {
		this.responseClassName = responseClassName;
	}

}
