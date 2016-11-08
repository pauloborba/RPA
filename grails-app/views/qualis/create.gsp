<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Criar qualis</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">Início</a></li>
				<li><g:link class="list" controller="qualis" action="index">Lista</g:link></li>
			</ul>
		</div>
		<div id="create-qualis" class="content scaffold-create" role="main">
			<h1>Adicionar qualis</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${qualis}">
			<ul class="errors" role="alert">
				<g:eachError bean="${qualis}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>>
                    <g:message error="${error}"/>
                </li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[resource:qualis, action:'save']" method="POST" enctype="multipart/form-data">
				<fieldset class="form">
                    <g:render template="form" model="[qualis: qualis]"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${'Criar'}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
