<%@ page import="data.User" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Mon Profile</title>
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

                <div class="card-body pt-5" >
                    <form class="text-center border border-light p-5" method="POST" action="edit-account">

                        <p class="h4 mb-4">Gestion du compte</p>
                        <%User user = (User)session.getAttribute("user");%>

                        <div class="form-row mb-4">
                            <div class="col">
                                <!-- First name -->
                                <input type="text" name="first_name" id="first_name" class="form-control" pattern="[A-Za-z]{1,32}" placeholder="Prénom" value="<%out.print(user.getFirstName());%>" disabled required>
                            </div>
                            <div class="col">
                                <!-- Last name -->
                                <input type="text" name="last_name" id="last_name" class="form-control" pattern="[A-Za-z]{1,32}" placeholder="Nom" value="<%out.print(user.getLastName());%>" disabled required>
                            </div>
                        </div>

                        <!-- E-mail -->
                        <input pattern="/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/" name="email" id="email" class="form-control mb-4" placeholder="E-mail" value="<%out.print(user.getEmail());%>" disabled required>
                        <%
                            if (request.getAttribute("error-email")!=null)
                                out.print("<small class=\"form-text red-text mb-4\">\n" +
                                        "                            Cette adresse email existe déja" +
                                        "                            </small>");


                        %>

                        <!-- Password -->
                        <input type="password" name="password" id="password" class="form-control mb-4" placeholder="Mot de passe" aria-describedby="defaultRegisterFormPasswordHelpBlock" value="<%out.print(user.getPassword());%>"  required>

                        <!-- User Name -->
                        <input type="text" name="user_name" id="user_name" class="form-control mb-4" placeholder="User name" aria-describedby="defaultRegisterFormUserNameHelpBlock" value="<%out.print(user.getUserName());%>" disabled  required>

                        <!-- Phone number -->
                        <input type="tel" pattern="[0-9]{10}" name="phone" id="phone" class="form-control mb-4" placeholder="Téléphone" aria-describedby="defaultRegisterFormPhoneHelpBlock" value="<%out.print(user.getPhone());%>" required>


                        <!-- Address -->
                        <input type="text" name="street" id="street" class="form-control mb-4" placeholder="Street" aria-describedby="defaultRegisterFormPhoneHelpBlock" value="<%out.print(user.getAddress().getStreet());%>" required>
                        <input type="text" name="city" id="city" class="form-control mb-4" placeholder="City" aria-describedby="defaultRegisterFormPhoneHelpBlock" value="<%out.print(user.getAddress().getCity());%>" required>
                        <input type="text" name="supplement" id="supplement" class="form-control mb-4" placeholder="Complement" aria-describedby="defaultRegisterFormPhoneHelpBlock" value="<%out.print(user.getAddress().getSupplement());%>" required>
                        <!-- Description -->
                        <textarea name="description" id="description" class="form-control" placeholder="Description" aria-describedby="defaultRegisterFormDescHelpBlock" required><%out.print(user.getDescription());%></textarea>

                        <!-- Sign up button -->
                        <button class="btn btn-success" type="submit">Enregistrer</button>
                        <a href="" class="btn btn-danger" data-toggle="modal" data-target="#modalContactForm5">Supprimer compte</a>

                    </form>
                </div>

            </div>
        </section>
        <!--Section: Content-->
        <div class="modal fade" id="modalContactForm5" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Suppression du Compte</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        Voulez-vous supprimer votre compte ?
                    </div>
                    <div class="modal-footer">
                        <form method="post" action="delete-account">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                            <button class="btn btn-primary" type="submit">Supprimer</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<!--Main layout-->
<jsp:include page="footer.jsp" />

<jsp:include page="importsJS.jsp" />




</body>

</html>
