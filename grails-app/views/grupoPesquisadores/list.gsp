<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 23/10/2016
  Time: 02:18
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'default.ResearcherGroup.label', default: 'Researcher Group')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
    <span class="menuButton"><g:link class="compare" action="compare"><g:message code="default.button.compareGroups.label" default= 'Compare Researcher Groups'/></g:link></span>
</div>
<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="nomeGrupo" title="${message(code: 'rpa.nomeGrupo.label', default: 'NomeGrupo')}" />
                <g:sortableColumn property="tamanhoGrupo" title="${message(code: 'rpa.nomeGrupo.label', default: 'Numero de Pesquisadores')}" />

            </tr>
            </thead>
            <tbody>
            <g:each in="${grupoPesquisadoresInstanceList}" status="i" var="grupoPesquisadoresInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show" id="${grupoPesquisadoresInstance.id}">${fieldValue(bean: grupoPesquisadoresInstance, field: "nomeGrupo")}</g:link></td>
                    <td>${fieldValue(bean: grupoPesquisadoresInstance, field: "tamanhoGrupo")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${grupoPesquisadoresInstanceTotal}" />
    </div>
</div>
</body>
</html>