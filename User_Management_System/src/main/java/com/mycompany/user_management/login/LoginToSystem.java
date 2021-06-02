/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user_management.login;

import com.mycompany.user_management.registration.PasswordEncryptionAndDecryption;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
public class LoginToSystem extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            LoginNeeds lgn =new LoginNeeds();
            HttpSession session = request.getSession();
            
            String loginemail = request.getParameter("loginemail");
            String loginpassword = request.getParameter("loginpassword");
            
            lgn.setLoginemail(request.getParameter("loginemail"));
            lgn.setLoginPassword(request.getParameter("loginpassword"));      
            boolean result = lgn.doLogin();
            
            if(result==true) {
                HttpSession SessionID = request.getSession();
                SessionID.setAttribute("loginemail", loginemail);
                
                if (jspcrud.UserDao.getAdmin(loginemail)=="Yes"&&jspcrud.UserDao.get_blocked_status(loginemail)=="No") {
                    session.setAttribute("Username", loginemail);
                    String action = "Logged in to the system";
                    String time = LocalDateTime.now().toString();

                    History.History h = new History.History(loginemail,time, action);//History instance

                    History.HistoryDao.addHistory(h);

                    response.sendRedirect("http://localhost:8080/User_Management_System/homepage.jsp");
                    
                }
                else if (jspcrud.UserDao.getAdmin(loginemail)=="No"&&jspcrud.UserDao.get_blocked_status(loginemail)=="No") {
                    session.setAttribute("Username", loginemail);
                    String action = "Logged in to the system";
                    String time = LocalDateTime.now().toString();

                    History.History h = new History.History(loginemail,time, action);//History instance

                    History.HistoryDao.addHistory(h);
                      
                    response.sendRedirect("http://localhost:8080/User_Management_System/Homepage2.jsp");
                }
               else if (jspcrud.UserDao.get_blocked_status(loginemail)=="Yes") {
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/login.jsp");
                out.println("<font color=red> You are blocked. Please contact admin</font>");
                rd1.include(request, response);
                }
            }else {
                RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/login.jsp");
                out.println("<font color=red>Username or Password is incorrect . Please contact admin</font>");
                rd1.include(request, response);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginToSystem.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginToSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginToSystem.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginToSystem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginToSystem.class.getName()).log(Level.SEVERE, null, ex);
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
