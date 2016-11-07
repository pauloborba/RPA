
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'researcher.label', default: 'Pesquisador')}" />
    <title><g:message code="default.home.label" default="Principal"/></title>
</head>

<body>
    <a href="#index" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Principal"/></a></li>
            <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" default="Pesquisador Listagem"/></g:link></li>
            <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" default="Criar Pesquisador"/></g:link></li>
        </ul>
    </div>
</body>
</html>