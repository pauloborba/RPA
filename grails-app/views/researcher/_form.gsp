<%@ page import="rpa.Researcher" %>



<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="researcher.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${researcherInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'cpf', 'error')} required">
	<label for="cpf">
		<g:message code="researcher.cpf.label" default="Cpf" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="cpf" maxlength="11" required="" value="${researcherInstance?.cpf}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'articles', 'error')} ">
	<label for="articles">
		<g:message code="researcher.articles.label" default="Articles" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${researcherInstance?.articles?}" var="a">
    <li><g:link controller="article" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="article" action="create" params="['researcher.id': researcherInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'article.label', default: 'Article')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'updates', 'error')} ">
	<label for="updates">
		<g:message code="researcher.updates.label" default="Updates" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${researcherInstance?.updates?}" var="u">
    <li><g:link controller="updateLattes" action="show" id="${u.id}">${u?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="updateLattes" action="create" params="['researcher.id': researcherInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'updateLattes.label', default: 'UpdateLattes')])}</g:link>
</li>
</ul>


</div>

