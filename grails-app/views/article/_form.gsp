<%@ page import="rpa.Article" %>



<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="article.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${articleInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'journal', 'error')} required">
	<label for="journal">
		<g:message code="article.journal.label" default="Journal" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="journal" required="" value="${articleInstance?.journal}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'issn', 'error')} required">
	<label for="issn">
		<g:message code="article.issn.label" default="Issn" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="issn" required="" value="${articleInstance?.issn}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'owner', 'error')} ">
	<label for="owner">
		<g:message code="article.owner.label" default="Owner" />
		
	</label>
	<g:select id="owner" name="owner.id" from="${rpa.Researcher.list()}" optionKey="id" value="${articleInstance?.owner?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'authors', 'error')} ">
	<label for="authors">
		<g:message code="article.authors.label" default="Authors" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${articleInstance?.authors?}" var="a">
    <li><g:link controller="author" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="author" action="create" params="['article.id': articleInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'author.label', default: 'Author')])}</g:link>
</li>
</ul>


</div>

