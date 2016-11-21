<%@ page import="rpa.Qualis" %>



<div class="fieldcontain ${hasErrors(bean: qualisInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="qualis.description.label" default="Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${qualisInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: qualisInstance, field: 'avaliations', 'error')} ">
	<label for="avaliations">
		<g:message code="qualis.avaliations.label" default="Avaliations" />
		
	</label>
	<g:select name="avaliations" from="${rpa.QualisEvaluation.list()}" multiple="multiple" optionKey="id" size="5" value="${qualisInstance?.avaliations*.id}" class="many-to-many"/>

</div>

