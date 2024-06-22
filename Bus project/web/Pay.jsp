<%-- 
    Document   : Pay
    Created on : 03-Jun-2024, 1:06:32 pm
    Author     : Jiya
--%>

<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="styles.css">
    </head>
<body>
    <h1>Choose payment methods</h1>
    <form action="Bookiessss.jsp" method="post"> <!-- Changed method to "post" for form submission -->
        <br>            
        <%
            double finalssprice = 0.0;
            double finalslsprice = 0.0;
            double totalssprice = 0.0;
            
            String sseats = request.getParameter("sseats");
            String slseats = request.getParameter("slseats");
            String dd = request.getParameter("id");
            String ccemailid = request.getParameter("ccemailid");
            String emaill = request.getParameter("emaill");
            
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo", "root", "");
                Statement smt = cn.createStatement();
                ResultSet rs = smt.executeQuery("select * from registered_bus where id = " + dd);

                if (rs.next()) {
                    String sseats_price = rs.getString("sprice");
                    String slseats_price = rs.getString("slprice");
                    String Bus_num = rs.getString("bus_num");

                    double sseatsInt = Double.parseDouble(sseats);
                    double slseatsInt = Double.parseDouble(slseats);
                    double sseatspriceInt = Double.parseDouble(sseats_price);
                    double slseatspriceInt = Double.parseDouble(slseats_price);

                    if (sseatsInt > 0) {
                        finalssprice = sseatsInt * sseatspriceInt;
                    }

                    if (slseatsInt > 0) {
                        finalslsprice = slseatsInt * slseatspriceInt;
                    }
                    
                    totalssprice = finalssprice + finalslsprice;
                } else {
                    out.println("No data found for the provided bus_id.");
                }

                cn.close();
            } catch (Exception e) {
                out.println("An error occurred: " + e.getMessage());
            }
        %>
        <% if (finalssprice > 0) { %>
            Seater Seats Price : <%= finalssprice %><br><br>
        <% } %>
        <% if (finalslsprice > 0) { %>
            Sleeper Seats Price : <%= finalslsprice %><br>
        <% } %>
        <br>
        Total Bus Fare : <%= totalssprice %>
        <br>
        <label for="payment_method">Select Payment Method:</label>
        <select id="payment_method" name="payment_method">
            <option value="credit_card">Credit Card</option>
            <option value="debit_card">Debit Card</option>
            <option value="net_banking">Net Banking</option>
            <option value="wallet">UPI</option>
        </select>
        <br>
        <br>
        <input type="submit" value="Proceed">
    </form>
    <%-- Set session attributes after form processing --%>
    <%
        HttpSession hs = request.getSession(false);
        if (hs != null) {
            hs.setAttribute("Bus_id", dd);
            hs.setAttribute("Sseats", sseats);
            hs.setAttribute("Slseats", slseats);
            hs.setAttribute("ccemailid", ccemailid);
            //hs.setAttribute("Bus_num", Bus_num);
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
