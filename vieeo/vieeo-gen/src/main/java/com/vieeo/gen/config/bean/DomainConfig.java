package com.vieeo.gen.config.bean;

import java.util.List;

public class DomainConfig {

	private String domainConfigPath;

	private String packageName;

	private String className;

	private String tableName;

	private DomainAttribute priKey;

	private List<DomainAttribute> attributes;

	public String getDomainConfigPath() {
		return domainConfigPath;
	}

	public void setDomainConfigPath(String domainConfigPath) {
		this.domainConfigPath = domainConfigPath;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public DomainAttribute getPriKey() {
		return priKey;
	}

	public void setPriKey(DomainAttribute priKey) {
		this.priKey = priKey;
	}

	public List<DomainAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<DomainAttribute> attributes) {
		this.attributes = attributes;
	}

	public static class DomainAttribute{

		private String name;

		private String javaType;

		private String hbmType;

		private String hbmColumn;

		private String hbmLength;

		private String hbmDefValue;

		private String notNull;

		private String webQuery;

		private String webType;

		private String webColumn;

		private String webModify;

		private String webText;

		public String getWebText() {
			return webText;
		}

		public void setWebText(String webText) {
			this.webText = webText;
		}

		public String getWebQuery() {
			return webQuery;
		}

		public void setWebQuery(String webQuery) {
			this.webQuery = webQuery;
		}

		public String getWebType() {
			return webType;
		}

		public void setWebType(String webType) {
			this.webType = webType;
		}

		public String getWebColumn() {
			return webColumn;
		}

		public void setWebColumn(String webColumn) {
			this.webColumn = webColumn;
		}

		public String getWebModify() {
			return webModify;
		}

		public void setWebModify(String webModify) {
			this.webModify = webModify;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getJavaType() {
			return javaType;
		}

		public void setJavaType(String javaType) {
			this.javaType = javaType;
		}

		public String getHbmType() {
			return hbmType;
		}

		public void setHbmType(String hbmType) {
			this.hbmType = hbmType;
		}

		public String getHbmColumn() {
			return hbmColumn;
		}

		public void setHbmColumn(String hbmColumn) {
			this.hbmColumn = hbmColumn;
		}

		public String getHbmLength() {
			return hbmLength;
		}

		public void setHbmLength(String hbmLength) {
			this.hbmLength = hbmLength;
		}

		public String getHbmDefValue() {
			return hbmDefValue;
		}

		public void setHbmDefValue(String hbmDefValue) {
			this.hbmDefValue = hbmDefValue;
		}

		public String getNotNull() {
			return notNull;
		}

		public void setNotNull(String notNull) {
			this.notNull = notNull;
		}
	}

}
