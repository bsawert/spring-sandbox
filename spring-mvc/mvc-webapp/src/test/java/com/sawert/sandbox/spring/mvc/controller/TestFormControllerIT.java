package com.sawert.sandbox.spring.mvc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.gargoylesoftware.htmlunit.WebClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.sawert.sandbox.spring.mvc.model.TestModel;

/**
 * Integration test for test form controller
 * 
 * @author bsawert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context/test-applicationContext.xml" })
public class TestFormControllerIT {

    private final String WEBAPP_CONTEXT = "/mvc-webapp";

    private final String FIELD_NAME = "testString";
    private final String BUTTON_NAME = "submit";
    private final String RESULT_NAME = "result";
    private final String ID = "id01";
    private String testFormPath = WEBAPP_CONTEXT + "/action/test";

    @Autowired
    @Qualifier("jettyBaseUrl")
    private String jettyBaseUrl;

    // list of TestModel objects
    @Resource(name="testModelList")
    private List<TestModel> modelList;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method to process the test form.
     */
    @Test
    public void testProcessTestForm() {
        String testUrl = jettyBaseUrl + testFormPath;
        String acceptContent = "text/html";

        try {
            // create the web client
            WebClient client = new WebClient(BrowserVersion.CHROME);
            client.addRequestHeader("Accept", acceptContent);

            // build request settings for form post
            WebRequest request = new WebRequest(new URL(testUrl), HttpMethod.POST);

            // set the request parameters
            request.setRequestParameters(new ArrayList<NameValuePair>());
            request.getRequestParameters().add(new NameValuePair(FIELD_NAME, ID));

            HtmlPage page = client.getPage(request);
            int status = page.getWebResponse().getStatusCode();
            assertEquals(200, status);
            //System.out.println(page.asXml());
        }
        catch (Exception e) {
            fail(e.getMessage());
        }
    }

    /**
     * Test method to retrieve, fill and submit the form
     * 
     * @throws Exception
     */
    @Test
    public void testSubmitForm() throws Exception {
        String testUrl = jettyBaseUrl + testFormPath;
        String acceptContent = "text/html";

        // create the web client
        WebClient client = new WebClient(BrowserVersion.CHROME);
        client.addRequestHeader("Accept", acceptContent);

        // get the form page
        HtmlPage page = client.getPage(testUrl);
        //System.out.println(page.asXml());

        // get the form and input field
        HtmlForm form = page.getForms().get(0);
        HtmlTextInput field = form.getInputByName(FIELD_NAME);
        HtmlSubmitInput submit = form.getInputByName(BUTTON_NAME);
        
        // change the value of the text field
        field.setValueAttribute(ID);

        // submit the form by clicking the button and get back the second page.
        HtmlPage view = submit.click();
        //System.out.println(view.asXml());

        // make sure there is a "success" div
        DomElement resultDiv = view.getElementById(RESULT_NAME);
        assertNotNull(resultDiv);

        String result = resultDiv.getAttribute("class");
        assertEquals("success", result);

        client.close();
    }
}
