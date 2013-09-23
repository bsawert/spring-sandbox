package com.sawert.sandbox.spring.mvc.filter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Test request wrapper.
 * 
 * @author bsawert
 *
 */
public class TestRequestWrapper extends HttpServletRequestWrapper {
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(TestRequestWrapper.class);

	// request parameters
    private Map<String, String[]> parameterMap;
    
    // param=value pairs
    private Set<String> queryParametersAdded;

    /**
     * Holds a cached String that is returned by the <code>getQueryString</code>
     * method.  When <code>getQueryString</code> is called, if this field is 
     * <code>null</code>, then the method will determine the value and set it 
     * on this field.  All calls that add or remove parameters or parameter 
     * values must set this field to null.
     */
    private String queryString;

	/**
	 * Default constructor
	 * 
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	public TestRequestWrapper(HttpServletRequest request) {
        super(request);
        
        this.parameterMap = new HashMap<String, String[]>(request.getParameterMap());
        this.queryParametersAdded = new HashSet<String>();
    }

    /**
     * @see javax.servlet.ServletRequestWrapper#getParameter(java.lang.String)
     */
    @Override
    public String getParameter(final String name) {
        String result = null;
        
        String[] vals = getParameterValues(name);
        if (vals != null && vals.length > 0) {
            // return first value
            result = vals[0];
        }
        
        return result;
    }

    /**
     * @see javax.servlet.ServletRequestWrapper#getParameterMap()
     */
    @SuppressWarnings("rawtypes")
	@Override
    public Map getParameterMap() {
        // Must be immutable per javax.servlet.Servlet.getParameterMap(). 
        return Collections.unmodifiableMap(parameterMap);
    }

    /**
     * @see javax.servlet.ServletRequestWrapper#getParameterNames()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Enumeration getParameterNames() {
        Set keySet = parameterMap.keySet();
        Enumeration e = Collections.enumeration(keySet);
        
        return e;
    }

    /**
     * @see javax.servlet.ServletRequestWrapper#getParameterValues(java.lang.String)
     */
    @Override
    public String[] getParameterValues(String name) {
        return parameterMap.get(name);
    }

    /**
     * Adds the parameter with its value. Note that the parameter added is
     * treated as a query parameter, regardless of whether there already exists
     * a posted form parameter by the same name.
     * 
     * @param name parameter name
     * @param value parameter value
     */
    public void addParameter(final String name, final String value) {
        String[] paramVals = getParameterValues(name);
        String[] newParamVals;
        
        if (paramVals != null && paramVals.length > 0) {
        	// copy existing param values array
            int length = paramVals.length;
            newParamVals = new String[length + 1];
            System.arraycopy(paramVals, 0, newParamVals, 0, length);
            newParamVals[length] = value;
        }
        else {
        	// create new param values array
            newParamVals = new String[1];
            newParamVals[0] = value;
        }
        
        paramVals = null;
        parameterMap.put(name, newParamVals);
        queryParametersAdded.add(name + "=" + value);
        queryString = null;
    }

    /**
     * Sets the parameter with its value.  If the parameter already exists, 
     * then the existing values are overwritten by the supplied value.
     * 
     * @param name parameter name
     * @param value parameter value
     */
    public void setParameter(final String name, final String value) {
        String[] valArray = new String[1];
        valArray[0] = value;
        
        parameterMap.put(name, valArray);
        queryString = null;
    }

    /**
     * Sets the parameter with its values.  If the parameter already exists, 
     * then the existing values are overwritten by the supplied values.
     * 
     * @param name parameter name
     * @param values parameter values
     */
    public void setParameter(final String name, final String[] values) {
        parameterMap.put(name, values);
        queryString = null;
    }

    /**
     * Removes the parameter.
     * 
     * @param name name of parameter to be removed
     */
    public void removeParameter(String name) {
        parameterMap.remove(name);
    }

    /**
     * @see javax.servlet.http.HttpServletRequestWrapper#getQueryString()
     */
    @Override
    public String getQueryString() {
        if (queryString == null) {
            queryString = generateQueryString();
        }
        
        return queryString;
    }

    /**
     * Helper method to generate the queryString using the parameters from the 
     * superclass plus the additional parameters and values added with this 
     * class.
     * 
     * @return generated query string
     */
    private String generateQueryString() {
        boolean firstParameterWasAdded;
        String superQueryString = super.getQueryString();

        StringBuilder querySB = new StringBuilder();
        if (superQueryString == null) {
            firstParameterWasAdded = false;
        }
        else {
            querySB.append(superQueryString);
            firstParameterWasAdded = true;
        }

        if (!queryParametersAdded.isEmpty()) {
            for (String entry : queryParametersAdded) {
                if (superQueryString == null || superQueryString.indexOf(entry) == -1) {
                    if (firstParameterWasAdded) {
                        querySB.append("&");
                    }
                    querySB.append(entry);
                    firstParameterWasAdded = true;
                }
            }
        }

        String queryStr = querySB.toString();
        
        return queryStr;
    }
}
