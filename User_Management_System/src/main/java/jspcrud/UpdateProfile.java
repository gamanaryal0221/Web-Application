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

import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;


public class UpdateProfile extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();

            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            String first_name = request.getParameter("firstname");
            String last_name = request.getParameter("lastname");
            String Gender = request.getParameter("gender");
            String Phonenumber = request.getParameter("phonenumber");
            String Username = request.getParameter("username");
            String Email = request.getParameter("email");
            String username = (String) session.getAttribute("loginemail");
            
            if (request.getParameter("req").equals("show")) {
                
                if (session.getAttribute("loginemail")==null)                {
                    request.setAttribute("message", "You need to login first");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd1 = request.getRequestDispatcher("editProfile.jsp");
                    

                    String un = (String) session.getAttribute("loginemail");
                        
                    User u = UserDao.getProfile(un);

                    request.setAttribute("Updata", u);
                    rd1.forward(request, response);
                }

            } else if (request.getParameter("req").equals("update")) {
                
            Connection con=UserDao.getConnection(); 
            String query = "update users set Firstname=?,Lastname=?,Gender=?,Phonenumber=?,Username=?,Email=? where Username=?";//Sql query for updatng user in database

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, first_name);
            ps.setString(2, last_name);
            ps.setString(3, Gender);
            ps.setString(4, Phonenumber);
            ps.setString(5, Username);
            ps.setString(6, Email);
            ps.setString(7, username);

            ps.executeUpdate();
            String un = (String) session.getAttribute("loginemail");
            String action = "Updated profile";
                    String time = LocalDateTime.now().toString();

                    History.History h = new History.History(un, time, action);//History instance

                    History.HistoryDao.addHistory(h);
                
                
                
               
                RequestDispatcher rd1 = request.getRequestDispatcher("logout.jsp");
                rd1.forward(request, response);
            }
        }

    }

            /* TODO output your page here. You may use following sample code. */
            

         
    
    


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
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            

           

        } catch (Exception e) {
            out.print(e);
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
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
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
