<%-- 
    Document   : Search1
    Created on : 03-Jun-2024, 12:59:21 pm
    Author     : Jiya
--%>

<%@page import="java.sql.Connection" %>
<%@page import="java.sql.DriverManager" %>
<%@page import="java.sql.ResultSet" %>
<%@page import="java.sql.Statement" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
<body>
    
    <% 
    String from = request.getParameter("ap");
    String to = request.getParameter("dp");
    %>
    On going buses from <%= from %> to <%= to %>
    <%
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo","root","");
        Statement  smt = cn.createStatement();
        ResultSet rs = smt.executeQuery("select * from registered_bus where `from`='"+from+"' and `to` ='"+to+"'");
    %>
    <table border='2'>
        <tr>
            <th>Id</th>
            <th>Bus Number</th>
            <th>Agency Name</th>
            <th>Departure Venue</th>
            <th>Arrival Venue</th>
            <th>Date</th>
            <th>Departure Timings </th>
            <th>Arrival Timings</th>
            <th>Available Seater Seats</th>
            <th>Seater Seats Price</th>
            <th>Available Sleeper Seats</th>
            <th>Sleeper Seats Price</th>
            <th>Book</th>
        </tr>

    <%
        while(rs.next()){
            String id = rs.getString("id");
            String bus_num = rs.getString("bus_num");
            String agency_name = rs.getString("agency_name");
            String dp = rs.getString("from");
            String ap = rs.getString("to");
            String date = rs.getString("date");
            String dtimings = rs.getString("dtimings");
            String atimings = rs.getString("atimings");
            String sseats = rs.getString("sseats");
            String slseats = rs.getString("slseats");
            String sprice = rs.getString("sprice");
            String slprice = rs.getString("slprice");
            
    %>
        <tr>
            <td><%= id %></td>
            <td><%= bus_num %></td>
            <td><%= agency_name %></td>
            <td><%= dp %></td>
            <td><%= ap %></td>
            <td><%= date %></td>
            <td><%= dtimings %></td>
            <td><%= atimings %></td>
            <td><%= sseats %></td>
            <td><%= sprice %></td>
            <td><%= slseats%></td>
            <td><%= slprice %></td>
            
            <td><a href="Book.jsp?id=<%= id %>">Book</a></td>
        </tr>
    <% }  %>
    </table>

<%
        cn.close();
    }
    catch(Exception e){
        out.println(e.getMessage());
    }
%>
<br>
<br>
<br>
<form action="LogoutServlet" method="post">
<button type="submit">Logout</button>
</form>
</body>
</html>