/**
 * Unit test to demonstrate Spring test MVC framework
 * Test use MockMvc and web application context
 */
package com.sawert.sandbox.spring.mvc.controller;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Unit test for REST controller
 * 
 * @author bsawert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/context/test-applicationContext.xml" })
@WebAppConfiguration
public class TestRestControllerWebAppTest {

    private String ID = "id01";
    private String testAllModelsPath = "/models";
    private String testModelPath = "/model/" + ID;

    private final String acceptContent = "application/json;charset=UTF-8";

    @Autowired WebApplicationContext wac; // cached
    @Autowired MockServletContext servletContext; // cached
    @Autowired MockHttpSession session;
    @Autowired MockHttpServletRequest request;
    @Autowired MockHttpServletResponse response;
    @Autowired ServletWebRequest webRequest;

    private MockMvc mockMvc;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    	// set up a mock MVC with web application context
    	// global default is to expect tests to return 200 OK status 
        this.mockMvc = webAppContextSetup(this.wac).alwaysExpect(status().isOk()).build();
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
    public void testGetAllModelsAsJson() throws Exception {
        this.mockMvc.perform(
            get(testAllModelsPath)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8"))
        .andDo(print())
        .andExpect(content().contentType(acceptContent))
        .andExpect(jsonPath("$.modelList[0].name").value("name1"))
        .andExpect(jsonPath("$.modelList[1].name").value("name2"));
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.controller.TestRestController#getModelById(java.lang.String)}.
     */
    @Test
    public void testGetModelByIdAsJson() throws Exception {
        this.mockMvc.perform(
            get(testModelPath)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8"))
        .andDo(print())
        .andExpect(content().contentType(acceptContent))
        .andExpect(jsonPath("$.name").value("name1"));
    }

}
