<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 22/10/2016
  Time: 12:08
--%>
<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="rpa.GrupoPesquisadores" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.ResearcherGroup.label', default: 'Researcher Group')}" />
    <title>${message(code: 'default.button.create.label', default: 'Create')} ${message(code: 'default.ResearcherGroup.label', default: 'Researcher Group')}</title>
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
    <h1>Criar Novo Grupo De Pesquisadores </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${grupoPesquisadoresInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${grupoPesquisadoresInstance}" var="error">
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
        <g:if test="${flash.message}">
            <div class="message" style="display: block">${flash.message}</div>
        </g:if>
    </g:form>
</div>
</body>
</html>