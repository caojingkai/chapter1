package com.cjk.fighting.shiro.filter.mgt;

import javax.servlet.FilterChain;

import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;

public class MyDefaultFilterChainManager extends DefaultFilterChainManager {
	
    private String loginUrl;
    private String successUrl;
    private String unauthorizedUrl;
    
    
    
    public FilterChain proxy(FilterChain original, String chainName)
    {
        NamedFilterList configured = getChain(chainName);
        if(configured == null)
        {
            String msg = (new StringBuilder()).append("There is no configured chain under the name/key [").append(chainName).append("].").toString();
            throw new IllegalArgumentException(msg);
        } else
        {
            return configured.proxy(original);
        }
    }
	
	
	
	  public String getLoginUrl() {
			return loginUrl;
		}
		public void setLoginUrl(String loginUrl) {
			this.loginUrl = loginUrl;
		}
		public String getSuccessUrl() {
			return successUrl;
		}




		public void setSuccessUrl(String successUrl) {
			this.successUrl = successUrl;
		}




		public String getUnauthorizedUrl() {
			return unauthorizedUrl;
		}




		public void setUnauthorizedUrl(String unauthorizedUrl) {
			this.unauthorizedUrl = unauthorizedUrl;
		}

}
