<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 23/10/2016
  Time: 02:26
--%>
<%@ page import="rpa.GrupoPesquisadores" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'rpa.label', default: 'GrupoPesquisadores')}"/>
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
                <td valign="top" class="name"><g:message code="rpa.nomeGrupo.label" default="Name"/></td>
                <td valign="top" class="value">${fieldValue(bean: grupoPesquisadoresInstance, field: "nomeGrupo")}</td>

            </tr>


            <tr class="prop">
                <td valign="top" class="name"><g:message code="researcher.label" default="Researcher"/></td>

                <td valign="top" style="text-align: left;" class="value">
                    <ul>
                        <g:if test="${grupoPesquisadoresInstance.pesquisadores!= null}">
                        <g:each in="${grupoPesquisadoresInstance.pesquisadores}" var="r">
                            <li><g:link controller="pesquisador" action="show"
                                        id="${r.id}">${fieldValue(bean: r, field: "nome")}</g:link></li>
                        </g:each>
                        </g:if>
                    </ul>
                </td>

            </tr>


            </tbody>
        </table>
    </div>

    <div class="notas">
        <table>
            <thead>
            <tr>
                <th>Criterios</th>
                <th>2010</th>
                <th>2011</th>
                <th>2012</th>
                <th>2013</th>
                <th>2014</th>
                <th>2015</th>

            </tr>
            </thead>
            <tbody>
            <g:set var="i" value="${0}"/>
            <g:each var="crit" in="['A1','A2','B1','B2','B3','B4','B5','C']">
                <tr class="dados">
                    <td> ${crit}</td>
                    <td> ${grupoPesquisadoresInstance.getGroupScore(crit,"2010",grupoPesquisadoresInstance.getNomeGrupo())}</td>
                    <td> ${grupoPesquisadoresInstance.getGroupScore(crit,"2011",grupoPesquisadoresInstance.getNomeGrupo())}</td>
                    <td> ${grupoPesquisadoresInstance.getGroupScore(crit,"2012",grupoPesquisadoresInstance.getNomeGrupo())}</td>
                    <td> ${grupoPesquisadoresInstance.getGroupScore(crit,"2013",grupoPesquisadoresInstance.getNomeGrupo())}</td>
                    <td> ${grupoPesquisadoresInstance.getGroupScore(crit,"2014",grupoPesquisadoresInstance.getNomeGrupo())}</td>
                    <td> ${grupoPesquisadoresInstance.getGroupScore(crit,"2015",grupoPesquisadoresInstance.getNomeGrupo())}</td>
                </tr>
            </g:each>
            </tbody>
        </table>

    </div>

</div>
</body>



</html>