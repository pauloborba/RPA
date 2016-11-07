<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'researcher.label', default: 'Pesquisador')}" />
    <title><g:message code="default.create.label" args="[entityName]" default="Criar Pesquisador"/></title>
</head>
<body>
<h1><g:message code="default.create.label" args="[entityName]" default="Criar Pesquisador"/></h1>
<g:form action="importFile" enctype="multipart/form-data">
    <input class="file" type="file" name="file">
    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
</g:form>
</body>
</html>