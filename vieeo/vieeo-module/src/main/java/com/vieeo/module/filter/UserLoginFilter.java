package com.vieeo.module.filter;

import java.io.IOException;


import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.vieeo.module.filter.ex.LoginFilterException;
import com.vieeo.util.constant.WebConstant;

import org.apache.commons.lang.StringUtils;

/**
 * 登录验证filter,可指定不验证路径和后缀请求
 * @author roy.he
 *
 */
public class UserLoginFilter extends UrlGenericFilterBean{
	
	@Override
	protected void initialize(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response  = (HttpServletResponse)resp;
		if(request.getSession().getAttribute(WebConstant.SESSION_USER_KEY) != null) {
			chain.doFilter(request, response);
			return ;
		}
		//允许指定的额外路径和后缀请求通过
		String path = request.getServletPath();
		if(containsExPath(path)) {
			chain.doFilter(request, response);
			request.getAttribute("exception");
		}else {
			String suffix = StringUtils.substring(path, StringUtils.lastIndexOf(path, ".")+1);
			if(containsExSuffix(suffix)) chain.doFilter(request, response);
			else throw new LoginFilterException("对不起,请先登录后操作!");
		}
	}

	@Override
	public void destroy() {
		
	}

}
