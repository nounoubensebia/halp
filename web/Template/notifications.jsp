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
<!--Main Navigation-->
<header>

    <!-- Navbar -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
        <div class="container-fluid">

            <!-- Brand -->
            <a class="navbar-brand waves-effect" href="https://mdbootstrap.com/docs/jquery/" target="_blank">
                <strong class="blue-text">MDB</strong>
            </a>

            <!-- Collapse -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <!-- Links -->
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <!-- Right -->
                <%if(session.getAttribute("user")!=null){
                %>
                <ul class="navbar-nav ml-auto nav-flex-icons">
                    <li class="nav-item">
                        <a class="nav-link waves-effect waves-light" href="notifications"><%User user = (User)session.getAttribute("user");
                            out.print(user.getUnseenNotifications().size());%>
                            <i class="fas fa-bell"></i>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-333" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-user"></i>
                            <%
                                out.print(user.getFirstName());%>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right dropdown-default"
                             aria-labelledby="navbarDropdownMenuLink-333">
                            <a class="dropdown-item" href="#">Mon profile</a>
                            <a class="dropdown-item" href="#">Mes Service</a>
                            <a class="dropdown-item" href="#">Logout</a>
                        </div>
                    </li>
                </ul>
                <%}else{%>
                <a href="signin" class="btn btn-outline-secondary ml-auto btn-md my-2 my-sm-0 ml-3" type="button" >Login</a>
                <%}%>

            </div>

        </div>
    </nav>
    <!-- Navbar -->
</header>
<!--Main Navigation-->
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
<!--Footer-->
<footer class="page-footer text-center font-small primary-color-dark darken-2 mt-4 wow fadeIn">

    <!--Copyright-->
    <div class="footer-copyright py-3">
        © 2020 Copyright:
        <a href="" target="_blank"> ServeurJava.com </a>
    </div>
    <!--/.Copyright-->

</footer>
<!--/.Footer-->
<jsp:include page="importsJS.jsp" />

</body>
</html>
