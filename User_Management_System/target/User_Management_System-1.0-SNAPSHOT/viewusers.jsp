<%-- 
    Document   : viewusers
    Created on : Jun 1, 2020, 10:13:11 PM
    Author     : jiten
--%>

<%@page import="History.HistoryDao"%>
<%@page import="History.History"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Users</title>
</head>
<body>

<%@page import="jspcrud.UserDao,jspcrud.*,java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Users List</h1>

<%
List<User> list=UserDao.getAllRecords();
request.setAttribute("list",list);
%>


<table border="1" width="90%">
    <tr><th>Admin</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Email</th><th>Gender</th><th>Phone </th>
        <th>Is Blocked </th><th>Edit</th><th>Delete</th><th>Block</th><th>History</th></tr>
<c:forEach items="${list}" var="u">
	<tr><td>${u.getAdmin()}</td><td>${u.getFirstName()}</td><td>${u.getLastName()}</td><td>${u.getUsername()}</td>
            <td>${u.getEmail()}</td><td>${u.getGender()}</td><td>${u.getPhonenumber()}</td><td>${u.getBlocked_status()}</td><td><a href="editform.jsp?Username=${u.getUsername()}">Edit</a></td>
            <td><a href="DeleteServelet?Username=${u.getUsername()}">Delete</a></td>
            <td><a href="UserBlock?Username=${u.getUsername()}">Block/Unblock</a></td>
            
            <td><a href="SpecificHistory.jsp?Username=${u.getUsername()}">View History</a></td></tr>
            </c:forEach>

</table>
<br/><a href="adduserform.jsp">Add New User</a>
<br/><a href="homepage.jsp">Return to dashboard</a>

</body>
</html>