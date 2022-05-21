<%@ tag pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@ variable name-given="scheme" %>
<%@ variable name-given="serverName" %>
<%@ variable name-given="serverPort" %>
<%@ variable name-given="contextPath" %>
<%@ variable name-given="servletPath" %>

<c:set var="scheme"      value="${pageContext.request.scheme}" />
<c:set var="serverName"  value="${pageContext.request.serverName}" />
<c:set var="serverPort"  value="${pageContext.request.serverPort}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="servletPath" value="${pageContext.request.servletPath}" />

<fmt:setBundle basename="messages" />

<c:set var="template_title">
	<fmt:message key="template_title" />
</c:set>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>${template_title}</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<script src="scripts/stomp.js"></script>
	</head>
	<body>
	<body>

		<t:navigation>
			<jsp:attribute name="title">
				<a class="navbar-brand" href="${contextPath}/">
					<span>${template_title}</span>
				</a>
			</jsp:attribute>
			<jsp:attribute name="links">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<a class="nav-link" href="${contextPath}/index.jsp">
							<fmt:message key="navigation_basic" />
						</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="${contextPath}/message-broker.jsp">
							<fmt:message key="navigation_message_broker" />
						</a>
					</li>
				</ul>
			</jsp:attribute>
		</t:navigation>

		<jsp:doBody />

	</body>
</html>
