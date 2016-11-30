<%@ page import="rpa.QualisEvaluation" %>



<div class="fieldcontain ${hasErrors(bean: qualisAvaliationInstance, field: 'journal', 'error')} required">
	<label for="journal">
		<g:message code="qualisAvaliation.journal.label" default="Journal" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="journal" required="" value="${qualisAvaliationInstance?.journal}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: qualisAvaliationInstance, field: 'avaliation', 'error')} required">
	<label for="avaliation">
		<g:message code="qualisAvaliation.avaliation.label" default="Avaliation" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="avaliation" required="" value="${qualisAvaliationInstance?.avaliation}"/>

</div>

