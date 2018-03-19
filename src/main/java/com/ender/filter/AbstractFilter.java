package com.ender.filter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 * Created by ender on 2017/3/23.
 */
public abstract class AbstractFilter implements Filter {
    protected Logger log = LogManager.getLogger(this.getClass().getName());

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
