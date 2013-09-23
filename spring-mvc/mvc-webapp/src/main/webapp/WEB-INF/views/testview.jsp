<%@ include file="/WEB-INF/views/includes.jsp" %>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="formInfo">
	<h2>Form Results</h2>
	<h4>Test Model</h4>
	<c:choose>
		<c:when test="${not empty testModel}">
			<div id="result" class="success"> 
				<p>ID: ${testModel.id}</p>
				<p>Name: ${testModel.name}</p>
				<p>Value: ${testModel.value}</p>
			</div>
		</c:when>
		<c:otherwise>
			<div id=""result"" class="warning">
				<p>No matching test model found for ID: ${testData.testString}</p>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>

