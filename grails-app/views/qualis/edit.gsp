<%@ page import="rpa.Qualis" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Editar ${qualis.title}</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">Início</a></li>
				<li><g:link class="list" action="index">Lista</g:link></li>
				<li><g:link class="create" action="create">Criar qualis</g:link></li>
			</ul>
		</div>
		<div id="edit-qualis" class="content scaffold-edit" role="main">
			<h1>Editar qualis</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="qualis">
			<ul class="errors" role="alert">
				<g:eachError bean="${'qualis'}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[controller: 'qualis', action: 'update']" method="POST" enctype="multipart/form-data">
				<g:hiddenField name="id" value="${qualis?.id}" />
				<fieldset class="form">
					<g:render template="form" model="[qualis: qualis]"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="Atualizar" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
