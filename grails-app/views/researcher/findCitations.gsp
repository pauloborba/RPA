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
    <g:set var="entityName" value="${message(code: 'default.researchercitations.label', default: 'ResearcherCitations')}" />
    <title><g:message code="default.researchercitations.label" args="[entityName]" /></title>
</head>

<body>
    <a href="#show-citations-researcher" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav" role="navigation">
        <ul>
            <li><g:link url="${request.getHeader('referer')}"><g:message code="default.link.back" args="[entityName]" /></g:link></li>
        </ul>
    </div>
    <div id="show-citations-researcher" class="content scaffold-show" role="main">
        <h1><g:message code="default.seeCitations.label" args="[entityName]" /></h1>
        <ol class="property-list citations">
            <g:form>
                <fieldset class="form">
                    <div class="fieldcontain">
                        <label for="researcher">
                            <g:message code="default.researcher.label" default="Researcher" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="researcher" id="123" required="" value="${researcher ?: ""}"/>
                    </div>

                    <div class="fieldcontain">
                        <label for="citations">
                            <g:message code="default.citations.label" default="Citations" />
                        </label>
                        <g:textField name="citations" id="222" value="${citationsCount ?: ""}"/>
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