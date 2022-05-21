<%@ tag pageEncoding="UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%-- FRAGMENT ATTRIBUTES --%>
<%@ attribute name="title" fragment="true" %>
<%@ attribute name="links" fragment="true" %>

<%-- SIMPLE ATTRIBUTES --%>
<%@ attribute name="theme" type="String" %>

<c:choose>
	<c:when test="${theme == 'light'}">
		<c:set var="color" value="light" />
	</c:when>
	<c:otherwise>
		<c:set var="color" value="dark" />
	</c:otherwise>
</c:choose>

<nav class="navbar navbar-expand-sm navbar-${color} bg-${color} mb-4">
	<div class="container">

		<jsp:invoke fragment="title" />

		<button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbar" 
			aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div id="navbar" class="collapse navbar-collapse">
			<jsp:invoke fragment="links" />
		</div>

	</div>
</nav>
