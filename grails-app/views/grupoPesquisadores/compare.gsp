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
    <title>${message(code: 'default.button.compare.label', default: 'Compare')} ${message(code: 'default.ResearcherGroup.label', default: 'Researcher Group')}</title>

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
    <h1>${message(code: 'default.button.compare.label', default: 'Compare')} ${message(code: 'default.ResearcherGroup.label', default: 'Researcher Group')} </h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
</div>
<div class="selecionar">
    <g:form action="resultCompare">
        <g:select name="grupoSelecionado1" from="${GrupoPesquisadores.list()}" optionValue="nomeGrupo" optionKey="nomeGrupo" class="many-to-many"></g:select>
        <g:select name="grupoSelecionado2" from="${GrupoPesquisadores.list()}" optionValue="nomeGrupo" optionKey="nomeGrupo" class="many-to-many"></g:select>
        <g:textField name="qualis" required="" />

        <g:submitButton name="resultCompare" class="resultCompare" value="${message(code: 'default.button.compare.label', default: 'Compare')}" />
    </g:form>
</div>
</body>
</html>