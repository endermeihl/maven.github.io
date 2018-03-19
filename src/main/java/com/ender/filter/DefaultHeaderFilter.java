package com.ender.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 设置默认的返回头
 * Created by ender on 2017/3/13.
 */
public class DefaultHeaderFilter extends AbstractFilter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        defultHeader(servletResponse);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 默认的返回头方法
     * 版本号根据实际设置
     *
     * @param servletResponse
     */
    private void defultHeader(ServletResponse servletResponse) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
    }
}
