package com.cjk.fighting.shiro.filter.mgt;

import java.util.Iterator;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

public class MyPathMatchingFilterChainResolver extends PathMatchingFilterChainResolver{
	
	private MyDefaultFilterChainManager myDefaultFilterChainManager;
	
	
	public void setMyDefaultFilterChainManager(
			MyDefaultFilterChainManager myDefaultFilterChainManager) {
		this.myDefaultFilterChainManager = myDefaultFilterChainManager;
		
		setFilterChainManager(myDefaultFilterChainManager);
	}



	public FilterChain getChain(ServletRequest request, ServletResponse response, FilterChain originalChain)
    {
        FilterChainManager filterChainManager = getFilterChainManager();
        if(!filterChainManager.hasChains())
            return null;
        String requestURI = getPathWithinApplication(request);
        for(Iterator i$ = filterChainManager.getChainNames().iterator(); i$.hasNext();)
        {
            String pathPattern = (String)i$.next();
            if(pathMatches(pathPattern, requestURI))
            {
                 return filterChainManager.proxy(originalChain, pathPattern);
            }
        }

        return null;
    }
}
