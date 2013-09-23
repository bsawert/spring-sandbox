package com.sawert.sandbox.spring.mvc.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sawert.sandbox.spring.mvc.model.TestModel;
import com.sawert.sandbox.spring.mvc.model.TestModels;
import com.sawert.sandbox.spring.mvc.service.TestModelService;

/**
 * List-based implementation of TestModelService interface.
 * 
 * @author bsawert
 *
 */
public class TestModelServiceListImpl implements TestModelService {
    private static final Log log = LogFactory.getLog(TestModelServiceListImpl.class);

    // list of TestModel objects
    private TestModels testModels;

    /**
     * Default constructor
     */
    public TestModelServiceListImpl() {
    	testModels = new TestModels();
    }

    /**
     * Parameterized constructor
     *
     * @param modelList
     */
    public TestModelServiceListImpl(List<TestModel> modelList) {
    	testModels = new TestModels(modelList);
    }

    /* (non-Javadoc)
     * @see com.sawert.sandbox.spring.mvc.service.TestModelService#getModelById(java.lang.String)
     */
    public TestModel getModelById(String id) {
        for (TestModel model : testModels.getModelList()) {
            if (model.getId().equals(id)) {
                if (log.isDebugEnabled()) {
                    log.debug("Found matching TestModel for id: " + id);
                }
                return model;
            }
        }

        return null;
    }

    /* (non-Javadoc)
     * @see com.sawert.sandbox.spring.mvc.service.TestModelService#getAllModels()
     */
    public TestModels getAllModels() {
        return testModels;
    }

	/**
	 * @return the testModels
	 */
	public TestModels getTestModels() {
		return testModels;
	}

	/**
	 * @param testModels the testModels to set
	 */
	public void setTestModels(TestModels testModels) {
		this.testModels = testModels;
	}

}
