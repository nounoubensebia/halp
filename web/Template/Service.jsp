<%@ page import="data.Service" %>
<%@ page import="data.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="data.ServiceType" %>
<%@ page import="java.util.List" %>
<%@ page import="data.ServiceNature" %><%--
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
    <title>Service</title>
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

                    <div class="row">

                        <div class="p-5 align-self-center">
                            <%
                                Service service = (Service) request.getAttribute("Service");
                            %>

                            <h5 class="font-weight-normal mb-3">Description détaillée</h5>

                            <p class="text-muted"><%out.print(service.getLongDescription());%></p>

                            <ul class="list-unstyled font-small mt-5 mb-0">
                                <li>
                                    <p class="text-uppercase mb-2"><strong>Référence</strong></p>
                                    <p class="text-muted mb-4"><%out.print(service.getReference());%></p>
                                </li>

                                <li>
                                    <p class="text-uppercase mb-2"><strong>Type</strong></p>
                                    <p class="text-muted mb-4"><%out.print(service.getServiceType().getName());%></p>
                                </li>

                                <li>
                                    <p class="text-uppercase mb-2"><strong>Nature</strong></p>
                                    <p class="text-muted mb-4"><%out.print(service.getServiceNature().getNature());%></p>
                                </li>

                                <li>
                                    <p class="text-uppercase mb-2"><strong>Pseudonyme de l’utilisateur</strong></p>
                                    <p class="text-muted mb-4"><%out.print(service.getUser().getUserName());%></p>
                                </li>

                                <li>
                                    <p class="text-uppercase mb-2"><strong>Valide jusqu'au</strong></p>
                                    <p class="text-muted mb-4"><%out.print(service.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));%></p>
                                </li>

                                <li>
                                    <p class="text-uppercase mb-2"><strong>Adresse</strong></p>
                                    <p class="text-muted mb-4"><%out.print(service.getLocation().getProvince());%>, <%out.print(service.getLocation().getCity());%>, <%out.print(service.getLocation().getCommune());%></p>
                                </li>

                                <li>
                                    <p class="text-uppercase mb-2"><strong>Description courte</strong></p>
                                    <p class="text-muted mb-4"><%out.print(service.getShortDescription());%></p>
                                </li>
                                <%if(session.getAttribute("user")!=null){
                                %>
                                <li>
                                    <div class="d-flex justify-content-start">
                                        <% User currentUser = (User)session.getAttribute("user");
                                        if (currentUser.getId()!=service.getUser().getId()){%>
                                            <a href="" class="btn btn-default btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm">
                                                <%if(service.isOffer()){
                                                %>Accepter l'offre
                                                <%}else{
                                                %>Proposer votre service
                                                <%}
                                                %>
                                            </a>
                                        <%}else{%>
                                            <a href="" class="btn btn-success btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm2">Modifier Service</a>
                                            <a href="" class="btn btn-danger btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm3">Supprimer Service</a>
                                        <%}%>

                                    </div>
                                </li>
                                <%}%>

                            </ul>

                        </div>

                    </div>

                </div>

            </section>
            <div class="modal fade" id="modalContactForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body mx-3">
                           <form method="post" action="create-user-response">

                            <div class="md-form">
                                <i class="fas fa-pencil prefix grey-text"></i>
                                <textarea type="text" name="message" id="message" class="md-textarea form-control" rows="4"></textarea>
                                <label data-error="wrong" data-success="right" for="message">Votre message</label>
                            </div>
                               <input name="service_id" id="service_id" value="<%out.print(service.getId());%>" hidden>

                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <button class="btn btn-unique" type="submit" >Envoyer <i class="fas fa-paper-plane-o ml-1"></i></button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="modalContactForm2" role="dialog" aria-labelledby="myModalLabel2"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header text-center">
                            <h4 class="modal-title w-100 font-weight-bold">Modifier Service</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body mx-3">
                            <form method="POST" action="update-service">
                                <div class="form-row mb-4">
                                    <input name="service_id_edit" id="service_id_edit" value="<%out.print(service.getId());%>" hidden>
                                    <div class="col">
                                        <label class="mdb-main-label">Date début</label>
                                        <div class="input-group date" id="datetimepicker7" data-target-input="nearest">
                                            <input type="text" name="start_date" id="start_date" class="form-control datetimepicker-input" data-target="#datetimepicker7" value="<%out.print(service.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));%>" required/>
                                            <div class="input-group-append" data-target="#datetimepicker7" data-toggle="datetimepicker">
                                                <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col">
                                        <label class="mdb-main-label">Date fin</label>
                                        <div class="input-group date" id="datetimepicker8" data-target-input="nearest">
                                            <input type="text" name="end_date" id="end_date" class="form-control datetimepicker-input" data-target="#datetimepicker8" value="<%out.print(service.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));%>" required/>
                                            <div class="input-group-append" data-target="#datetimepicker8" data-toggle="datetimepicker">
                                                <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <!-- Localisation -->
                                <label class="mdb-main-label">Adresse</label>
                                <input type="text" name="province" id="province" class="form-control mb-2" placeholder="Département" aria-describedby="defaultRegisterFormPhoneHelpBlock" value="<%out.print(service.getLocation().getProvince());%>" required>
                                <input type="text" name="city" id="city" class="form-control mb-2" placeholder="Ville" aria-describedby="defaultRegisterFormPhoneHelpBlock" value="<%out.print(service.getLocation().getCity());%>" required>
                                <input type="text" name="commune" id="commune" class="form-control mb-4" placeholder="Commune" aria-describedby="defaultRegisterFormPhoneHelpBlock" value="<%out.print(service.getLocation().getCommune());%>" required>

                                <!-- Description -->
                                <label class="mdb-main-label">Description courte</label>
                                <textarea name="short_description" id="short_description" class="form-control mb-4" placeholder="" aria-describedby="defaultRegisterFormDescHelpBlock" rows="2"  required><%out.print(service.getShortDescription());%></textarea>

                                <label class="mdb-main-label">Description Détaillée</label>
                                <textarea name="long_description" id="long_description" class="form-control" placeholder="" aria-describedby="defaultRegisterFormDescHelpBlock" rows="5" required><%out.print(service.getLongDescription());%></textarea>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <button class="btn btn-deep-orange">Enregistrer</button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="modalContactForm3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Suppression du service</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            Voulez-vous supprimer ce service?
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button type="button" class="btn btn-primary">Supprimer</button>
                        </div>
                    </div>
                </div>
            </div>
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
