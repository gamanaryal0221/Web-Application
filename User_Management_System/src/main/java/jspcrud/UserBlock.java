/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jspcrud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "UserBlock", urlPatterns = {"/UserBlock"})
public class UserBlock extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response)   
             throws ServletException, IOException {  
        try {
            String username=request.getParameter("Username"); 
            
            if(UserDao.get_blocked_status(username)=="No"){
            Connection con=UserDao.getConnection(); 
            String query = "update users set Blocked_Status=? where Username=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,"Yes");
            ps.setString(2,username); 
          String action = "user Blocked";
                    String time = LocalDateTime.now().toString();

                    History.History h = new History.History(username,time, action);
                    History.HistoryDao.addHistory(h);
            ps.executeUpdate();
           }
            else if(UserDao.get_blocked_status(username)=="Yes"){
                Connection con=UserDao.getConnection(); 
            String query = "update users set Blocked_Status=? where Username=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,"No");
            ps.setString(2,username); 
          String action = "user unBlocked";
                    String time = LocalDateTime.now().toString();

                    History.History h = new History.History(username,time, action);
                    History.HistoryDao.addHistory(h);
            ps.executeUpdate();
            
            }
            response.sendRedirect("viewusers.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }

    
}
}