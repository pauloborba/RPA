<%--
  Created by IntelliJ IDEA.
  User: rlsma
  Date: 06/11/16
  Time: 13:40
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:set var="entityName" value="${message(code: 'researcher.label', default: 'Pesquisador')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>
<div id="show-reseacher" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="list researcher">
        <g:if test="${researcherInstance?.name}">
            <li class="fieldcontain">
                <span id="name-label"><g:message code="researcher.name.label" default="Name" /></span>
                <span id="name-value"><g:fieldValue bean="${researcherInstance}" field="name"/></span>
            </li>
        </g:if>
        <g:if test="${researcherInstance?.cpf}">
            <li class="fieldcontain">
                <span id="cpf-label"><g:message code="researcher.cpf.label" default="Cpf" /></span>
                <span id="cpf-value" ><g:fieldValue bean="${researcherInstance}" field="cpf"/></span>
            </li>
        </g:if>
        <g:if test="${researcherInstance?.articles && researcherInstance.articles.size() >= 1}">
            <li class="fieldcontain"><g:message code="articles.label" default="Artigos"/><br/>
                <g:each status="i" var="it" in="${researcherInstance?.articles}">
                    <span id="article-label" class="property-label"><g:message code="article.label" default="Artigo"/>${" "}${(i+1)}</span>
                    <ol class="list articles">
                        <li>
                            <span id="title-label${i}"><g:message code="article.title.label" default="Titulo" /></span>
                            <span id="title-value${i}" class="title" ><g:fieldValue bean="${it}" field="tittle"/></span>
                        </li>
                        <li>
                            <span id="journal-label${i}"><g:message code="article.journal.label" default="Titulo" /></span>
                            <span id="journal-value${i}" class="journal" ><g:fieldValue bean="${it}" field="journal"/></span>
                        </li>
                        <li>
                            <span id="issn-label${i}"><g:message code="article.issn.label" default="Titulo" /></span>
                            <span id="issn-value${i}" class="issn" ><g:fieldValue bean="${it}" field="issn"/></span>
                        </li>
                    </ol>
                </g:each>
            </li>
        </g:if>
        <g:if test="${researcherInstance?.diffs && researcherInstance.diffs.size() >= 1}">
            <li class="fieldcontain"><g:message code="diffs.label" default="Diffs"/><br/>
                <ol>
                    <g:each status="i" var="it" in="${researcherInstance?.diffs}">
                        <li>
                            <g:if test="${it.typeDiff == 1}">
                                <span id="diff-value${i}" class="diff" ><g:message code="diff.added" args="${[it.attributeOld]}" default="O artigo ${it.attributeOld} foi adicionado"/></span>
                            </g:if>
                            <g:if test="${it.typeDiff == 2}">
                                <span id="diff-value${i}" class="diff" ><g:message code="diff.removed" args="${[it.attributeOld]}" default="O artigo ${it.attributeOld} foi removido"/></span>
                            </g:if>
                            <g:if test="${it.typeDiff == 3}">
                                <span id="diff-value${i}" class="diff" ><g:message code="diff.name.updated"  default="O nome do pesquisador foi atualizado"/></span>
                            </g:if>
                        </li>
                    </g:each>
                </ol>
            </li>
        </g:if>
    </ol>
</div>
</body>
</html>