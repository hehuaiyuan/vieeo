package com.vieeo.module.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.vieeo.core.domain.BaseEntity;
import com.vieeo.util.constant.WebConstant;
import com.vieeo.module.session.UserSession;

/**
 * 为数据保存时设置提供当前操作对象
 * @author roy.he
 *
 */
public class OperationFilter implements Filter{
	
	private String userSessionKey = WebConstant.SESSION_USER_KEY;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String userkey = filterConfig.getInitParameter("userSessionKey");
		if(!StringUtils.isBlank(userkey)) userSessionKey = userkey;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		BaseEntity user = (BaseEntity)request.getSession().getAttribute(userSessionKey);
		if(user != null) {
			String userId = user.getId();
			UserSession.put(userId);
		}
		chain.doFilter(req, response);
	}

	@Override
	public void destroy() {
		UserSession.remove();
	}

}
