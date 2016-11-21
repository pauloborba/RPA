<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="rpa.Researcher" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'Pesquisador.label', default: 'Researcher')}" />
    <title>Create Researcher</title>
</head>
<body>
<a href="#create-residueGenerator" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
        <span class="menuButton"><g:link class="removeStep1" action="removeStep1"><g:message code="Remove Researcher" /></g:link></span>
    </ul>
</div>
<div id="create-residueGenerator" class="content scaffold-create" role="main">
    <h1>Criar Researcher </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${ResearcherInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${ResearcherInstance}" var="error">
                // <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="save" >
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create') }" />
        </fieldset>
    </g:form>
</div>
</body>
</html>