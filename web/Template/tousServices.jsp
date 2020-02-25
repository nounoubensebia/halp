<%@ page import="data.Service" %>
<%@ page import="java.util.List" %>
<%@ page import="data.User" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Admin</title>
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

<!--Main layout-->
<main class="pt-5 mx-lg-5">
    <div class="container mt-5">

        <style>
            .card-body {
                border-bottom-left-radius: inherit !important;
                border-bottom-right-radius: inherit !important;
            }
        </style>

        <!--Section: Content-->
        <section class="dark-grey-text">
            <!-- Heading -->
            <div class="card mb-4 wow fadeIn">
                <!--Card content-->

                <div class="card-body pt-5">
                    <table id="dtBasicExample3" class="table table-hover table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th class="th-sm">Référence
                            </th>
                            <th class="th-sm">Offre/Demande
                            </th>
                            <th class="th-sm">Statut
                            </th>
                            <th class="th-sm">Voir service
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Service> tousServices = (List<Service>)request.getAttribute("tousServices");
                            for (Service service:tousServices) {
                                out.print("<tr>\n" +
                                        "                            <td>"+service.getReference()+"</td>\n" +
                                        "                            <td>"+service.isOffer()+"</td>\n" +
                                        "                            <td>"+service.getStatus()+"</td>\n" +
                                        "                            <td>\n" +
                                        "                                <a class=\"btn-floating btn-sm btn-default\" href=\"service?service_id="+service.getId()+"\"><i class=\"fas fa-angle-right\"></i></a>\n" +
                                        "                            </td>\n" +
                                        "                            </tr>");
                            }
                        %>
                        </tbody>
                    </table>

                </div>

            </div>
        </section>
        <!--Section: Content-->
    </div>
</main>
<!--Main layout-->

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

