<%--
  Created by IntelliJ IDEA.
  User: Bruno
  Date: 02/11/2016
  Time: 16:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'default.ResearcherGroup.label', default: 'Researcher Group')}" />
    <title>${message(code: 'default.resultCompare.label', default: 'Compare Results')}</title>
</head>
<body>


    <div class="nav">
        <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
        <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        <span class="menuButton"><g:link class="compare" action="compare"><g:message code="default.button.compareGroups.label" default= 'Compare Researcher Groups'/></g:link></span>
    </div>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:else>
        <div class="list1">
            <h1>${message(code: 'default.resultCompare.label', default: 'Compare Results')} ${message(code: 'default.forAbsValues.label', default: 'For Absolute Values')}</h1>
            <table id="absValues" bgcolor='#a9a9a9'>
                <thead>
                <tr>
                    <th>${message(code: 'default.criterion.label', default: 'Criterion')}</th>
                    <th>${grupoPesquisadoresInstance1.getNomeGrupo()}</th>
                    <th>${grupoPesquisadoresInstance2.getNomeGrupo()}</th>
                </tr>
                </thead>
                <tbody>
                 <g:each var="crit" in="['A1','A2','B1','B2','B3','B4','B5','C']">
                    <tr class="dados">
                        <td class="Criterio"> ${crit}</td>

                        <g:set var="notaG1" value="${grupoPesquisadoresInstance1.getGroupScore(crit,qualis,grupoPesquisadoresInstance1.getNomeGrupo())}"></g:set>
                        <g:set var="notaG2" value="${grupoPesquisadoresInstance2.getGroupScore(crit,qualis,grupoPesquisadoresInstance2.getNomeGrupo())}"></g:set>
                        <g:if test="${(notaG1==notaG2)}">
                            <td class="${'blue'}"> ${grupoPesquisadoresInstance1.getGroupScore(crit,qualis,grupoPesquisadoresInstance1.getNomeGrupo()) }</td>
                            <td class="${'blue'}"> ${grupoPesquisadoresInstance2.getGroupScore(crit,qualis,grupoPesquisadoresInstance2.getNomeGrupo())}</td>
                        </g:if>
                        <g:else>
                            <td class="${(notaG1>notaG2)?'green' : 'red'}"> ${grupoPesquisadoresInstance1.getGroupScore(crit,qualis,grupoPesquisadoresInstance1.getNomeGrupo()) }</td>
                            <td class="${(notaG1>notaG2)?'red' : 'green'}"> ${grupoPesquisadoresInstance2.getGroupScore(crit,qualis,grupoPesquisadoresInstance2.getNomeGrupo())}</td>
                        </g:else>
                    </tr>
                 </g:each>
                </tbody>
            </table>
        </div>


        <div class="list2">
            <h1>${message(code: 'default.ArtAvgRese.label', default: 'Article Average per Researcher')}</h1>
            <table id="avgValues" bgcolor='#a9a9a9'>
                <thead>
                <tr>
                    <th>${message(code: 'default.criterion.label', default: 'Criterion')}</th>
                    <th>${grupoPesquisadoresInstance1.getNomeGrupo()}</th>
                    <th>${grupoPesquisadoresInstance2.getNomeGrupo()}</th>
                </tr>
                </thead>
                <tbody>
                <g:each var="crit" in="['A1','A2','B1','B2','B3','B4','B5','C']">
                    <tr class="dadosMedia">
                        <td class="Criterio"> ${crit}</td>
                        <g:set var="mediaG1" value="${(grupoPesquisadoresInstance1.getGroupScore(crit,qualis,grupoPesquisadoresInstance1.getNomeGrupo()))}"></g:set>
                        <g:set var="mediaG2" value="${(grupoPesquisadoresInstance2.getGroupScore(crit,qualis,grupoPesquisadoresInstance2.getNomeGrupo()))}"></g:set>
                        <g:if test="${(grupoPesquisadoresInstance1.pesquisadores.size())>0}">
                            <g:set var="mediaG1" value="${(grupoPesquisadoresInstance1.getGroupScore(crit,qualis,grupoPesquisadoresInstance1.getNomeGrupo()))/(grupoPesquisadoresInstance1.pesquisadores.size())}"></g:set>
                        </g:if>
                        <g:if test="${(grupoPesquisadoresInstance2.pesquisadores.size())>0}">
                            <g:set var="mediaG2" value="${(grupoPesquisadoresInstance2.getGroupScore(crit,qualis,grupoPesquisadoresInstance2.getNomeGrupo()))/(grupoPesquisadoresInstance2.pesquisadores.size())}"></g:set>
                        </g:if>

                        <g:if test="${(mediaG1==mediaG2)}">

                            <td class="${'blue'}"> ${Math.round((mediaG1 + 0.00001) * 100) / 100}</td>
                            <td class="${'blue'}"> ${Math.round((mediaG2 + 0.00001) * 100) / 100}</td>
                        </g:if>
                        <g:else>
                            <td class="${(mediaG1>mediaG2)?'green' : 'red'}"> ${Math.round((mediaG1 + 0.00001) * 100) / 100}</td>
                            <td class="${(mediaG1>mediaG2)?'red' : 'green'}"> ${Math.round((mediaG2+ 0.00001) * 100) / 100}</td>
                        </g:else>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </g:else>
</body>
</html>