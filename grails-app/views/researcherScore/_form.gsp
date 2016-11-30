<%@ page import="rpa.ResearcherScore" %>



<div class="fieldcontain ${hasErrors(bean: researcherScoreInstance, field: 'researcher', 'error')} required">
	<label for="researcher">
		<g:message code="researcherScore.researcher.label" default="Researcher" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="researcher" name="researcher" from="${rpa.Researcher.list()}" optionKey="id" required="" value="${researcherScoreInstance?.researcher?.name}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: researcherScoreInstance, field: 'qualis', 'error')} required">
	<label for="qualis">
		<g:message code="researcherScore.qualis.label" default="Qualis" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="qualis" name="qualis" from="${rpa.Qualis.list()}" optionKey="id" required="" value="${researcherScoreInstance?.qualis?.description}" class="many-to-one"/>

</div>

