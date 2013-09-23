package com.sawert.sandbox.spring.mvc.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Test model list class.
 * 
 * @author bsawert
 *
 */
@XmlRootElement(name="models")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestModels {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -8591795993313817050L;
	
	@XmlElement(name="model")
	private List<TestModel> modelList;
	
	/**
	 * Default constructor
	 */
	public TestModels() {
		this.modelList = new ArrayList<TestModel>();
	}

    /**
     * Parameterized constructor
     *
     * @param modelList
     */
	public TestModels(List<TestModel> modelList) {
		this.modelList = modelList;
	}

	/**
	 * Add model collection to list
	 * 
	 * @param modelList
	 * @return true if list changed
	 */
	public boolean addAll(Collection<TestModel> modelList) {
		return modelList.addAll(modelList);
	}
	
	/**
	 * Add model to list
	 * 
	 * @param model
	 * @return true
	 */
	public boolean add(TestModel model) {
		return modelList.add(model);
	}
	
	/**
	 * Remove model from list
	 * 
	 * @param model
	 * @return true if list contained entry
	 */
	public boolean remove(TestModel model) {
		return modelList.remove(model);
	}
	
	/**
	 * @return the modelList
	 */
	public List<TestModel> getModelList() {
		return modelList;
	}

	/**
	 * @param models the modelList to set
	 */
	public void setModelList(List<TestModel> modelList) {
		this.modelList = modelList;
	}
}
