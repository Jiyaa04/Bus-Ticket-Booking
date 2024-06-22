<%-- 
    Document   : Approve
    Created on : 03-Jun-2024, 3:32:33 pm
    Author     : Jiya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.mycompany.Mailer"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Statement"%>
<%@page import="javax.servlet.RequestDispatcher"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String agency_email = request.getParameter("agency_email");
    String agency_name = request.getParameter("agency_name");

    out.println("agency_email: " + agency_email + "<br>");
    out.println("agency_name: " + agency_name + "<br>");

    if (agency_email != null && agency_name != null) {
                Connection cn = null;
                Statement smt = null;
                
                try {
                    // Load JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    // Establish connection
                    cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo", "root", "");
                    // Create statement
                    smt = cn.createStatement();
                    // Update the agent status
                    int i = smt.executeUpdate("UPDATE agent_data SET status=true WHERE email='" + agency_email + "'");

                    if (i > 0) {
                        // Include the Unverified servlet for updating the list
                        RequestDispatcher rd = request.getRequestDispatcher("Unverified");
                        rd.include(request, response);

                        out.println("<h2>Successfully approved " + agency_name + "</h2>");

                        // Send email notification using Mailer class
                        String sub = "Agency Approved for Jigimigi";
                        String msg = "Your Travel Agency has been approved. You can login now with your entered credentials.";
                        Mailer.send(agency_email, sub, msg);

                        out.println("<h2>Mail sent successfully to " + agency_email + "</h2>");
                    } else {
                        out.println("Failed to approve the agency.");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    out.println("Database error: " + e.getMessage());
                } finally {
                    try {
                        if (smt != null) smt.close();
                        if (cn != null) cn.close();
                    } catch (SQLException e) {
                        out.println("Error closing resources: " + e.getMessage());
                    }
                }
            } else {
                out.println("Invalid agency email or name.");
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