<%@ page import="data.Service" %>
<%@ page import="java.util.List" %>
<%@ page import="data.ServiceType" %>
<%@ page import="data.ServiceNature" %>
<%@ page import="data.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="utils.DateUtils" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Mes Services</title>
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
                                <th class="th-sm">Description
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
                            <%  List<Service> mesServices = (List<Service>)request.getAttribute("mesServices");
                                for (Service service:mesServices) {%>
                                   <tr>
                                       <td><%out.print(service.getShortDescription());%></td>
                                       <% if(service.isOffer()){%> <td><button type="button" class="btn btn-success btn-sm m-0">Offre</button></td><%}else{%> <td><button type="button" class="btn btn-warning btn-sm m-0">Demande</button></td> <%}%>
                                       <td><% if(service.getStatus()==0){%> <p class="text-warning">A_VALIDER</p>
                                           <%}if(service.getStatus()==1){%> <p class="text-success">VALIDE</p>
                                           <%}if(service.getStatus()==2){%> <p class="text-danger">EN_COURS</p>
                                           <%}if(service.getStatus()==3){%> <p class="text-dark">OBSOLETE</p><%}%>
                                       </td>
                                       <td>
                                           <a class="btn-floating btn-sm btn-default" href="service?service_id=<%out.print(service.getId());%>"><i class="fas fa-angle-right"></i></a>
                                       </td>
                                   </tr>
                               <%}%>
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
