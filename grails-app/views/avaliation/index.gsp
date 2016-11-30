
<%@ page import="rpa.Avaliation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'avaliation.label', default: 'Avaliation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-avaliation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-avaliation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="avaliation.qualis.label" default="Qualis" /></th>
					
						<th><g:message code="avaliation.researcher.label" default="Researcher" /></th>
					
						<g:sortableColumn property="categoryPoints" title="${message(code: 'avaliation.categoryPoints.label', default: 'Category Points')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${avaliationInstanceList}" status="i" var="avaliationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${avaliationInstance.id}">${fieldValue(bean: avaliationInstance, field: "qualis")}</g:link></td>
					
						<td>${fieldValue(bean: avaliationInstance, field: "researcher")}</td>
					
						<td>${fieldValue(bean: avaliationInstance, field: "categoryPoints")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${avaliationInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
