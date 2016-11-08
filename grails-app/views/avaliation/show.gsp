
<%@ page import="rpa.Avaliation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'avaliation.label', default: 'Avaliation')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-avaliation" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-avaliation" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list avaliation">
			
				<g:if test="${avaliationInstance?.qualis}">
				<li class="fieldcontain">
					<span id="qualis-label" class="property-label"><g:message code="avaliation.qualis.label" default="Qualis" /></span>
					
						<span class="property-value" aria-labelledby="qualis-label"><g:link controller="qualis" action="show" id="${avaliationInstance?.qualis?.id}">${avaliationInstance?.qualis?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${avaliationInstance?.researcher}">
				<li class="fieldcontain">
					<span id="researcher-label" class="property-label"><g:message code="avaliation.researcher.label" default="Researcher" /></span>
					
						<span class="property-value" aria-labelledby="researcher-label"><g:link controller="researcher" action="show" id="${avaliationInstance?.researcher?.id}">${avaliationInstance?.researcher?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:avaliationInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${avaliationInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
