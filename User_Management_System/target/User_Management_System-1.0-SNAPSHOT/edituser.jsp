<%@page import="java.time.LocalDateTime"%>
<%@page import="jspcrud.UserDao"%>  
<jsp:useBean id="u" class="jspcrud.User"></jsp:useBean>  
<jsp:setProperty property="*" name="u"/>  
<%  
    String un = (String) session.getAttribute("loginemail");
                    String action = "User updated";
                    String time = LocalDateTime.now().toString();

                    History.History h = new History.History(un,time, action);//History instance

                    History.HistoryDao.addHistory(h);
int i=UserDao.update(u);  
response.sendRedirect("viewusers.jsp");  
%>  