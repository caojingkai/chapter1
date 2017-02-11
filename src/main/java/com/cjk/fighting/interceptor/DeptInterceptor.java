package com.cjk.fighting.interceptor;

import javax.servlet.http.HttpServletRequest;
import static java.lang.System.out;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DeptInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			Exception exception) throws Exception {
		// TODO Auto-generated method stub
		out.println("afterCompletion");

	}

	public void postHandle(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj,
			ModelAndView modelandview) throws Exception {
		// TODO Auto-generated method stub
		out.println("postHandle");

	}

	public boolean preHandle(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse, Object obj)
			throws Exception {
		// TODO Auto-generated method stub
		out.println("preHandle");

		return true;
	}

}
