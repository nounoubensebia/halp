<%@ page import="data.Service" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Home</title>
    <jsp:include page="./Imports.jsp" />
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
           <!-- Left -->
           <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link waves-effect" href="#">Offres de service
                  <span class="sr-only">(current)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link waves-effect" href="#" target="_blank">
                  Demandes de service
                </a>
              </li>
            </ul>

          <!-- Right -->
         
          <ul class="navbar-nav ml-auto nav-flex-icons">
            <li class="nav-item">
              <a class="nav-link waves-effect waves-light">1
                <i class="fas fa-envelope"></i>
              </a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-333" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-user"></i>
              </a>
              <div class="dropdown-menu dropdown-menu-right dropdown-default"
                aria-labelledby="navbarDropdownMenuLink-333">
                <a class="dropdown-item" href="#">Action</a>
                <a class="dropdown-item" href="#">Another action</a>
                <a class="dropdown-item" href="#">Something else here</a>
              </div>
            </li>
          </ul>
        

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
            <div class="card-body">

                <table id="dtBasicExample" class="table table-hover table-bordered" cellspacing="0" width="100%">
                    <thead>
                    <tr>
                        <th class="th-sm">Référence du service
                        </th>
                        <th class="th-sm">Type
                        </th>
                        <th class="th-sm">Nature
                        </th>
                        <th class="th-sm">Description
                        </th>
                        <th class="th-sm">Voir service
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <% List<Service> services = (List<Service>)request.getAttribute("services");
                        for (Service service:services) {
                            out.print("<tr>\n" +
                                    "                            <td>"+service.getReference()+"</td>\n" +
                                    "                            <td>"+service.getServiceType()+"</td>\n" +
                                    "                            <td>"+service.getServiceNature()+"</td>\n" +
                                    "                            <td>"+service.getShortDescription()+"</td>\n" +
                                    "                            <td>\n" +
                                    "                                <a class=\"btn-floating btn-sm btn-default\" href=\"Servlet\"><i class=\"fas fa-angle-right\"></i></a>\n" +
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
  <jsp:include page="./ImportsJS.jsp" />



</body>

</html>
