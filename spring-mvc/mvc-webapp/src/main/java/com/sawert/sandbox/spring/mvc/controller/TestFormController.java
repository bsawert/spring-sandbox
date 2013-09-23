package com.sawert.sandbox.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sawert.sandbox.spring.mvc.model.TestModel;
import com.sawert.sandbox.spring.mvc.service.TestModelService;
import com.sawert.sandbox.spring.mvc.validator.TestDataValidator;

/**
 * @author bsawert
 * 
 * Controller for test data operations.
 * 
 * Handles requests to the /test URI
 *
 */
@Controller
@RequestMapping("/test")
public class TestFormController {
	@SuppressWarnings("unused")
    private static final Log log = LogFactory.getLog(TestFormController.class);

    @Autowired
    private ApplicationContext context;

    @Autowired
    private TestModelService testModelService;

    @InitBinder("testData")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new TestDataValidator());
    }

    /**
     * Show the test data form view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showTestForm(HttpServletRequest request, HttpServletResponse response, Model model,
        @ModelAttribute("testData") TestData testData) {

        if (testData == null) {
            testData = new TestData();
        }

        model.addAttribute("testData", testData);

        return "testform";
    }

    /**
     * Process a test data form post
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processTestForm(HttpServletRequest request,
        @Valid @ModelAttribute("testData") TestData testData, BindingResult result, Model model) {
        // default view
        String view = "testview";

        if (result.hasErrors()) {
            view = "testform";
        }
        else {
            TestModel testModel = testModelService.getModelById(testData.getTestString());
            model.addAttribute("testModel", testModel);
        }

        return view;
    }

    public TestModelService getTestModelService() {
        return testModelService;
    }

    public void setTestModelService(TestModelService testModelService) {
        this.testModelService = testModelService;
    }

}
