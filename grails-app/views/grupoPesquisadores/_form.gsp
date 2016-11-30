<%@ page import="rpa.GrupoPesquisadores" %>
<%@ page import="rpa.Pesquisador" %>
<div class="fieldcontain ${hasErrors(bean: grupoPesquisadoresInstance, field: 'nomeGrupo', 'error')} required">
    <label for="nomeGrupo">
        <g:message code="rpa.pesquisadores.label" default="Nome do Grupo:" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="nomeGrupo" required="" value="${grupoPesquisadoresInstance?.nomeGrupo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: grupoPesquisadoresInstance, field: 'pesquisadores', 'error')}">

   <g:select name="pesquisador" from="${Pesquisador.list()}" optionValue="nome"  optionKey="cpf" multiple="multiple" class="many-to-many"></g:select>
    <input type="button" value=">>" class="button" onclick="myField.setAttribute(value,pesquisador.selected())"/>
   <g:textArea name="myField" value="${PesquisadoresInstanceList}"></g:textArea>

</div>





