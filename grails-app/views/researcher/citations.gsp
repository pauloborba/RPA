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
            <g:form>
                <fieldset class="form">
                    <div class="fieldcontain">
                        <label for="researcher">
                            <g:message code="Researcher" default="Researcher" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="researcher" id="123" required="" value="${researcher ?: ""}"/>
                    </div>

                    <div class="fieldcontain">
                        <label for="article">
                            <g:message code="Article" default="Article" />
                            <span class="required-indicator">*</span>
                        </label>
                        <g:textField name="article" id="321" required="" value="${article ?: ""}"/>
                    </div>

                    <div class="fieldcontain">
                        <label for="results">
                            <g:message code="Citations" default="Citations" />
                        </label>
                        <g:textField name="citations" id="111" value="${citationsCount ?: ""}"/>
                    </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:actionSubmit action="findCitations" value="Buscar Citações" />
                </fieldset>
            </g:form>
        </ol>
    </div>
    %{--<script type="text/javascript">--}%

    %{--function getSelectedItem() {--}%
        %{--var pesq = document.getElementById("123").value;--}%
        %{--alert(pesq);--}%
        %{--var artc = document.getElementById("321").value;--}%
        %{--alert(artc);--}%
        %{--var bla = ${remoteFunction(controller: 'researcher', action: 'findCitations', params: '\'res=\'pesq+\'&art=\'+artc')}--}%
        %{--document.getElementById("111").value = bla.toString();--}%
    %{--}--}%

    %{--</script>--}%


</body>

</html>