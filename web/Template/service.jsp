<%@ page import="data.Service" %><%--
  Created by IntelliJ IDEA.
  User: noure
  Date: 15/02/2020
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Service</title>
</head>
<body>
    <p>
        <%
            Service service = (Service) request.getAttribute("Service");
            out.print(service.getLongDescription());
        %>
    </p>
</body>
</html>
