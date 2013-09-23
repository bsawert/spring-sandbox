/**
 * Integration test for REST controller using Jetty
 */
package com.sawert.sandbox.spring.mvc.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.sawert.sandbox.spring.mvc.model.TestModel;
import com.sawert.sandbox.spring.mvc.model.TestModels;

/**
 * Integration test for REST controller
 * 
 * @author bsawert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context/test-applicationContext.xml" })
public class TestRestControllerIT {

    private final String WEBAPP_CONTEXT = "/mvc-webapp";

    private final String ID = "id01";
    private String testAllModelsPath = WEBAPP_CONTEXT + "/action/models";
    private String testModelPath = WEBAPP_CONTEXT + "/action/model/" + ID;

    @Autowired
    @Qualifier("jettyBaseUrl")
    private String jettyBaseUrl;

    // list of TestModel objects
    @Resource(name="testModelList")
    private List<TestModel> testModelList;

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
     * Test method for {@link com.sawert.sandbox.spring.mvc.controller.TestRestController#getAllModels()}.
     */
    @Test
    public void testGetAllModelsAsJson() {
        String testUrl = jettyBaseUrl + testAllModelsPath + ".json";
        String acceptContent = "application/json";
        
        try {
            // call web application
            WebClient client = new WebClient(BrowserVersion.CHROME);
            client.addRequestHeader("Accept", acceptContent);
            Page page = client.getPage(testUrl);
            WebResponse response = page.getWebResponse();

            // make sure we have a JSON response
            assertEquals(acceptContent, response.getContentType());

            // deserialize and check content
            String json = response.getContentAsString();

            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createJsonParser(json);
            TestModels models = mapper.readValue(parser, new TypeReference<TestModels>() {});

            assertNotNull(models);
            assertFalse(models.getModelList().isEmpty());
            assertEquals(testModelList.size(), models.getModelList().size());

            // make sure we have matching content
            int matches = 0;
            for (TestModel testModel : testModelList) {
                String id = testModel.getId();
                for (TestModel model : models.getModelList()) {
                    if (id.equals(model.getId())) {
                        matches++;
                    }
                }
            }

            assertEquals(testModelList.size(), matches);
        }
        catch (Exception e) {
            // FailingHttpStatusCodeException, MalformedURLException, IOException, JsonSyntaxException
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.controller.TestRestController#getAllModels()}.
     */
    @Test
    public void testGetAllModelsAsXml() {
        String testUrl = jettyBaseUrl + testAllModelsPath + ".xml";
        String acceptContent = "application/xml";
        
        try {
            // call web application
            WebClient client = new WebClient(BrowserVersion.CHROME);
            client.addRequestHeader("Accept", acceptContent);
            Page page = client.getPage(testUrl);
            WebResponse response = page.getWebResponse();

            // make sure we have an XML response
            assertEquals(acceptContent, response.getContentType());
            
            // deserialize and check content
			JAXBContext jc = JAXBContext.newInstance(TestModels.class);
			Unmarshaller u = jc.createUnmarshaller();
			StreamSource source = new StreamSource(response.getContentAsStream());
			TestModels models = (TestModels) u.unmarshal(source);
			
			assertNotNull(models);
			assertFalse(models.getModelList().isEmpty());
            assertEquals(testModelList.size(), models.getModelList().size());

            // make sure we have matching content
            int matches = 0;
            for (TestModel testModel : testModelList) {
                String id = testModel.getId();
                for (TestModel model : models.getModelList()) {
                    if (id.equals(model.getId())) {
                        matches++;
                    }
                }
            }

            assertEquals(testModelList.size(), matches);
        }
        catch (Exception e) {
            // FailingHttpStatusCodeException, MalformedURLException, IOException, JsonSyntaxException
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.controller.TestRestController#getModelById(java.lang.String)}.
     */
    @Test
    public void testGetModelByIdAsJson() {
        String testUrl = jettyBaseUrl + testModelPath + ".json";
        String acceptContent = "application/json";

        try {
            // call web application
            WebClient client = new WebClient(BrowserVersion.CHROME);
            client.addRequestHeader("Accept", acceptContent);
            Page page = client.getPage(testUrl);
            WebResponse response = page.getWebResponse();

            // make sure we have a JSON response
            assertEquals(acceptContent, response.getContentType());

            // deserialize and check content
            String json = response.getContentAsString();

            ObjectMapper mapper = new ObjectMapper();
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createJsonParser(json);
            TestModel model = mapper.readValue(parser, TestModel.class);

            assertNotNull(model);
            assertEquals(ID, model.getId());
        } catch (Exception e) {
            // FailingHttpStatusCodeException, MalformedURLException, IOException, JsonSyntaxException
            fail(e.getMessage());
        }
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.controller.TestRestController#getModelById(java.lang.String)}.
     */
    @Test
    public void testGetModelByIdAsXml() {
        String testUrl = jettyBaseUrl + testModelPath + ".xml";
        String acceptContent = "application/xml";

        try {
            // call web application
            WebClient client = new WebClient(BrowserVersion.CHROME);
            client.addRequestHeader("Accept", acceptContent);
            Page page = client.getPage(testUrl);
            WebResponse response = page.getWebResponse();

            // make sure we have an XML response
            assertEquals(acceptContent, response.getContentType());

            // deserialize and check content
            JAXBContext jc = JAXBContext.newInstance(TestModel.class);
            Unmarshaller u = jc.createUnmarshaller();
            StreamSource source = new StreamSource(response.getContentAsStream());
            TestModel model = (TestModel) u.unmarshal(source);
            assertNotNull(model);
            assertEquals(ID, model.getId());
        }
        catch (Exception e) {
            // FailingHttpStatusCodeException, MalformedURLException, IOException, JAXBException
            fail(e.getMessage());
        }
    }
    
}
