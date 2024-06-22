<%-- 
    Document   : Book
    Created on : 03-Jun-2024, 1:04:01 pm
    Author     : Jiya
--%>

<%@page import ="java.sql.Connection"%>
<%@page import ="java.sql.DriverManager"%>
<%@page import ="java.sql.ResultSet"%>
<%@page import ="java.sql.Statement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
<body>
<center>Booking Bus Seats</center>
<br>
<form action="Pay.jsp">   
<%
String bus_id = request.getParameter("id");
String emaill= request.getParameter("email");

Class.forName("com.mysql.cj.jdbc.Driver");
Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo","root","");
Statement smt = cn.createStatement();
ResultSet rs = smt.executeQuery("select * from registered_bus where id =" + bus_id + "");
if (rs.next()) {
    String sseats = rs.getString("sseats");
    int sseats_num = Integer.parseInt(sseats);
    %>

    <input type="hidden" name="id" value="<%= bus_id %>"> 
    <input type="hidden" name="emaill" value="<%= emaill %>"> 

<label for="sseats booking">Seater Seats to book:</label>
<select id="sseats" name="sseats" placeholder="Enter the number of seats to be booked" >

    <%
    for (int i = 0; i < sseats_num+1; i++) {
%>
        <option value="<%= i %>"><%= i %></option>
<%
    }
%>
</select>
<%
}
String slseats = rs.getString("slseats");
int slseats_num = Integer.parseInt(slseats);
if (slseats_num > 0) {
%>
<label for="slseats booking">Sleeper Seats to book:</label>
<select id="slseats" name="slseats" placeholder="Enter the number of sleeper seats to be booked">
<%
    for (int i = 0; i < slseats_num+1 ; i++) {
%> 
        <option value="<%= i %>"><%= i %></option>
<%
    }
%>
</select>
<%
} else {
%>
    
<%
}
String DP=rs.getString("from");
String Date=rs.getString("date");
String dtimings=rs.getString("dtimings");
String AP = rs.getString("to");

HttpSession hs = request.getSession(false); 
               hs.setAttribute("Departure_point", DP);
               hs.setAttribute("Arrival_point", AP);
               hs.setAttribute("Departure_timings", dtimings);
               hs.setAttribute("Date", Date);
%>
<br>
<br>
Departure Point :<%= DP %>
<br>
Date :<%= Date %>
<br>
Departure Time : <%= dtimings %>
<br>
<br>
Alternate E-mail id on which e-mail is to be sent :<input type="email" name="ccemailid" placeholder="Enter Email Id">
<br>
<br>
<input type="submit" value="Proceed">
</form>
    <br>
    <br>
    <br>
    <form action="LogoutServlet" method="post">
    <button type="submit">Logout</button>
    </form>
</body>
</html>
