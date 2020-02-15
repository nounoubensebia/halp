<%--
  Created by IntelliJ IDEA.
  User: noure
  Date: 15/02/2020
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Good Test</title>
</head>
<body>
    <p>
        <%
            out.print((String)request.getAttribute("chkoupi"));
        %>
    </p>
</body>
</html>
