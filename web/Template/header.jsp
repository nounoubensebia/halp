<%@ page import="data.User" %>
<!--Main Navigation-->
<header>

    <!-- Navbar -->
    <nav class="navbar fixed-top navbar-expand-lg navbar-light white scrolling-navbar">
        <div class="container-fluid">

            <!-- Brand -->
            <a class="navbar-brand waves-effect" href="Servlet" target="">
                <strong class="grey-text">HALP</strong>
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
                        <a class="nav-link waves-effect waves-light" href="notifications"><%
                            User user = (User)session.getAttribute("user");
                            out.print(user.getUnseenNotifications().size());%>
                            <i class="fas fa-bell"></i>
                        </a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-333" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-user"></i>
                            <%out.print(user.getFirstName());%> <%out.print(user.getLastName());%>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right dropdown-default"
                             aria-labelledby="navbarDropdownMenuLink-333">
                            <a class="dropdown-item" href="edit-account">Mon profile</a>
                            <a class="dropdown-item" href="My-Services">Mes Services</a>
                            <%if (user.isAdmin()){%>
                            <a class="dropdown-item" href="All-Services">Services a valider</a>
                            <%}%>
                            <a class="dropdown-item" href="logout">Logout</a>
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
