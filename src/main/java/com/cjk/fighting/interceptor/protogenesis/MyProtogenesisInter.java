package com.cjk.fighting.interceptor.protogenesis;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import static java.lang.System.out;

public class MyProtogenesisInter implements Filter{



	public void init(FilterConfig filterconfig) throws ServletException {
		// TODO Auto-generated method stub
		out.println(filterconfig.getFilterName()+",  my Filter initing...");
		
	}

	public void doFilter(ServletRequest servletrequest,
			ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		out.println("just do it");
		filterchain.doFilter(servletrequest, servletresponse);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		out.println("my Filter destroying...");

	}

}
