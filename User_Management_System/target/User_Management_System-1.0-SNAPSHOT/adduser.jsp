<%-- 
    Document   : adduser
    Created on : Jun 1, 2020, 10:09:03 PM
    Author     : jiten
--%>


<%@page import="java.time.LocalDateTime"%>
<%@page import="jspcrud.UserDao"%>

<jsp:useBean id="u" class="jspcrud.User"> </jsp:useBean>
<jsp:setProperty property="*" name="u"/>

<%
                    String un = (String) session.getAttribute("loginemail");
                    String action = "User added";
                    String time = LocalDateTime.now().toString();

                    History.History h = new History.History(un,time, action);//History instance

                    History.HistoryDao.addHistory(h);
int i=UserDao.save(u);
if(i>0){
response.sendRedirect("adduser-success.jsp");
}else{
response.sendRedirect("adduser-error.jsp");
}
%>