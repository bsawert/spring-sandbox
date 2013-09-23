/**
 * 
 */
package com.sawert.sandbox.spring.mvc.filter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author bsawert
 *
 */
public class TestRequestWrapperTest {

    // object under test
    private TestRequestWrapper wrapper;

    // mock objects
    @Mock HttpServletRequest request;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        // this must be called for the @Mock annotations above to be processed.
        MockitoAnnotations.initMocks(this);
        setupMocks();

        // create wrapper with mock request
        wrapper = new TestRequestWrapper(request);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.filter.TestRequestWrapper#setParameter(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testSetParameterStringString() {
        wrapper.setParameter("key1", "value1");
        String[] vals = wrapper.getParameterValues("key1");
        assertNotNull(vals);
        assertTrue(vals.length > 0);
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.filter.TestRequestWrapper#getParameterValues(java.lang.String)}.
     */
    @Test
    public void testGetParameterValues() {
        // get the parameter values for the wrapped mock request
        String[] rvals = wrapper.getParameterValues("rname");
        assertNotNull(rvals);
        assertTrue(rvals.length > 0);
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.filter.TestRequestWrapper#removeParameter(java.lang.String)}.
     */
    @Test
    public void testRemoveParameter() {
        // remove the wrapped mock request parameter
        wrapper.removeParameter("rname");
        String[] rvals = wrapper.getParameterValues("rname");
        assertNull(rvals);
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.filter.TestRequestWrapper#getParameterMap()}.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testGetParameterMap() {
        wrapper.setParameter("key1", "value1");
        Map<String, String[]> params = wrapper.getParameterMap();
        assertNotNull(params);
        assertTrue(params.size() == 2);
    }

    /**
     * Test method for {@link com.sawert.sandbox.spring.mvc.filter.TestRequestWrapper#getQueryString()}.
     */
    @Test
    public void testGetQueryString() {
        wrapper.addParameter("key1", "value1");
        String query = wrapper.getQueryString();
        assertNotNull(query);
        assertTrue(query.contains("key1"));
        assertTrue(query.contains("rname"));
    }

    /**
     * Set up mock services
     */
    private void setupMocks() {
        // set up mock request
        Map<String, String[]> params = new HashMap<String, String[]>();
        params.put("rname", new String[] {"rvalue"});
        when(request.getParameterMap()).thenReturn(params);
        when(request.getQueryString()).thenReturn("rname=rvalue");
    }

}
