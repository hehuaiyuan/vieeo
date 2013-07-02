package com.vieeo.test.filter;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;

public class UrlGenericFilterTest extends TestCase{
	
	public static final String EXCLUDE_SUFFIXES="excludeSuffixes";
	
	public static final String INCLUDE_SUFFIXES="includeSuffixes";
	
	public static final String URL_SPLIT=",";
	
	private String[] excludeSuffixes;
	
	private String[] includeSuffixes;
	
	public void testSuffix() {
		String suffixes = "css,js,jpg";
		//分割禁止后缀集合
		if(!StringUtils.isBlank(suffixes) && suffixes.indexOf(URL_SPLIT)>0) {
			excludeSuffixes = StringUtils.split(suffixes, URL_SPLIT);
		}
		//分割允许后缀集合
		if(!StringUtils.isBlank(suffixes) && suffixes.indexOf(URL_SPLIT)>0) {
			includeSuffixes = StringUtils.split(suffixes, URL_SPLIT);
		}
		
		System.out.println(containsInSuffix("css"));
		
		System.out.println(StringUtils.substring("/index.jsp", StringUtils.indexOf("/index.jsp", ".")+1));
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

}
