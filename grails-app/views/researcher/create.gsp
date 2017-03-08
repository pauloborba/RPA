<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'researcher.label', default: 'Pesquisador')}" />
    <title><g:message code="default.create.label" args="[entityName]" default="Criar Pesquisador"/></title>
</head>
<body>
<a href="#create-researcher" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Principal"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" default="Pesquisador Listagem"/></g:link></li>
    </ul>
</div>
<div id="create-researcher" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]" default="Criar Pesquisador" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form action="importFile" enctype="multipart/form-data" >
        <fieldset class="form">
            <g:render template="form"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>