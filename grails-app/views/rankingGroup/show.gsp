<!DOCTYPE html>
<html>

<head>
</head>

<body>

<g:if test="${error == ''}">
    <g:each in="${rank}" var="pq" >
        <td>${pq.name}: ${pq.grade} </td>
    </g:each>
</g:if>
<g:else>
    <p class="error"> Erro ao gerar rank do grupo.</p>
    <p> ${error} </p>
    <br/>
</g:else>


</body>
</html>