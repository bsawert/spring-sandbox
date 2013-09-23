package com.sawert.sandbox.spring.mvc.service;

import com.sawert.sandbox.spring.mvc.model.TestModel;
import com.sawert.sandbox.spring.mvc.model.TestModels;

/**
 * Interface for TestModel service.
 * 
 * @author bsawert
 *
 */
public interface TestModelService {
	
	/**
	 * Get TestModel by id
	 * 
	 * @param id
	 * @return TestModel
	 */
	public TestModel getModelById(String id);

	/**
	 * Get all TestModels
	 * 
	 * @return List of TestModels
	 */
	public TestModels getAllModels();
}
