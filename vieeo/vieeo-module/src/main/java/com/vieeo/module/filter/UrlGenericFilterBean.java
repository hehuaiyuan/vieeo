package com.vieeo.module.filter;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;

/**
 * URL后缀处理超类,包含处理请求url后缀是否包含在指定后缀集合中 或包含在禁止后缀集合中
 * @author roy.he
 *
 */
public abstract class UrlGenericFilterBean implements Filter{
	
	public static final String EXCLUDE_SUFFIXES="excludeSuffixes";
	
	public static final String INCLUDE_SUFFIXES="includeSuffixes";
	
	public static final String EXCLUDE_PATHS="excludePaths";
	
	public static final String INCLUDE_PATHS="includePaths";
	
	public static final String URL_SPLIT="|";
	
	private String[] excludeSuffixes;
	
	private String[] includeSuffixes;
	
	private String[] excludePaths;
	
	private String[] includePaths;
	
	private ServletContext context;
	
	@Override
	public final void init(FilterConfig filterConfig) throws ServletException {
		context = filterConfig.getServletContext();
		String exSuffixes = filterConfig.getInitParameter(EXCLUDE_SUFFIXES);
		String inSuffixes = filterConfig.getInitParameter(INCLUDE_SUFFIXES);
		String exPaths = filterConfig.getInitParameter(EXCLUDE_PATHS);
		String inPaths = filterConfig.getInitParameter(INCLUDE_PATHS);
		//分割禁止后缀集合
		if(!StringUtils.isBlank(exSuffixes)) {
			excludeSuffixes = StringUtils.indexOf(exSuffixes, URL_SPLIT)>0 ? StringUtils.split(exSuffixes, URL_SPLIT) : new String[]{exSuffixes};
		}
		//分割允许后缀集合
		if(!StringUtils.isBlank(inSuffixes)) {
			includeSuffixes = StringUtils.indexOf(inSuffixes, URL_SPLIT)>0 ? StringUtils.split(inSuffixes, URL_SPLIT) : new String[]{inSuffixes};
		}
		//分割禁止路径集合
		if(!StringUtils.isBlank(exPaths)) {
			excludePaths = StringUtils.indexOf(exPaths, URL_SPLIT)>0 ? StringUtils.split(exPaths, URL_SPLIT) : new String[]{exPaths};
		}
		//分割允许路径集合
		if(!StringUtils.isBlank(inPaths)) {
			includePaths = StringUtils.indexOf(inPaths, URL_SPLIT)>0 ? StringUtils.split(inPaths, URL_SPLIT) : new String[]{inPaths};
		}
		initialize(filterConfig);
	}
	
	/**
	 * 后缀是否包含在禁止集合内
	 * @param suffix
	 * @return 包含：true
	 */
	protected boolean containsExSuffix(String suffix){
		if(StringUtils.isBlank(suffix) || getExcludeSuffixes()==null || getExcludeSuffixes().length<=0) return false;
		for (String exsuffix : getExcludeSuffixes()) {
			if(suffix.equals(exsuffix)) return true;
		}
		return false;
	}
	
	/**
	 * 后缀是否包含在允许集合内
	 * @param suffix
	 * @return 包含：true
	 */
	protected boolean containsInSuffix(String suffix){
		if(StringUtils.isBlank(suffix) || getIncludeSuffixes()==null || getIncludeSuffixes().length<=0) return false;
		for (String insuffix : getIncludeSuffixes()) {
			if(suffix.equals(insuffix)) return true;
		}
		return false;
	}
	
	/**
	 * 后缀是否包含在禁止集合内
	 * @param suffix
	 * @return 包含：true
	 */
	protected boolean containsExPath(String path){
		if(StringUtils.isBlank(path) || getExcludePaths()==null || getExcludePaths().length<=0) return false;
		for (String expath : getExcludePaths()) {
			if(path.equals(expath)) return true;
		}
		return false;
	}
	
	/**
	 * 后缀是否包含在允许集合内
	 * @param suffix
	 * @return 包含：true
	 */
	protected boolean containsInPath(String path){
		if(StringUtils.isBlank(path) || getIncludePaths()==null || getIncludePaths().length<=0) return false;
		for (String inpath : getIncludePaths()) {
			if(path.equals(inpath)) return true;
		}
		return false;
	}
	
	//初始化方法
	protected abstract void initialize(FilterConfig filterConfig)throws ServletException;

	public String[] getExcludeSuffixes() {
		return excludeSuffixes;
	}

	public void setExcludeSuffixes(String[] excludeSuffixes) {
		this.excludeSuffixes = excludeSuffixes;
	}

	public String[] getIncludeSuffixes() {
		return includeSuffixes;
	}

	public void setIncludeSuffixes(String[] includeSuffixes) {
		this.includeSuffixes = includeSuffixes;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public String[] getExcludePaths() {
		return excludePaths;
	}

	public void setExcludePaths(String[] excludePaths) {
		this.excludePaths = excludePaths;
	}

	public String[] getIncludePaths() {
		return includePaths;
	}

	public void setIncludePaths(String[] includePaths) {
		this.includePaths = includePaths;
	}

}
