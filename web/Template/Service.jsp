<%@ page import="data.Service" %>
<%@ page import="data.User" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="data.ServiceType" %>
<%@ page import="java.util.List" %>
<%@ page import="data.ServiceNature" %>
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
    <jsp:include page="header.jsp" />
    <main class="pt-5 mx-lg-5">
        <div class="container my-5">

            <section>

                <div class="card mb-4">

                    <div class="row">

                        <div class="p-5 align-self-center">
                            <%
                                Service service = (Service) request.getAttribute("Service");
                            %>

                            <h5 class="font-weight-normal mb-2"><%if(service.isOffer()){out.print("Offre de service");}else{out.print("Demande de service");}%></h5>

                            <p class="text-muted"><%out.print(service.getLongDescription());%></p>

                            <ul class="list-group list-group-horizontal font-small mt-4 mb-4">
                                <li class="list-group-item">
                                    <p class="text-uppercase mb-2"><strong>Référence</strong></p>
                                    <p class="text-muted mb-2"><%out.print(service.getReference());%></p>
                                </li>

                                <li class="list-group-item">
                                    <p class="text-uppercase mb-2"><strong>Type</strong></p>
                                    <p class="text-muted mb-2"><%out.print(service.getServiceType().getName());%></p>
                                </li>

                                <li class="list-group-item">
                                    <p class="text-uppercase mb-2"><strong>Nature</strong></p>
                                    <p class="text-muted mb-2"><%out.print(service.getServiceNature().getNature());%></p>
                                </li>

                                <li class="list-group-item">
                                    <p class="text-uppercase mb-2"><strong>Utilisateur</strong></p>
                                    <p class="text-muted mb-2"><%out.print(service.getUser().getUserName());%></p>
                                </li>

                                <li class="list-group-item">
                                    <p class="text-uppercase mb-2"><strong>Date debut</strong></p>
                                    <p class="text-muted mb-2"><%out.print(service.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));%></p>
                                </li>

                                <li class="list-group-item">
                                    <p class="text-uppercase mb-2"><strong>Date fin</strong></p>
                                    <p class="text-muted mb-2"><%out.print(service.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));%></p>
                                </li>

                                <li class="list-group-item">
                                    <p class="text-uppercase mb-2"><strong>Localisation</strong></p>
                                    <p class="text-muted mb-2"><%out.print(service.getLocation().getProvince());%>, <%out.print(service.getLocation().getCity());%>, <%out.print(service.getLocation().getCommune());%></p>
                                </li>

                                <li class="list-group-item">
                                    <p class="text-uppercase mb-2"><strong>Description courte</strong></p>
                                    <p class="text-muted mb-2"><%out.print(service.getShortDescription());%></p>
                                </li>
                            </ul>
                                <%if(session.getAttribute("user")!=null){
                                %>

                                <div class="d-flex justify-content-start">
                                    <% User currentUser = (User)session.getAttribute("user");
                                    if (currentUser.isAdmin() && service.getStatus()==0){ %>
                                    <form method="post" action="validate-service">
                                        <input name="service_id_valider" id="service_id_valider" value="<%out.print(service.getId());%>" hidden>
                                        <button class="btn btn-yellow" type="submit">Valider</button>
                                    </form>
                                    <a href="" class="btn btn-danger btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm3">Supprimer Service</a>
                                    <%
                                    }if (currentUser.getId()!=service.getUser().getId()){
                                        if (service.getStatus()==1){%>
                                        <a href="" class="btn btn-default btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm">
                                            <%if(service.isOffer()){
                                            %>Accepter l'offre
                                            <%}else{
                                            %>Proposer le service
                                            <%}
                                            %>
                                        </a>
                                    <%}}else{%>
                                        <a href="" class="btn btn-success btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm2">Modifier Service</a>
                                        <a href="" class="btn btn-danger btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm3">Supprimer Service</a>
                                    <%}%>

                                </div>

                                <%}%>

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
                            <button type="button" class="btn btn-danger btn-rounded mb-4" data-toggle="modal" data-target="#modalContactForm7">Enregistrer</button>
                        </div>
                        <div class="modal fade left" id="modalContactForm7" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog modal-side modal-bottom-right modal-notify modal-info" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel2">Modification du service</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        Voulez-vous enregistrer les modifications ?
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                                        <button class="btn btn-primary" type="submit">Enregistrer</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="modal fade" id="modalContactForm3" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog modal-notify modal-danger" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Suppression du service</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form method="post" action="delete-service">
                            <p>Voulez-vous supprimer ce service?</p>
                            <%User currentUser = (User)session.getAttribute("user");
                              if (currentUser.isAdmin()){%>
                            <div class="md-form">
                                <i class="fas fa-pencil prefix grey-text"></i>
                                <textarea type="text" name="messageSupprimer" id="messageSupprimer" class="md-textarea form-control" rows="4"></textarea>
                                <label data-error="wrong" data-success="right" for="messageSupprimer">Votre message</label>
                            </div>
                            <%}%>
                        </div>
                        <div class="modal-footer">

                                <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Annuler</button>
                                <input name="service_id_delete" id="service_id_delete" value="<%out.print(service.getId());%>" hidden>
                                <button class="btn btn-danger" type="submit">Supprimer</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <jsp:include page="footer.jsp" />
    <jsp:include page="importsJS.jsp" />

</body>
</html>
