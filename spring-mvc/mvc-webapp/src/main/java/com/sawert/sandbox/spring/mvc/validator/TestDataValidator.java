package com.sawert.sandbox.spring.mvc.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.sawert.sandbox.spring.mvc.controller.TestData;

/**
 * This class validates the fields in a TestData object
 * 
 * @author bsawert
 *
 */
public class TestDataValidator implements Validator {

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return TestData.class.equals(clazz);
    }

    /* (non-Javadoc)
     * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
     */
    @Override
    public void validate(Object obj, Errors e) {
        // validate request info
        ValidationUtils.rejectIfEmpty(e, "testString", "testString.empty", "testString is empty");
    }
}
