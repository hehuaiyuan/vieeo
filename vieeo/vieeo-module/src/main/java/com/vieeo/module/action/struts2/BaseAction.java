package com.vieeo.module.action.struts2;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


/**
 *
 * @author roy
 */
public class BaseAction extends ActionSupport {

	private static final long serialVersionUID = 4232338017350030377L;

	@Override
	public String execute() throws Exception {
		return super.execute();
	}

	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse(){
		return ServletActionContext.getResponse();
	}

	public ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}

	public HttpSession getSession(){
		return getRequest().getSession();
	}

	public ActionContext getActionContext(){
		return ServletActionContext.getActionContext(getRequest());
	}

	@SuppressWarnings("unchecked")
	public Map<String,Object> getParameter(){
		return getRequest().getParameterMap();
	}

}
