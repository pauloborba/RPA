
<%@ page import="rpa.QualisEvaluation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'qualisAvaliation.label', default: 'QualisEvaluation')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-qualisAvaliation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-qualisAvaliation" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="journal" title="${message(code: 'qualisAvaliation.journal.label', default: 'Journal')}" />
					
						<g:sortableColumn property="avaliation" title="${message(code: 'qualisAvaliation.avaliation.label', default: 'Avaliation')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${qualisAvaliationInstanceList}" status="i" var="qualisAvaliationInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${qualisAvaliationInstance.id}">${fieldValue(bean: qualisAvaliationInstance, field: "journal")}</g:link></td>
					
						<td>${fieldValue(bean: qualisAvaliationInstance, field: "avaliation")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${qualisAvaliationInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
