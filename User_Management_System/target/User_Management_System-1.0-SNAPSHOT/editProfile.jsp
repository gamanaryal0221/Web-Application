

<!DOCTYPE html>  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">  
<title>Edit Profile</title>  
</head>  
<body>  
<%@page import="jspcrud.UserDao,jspcrud.User"%>  
  
<%
    User u = (User)request.getAttribute("Updata");
    %>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form-title" style="background-image: url(images/bg-01.jpg);">
					<span class="login100-form-title-1">
						Edit Account
					</span>
				</div>

                            <form  action="/User_Management_System/UpdateProfile" method="get" class="login100-form validate-form">
                                
                                
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<span class="label-input100">First name </span>
						<input class="input100" type="text" name="firstname" placeholder="Enter firstname" value="<%= u.getFirstName()%>"/>
						<span class="focus-input100"></span>
					</div>
                                        
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Lastname is required">
						<span class="label-input100">Last name</span>
						<input class="input100" type="text" name="lastname" placeholder="Enter lastname" value="<%= u.getLastName()%>"/>
						<span class="focus-input100"></span>
					</div>
                                
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Enter M/F/O">
						<span class="label-input100">Gender</span>
						<input class="input100" type="text" name="gender" placeholder="Enter M/F/O" value="<%= u.getGender()%>"/>
						<span class="focus-input100"></span>
					</div>
                                    
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Phone Number is required">
						<span class="label-input100">Phone Number</span>
						<input class="input100" type="text" name="phonenumber" placeholder="Enter Phone number"value="<%= u.getPhonenumber()%>"/>
						<span class="focus-input100"></span>
					</div>
                                    
                                        
					<div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<span class="label-input100">Username</span>
						<input class="input100" type="text" name="username" placeholder="Enter username" value="<%= u.getUsername()%>"/>
						<span class="focus-input100"></span>
					</div>
                                        
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Email is required">
						<span class="label-input100">E-mail</span>
						<input class="input100" type="text" name="email" placeholder="Enter E-mail" value="<%= u.getEmail()%>"/>
                                                <input type="hidden" name="req" value="update">
						<span class="focus-input100"></span>
					</div>
                                    
                                    
                             
                                    
                                <div class="container-login100-form-btn">
                                        <input  type="submit" class="login100-form-btn"> 
							
		
					</div>
                                   
				</form>
			</div>
		</div>
	</div>
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>
