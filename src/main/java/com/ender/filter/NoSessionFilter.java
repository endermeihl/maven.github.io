package com.ender.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 通过filter关闭自动创建session
 * Created by ender on 2017/3/11.
 */
public class NoSessionFilter extends AbstractFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new NoSessionHttpServletRequest((HttpServletRequest) servletRequest), response);
    }

    /**
     * 通过filter关闭自动创建session
     * Created by ender on 2017/3/11.
     */
    private class NoSessionHttpServletRequest extends HttpServletRequestWrapper {
        public NoSessionHttpServletRequest(HttpServletRequest httpServletRequest) {
            super(httpServletRequest);
        }

        @Override
        public HttpSession getSession() {
            return null;
        }

        @Override
        public HttpSession getSession(boolean var1) {
            return null;
        }
    }

}
