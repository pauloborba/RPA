<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>
            <g:message code="default.add.label" args="['Qualis']" default="Adicionar Qualis"/>
        </title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Príncipal"/></a></li>
				<li>
                    <g:link class="list" controller="qualis" action="index">
                        <g:message code="default.list.label" args="['Qualis']" default="Qualis Listagem"/>
                    </g:link>
                </li>
			</ul>
		</div>
		<div id="create-qualis" class="content scaffold-create" role="main">
			<h1>
                <g:message code="default.add.label" args="['Qualis']" default="Adicionar Qualis"/>
            </h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">
				<g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"/>
			</div>
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
					<g:submitButton name="create" class="save" value="${message(code:'default.button.create.label', default:'Criar')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
