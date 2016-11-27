<%--
  Created by IntelliJ IDEA.
  User: ajgan
  Date: 07/11/16
  Time: 14:55
--%>
import rpa.Researcher
<%@ page import="rpa.Researcher" contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.researcher.label', default: 'Researcher')}" />
    <title>Remove Screen</title>
</head>

<body>

    <div class="nav">
        <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </span>
        <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                               args="[entityName]"/></g:link></span>
        <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                                   args="[entityName]"/></g:link></span>
    </div>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:form  action="remove" >
    <g:textField name="typed" ></g:textField>
   <div class="lista">
    <g:select name="ResearchersSelector" from="${rpa.Researcher.list()}" optionValue = "cpf" optionKey="cpf" multiple = "multiple" ></g:select>
   </div>


      <fieldset class="buttons">
          <g:submitButton name="ResearcherRemove" class="remove" value="${message(code: 'default.button.Delete.label', default: 'Remove') }" />
      </fieldset>
    </g:form>
</body>
</html>

