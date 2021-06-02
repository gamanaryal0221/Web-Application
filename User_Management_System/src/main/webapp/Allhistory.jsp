

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="History.HistoryDao"%>
<%@page import="History.History"%>
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
List<History> list=HistoryDao.getAllHistory();
request.setAttribute("list",list);
%>
 <table border="1" width="90%">
                            <thead>
                                <tr class="table100-head">
                                    <th class="column2">Username</th>
                                    <th class="column2">Time</th>
                                    <th class="column6">Action </th>
                                </tr>
                            </thead>
                            <tbody>
                                 <%
                                                if (session != null) {
                                                    try {
                                                        Class.forName("com.mysql.cj.jdbc.Driver");
                                                        String username = "root";
                                                        String password = "";
                                                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework?serverTimezone=UTC", username, password);
                                                        
                                                        String query = "select * from history";
                                                        
                                                        
                                                        PreparedStatement ps = con.prepareStatement(query);
                                     
                                                        ResultSet rs = ps.executeQuery();
                                                        while (rs.next()) {
                                                            out.print("<tbody>");
                                                            out.print("<tr><td>");
                                                            out.println(rs.getString(1));
                                                            out.print("</td>");
                                                            out.print("<td>");
                                                            out.print(rs.getString(2));
                                                            out.print("</td>");
                                                            out.print("<td>");
                                                            out.print(rs.getString(3));
                                                            out.print("</td>");
                                                            out.print("</tbody>");

                                                        }

                                                    } catch (Exception e) {
                                                        out.println(e);
                                                    }
                                                }
                                            %>


                            </tbody>
                        </table>
    </body>
</html>

  

                
                
                
    