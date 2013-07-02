package com.vieeo.component.spring.resolver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.vieeo.component.exception.MsgBindException;
import com.vieeo.component.exception.RedirectRuntimeException;

/**
 * 重定向Resolver,当controller抛出的异常为com.vieeo.component.exception.RedirectRuntimeException异常时,
 * 为request对象设置redirectUrl
 * @author roy.he
 *
 */
public class RedirectExceptionResolver extends SimpleMappingExceptionResolver{
	
	Logger log = Logger.getLogger(RedirectExceptionResolver.class);
	
	public static final String EX_REDIRECT_URL="ex_redirect_url";
	
	public static final String REQUEST_TYPE_AJAX="XMLHttpRequest";
	
	private String ajaxErrorPage ;
	
	//多个错误信息是否换行
	private boolean isBr = true;
	
	//每条错误信息前缀
	private String prefix;
	
	/*@Override
	protected ModelAndView doResolveException(HttpServletRequest request,
			HttpServletResponse response,
			Object handler,
			Exception ex) {
		 
		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(ex, request);
		if (viewName != null) {
			// Apply HTTP status code for error views, if specified.
			// Only apply it if we're processing a top-level request.
			Integer statusCode = determineStatusCode(request, viewName);
			if (statusCode != null) {
				applyStatusCodeIfPossible(request, response, statusCode);
			}
			return getModelAndView(viewName, ex, request);
		}
		else {
			return null;
		}
	}
*/
	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex,
			HttpServletRequest request) {
		log.error("request error happend", ex);
		//是否为ajax请求,并且返回json格式
		String requestType = request.getHeader("x-requested-with");
		if(!StringUtils.isBlank(requestType) && REQUEST_TYPE_AJAX.equals(requestType)) {
			String message = ex.getMessage();
			if(ex instanceof BindException){
				BindException bex = (BindException)ex;
				message = getErrorMessage(bex); 
				ex = new MsgBindException(message,bex.getBindingResult());
			}
			return getModelAndView(ajaxErrorPage, ex);
		}
		
		//如果是bean验证错误,则把提示消息汇总为一条错误消息返回错误页面显示
		if(ex instanceof BindException){
			BindException bex = (BindException)ex;
			String processedMsg = getErrorMessage(bex); 
			ex = new MsgBindException(processedMsg,bex.getBindingResult());
		}
		
		if(ex instanceof RedirectRuntimeException) {
			request.setAttribute(EX_REDIRECT_URL, ((RedirectRuntimeException)ex).getRedirectUrl());
		}
		return getModelAndView(viewName, ex);
	}
	
	@SuppressWarnings("unchecked")
	private String getErrorMessage(BindException ex){
		StringBuilder result = new StringBuilder();
		List<ObjectError> errors = ex.getAllErrors();
		if(errors == null || errors.size()<=0) return "";
		for (int i=0;i<errors.size();i++) {
			result.append(StringUtils.isBlank(prefix)? (i+1)+":" : prefix);
			result.append("&nbsp;&nbsp;");
			result.append(errors.get(i).getDefaultMessage());
			if(i!=errors.size()-1 && isBr) result.append("<br>");
		}
		return result.toString();
	}

	public String getAjaxErrorPage() {
		return ajaxErrorPage;
	}

	public void setAjaxErrorPage(String ajaxErrorPage) {
		this.ajaxErrorPage = ajaxErrorPage;
	}

	public boolean isBr() {
		return isBr;
	}

	public void setBr(boolean isBr) {
		this.isBr = isBr;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
