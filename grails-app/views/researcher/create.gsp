<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Importe um xml</title>
</head>

<body>
<g:form action="importFile" enctype="multipart/form-data">
    <input class="file" type="file" name="file">
    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
</g:form>
</body>
</html>