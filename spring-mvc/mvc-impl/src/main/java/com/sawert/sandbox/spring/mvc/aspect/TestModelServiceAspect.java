package com.sawert.sandbox.spring.mvc.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Sample aspect implementation
 * 
 * @author bsawert
 *
 */
@Aspect
public class TestModelServiceAspect {
    private static final Log log = LogFactory.getLog(TestModelServiceAspect.class);

    /**
     * Default constructor
     */
    public TestModelServiceAspect() {
        if (log.isDebugEnabled()) {
            log.debug("Constructing TestModelServiceAspect.");
        }                
    }

    // define method pointcuts

    @Pointcut("execution(* com.sawert.sandbox.spring.mvc.service.TestModelService.getModelById(*)) && args(id)")
    public void getModelByIdAspect(String id) {
    }

    @Pointcut("execution(* com.sawert.sandbox.spring.mvc.service.TestModelService.getAllModels())")
    public void getAllModelsAspect() {
    }

    // define pointcut advice

    @Before("getModelByIdAspect(id)")
    public void logBeforeGetModelById(JoinPoint jp, String id) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Aspect before execution of getModelById()");
        }
    }

    @Before("getAllModelsAspect()")
    public void logBeforeGetAllModelsAspect(JoinPoint jp) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("Aspect before execution of getAllModels()");
        }
    }

}
