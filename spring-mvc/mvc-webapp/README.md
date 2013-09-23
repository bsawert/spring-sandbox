mvc-webapp
==============

This project explores Spring MVC and the Spring MVC test framework.

It demonstrates using Spring MVC to provide a REST service endpoint with a @ResponseBody annotation. The context configuration
uses the default HttpMessageConverter to return either XML or JSON, depending on the path extension or parameter.

It also demonstrates using the ContentNegotiatingViewResolver to return the proper content type for a ModelAndView returned by a controller.

Unit tests demonstrate the Spring MVC test framework applied to a controller class.

Integration tests use the Maven Jetty plugin to provide a runtime Jetty instance for automated integration tests. This feature is enabled by running Maven with the "integration-test" profile (mvn -P integration-test integration-test).
