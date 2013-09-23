package com.sawert.sandbox.spring.mvc.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;

public class TestModelTest {

	@Test
	public void testXmlMarshal() {
		TestModel model = new TestModel("id1", "name1", "value1");

	    try {
		    JAXBContext jaxbContext = JAXBContext.newInstance(TestModel.class);
		    
	    	// marshal the object to xml
	        Marshaller marshaller = jaxbContext.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        StringWriter result = new StringWriter();
	        marshaller.marshal(model, result);
	        String xml = result.toString();
	        
	        assertTrue(xml.contains("<id>id1</id>"));
	        assertTrue(xml.contains("<name>name1</name>"));
	        assertTrue(xml.contains("<value>value1</value>"));

	        // unmarshal the xml back to an object
	        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	        Source source = new StreamSource(new StringReader(xml)) ;
	        TestModel newModel = (TestModel) unmarshaller.unmarshal(source);	        
	        assertNotNull(newModel);
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
