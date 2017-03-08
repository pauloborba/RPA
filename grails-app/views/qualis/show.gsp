
<%@ page import="rpa.Qualis" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>
			<g:message code="default.show.label" args="[qualis.title]" default="Ver ${qualis.title}"/>
		</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Príncipal"/></a></li>
				<li>
                    <g:link class="list" action="index">
                        <g:message code="default.list.label" args="['Qualis']" default="Qualis Listagem"/>
                    </g:link>
                </li>
				<li>
                    <g:link class="create" action="create">
                        <g:message code="default.add.label" args="['Qualis']" default="Criar Qualis"/>
                    </g:link>
                </li>
			</ul>
		</div>
		<div id="show-qualis" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[qualis.title]" default="Ver ${qualis.title}"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">
                <g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"/>
            </div>
			</g:if>
			<ol class="property-list qualis">
				<g:if test="${qualis?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="qualis.title.label" default="Título"/></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${qualis}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${qualis?.qualisAvaliations}">
				<li class="fieldcontain">
					<span id="qualisAvaliations-label" class="property-label"><g:message code="qualis.avaliations.label" default="Avaliações"/></span>
					
						<g:each in="${qualis.qualisAvaliations}" var="q">
						<span class="property-value" aria-labelledby="qualisAvaliations-label"><g:link class="qualis-avaliation-link" controller="qualisAvaliation" action="show" id="${q.id}">${q?.journal}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:qualis, action:'delete']" method="POST">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${qualis}">
                        <g:message code="default.button.edit.label"/>
                    </g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Deletar')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Tem certeza?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
