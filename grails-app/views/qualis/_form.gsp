<%@ page import="rpa.Qualis" %>

<div class="fieldcontain ${hasErrors(bean: qualis, field: 'title', 'error')} required">
    <label for="title">
        <g:message code="qualis.title.label" default="Título"/>
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="title" required="" value="${qualis?.title}"/>
</div>

<div class="fieldcontain">
    <label for="qualis-sheet">
        <g:message code="qualis.qualis-sheet.label" default="Planilha"/>
        <span class="required-indicator">*</span>
    </label>
    <input type="file" id="qualis-sheet" name="qualis-sheet" />
</div>