<%@ page import="data.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.util.List" %>
<%@ page import="data.Notification" %>
<%@ page import="data.ServiceValidationNotification" %><%--
  Created by IntelliJ IDEA.
  User: noure
  Date: 15/02/2020
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Notifications</title>
    <jsp:include page="importsCss.jsp" />
    <style>

        .map-container{
            overflow:hidden;
            padding-bottom:56.25%;
            position:relative;
            height:0;
        }
        .map-container iframe{
            left:0;
            top:0;
            height:100%;
            width:100%;
            position:absolute;
        }
        .navbar, .page-footer, main {
            padding-left: unset;
        }
    </style>
</head>
<body class="grey lighten-4">
<jsp:include page="header.jsp" />
<main class="pt-5 mx-lg-5">
    <div class="container my-5">

        <section>

            <div class="card mb-4">

                <div class="list-group">
                    <% User user = (User)session.getAttribute("user");
                        List<Notification> notifications = (List<Notification>)request.getAttribute("unseenNotifications");
                        for (Notification notification:notifications) {
                    %>
                    <a href="#!" class="list-group-item list-group-item-action flex-column align-items-start disabled">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-2 h5">Le <%out.print(notification.getLocalDateTime());%></h5>
                        </div>
                        <p class="mb-2"><%out.print(notification.getMessage());%></p>
                    </a>
                    <%}%>
                </div>

            </div>

        </section>
    </div>
</main>
<jsp:include page="footer.jsp" />
<jsp:include page="importsJS.jsp" />

</body>
</html>
