<%@ page import="rpa.ResearchGroup" %>



<div class="fieldcontain ${hasErrors(bean: researchGroupInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="researchGroup.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${researchGroupInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: researchGroupInstance, field: 'researchers', 'error')} ">
	<label for="researchers">
		<g:message code="researchGroup.researchers.label" default="Researchers" />
		
	</label>
	<g:select name="researchers" from="${rpa.Researcher.list()}" multiple="multiple" optionKey="id" size="5" value="${researchGroupInstance?.researchers*.id}" class="many-to-many"/>

</div>

