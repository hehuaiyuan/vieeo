package com.vieeo.gen.config.bean;

public class ControllerConfig {

	private String view;

	private String className;

	private String packageName;

	private String domainPackage;

	private String viewBeanTemplateFileName;

	private String viewBeanOutputFileName;

	public String getViewBeanTemplateFileName() {
		return viewBeanTemplateFileName;
	}

	public void setViewBeanTemplateFileName(String viewBeanTemplateFileName) {
		this.viewBeanTemplateFileName = viewBeanTemplateFileName;
	}

	public String getViewBeanOutputFileName() {
		return viewBeanOutputFileName;
	}

	public void setViewBeanOutputFileName(String viewBeanOutputFileName) {
		this.viewBeanOutputFileName = viewBeanOutputFileName;
	}

	public String getDomainPackage() {
		return domainPackage;
	}

	public void setDomainPackage(String domainPackage) {
		this.domainPackage = domainPackage;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

}
