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
<jsp:include page="header.jsp" />
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
<jsp:include page="footer.jsp" />

<jsp:include page="importsJS.jsp" />




</body>

</html>

