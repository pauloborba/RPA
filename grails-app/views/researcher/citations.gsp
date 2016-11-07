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
                    <g:textField name="researcher" id="123" />
                </span>
            </li>
            <li class="fieldcontain">
            <span id="Articles-label" class="property-label"><g:message code="Artigo" /></span>
                <span class="property-value" aria-labelledby="name-label">
                    <g:textField name="article" id="321" />
                </span>
            </li>
            <li class="fieldcontain">
                <g:submitButton name="buscar" value="Buscar Citações" onclick="getSelectedItem();" />
            </li>
            <li class="fieldcontain">
                <g:textField name="result" id="111" />
            </li>
        </ol>
    </div>
    <script type="text/javascript">

    function getSelectedItem() {
        var pesq = document.getElementById("123").value;
        var artc = document.getElementById("321").value;
        var bla = ${remoteFunction(controller: 'researcher', action: 'findCitations', params: '\'res=\'pesq+\'&art=\'+artc')}
        document.getElementById("111").value = bla.toString();
    }

    </script>


</body>

</html>