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

<div class="fieldcontain ${hasErrors(bean: articleInstance, field: 'authors', 'error')} ">
	<label for="authors">
		<g:message code="article.authors.label" default="Authors" />
		
	</label>
	

</div>

