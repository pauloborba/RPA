
<%@ page import="rpa.Article" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'article.label', default: 'Article')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-article" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
				<li><g:link class="home" action="articleCitations"><g:message code="default.seeCitations.label" args="[entityName]"/></g:link></li>
			</ul>
		</div>
		<div id="list-article" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="title" title="${message(code: 'article.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="journal" title="${message(code: 'article.journal.label', default: 'Journal')}" />
					
						<g:sortableColumn property="issn" title="${message(code: 'article.issn.label', default: 'Issn')}" />
					
						<th><g:message code="article.owner.label" default="Owner" /></th>
					
						<g:sortableColumn property="citationAmount" title="${message(code: 'article.citationAmount.label', default: 'Citation Amount')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${articleInstanceList}" status="i" var="articleInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${articleInstance.id}">${fieldValue(bean: articleInstance, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: articleInstance, field: "journal")}</td>
					
						<td>${fieldValue(bean: articleInstance, field: "issn")}</td>
					
						<td>${fieldValue(bean: articleInstance, field: "owner")}</td>
					
						<td>${fieldValue(bean: articleInstance, field: "citationAmount")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${articleInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
