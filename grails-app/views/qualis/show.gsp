
<%@ page import="rpa.Qualis" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'qualis.label', default: 'Qualis')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-qualis" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-qualis" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list qualis">
			
				<g:if test="${qualisInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="qualis.description.label" default="Year" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${qualisInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${qualisInstance?.avaliations}">
				<li class="fieldcontain">
					<span id="avaliations-label" class="property-label"><g:message code="qualis.avaliations.label" default="Avaliations" /></span>
					
						<g:each in="${qualisInstance.avaliations}" var="a">
						<span class="property-value" aria-labelledby="avaliations-label"><g:link controller="qualisEvaluation" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:qualisInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${qualisInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
