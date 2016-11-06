<%@ page import="rpa.Researcher" %>



<div class="fieldcontain ${hasErrors(bean: researcherInstance, field: 'arquivo', 'error')} required">
    <label for="arquivo">
        <g:message code="researcher.arquivo.label" default="Arquivo" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="arquivo" required="" value="${researcherInstance?.name}"/>

</div>