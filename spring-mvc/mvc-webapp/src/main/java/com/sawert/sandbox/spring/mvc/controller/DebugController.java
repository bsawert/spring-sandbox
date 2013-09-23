package com.sawert.sandbox.spring.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author bsawert
 * 
 * Controller for debug operations.
 * 
 * Handles requests to the /debug URI
 *
 */
@Controller
@RequestMapping("/debug")
public class DebugController {
    private static final Log log = LogFactory.getLog(DebugController.class);

    /**
     * Show the debug view
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String showReplaceForm(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (log.isDebugEnabled()) {
            log.debug("Showing debug view");
        }
        return "debug";
    }

}
