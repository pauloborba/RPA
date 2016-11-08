<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 25/10/2016
  Time: 08:04
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'default.Researcher.label', default: 'Researcher')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
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
                <g:sortableColumn property="nome" title="${message(code: 'default.name.label', default: 'Name')}" />


            </tr>
            </thead>
            <tbody>
            <g:each in="${PesquisadorInstanceList}" status="i" var="PesquisadorInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show" id="${PesquisadorInstance.id}">${fieldValue(bean: PesquisadorInstance, field: "nome")}</g:link></td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>
    <div class="paginateButtons">
        <g:paginate total="${PesquisadorInstanceTotal}" />
    </div>
</div>
</body>
</html>