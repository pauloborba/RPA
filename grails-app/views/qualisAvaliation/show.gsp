
<%@ page import="rpa.QualisAvaliation" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<title>
            <g:message code="default.show.label" args="[qualisAvaliation.journal]" default="Ver ${qualisAvaliation.journal}"/>
        </title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label" default="Príncipal"/></a></li>
                <li>
                    <g:link class="list" action="index">
                        <g:message code="default.list.label" args="['Qualis']" default="Qualis Listagem"/>
                    </g:link>
                </li>
			</ul>
		</div>
		<div id="show-qualisAvaliation" class="content scaffold-show" role="main">
			<h1>
                <g:message code="default.show.label" args="[qualisAvaliation.journal]" default="Ver ${qualisAvaliation.journal}"/>
            </h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list qualisAvaliation">
			
				<g:if test="${qualisAvaliation?.issn}">
				<li class="fieldcontain">
					<span id="issn-label" class="property-label">
                        <g:message code="qualisAvaliation.issn.label" default="ISSN"/>
                    </span>
					
						<span class="property-value" aria-labelledby="issn-label"><g:fieldValue bean="${qualisAvaliation}" field="issn"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${qualisAvaliation?.journal}">
				<li class="fieldcontain">
					<span id="journal-label" class="property-label">
                        <g:message code="qualisAvaliation.journal.label" default="Veículo"/>
                    </span>
					
						<span class="property-value" aria-labelledby="journal-label"><g:fieldValue bean="${qualisAvaliation}" field="journal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${qualisAvaliation?.evaluation}">
				<li class="fieldcontain">
					<span id="evaluation-label" class="property-label">
                        <g:message code="qualisAvaliation.evaluation.label" default="Avaliação"/>
                    </span>
					
						<span class="property-value" aria-labelledby="evaluation-label"><g:fieldValue bean="${qualisAvaliation}" field="evaluation"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${qualisAvaliation?.subject}">
				<li class="fieldcontain">
					<span id="subject-label" class="property-label">
                        <g:message code="qualisAvaliation.subject.label"/>
                    </span>
					
						<span class="property-value" aria-labelledby="subject-label"><g:fieldValue bean="${qualisAvaliation}" field="subject"/></span>
					
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
