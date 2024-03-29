<%-- 
    Document   : signin
    Created on : May 21, 2020, 2:20:18 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Sign Up </title>
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
						Create account
					</span>
				</div>

                            <form  action="Register" method="POST" class="login100-form validate-form">
                                
                                        <div class="wrap-input100 validate-input m-b-26" >
						<span class="label-input100">Admin ?</span>
						<input class="input100" type="text" name="admin" placeholder="Enter Y/N">
						<span class="focus-input100"></span>
					</div>
                                <br>
                                
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<span class="label-input100">Firstname</span>
						<input class="input100" type="text" name="firstname" placeholder="Enter firstname">
						<span class="focus-input100"></span>
					</div>
                                        
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Lastname is required">
						<span class="label-input100">Lastname</span>
						<input class="input100" type="text" name="lastname" placeholder="Enter lastname">
						<span class="focus-input100"></span>
					</div>
                                
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Enter M/F/O">
						<span class="label-input100">Gender</span>
						<input class="input100" type="text" name="gender" placeholder="Enter M/F/O">
						<span class="focus-input100"></span>
					</div>
                                    
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Phone Number is required">
						<span class="label-input100">Phone Number</span>
						<input class="input100" type="text" name="phonenumber" placeholder="Enter Phone number">
						<span class="focus-input100"></span>
					</div>
                                    
                                        
					<div class="wrap-input100 validate-input m-b-26" data-validate="Username is required">
						<span class="label-input100">Username</span>
						<input class="input100" type="text" name="username" placeholder="Enter username">
						<span class="focus-input100"></span>
					</div>
                                        
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Email is required">
						<span class="label-input100">E-mail</span>
						<input class="input100" type="text" name="email" placeholder="Enter E-mail">
						<span class="focus-input100"></span>
					</div>
                                    
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Password is required">
						<span class="label-input100">Password</span>
						<input class="input100" type="password" name="password" placeholder="Enter password">
						<span class="focus-input100"></span>
					</div>
                                    
                                        <div class="wrap-input100 validate-input m-b-26" data-validate="Confirm password is mandatory">
						<span class="label-input100">Confirm password</span>
						<input class="input100" type="password" name="confirmpassword" placeholder="Confirm password">
						<span class="focus-input100"></span>
					</div>
                                    
					<div class="container-login100-form-btn">
						<input value="Sign Up" type="submit" name= "Register" class="login100-form-btn">
							
		
					</div>
                                    
                                        <div class="text-center p-t-136">
                                            Already have a account? <br>
						<a  href="login/login.jsp" class="txt2">
							Go to login page
							<i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
						</a>
					</div>
                                <div class="text-center p-t-136">
						<a  href="About.jsp" class="txt2">
							About Usermanagement System
							<i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
						</a>
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
