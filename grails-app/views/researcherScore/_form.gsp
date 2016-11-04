<%@ page import="rpa.ResearcherScore" %>



<div class="fieldcontain ${hasErrors(bean: researcherScoreInstance, field: 'researcher', 'error')} required">
	<label for="researcher">
		<g:message code="researcherScore.researcher.label" default="Researcher" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="researcher" name="researcher.id" from="${rpa.Researcher.list()}" optionKey="id" required="" value="${researcherScoreInstance?.researcher?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: researcherScoreInstance, field: 'qualis', 'error')} required">
	<label for="qualis">
		<g:message code="researcherScore.qualis.label" default="Qualis" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="qualis" name="qualis.id" from="${rpa.Qualis.list()}" optionKey="id" required="" value="${researcherScoreInstance?.qualis?.id}" class="many-to-one"/>

</div>

