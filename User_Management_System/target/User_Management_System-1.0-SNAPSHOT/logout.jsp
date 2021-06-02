<%-- 
    Document   : logout
    Created on : May 22, 2020, 9:57:49 PM
    Author     : Asus
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="0" cellpadding="0" cellspacing="0" width="480" >
                <tr>
                    <td align="center" valign="top" style="padding: 40px 10px 40px 10px;">
                        <a href="login/login.jsp" target="_blank">
                            <img alt="Logo" src="logo.jpg" width="100" height="100" style="display: block;  font-family: 'Lato', Helvetica, Arial, sans-serif; color: #ffffff; font-size: 18px;" border="0">
                        </a>
                    </td>
                </tr>
            </table>
        <%
            String un = (String) session.getAttribute("loginemail");
                    String action = "Logged out from the system";
                    String time = LocalDateTime.now().toString();

                    History.History h = new History.History(un,time, action);//History instance

                    History.HistoryDao.addHistory(h);
            session.invalidate();          
        %>
        <h1>You are logged out of the System</h1>
        <a href="login/login.jsp">Move to the login page</a>
    </body>
</html>
