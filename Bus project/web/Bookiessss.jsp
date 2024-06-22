<%-- 
    Document   : Bookiessss
    Created on : 03-Jun-2024, 1:16:51 pm
    Author     : Jiya
--%>

<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.mycompany.Mailer"%>
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
    int available_sseats = 0;
    int available_slseats = 0;

    HttpSession hs = request.getSession(false);

    String User_name = (String) hs.getAttribute("User_name");
    String User_email = (String) hs.getAttribute("User_email");
    String Bus_id = (String) hs.getAttribute("Bus_id");
    int Sseats = Integer.parseInt((String) hs.getAttribute("Sseats"));
    int Slseats = Integer.parseInt((String) hs.getAttribute("Slseats"));
    String ccemailid = (String) hs.getAttribute("ccemailid");
    String Departure_point = (String) hs.getAttribute("Departure_point");
    String Date = (String) hs.getAttribute("Date");
    String Ariival_point =(String)hs.getAttribute("Arrival_point");
    String Departure_timings = (String) hs.getAttribute("Departure_timings");
    
    
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo", "root", "");
        Statement smt = cn.createStatement();

        ResultSet rs = smt.executeQuery("select * from registered_bus where id=" + Bus_id);
        if (rs.next()) {
            int total_sseats = rs.getInt("sseats");
            int total_slseats = rs.getInt("slseats");

            available_sseats = total_sseats - Sseats;
            available_slseats = total_slseats - Slseats;

            smt.executeUpdate("update registered_bus set sseats=" + available_sseats + ", slseats=" + available_slseats + " where id='" + Bus_id + "'");

            String sub = "Bus seats successfully booked on jhigimigi";
            
            String msg = "Your "+Sseats+" Seater Seats and "+Slseats+" have been booked, your departure time is "+Departure_timings+", from "+Departure_point+" to "+Ariival_point+", on "+Date+" ";
            
            Mailer.send(User_email, sub, msg); 
            %>
            
        <center><h1>Booking Successfully Done</h1></center>
        <center><h6>booking details have been sent to <%= User_email %></h6></center>
        <center><h2><u>Booking Details :</u></h2></center>
        
        Booking name :<%= User_name%>
        <br>
        Departure point :<%= Departure_point%>
        <br>
        Arrival point :<%= Ariival_point%>
        <br>
        Date :<%= Date%>
        <br>
        Departure Timings :<%= Departure_timings%>
        <br>
               
        <center><h1>Thanks for choosing jhigimigi</h1></center>
    
    <%
        
    %>
    <center><a href="User_home.jsp">Get me back to the home page</a></center>
        
        
        <%
}else {
%>
            Some error occurred
<%
        }

        cn.close();
    } catch (Exception e) {
        out.println("Exception: " + e.getMessage());
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

