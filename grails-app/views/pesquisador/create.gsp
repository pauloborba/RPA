<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 25/10/2016
  Time: 08:05
--%>

<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="rpa.Pesquisador" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.researcher.label', default: 'Pesquisadores')}" />
    <title>${message(code: 'default.button.create.label', default: 'Create')} ${message(code: 'default.researcher.label', default: 'Researcher')}</title>
</head>
<body>
<a href="#create-residueGenerator" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="create-residueGenerator" class="content scaffold-create" role="main">
    <h1>${message(code: 'default.button.create.label', default: 'Create')} ${message(code: 'default.researcher.label', default: 'Researcher')} </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${PesquisadorInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${PesquisadorInstance}" var="error">
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