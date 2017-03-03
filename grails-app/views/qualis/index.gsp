
<%@ page import="rpa.Qualis" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>
            <g:message code="default.list.label" args="['Qualis']" default="Qualis Listagem"/>
		</title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Príncipal"/></a></li>
				<li>
                    <g:link class="create" controller="qualis" action="create">
                        <g:message code="default.add.label" args="['Qualis']" default="Criar Qualis"/>
                    </g:link>
                </li>
			</ul>
		</div>
		<div id="list-qualis" class="content scaffold-list" role="main">
			<h1>
                <g:message code="default.list.label" args="['Qualis']" default="Qualis Listagem" />
            </h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">
                    <g:message code="${flash.message}" args="${flash.args}" default="${flash.default}"/>
                </div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="title" title="${message(code:'qualis.title.label', default:'Título')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${qualises}" status="i" var="qualisInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>
                            <g:link controller="qualis" action="show" id="${qualisInstance.id}">
                                ${fieldValue(bean: qualisInstance, field: "title")}
                            </g:link>
                        </td>
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
