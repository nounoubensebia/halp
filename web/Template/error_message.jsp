<%--
  Created by IntelliJ IDEA.
  User: noure
  Date: 29/02/2020
  Time: 06:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erreur</title>
</head>
<body>
    <%
        out.print((String)request.getAttribute("message"));
    %>
</body>
</html>
