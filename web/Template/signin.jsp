<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Création du compte</title>
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
                  <!-- Default form login -->
                  <form class="text-center border border-light p-5" method="POST" action="signin">

                    <p class="h4 mb-4">Sign in</p>

                    <%
                      if (request.getAttribute("error")!=null)
                        out.print("<small class=\"form-text text-muted mb-4\">\n" +
                                "                            Email ou Password incorrect" +
                                "                            </small>");


                    %>

                    <!-- Email -->
                    <input type="email" name="email" id="email" class="form-control mb-4" placeholder="E-mail" required>

                    <!-- Password -->
                    <input type="password" name="password" id="password" class="form-control mb-4" placeholder="Password" required>

                  

                    <!-- Sign in button -->
                    <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>

                    <!-- Register -->
                    <p>Not a member?
                        <a href="/signup.html">Register</a>
                    </p>

                  </form>
                  <!-- Default form login -->
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
