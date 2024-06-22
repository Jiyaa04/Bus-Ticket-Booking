<%-- 
    Document   : Agent_home
    Created on : 03-Jun-2024, 1:24:23 pm
    Author     : Jiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
<body>
<%
HttpSession hs = request.getSession(false);

if (hs != null) {
    String Agency_name = (String) hs.getAttribute("Agency_name");
    String Agency_id = (String) hs.getAttribute("Agent_id"); // Make sure this matches the attribute name set in Agent_Login.jsp

%>

<h2>Welcome <%= Agency_name %></h2>
<h4>Your Agency Id is <%= Agency_id %></h4>

<a href="Register_bus.html">Register New Bus Details</a>
<br>
<a href="Edit_bus?agency_name=<%= Agency_name %>">View and Delete On Going Buses</a>

<form action="LogoutServlet" method="post">
    <button type="submit">Logout</button>
</form>


<%
} else {
%>
<center><h1>SOME ERROR OCCURRED</h1></center>
<%
}
%>
</body>
</html>
