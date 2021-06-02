/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jspcrud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
@WebServlet(name = "DeleteServelet", urlPatterns = {"/DeleteServelet"})
public class DeleteServelet extends HttpServlet {
protected void doGet(HttpServletRequest request, HttpServletResponse response)   
             throws ServletException, IOException {  
    try {
        String sid=request.getParameter("Username");
        String id=sid.toString();
        String action = "user deleated";
        String time = LocalDateTime.now().toString();
        
        History.History h = new History.History(id,time, action);
        History.HistoryDao.addHistory(h);
        UserDao.delete(id);
          
        response.sendRedirect("viewusers.jsp");
    } catch (SQLException ex) {
        Logger.getLogger(DeleteServelet.class.getName()).log(Level.SEVERE, null, ex);
    }

    
}
}
