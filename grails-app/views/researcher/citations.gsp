<%--
  Created by IntelliJ IDEA.
  User: rbb3
  Date: 06/11/16
  Time: 06:34
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'researcher.label', default: 'Researcher')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>
    <a href="#show-citations" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <ul>
            <li><g:link url="${request.getHeader('referer')}"><g:message code="Voltar" args="[entityName]" /></g:link></li>
        </ul>
    </div>
    <div id="show-citations" class="content scaffold-show" role="main">
        <h1><g:message code="Ver Citações" args="[entityName]" /></h1>
        <ol class="property-list citations">
            <li class="fieldcontain">
            <span id="Researcher-label" class="property-label"><g:message code="Pesquisador" /></span>
                <span class="property-value" aria-labelledby="name-label">
                    <g:select onchange="${remoteFunction(action:'selRes', controller:'researcher', params:'\'ResearcherId=\' + this.value' )}" style="width:250px;font-size: 14px" name="researchers" from="${rpa.Researcher.list()}" multiple="true" optionKey="id" size="6" value="${researcherInstance.id}" optionValue="${{it.name}}" class="many-to-many"/>
                </span>
            </li>
            <li class="fieldcontain">
            <span id="Articles-label" class="property-label"><g:message code="Artigo" /></span>
                <span class="property-value" aria-labelledby="name-label">
                    <g:select onchange="${remoteFunction(action:'selArt', controller:'article', params:'\'ArticleId=\' + this.value' )}" style="width:250px;font-size: 14px" name="articles" from="${rpa.Article.list()}" multiple="true" optionKey="id" size="6" value="${researcherInstance.articles*.id}" optionValue="${{it.tittle}}" class="many-to-many"/>
                </span>
            </li>
            <li class="fieldcontain">
                <g:link action="findCit"><g:submitButton name="buscar" value="Buscar Citações" /></g:link>
            </li>
        </ol>
    </div>


</body>

</html>