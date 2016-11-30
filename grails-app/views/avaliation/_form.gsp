<%@ page import="rpa.Avaliation" %>



<div class="fieldcontain ${hasErrors(bean: avaliationInstance, field: 'qualis', 'error')} required">
	<label for="qualis">
		<g:message code="avaliation.qualis.label" default="Qualis" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="qualis" name="qualis" from="${rpa.Qualis.list()}" optionKey="id" required="" value="${avaliationInstance?.qualis?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: avaliationInstance, field: 'researcher', 'error')} required">
	<label for="researcher">
		<g:message code="avaliation.researcher.label" default="Researcher" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="researcher" name="researcher" from="${rpa.Researcher.list()}" optionKey="id" required="" value="${avaliationInstance?.researcher?.id}" class="many-to-one"/>

</div>


