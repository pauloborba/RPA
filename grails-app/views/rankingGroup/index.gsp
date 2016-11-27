<!DOCTYPE html>
<html xmlns:g="http://www.w3.org/1999/xhtml">

<head>
</head>

<body>

<g:if test="${groups.size > 0}">
        <p>groups</p>
        %{--<g:each in="${groups}" >--}%
            %{--<g:radio name="groupName" value="${it.name}"/> ${it.name}--}%
            %{--<br>--}%
        %{--</g:each>--}%
        <g:radioGroup name="${groupName}"
                      labels="${groups}"
                      values="${groups}">
            <p>${it.radio} ${it.label}</p>
        </g:radioGroup>
        <g:link action="show" params="[groupName: groupName]" >
            <input type="button" value="Apresentar">
            </input>
        </g:link>
</g:if>
<g:else>
    <p> Nao ha grupos existentes</p>
</g:else>


</body>
</html>