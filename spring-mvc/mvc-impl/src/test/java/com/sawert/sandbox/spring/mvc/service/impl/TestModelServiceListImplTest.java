package com.sawert.sandbox.spring.mvc.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sawert.sandbox.spring.mvc.model.TestModel;
import com.sawert.sandbox.spring.mvc.model.TestModels;
import com.sawert.sandbox.spring.mvc.service.TestModelService;

/**
 * Unit test for TestModelServiceListImpl
 * 
 * @author bsawert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context/test-applicationContext.xml" })
public class TestModelServiceListImplTest {
	
	// list of TestModel objects
	@Resource(name="testModelList")
	private List<TestModel> testModelList;
	
	// service under test
	@Autowired
	private TestModelService service;

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

	@Test
	public void testGetModelById() {
		TestModel testModel = testModelList.get(0);
		TestModel serviceTestModel = service.getModelById(testModel.getId());
		assertNotNull(serviceTestModel);
		assertEquals(serviceTestModel, testModel);
	}
	
	@Test
	public void testGetAllModels() {
		TestModels serviceTestModels = service.getAllModels();
		assertNotNull(serviceTestModels);
		assertEquals(serviceTestModels.getModelList().size(), testModelList.size());
	}
	
}
