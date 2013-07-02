package com.vieeo.component.spring.resolver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.vieeo.component.exception.MsgBindException;

/**
 * 错误消息转换resolver,把异常消息整理,只留下用户自定义message
 * @author roy.he
 *
 */
public class ProcessErrMessagesExceptionResolver extends SimpleMappingExceptionResolver{
	
	//多个错误信息是否换行
	private boolean isBr = true;
	
	//每条错误信息前缀
	private String prefix;
	
	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex,
			HttpServletRequest request) {
		//如果是bean验证错误,则把提示消息汇总为一条错误消息返回错误页面显示
		if(ex instanceof BindException){
			BindException bex = (BindException)ex;
			String processedMsg = getErrorMessage(bex); 
			ex = new MsgBindException(processedMsg,bex.getBindingResult());
		}
		return getModelAndView(viewName, ex);
	}
	
	@SuppressWarnings("unchecked")
	private String getErrorMessage(BindException ex){
		StringBuilder result = new StringBuilder();
		List<ObjectError> errors = ex.getAllErrors();
		if(errors == null || errors.size()<=0) return "";
		for (int i=0;i<errors.size();i++) {
			result.append(prefix);
			result.append(errors.get(i).getDefaultMessage());
			if(i!=errors.size()-1 && isBr) result.append("<br>");
		}
		return result.toString();
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
