<%@page import="jspcrud.UserDao"%>  
<jsp:useBean id="u" class="jspcrud.User"></jsp:useBean>  
<jsp:setProperty property="*" name="u"/>  
<%  
UserDao.delete(u);  
response.sendRedirect("viewusers.jsp");  
%>  