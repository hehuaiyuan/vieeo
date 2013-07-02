package com.vieeo.component.spring.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

/**
 * 当一个请求需要返回以请求参数中指定的返回页面时,可使用此类处理
 * 处理请求参数中指定要跳转的页面
 * @author roy.he
 *
 */
public class AnnotationMethodWithBackPageHandlerAdapter extends AnnotationMethodHandlerAdapter{
	
	//得到指定返回页面url的参数key
	private String backPageParamName;  

	@Override
	protected ModelAndView invokeHandlerMethod(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		ModelAndView result = super.invokeHandlerMethod(request, response, handler);
		String path = request.getParameter(backPageParamName);
		if(!StringUtils.isBlank(path)) {
			result.setViewName(path);
		}
		return result;
	}

	public String getBackPageParamName() {
		return backPageParamName;
	}

	public void setBackPageParamName(String backPageParamName) {
		this.backPageParamName = backPageParamName;
	}

}
