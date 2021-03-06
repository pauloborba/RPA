<%@ page import="rpa.UpdateType" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'researcher.label', default: 'Pesquisador')}" />
    <title><g:message code="default.show.label" args="[entityName]" default="Ver Pesquisador"/></title>
</head>

<body>
    <a href="#show-reseacher" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <ul>
            <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Principal"/></a></li>
            <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" default="Pesquisador Listagem"/></g:link></li>
            <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" default="Criar Pesquisador"/></g:link></li>
        </ul>
    </div>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        <g:if test="${lastUpdates?.size()>0}">
            <ul class="message lastUpdates" style="list-style-type: none">
                <g:each status="i" var="it" in="${lastUpdates}">
                    <g:if test="${it.typeUpdate == UpdateType.ADD_ARTICLE}">
                        <li><span class="lastUpdate"><g:message code="updateLattes.added" args="${[it.attribute]}" default="O artigo ${it.attribute} foi adicionado"/></span></li>
                    </g:if>
                    <g:if test="${it.typeUpdate == UpdateType.REMOVE_ARTICLE}">
                        <li><span class="lastUpdate"><g:message code="updateLattes.removed" args="${[it.attribute]}" default="O artigo ${it.attribute} foi removido"/></span></li>
                    </g:if>
                </g:each>
            </ul>
        </g:if>

    </g:if>
    <div id="show-researcher" class="content scaffold-show" role="main">
        <ol class="property-list researcher">
            <g:if test="${researcherInstance?.name}">
                <li class="fieldcontain">
                    <span id="name-label" class="property-label"><g:message code="researcher.name.label" default="Name" /></span>
                    <span id="name-value" class="property-value"><g:fieldValue bean="${researcherInstance}" field="name"/></span>
                </li>
            </g:if>
            <g:if test="${researcherInstance?.cpf}">
                <li class="fieldcontain">
                    <span id="cpf-label" class="property-label"><g:message code="researcher.cpf.label" default="Cpf" /></span>
                    <span id="cpf-value" class="property-value"><g:fieldValue bean="${researcherInstance}" field="cpf"/></span>
                </li>
            </g:if>
            <g:if test="${researcherInstance?.articles && researcherInstance.articles.size() >= 1}">
                <li class="fieldcontain "><span class="property-label"><g:message code="articles.label" default="Artigos"/></span><br/>
                    <g:each status="i" var="it" in="${researcherInstance?.articles}">
                         <ol class="list articles">
                            <li class="fieldcontain" >
                                <span id="title-label${i}" class="property-label"><g:message code="article.title.label" default="Titulo" /></span>
                                <span id="title-value${i}" class="title property-value" ><g:fieldValue bean="${it}" field="title"/></span>
                                <span id="journal-label${i}" class="property-label"><g:message code="article.journal.label" default="Periodico" /></span>
                                <span id="journal-value${i}" class="journal property-value" ><g:fieldValue bean="${it}" field="journal"/></span>
                                <span id="issn-label${i}" class="property-label"><g:message code="article.issn.label" default="Issn" /></span>
                                <span id="issn-value${i}" class="issn property-value" ><g:fieldValue bean="${it}" field="issn"/></span>
                                <span id="authors-label${i}" class="property-label"><g:message code="article.authors.label" default="Autores"/></span>
                                <g:each var="author" in="${it.authors}">
                                    <span class="author property-value">${author.name}</span>
                                </g:each>
                            </li>
                        </ol>
                    </g:each>
                </li>
            </g:if>
            <g:if test="${researcherInstance?.updates && researcherInstance.updates.size() >= 1}">
                <li class="fieldcontain"><span class="property-label"><g:message code="updatesLattes.label" default="Atualizações"/></span><br/>
                    <ol class="list updatesLattes">
                        <g:each status="i" var="it" in="${researcherInstance?.updates}">
                            <li class="fieldcontain">
                                <g:if test="${it.typeUpdate == UpdateType.ADD_ARTICLE}">
                                    <span id="updateLattes-value${i}" class="updateLattes property-value" ><g:message code="updateLattes.added" args="${[it.attribute]}" default="O artigo ${it.attribute} foi adicionado"/></span>
                                </g:if>
                                <g:if test="${it.typeUpdate == UpdateType.REMOVE_ARTICLE}">
                                    <span id="updateLattes-value${i}" class="updateLattes property-value" ><g:message code="updateLattes.removed" args="${[it.attribute]}" default="O artigo ${it.attribute} foi removido"/></span>
                                </g:if>
                            </li>
                        </g:each>
                    </ol>
                </li>
            </g:if>
        </ol>
        </div>
    </div>
</body>
</html>