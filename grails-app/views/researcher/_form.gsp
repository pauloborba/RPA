<%@ page import="rpa.Researcher" %>

<div class="fieldcontain ${hasErrors(bean:ResearcherInstance, field: 'name', 'error')} required">
    <label for="name">
        <g:message code="default.name.label" default="name:" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="name" required="" value="${ResearcherInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean:ResearcherInstance, field: 'cpf', 'error')} required">
    <label for="cpf">
        <g:message code="default.cpf.label" default="CPF:" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="cpf"  required="" value="${ResearcherInstance?.cpf}"/>
</div>