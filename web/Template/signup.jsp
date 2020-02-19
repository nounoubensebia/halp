<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Creation du compte</title>
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
  </style>
</head>

<body class="grey lighten-3">

 

  <!--Main layout-->
  <main class="pt-5 mx-lg-5">
    <div class="container-fluid mt-5">
        <div class="row wow fadeIn">

            <!--Grid column-->
            <div class="col-md-9 mb-4">
    
              <!--Card-->
              <div class="card">
    
                <!--Card content-->
                <div class="card-body">
                  <!-- Default form register -->
                    <form class="text-center border border-light p-5" method="POST" action="signup">

                        <p class="h4 mb-4">Sign up</p>

                        <div class="form-row mb-4">
                            <div class="col">
                                <!-- First name -->
                                <input type="text" name="first_name" id="first_name" class="form-control" pattern="[A-Za-z]{1,32}" placeholder="Prénom" required>
                            </div>
                            <div class="col">
                                <!-- Last name -->
                                <input type="text" name="last_name" id="last_name" class="form-control" pattern="[A-Za-z]{1,32}" placeholder="Nom" required>
                            </div>
                        </div>

                        <!-- E-mail -->
                        <input type="email" name="email" id="email" class="form-control mb-4" placeholder="E-mail" required>
                        <%
                            if (request.getAttribute("error-email")!=null)
                                out.print("<small class=\"form-text red-text mb-4\">\n" +
                                        "                            Cette adresse email existe déja" +
                                        "                            </small>");


                        %>

                        <!-- Password -->
                        <input type="password" name="password" id="password" class="form-control mb-4" placeholder="Mot de passe" aria-describedby="defaultRegisterFormPasswordHelpBlock" required>
                       
                        <!-- User Name -->
                        <input type="text" name="user_name" id="user_name" class="form-control mb-4" placeholder="User name" aria-describedby="defaultRegisterFormUserNameHelpBlock" required>
                      
                        <!-- Phone number -->
                        <input type="number" name="phone" id="phone" class="form-control mb-4" placeholder="Téléphone" aria-describedby="defaultRegisterFormPhoneHelpBlock" required>
                      

                        <!-- Address -->
                        <input type="text" name="street" id="street" class="form-control mb-4" placeholder="Street" aria-describedby="defaultRegisterFormPhoneHelpBlock" required>
                        <input type="text" name="city" id="city" class="form-control mb-4" placeholder="City" aria-describedby="defaultRegisterFormPhoneHelpBlock" required>
                        <input type="text" name="supplement" id="supplement" class="form-control mb-4" placeholder="Supplement" aria-describedby="defaultRegisterFormPhoneHelpBlock" required>
                        <!-- Description -->
                        <textarea name="description" id="description" class="form-control" placeholder="Description" aria-describedby="defaultRegisterFormDescHelpBlock" required></textarea>
                        <small id="defaultRegisterFormPasswordHelpBlock" class="form-text text-muted mb-4">
                            Une description personnelle en guise de présentation
                        </small>
 

                        <!-- Sign up button -->
                        <button class="btn btn-info my-4 btn-block" type="submit">Enregistrer</button>

                        <hr>

                        <!-- Terms of service -->
                        <p>En cliquant
                            <em>Enregistrer</em> vous acceptez notre
                            <a href="" target="#">conditions d'utilisation</a>
                        </p>

                    </form>
                    <!-- Default form register -->
                </div>
    
              </div>
            </div>
        </div>
        
    </div>
  </main>
  <!--Main layout-->

  <jsp:include page="importsJS.jsp" />
</body>

</html>
