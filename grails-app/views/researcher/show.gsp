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
				<span id="name-value" class="property-value"><g:fieldValue bean="${researcherInstance}" field="name"/></span>
			</li>
		</g:if>
		<g:if test="${researcherInstance?.cpf}">
			<li class="fieldcontain">
				<span id="cpf-label" class="property-label"><g:message code="researcher.cpf.label" default="Cpf" /></span>
				<span id="cpf-value" class="property-value"><g:fieldValue bean="${researcherInstance}" field="cpf"/></span>
			</li>
		</g:if>
		<g:if test="${researcherInstance?.articles && researcherInstance.articles.size() >= 1}">
			<li class="fieldcontain "><span class="property-label"><g:message code="articles.label" default="Artigos"/></span><br/>
				<g:each status="i" var="it" in="${researcherInstance?.articles}">
					<ol class="list articles">
						<li class="fieldcontain" >
							<span id="title-label${i}" class="property-label"><g:message code="article.title.label" default="Titulo" /></span>
							<span id="title-value${i}" class="title property-value" ><g:fieldValue bean="${it}" field="tittle"/></span>
							<span id="journal-label${i}" class="property-label"><g:message code="article.journal.label" default="Periodico" /></span>
							<span id="journal-value${i}" class="journal property-value" ><g:fieldValue bean="${it}" field="journal"/></span>
							<span id="issn-label${i}" class="property-label"><g:message code="article.issn.label" default="Issn" /></span>
							<span id="issn-value${i}" class="issn property-value" ><g:fieldValue bean="${it}" field="issn"/></span>
						</li>
					</ol>
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