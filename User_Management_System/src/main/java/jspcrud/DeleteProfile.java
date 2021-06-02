/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jspcrud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Acer
 */
@WebServlet(name = "DeleteProfile", urlPatterns = {"/DeleteProfile"})
public class DeleteProfile extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response)   
             throws ServletException, IOException {  
        HttpSession session = request.getSession();
        String loginemail = session.getAttribute("loginemail").toString();  
        
        UserDao.delete(loginemail);  
     response.sendRedirect("http://localhost:8080/User_Management_System/login/login.jsp"); 

    
}
}
