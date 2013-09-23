package com.sawert.sandbox.spring.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sawert.sandbox.spring.mvc.controller.TestRestController;

/**
 * Handler interceptor for mvc webapp
 * 
 * @author bsawert
 *
 */
public class TestHandlerInterceptor extends HandlerInterceptorAdapter {
    private static final Log log = LogFactory.getLog(TestRestController.class);

    /**
     * Default constructor
     */
    public TestHandlerInterceptor() {
        super();
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Executing TestHandlerInterceptor preHandle");
        }

        return super.preHandle(request, response, handler);
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Executing TestHandlerInterceptor postHandle");
        }

        super.postHandle(request, response, handler, modelAndView);
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Executing TestHandlerInterceptor afterCompletion");
        }
        
        super.afterCompletion(request, response, handler, ex);
    }

}
