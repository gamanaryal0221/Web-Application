<%-- 
    Document   : createnewpassword
    Created on : May 23, 2020, 7:07:02 PM
    Author     : Asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<center style="width: 100%; min-height: 100vh; display: flex;flex-wrap: wrap;justify-content: center;align-items: center;padding: 15px; background: linear-gradient(-135deg, #c850c0, #4158d0);">
    <div class="container" style="width: 850px; height: 650px; background: #fff;border-radius: 10px;overflow: hidden; display: flex;flex-wrap: wrap;justify-content: space-between;box-sizing: border-box;">
        
        <div class="row">
            <div class="col-sm-6 col-sm-offset-3" style="margin-top: 120px;">
                <h1>Change Password</h1><br><br>
                <p class="text-center">Use the form below to change your password. Your password cannot be the same as your username.</p>
                <form action="ChangePassword" method="post" id="passwordForm">
                   
                     <input type="password" class="input-lg form-control" name="oldPassword" id="password1" placeholder="Old password" style="margin-top: 25px;margin-bottom: 20px">
                    <input type="password" class="input-lg form-control" name="updatepassword" id="password1" placeholder="New Password" autocomplete="off" style="margin-bottom: 20px">
                    
                    <input type="password" class="input-lg form-control" name="updateconfirmpassword" id="password2" placeholder="Repeat Password" autocomplete="off" style="margin-bottom: 25px;">
                    <div class="row">
                    </div>
                    <input type="submit" class="col-xs-12 btn btn-primary btn-load btn-lg" data-loading-text="Changing... Password" value="Update Password">
                </form>
            </div><!--/col-sm-6-->
        </div><!--/row-->
    </div>
</center>