<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 25/10/2016
  Time: 08:05
--%>
<%@ page import="rpa.Researcher" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'rpa.label', default: 'Researcher')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>
<body>
<div>



    <div class="nav">
        <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </span>
        <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                               args="[entityName]"/></g:link></span>
        <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                                   args="[entityName]"/></g:link></span>
        <span class="menuButton"><g:link class="removeStep1" action="removeStep1"><g:message code="Remove Researcher" /></g:link></span>
    </div>

    <div class="body">
        <h1><g:message code="default.show.label" args="[entityName]"/></h1>
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="rpa.name.label" default="Name"/></td>
                    <td valign="top" class="value">${fieldValue(bean: ResearcherInstance, field: "name")}</td>

                </tr>

                <tr class="prop">
                    <td valign="top" class="name"><g:message code="rpa.cpf.label" default="CPF"/></td>
                    <td valign="top" class="value">${fieldValue(bean: ResearcherInstance, field: "cpf")}</td>

                </tr>


                <tr class="prop">
                    <td valign="top" class="name"><g:message code="rpa.listaPublicacoes.label" default="Artigos"/></td>

                    <td valign="top" style="text-align: left;" class="value">
                        <ul>
                            <g:if test="${ResearcherInstance.articles!= null}">
                                <g:each in="${ResearcherInstance.articles}" var="r">
                                    <li><g:link action="show"
                                                id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
                                </g:each>
                            </g:if>
                        </ul>
                    </td>

                </tr>

                </tbody>
            </table>
        </div>

    </div>
</body>

</html>