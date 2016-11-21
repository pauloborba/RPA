<!DOCTYPE html>
<html xmlns:g="http://www.w3.org/1999/xhtml">

<head>
</head>

<body>

<g:if env="${groups}.size() > 0">
    <g:form>
        <p>groups</p>
        <g:each in="${groups}" v-ar="group" >
            <g:radio name="groupName" value="${group.name}" /> ${group.name} <br/>
        </g:each>
        <g:button params="[groupName: groupName]" action="show">
            Apresentar
        </g:button>
    </g:form>
</g:if>
<g:else>
    <p> Nao ha grupos existentes</p>
</g:else>

<!--<g:render template="show" model="[groupName: groupName]"/>-->

</body>
</html>