package com.user.management.UserManagement.Security;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.security.core.context.SecurityContextHolder;

public class ZuulHeaderFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        Object p = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RequestContext r = RequestContext.getCurrentContext();
        r.addZuulRequestHeader("username", p.toString());
        return null;
    }
}
