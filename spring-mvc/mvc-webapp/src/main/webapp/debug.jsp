<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" isELIgnored="false" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName()
			+ ":" + request.getServerPort() + path + "/";
%>

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
	<title>Debug Info</title>
</head>
<body class="body">
	<div id="header"><h2>Debug Info</h2></div>

	<div id="main">
		<TABLE width='80%' border='1' align='center' cellPadding='0' cellSpacing='0' bgColor=#dddddd>
			<TBODY>
				<tr>
					<td>
						<h4>Session Scope Objects</h4>
						<c:forEach var="attr" items="${sessionScope}">
							<c:out value="${attr}"/><br/>
						</c:forEach>
						
						<h4>Page Scope Objects</h4>
						<c:forEach var="attr" items="${pageScope}">
							<c:out value="${attr}"/><br/>
						</c:forEach>
		
						<h4>Request Scope Objects</h4>
						<c:forEach var="attr" items="${requestScope}">
							<c:out value="${attr}"/><br/>
						</c:forEach>
		
						<h4>Request Parameters</h4>
						<c:forEach var="attr" items="${param}">
							<c:out value="${attr}"/><br/>
						</c:forEach>
					</td>
				</tr>
			</TBODY>
		</TABLE>
	</div>

</body>
</html>