
<%@ page import="rpa.Researcher" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'researcher.label', default: 'Researcher')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-researcher" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-researcher" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list researcher">
			
				<g:if test="${researcherInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="researcher.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${researcherInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${researcherInstance?.cpf}">
				<li class="fieldcontain">
					<span id="cpf-label" class="property-label"><g:message code="researcher.cpf.label" default="Cpf" /></span>
					
						<span class="property-value" aria-labelledby="cpf-label"><g:fieldValue bean="${researcherInstance}" field="cpf"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${researcherInstance?.citationAmount}">
				<li class="fieldcontain">
					<span id="citationAmount-label" class="property-label"><g:message code="researcher.citationAmount.label" default="Citation Amount" /></span>
					
						<span class="property-value" aria-labelledby="citationAmount-label"><g:fieldValue bean="${researcherInstance}" field="citationAmount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${researcherInstance?.articles}">
				<li class="fieldcontain">
					<span id="articles-label" class="property-label"><g:message code="researcher.articles.label" default="Articles" /></span>
					
						<g:each in="${researcherInstance.articles}" var="a">
						<span class="property-value" aria-labelledby="articles-label"><g:link controller="article" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${researcherInstance?.updates}">
				<li class="fieldcontain">
					<span id="updates-label" class="property-label"><g:message code="researcher.updates.label" default="Updates" /></span>
					
						<g:each in="${researcherInstance.updates}" var="u">
						<span class="property-value" aria-labelledby="updates-label"><g:link controller="updateLattes" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:researcherInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${researcherInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
