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
 <%-- <g:select name="pesquisador" from="${PesquisadoresInstanceList}" optionKey="cpf" optionValue="nome" multiple="multiple" value="${grupoPesquisadoresInstance?.pesquisadores}"></g:select>

    <g:each in="${Pesquisador.list()}" var="pesquisador" status="i">
        <div class="sub">
        <g:checkBox name="pesquisadores" value="${grupoPesquisadoresInstance?.pesquisadores}"  />
        <label for="pesquisadores">${pesquisador.nome}</label>
        </div>
    </g:each>
    --%>
</div>





