package com.sawert.sandbox.spring.mvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sawert.sandbox.spring.mvc.model.TestModel;
import com.sawert.sandbox.spring.mvc.model.TestModels;
import com.sawert.sandbox.spring.mvc.service.TestModelService;

/**
 * @author bsawert
 * 
 * Controller for test data operations.
 * 
 * Handles requests to the /test URI
 *
 */
@Controller
public class TestRestController {
    private static final Log log = LogFactory.getLog(TestRestController.class);

    @Autowired
    private TestModelService testModelService;

    /**
     * Return a specific test model
     * 
     * @param id
     * @return test model
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.GET, value="/model/{id}")
    public @ResponseBody TestModel getModelById(@PathVariable(value="id") String id) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Retrieving test model for id: " + id);
        }

        TestModel testModel = testModelService.getModelById(id);

        return testModel;
    }

    /**
     * Return all test models
     * 
     * @param id
     * @return test model
     * @throws Exception
     */
    @RequestMapping(method=RequestMethod.GET, value="/models")
    public @ResponseBody TestModels getAllModels() throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("Retrieving all test models");
        }

        TestModels testModels = testModelService.getAllModels();

        return testModels;
    }

    public TestModelService getTestModelService() {
        return testModelService;
    }

    public void setTestModelService(TestModelService testModelService) {
        this.testModelService = testModelService;
    }

}
