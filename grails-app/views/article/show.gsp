
<%@ page import="rpa.Article" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'article.label', default: 'Article')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-article" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-article" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list article">
			
				<g:if test="${articleInstance?.tittle}">
				<li class="fieldcontain">
					<span id="tittle-label" class="property-label"><g:message code="article.tittle.label" default="Tittle" /></span>
					
						<span class="property-value" aria-labelledby="tittle-label"><g:fieldValue bean="${articleInstance}" field="tittle"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${articleInstance?.journal}">
				<li class="fieldcontain">
					<span id="journal-label" class="property-label"><g:message code="article.journal.label" default="Journal" /></span>
					
						<span class="property-value" aria-labelledby="journal-label"><g:fieldValue bean="${articleInstance}" field="journal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${articleInstance?.issn}">
				<li class="fieldcontain">
					<span id="issn-label" class="property-label"><g:message code="article.issn.label" default="Issn" /></span>
					
						<span class="property-value" aria-labelledby="issn-label"><g:fieldValue bean="${articleInstance}" field="issn"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:articleInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${articleInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
