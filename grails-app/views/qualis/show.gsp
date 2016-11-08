
<%@ page import="rpa.Qualis" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Qualis ${qualis.title}</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">Início</a></li>
				<li><g:link class="list" action="index">Lista</g:link></li>
				<li><g:link class="create" action="create">Novo qualis</g:link></li>
			</ul>
		</div>
		<div id="show-qualis" class="content scaffold-show" role="main">
			<h1>Qualis</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list qualis">
			
				<g:if test="${qualis?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label">Título</span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${qualis}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${qualis?.qualisAvaliations}">
				<li class="fieldcontain">
					<span id="qualisAvaliations-label" class="property-label">Avaliações</span>
					
						<g:each in="${qualis.qualisAvaliations}" var="q">
						<span class="property-value" aria-labelledby="qualisAvaliations-label"><g:link class="qualis-avaliation-link" controller="qualisAvaliation" action="show" id="${q.id}">${q?.journal}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:qualis, action:'delete']" method="POST">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${qualis}">Editar</g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Deletar')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Deseja realmente deletar esse qualis?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
