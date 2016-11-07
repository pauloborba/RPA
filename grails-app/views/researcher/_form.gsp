<%@ page import="rpa.Researcher" %>



<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="Name" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${researcherInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'cpf', 'error')} required">
	<label for="cpf">
		<g:message code="CPF" default="Cpf" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cpf" maxlength="11" required="" value="${researcherInstance?.cpf}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'articles', 'error')} ">
	<label for="articles">
		<g:message code="New Article" default="New article" />
	</label>
	<g:textField name="narticles" required="" value="Insert new article"/>

</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'articles', 'error')} ">
	<label for="articles">
		<g:message code="Articles" default="Articles" />
		
	</label>
	<g:select name="articles" from="${rpa.Article.list()}" multiple="multiple" optionKey="id" size="5" value="${researcherInstance.articles*.id}" optionValue="${{it.tittle}}" class="many-to-many"/>
</div>