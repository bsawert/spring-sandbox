package com.sawert.sandbox.spring.mvc.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sawert.sandbox.spring.mvc.model.TestModel;
import com.sawert.sandbox.spring.mvc.model.TestModels;
import com.sawert.sandbox.spring.mvc.service.TestModelService;

/**
 * Implementation of TestModelService interface.
 * 
 * @author bsawert
 *
 */
public class TestModelServiceImpl implements TestModelService {
    private static final Log log = LogFactory.getLog(TestModelServiceImpl.class);

    /* (non-Javadoc)
     * @see com.sawert.sandbox.spring.mvc.service.TestModelService#getModelById(java.lang.String)
     */
    public TestModel getModelById(String id) {
        if (log.isDebugEnabled()) {
            log.debug("Calling getModelById stub.");
        }

        return null;
    }

    /* (non-Javadoc)
     * @see com.sawert.sandbox.spring.mvc.service.TestModelService#getAllModels()
     */
    public TestModels getAllModels() {
        if (log.isDebugEnabled()) {
            log.debug("Calling getAllModels stub.");
        }

        return null;
    }

}
