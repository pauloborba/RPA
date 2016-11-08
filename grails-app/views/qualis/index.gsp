
<%@ page import="rpa.Qualis" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>Qualis</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}">Início</a></li>
				<li><g:link class="create" controller="qualis" action="create">Novo Qualis</g:link></li>
			</ul>
		</div>
		<div id="list-qualis" class="content scaffold-list" role="main">
			<h1>Qualis existentes</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="title" title="Título" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${qualises}" status="i" var="qualisInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link controller="qualis" action="show" id="${qualisInstance.id}">${fieldValue(bean: qualisInstance, field: "title")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${qualisesCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
