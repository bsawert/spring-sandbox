package com.sawert.sandbox.spring.mvc.model.xml;

import java.util.List;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.sawert.sandbox.spring.mvc.model.TestModel;
import com.sawert.sandbox.spring.mvc.model.TestModels;

/**
 * XML adapter class for JAXB handling of TestModel lists
 * 
 * @author bsawert
 *
 */
public final class TestModelsAdapter extends XmlAdapter<TestModels, List<TestModel>> {
	
	@Override
	public TestModels marshal(List<TestModel> modelList) throws Exception {
		return new TestModels(modelList);
	}
	
	@Override
	public List<TestModel> unmarshal(TestModels models) throws Exception {
		return models.getModelList();
	}

}