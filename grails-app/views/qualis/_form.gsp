<%@ page import="rpa.Qualis" %>



<div class="fieldcontain ${hasErrors(bean: qualisInstance, field: 'year', 'error')} required">
	<label for="year">
		<g:message code="qualis.year.label" default="Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="year" required="" value="${qualisInstance?.year}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: qualisInstance, field: 'avaliations', 'error')} ">
	<label for="avaliations">
		<g:message code="qualis.avaliations.label" default="Avaliations" />
		
	</label>
	<g:select name="avaliations" from="${rpa.QualisAvaliation.list()}" multiple="multiple" optionKey="id" size="5" value="${qualisInstance?.avaliations*.id}" class="many-to-many"/>

</div>

