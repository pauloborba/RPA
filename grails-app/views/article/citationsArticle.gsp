<%--
  Created by IntelliJ IDEA.
  User: rbb3
  Date: 29/11/16
  Time: 21:56
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.articlecitations.label', default: 'ArticleCitations')}" />
    <title><g:message code="default.articlecitations.label" args="[entityName]" /></title>
</head>

<body>
<a href="#show-citations-article" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><g:link url="${request.getHeader('referer')}"><g:message code="Voltar" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="show-citations-article" class="content scaffold-show" role="main">
    <h1><g:message code="Ver Citações" args="[entityName]" /></h1>
    <ol class="property-list citations">
        <g:form>
            <fieldset class="form">
                <div class="fieldcontain">
                    <label for="article">
                        <g:message code="Article" default="Article" />
                        <span class="required-indicator">*</span>
                    </label>
                    <g:textField name="article" id="321" required="" value="${article ?: ""}"/>
                </div>

                <div class="fieldcontain">
                    <label for="citationsCount">
                        <g:message code="Citations" default="Citations" />
                    </label>
                    <g:textField name="citations" id="111" value="${citationsCount ?: ""}"/>
                </div>
            </fieldset>
            <fieldset class="buttons">
                <g:actionSubmit name="buscar" action="findCitations" value="Buscar Citações" />
            </fieldset>
        </g:form>
    </ol>
</div>


</body>
</html>