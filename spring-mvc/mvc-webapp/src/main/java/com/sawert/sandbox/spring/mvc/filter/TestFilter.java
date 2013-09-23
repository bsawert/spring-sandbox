package com.sawert.sandbox.spring.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test web filter.
 * 
 * @author bsawert
 *
 */
public class TestFilter implements Filter {
    private static final Log log = LogFactory.getLog(TestFilter.class);

    /**
     * Default constructor
     */
    public TestFilter() {
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (log.isDebugEnabled()) {
            log.debug("Initializing TestFilter");
        }
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("Executing TestFilter chain");
        }

        // create a request wrapper
        TestRequestWrapper wrappedRequest = new TestRequestWrapper((HttpServletRequest) request);
        chain.doFilter(wrappedRequest, response);
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
        if (log.isDebugEnabled()) {
            log.debug("Destroying TestFilter");
        }
    }

}
