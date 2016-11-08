<%@ page import="rpa.Pesquisador" %>

<div class="fieldcontain ${hasErrors(bean:PesquisadorInstance, field: 'nome', 'error')} required">
    <label for="nome">
        <g:message code="rpa.pesquisadores.label" default="Nome:" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="nome" required="" value="${PesquisadorInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean:PesquisadorInstance, field: 'cpf', 'error')} required">
    <label for="cpf">
        <g:message code="rpa.pesquisadores.label" default="CPF:" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="cpf"  required="" value="${PesquisadorInstance?.cpf}"/>
</div>



