<%@ include file="/WEB-INF/views/includes.jsp" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div>
	<h2>Test Form</h2>
	<form:form id="form" method="post" modelAttribute="testData" cssClass="cleanform">
		<div class="header">
	  		<h2>Enter Test Data</h2>
	  		<c:if test="${not empty message}">
				<div id="message" class="${message.type}">${message.text}</div>
	  		</c:if>
	  		<spring:bind path="*">
	  			<c:if test="${status.error}">
			  		<div id="message" class="error">Form has errors</div>
	  			</c:if>
	  		</spring:bind>
		</div>
		<fieldset>
		  	<legend>Test Data</legend>
			<form:label path="testString">Test model ID&nbsp;<form:errors path="testString" cssClass="error"/></form:label>
			<form:input path="testString" />
		</fieldset>
		
		<p><input type="submit" name="submit" /></p>
	</form:form>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>

