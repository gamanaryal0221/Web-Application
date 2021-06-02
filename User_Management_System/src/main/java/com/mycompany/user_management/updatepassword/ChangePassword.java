/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user_management.updatepassword;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Asus
 */
public class ChangePassword extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String display;
    UpdatePasswordDatabaseModel updm = new UpdatePasswordDatabaseModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/editPassword.jsp");

            String username = session.getAttribute("loginemail").toString(); 
             updm.setCheckUsername(session.getAttribute("loginemail").toString());
            updm.setOldPassword(request.getParameter("oldpassword"));
            String oldPassword = request.getParameter("oldPassword").toString();
            updm.setUpdatepassword(request.getParameter("updatepassword"));
            updm.setUpdateconfirmpassword(request.getParameter("updateconfirmpassword"));
            

            if (updm.passwordIsValid() == false) {
                out.println("<font color=red>Password must be 8 to 16 digit long and must contain uppercase, special character and number.</font>");
                rd.include(request, response);}
//            } else if (updm.OldPasswordIsCorrect(oldPassword,username)== false) {
//                out.println("<font color=red>Your old password is incorrect</font>");
//                rd.include(request, response);
            else if (updm.passwordsAreSame() == false) {
                out.println("<font color=red>Passwords are not matching.</font>");
                rd.include(request, response);
            } else if (updm.UsernameOrPasswordAsSameAsEnteredPasswordDoesExist() == true) {
                out.println("<font color=red>We can not take password :- " + request.getParameter("updatepassword") + " please change it.</font>");
                rd.include(request, response);
            } else {
                String action = "Password updated";
                String time = LocalDateTime.now().toString();

                History.History h = new History.History(username, time, action);
                History.HistoryDao.addHistory(h);

                updm.changePassword();
                updm.setBackToNormal();
                response.sendRedirect("http://localhost:8080/User_Management_System/login/login.jsp");
            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdatePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UpdatePassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}