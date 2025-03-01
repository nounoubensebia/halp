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
  <title>Home</title>
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

<body class="grey lighten-5">
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
              <div class="card-header">
                  <ul class="nav nav-pills" id="myTabMD" role="tablist">
                      <li class="nav-item">
                          <a class="nav-link active" id="home-tab-md" data-toggle="tab" href="#home-md" role="tab" aria-controls="home-md"
                             aria-selected="true">Offres de service</a>
                      </li>
                      <li class="nav-item">
                          <a class="nav-link" id="profile-tab-md" data-toggle="tab" href="#profile-md" role="tab" aria-controls="profile-md"
                             aria-selected="false">Demandes de service</a>
                      </li>
                  </ul>
              </div>
                <div class="tab-content card-body" id="myTabContentMD">
                    <div class="tab-pane fade show active" id="home-md" role="tabpanel" aria-labelledby="home-tab-md">
                        <table id="dtBasicExample" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="th-sm">Référence du service
                                </th>
                                <th class="th-sm">Type
                                </th>
                                <th class="th-sm">Nature
                                </th>
                                <th class="th-sm">Utilisateur
                                </th>
                                <th class="th-sm">Date début
                                </th>
                                <th class="th-sm">Date fin
                                </th>
                                <th class="th-sm">Description
                                </th>
                                <th class="th-sm">Voir service
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <% List<Service> offres = (List<Service>)request.getAttribute("offres");
                                for (Service service:offres) {
                                    out.print("<tr>\n" +
                                            "                            <td>"+service.getReference()+"</td>\n" +
                                            "                            <td>"+service.getServiceType().getName()+"</td>\n" +
                                            "                            <td>"+service.getServiceNature().getNature()+"</td>\n" +
                                            "                            <td>"+service.getUser().getUserName()+"</td>\n" +
                                            "                            <td>"+service.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"</td>\n" +
                                            "                            <td>"+service.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"</td>\n" +
                                            "                            <td>"+service.getShortDescription()+"</td>\n" +
                                            "                            <td>\n" +
                                            "                                <a class=\"btn-floating btn-sm btn-default\" href=\"service?service_id="+service.getId()+"\"><i class=\"fas fa-angle-right\"></i></a>\n" +
                                            "                            </td>\n" +
                                            "                            </tr>");
                                }
                            %>
                            </tbody>
                        </table>
                    </div>
                    <div class="tab-pane fade" id="profile-md" role="tabpanel" aria-labelledby="profile-tab-md">
                        <table id="dtBasicExample2" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th class="th-sm">Référence du service
                                </th>
                                <th class="th-sm">Type
                                </th>
                                <th class="th-sm">Nature
                                </th>
                                <th class="th-sm">Utilisateur
                                </th>
                                <th class="th-sm">Date début
                                </th>
                                <th class="th-sm">Date fin
                                </th>
                                <th class="th-sm">Description
                                </th>
                                <th class="th-sm">Voir service
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <% List<Service> demandes = (List<Service>)request.getAttribute("demandes");
                                for (Service service:demandes) {
                                    out.print("<tr>\n" +
                                            "                            <td>"+service.getReference()+"</td>\n" +
                                            "                            <td>"+service.getServiceType().getName()+"</td>\n" +
                                            "                            <td>"+service.getServiceNature().getNature()+"</td>\n" +
                                            "                            <td>"+service.getUser().getUserName()+"</td>\n" +
                                            "                            <td>"+service.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"</td>\n" +
                                            "                            <td>"+service.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"</td>\n" +
                                            "                            <td>"+service.getShortDescription()+"</td>\n" +
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

          </div>
      </section>
      <!--Section: Content-->
        <%if(session.getAttribute("user")!=null){
        %>

        <div class="modal fade" id="modalRegisterForm" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header text-center">
                        <h4 class="modal-title w-100 font-weight-bold">Nouveau Service</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body mx-3">
                        <form method="POST" action="create-service">
                            <input name="user_id" id="user_id" value="<%if(session.getAttribute("user")!=null){
                                  User user = (User)session.getAttribute("user");
                              out.print(user.getId());}%>" hidden>
                            <!-- Offre ou Demande -->
                            <label class="mdb-main-label">Demande ou Offre ?</label>
                            <select class="browser-default custom-select mb-4" name="isOffer" id="isOffer">
                                <option value="true" selected>Offre de service</option>
                                <option value="false">Demande de service</option>
                            </select>


                            <!-- Type -->
                            <label class="mdb-main-label">Type de service</label>
                            <select class="browser-default custom-select mb-4" name="service_type_id" id="service_type_id">
                                <% List<ServiceType> serviceTypes = (List<ServiceType>)request.getAttribute("types");
                                    for (ServiceType serviceType:serviceTypes) {
                                        out.print("<option value="+serviceType.getId()+">"+serviceType.getName()+"</option>");
                                    }%>
                            </select>


                            <!-- Nature -->
                            <label class="mdb-main-label">Nature de service</label>
                            <input type="text" name="service_nature_is_other" id="service_nature_is_other" value="" hidden>
                            <select class="browser-default custom-select mb-4" name="service_nature_id" id="service_nature_id">
                                <% List<ServiceNature> serviceNatures = (List<ServiceNature>)request.getAttribute("natures");
                                    for (ServiceNature serviceNature:serviceNatures) {
                                        out.print("<option value="+serviceNature.getId()+">"+serviceNature.getNature()+"</option>");
                                    }%>
                                <option value="autre">Autre</option>
                            </select>
                            <div id="autre">
                                <label class="mdb-main-label">Autre</label>
                                <input type="text" name="service_nature" id="service_nature" class="form-control mb-2" placeholder="Autre" aria-describedby="defaultRegisterFormPhoneHelpBlock">
                            </div>


                            <div class="form-row mb-4">

                                <div class="col">
                                    <label class="mdb-main-label">Date début</label>
                                    <div class="input-group date" id="datetimepicker7" data-target-input="nearest">
                                        <input type="text" name="start_date" id="start_date" class="form-control datetimepicker-input" data-target="#datetimepicker7" required/>
                                        <div class="input-group-append" data-target="#datetimepicker7" data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col">
                                    <label class="mdb-main-label">Date fin</label>
                                    <div class="input-group date" id="datetimepicker8" data-target-input="nearest">
                                        <input type="text" name="end_date" id="end_date" class="form-control datetimepicker-input" data-target="#datetimepicker8" required/>
                                        <div class="input-group-append" data-target="#datetimepicker8" data-toggle="datetimepicker">
                                            <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <!-- Localisation -->
                            <label class="mdb-main-label">Adresse</label>
                            <input type="text" name="province" id="province" class="form-control mb-2" placeholder="Département" aria-describedby="defaultRegisterFormPhoneHelpBlock" required>
                            <input type="text" name="city" id="city" class="form-control mb-2" placeholder="Ville" aria-describedby="defaultRegisterFormPhoneHelpBlock" required>
                            <input type="text" name="commune" id="commune" class="form-control mb-4" placeholder="Commune" aria-describedby="defaultRegisterFormPhoneHelpBlock" required>

                            <!-- Description -->
                            <label class="mdb-main-label">Description courte</label>
                            <textarea name="short_description" id="short_description" class="form-control mb-4" placeholder="" aria-describedby="defaultRegisterFormDescHelpBlock" rows="2" required></textarea>

                            <label class="mdb-main-label">Description Détaillée</label>
                            <textarea name="long_description" id="long_description" class="form-control" placeholder="" aria-describedby="defaultRegisterFormDescHelpBlock" rows="5" required></textarea>
                    </div>
                    <div class="modal-footer d-flex justify-content-center">
                        <button class="btn btn-deep-orange">Enregistrer</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="text-center">
            <a href="" class="btn btn-default btn-rounded mb-4" data-toggle="modal" data-target="#modalRegisterForm">Nouveau Service</a>
        </div>
        <%
            }%>
    </div>
  </main>
  <!--Main layout-->

    <jsp:include page="footer.jsp" />
  <jsp:include page="importsJS.jsp" />


</body>

</html>
