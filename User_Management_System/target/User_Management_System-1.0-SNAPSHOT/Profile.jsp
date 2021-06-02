

<%-- 
    Document   : Homepage2
    Created on : Jun 6, 2020, 3:50:45 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
                 <%@page import="jspcrud.User"%>
    <%
        ;
        if (session.getAttribute("loginemail") == null) {
            request.setAttribute("message", "You need to be logged in !!");
            response.sendRedirect("index.jsp");
        }
    %>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Home</title>
        <link rel="icon" type="image/x-icon" href="assets/img/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v5.13.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body id="page-top">
         <%
    User u = (User)request.getAttribute("Udata");
    %>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
     
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ml-auto">
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="Homepage2.jsp">Home</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="/User_Management_System/User_Profile?uname=${loginemail}">Profile</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="User_History.jsp">History</a></li>
                        <li class="nav-item"><a class="nav-link js-scroll-trigger" href="logout.jsp">Logout</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- Masthead-->
        <header class="masthead">
                <h1> <%= u.getFirstName() %> <%= u.getLastName() %> </h1>
                <p class="title"><%= u.getEmail() %></p>
                <p><%= u.getUsername() %></p>
                <p><%= u.getGender() %></p>
                <%= u.getPhonenumber() %></p>
                <p><a href="/User_Management_System/UpdateProfile?req=show">Update Profile</a></p>
                <p><a href="DeleteProfile?Username=${u.getUsername()}">Delete Profile</a></p>
                <p><a href="/User_Management_System/editPassword.jsp">Update Password</a></p>
        </header>


      
        <!-- Bootstrap core JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"></script>
        <!-- Third party plugin JS-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <!-- Contact form JS-->
        <script src="assets/mail/jqBootstrapValidation.js"></script>
        <script src="assets/mail/contact_me.js"></script>
        <!-- Core theme JS-->
        <script src="js/script.js"></script>
    </body>
</html>

  

                
                
                
    