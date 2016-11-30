
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
			
				<g:if test="${articleInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="article.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${articleInstance}" field="title"/></span>
					
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
			
				<g:if test="${articleInstance?.owner}">
				<li class="fieldcontain">
					<span id="owner-label" class="property-label"><g:message code="article.owner.label" default="Owner" /></span>
					
						<span class="property-value" aria-labelledby="owner-label"><g:link controller="researcher" action="show" id="${articleInstance?.owner?.id}">${articleInstance?.owner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${articleInstance?.citationAmount}">
				<li class="fieldcontain">
					<span id="citationAmount-label" class="property-label"><g:message code="article.citationAmount.label" default="Citation Amount" /></span>
					
						<span class="property-value" aria-labelledby="citationAmount-label"><g:fieldValue bean="${articleInstance}" field="citationAmount"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${articleInstance?.authors}">
				<li class="fieldcontain">
					<span id="authors-label" class="property-label"><g:message code="article.authors.label" default="Authors" /></span>
					
						<g:each in="${articleInstance.authors}" var="a">
						<span class="property-value" aria-labelledby="authors-label"><g:link controller="author" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></span>
						</g:each>
					
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
