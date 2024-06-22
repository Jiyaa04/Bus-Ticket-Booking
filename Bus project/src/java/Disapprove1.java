/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import com.mycompany.Mailer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jiya
 */
public class Disapprove1 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String agency_email = request.getParameter("emaill");

        if (agency_email == null || agency_email.isEmpty()) {
            out.println("Invalid agency email.");
            RequestDispatcher rd = request.getRequestDispatcher("Admin_home.html");
            rd.include(request, response);
            return;
        }

        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo", "root", "");
            Statement smt = cn.createStatement();

            // Delete all buses registered with the agent's email
            int busesDeleted = smt.executeUpdate("DELETE FROM registered_bus WHERE agency_email = '" + agency_email + "'");
            // Delete the agent from the agent_data table
            int agentDeleted = smt.executeUpdate("DELETE FROM agent_data WHERE email = '" + agency_email + "'");

            if (agentDeleted > 0 && busesDeleted>0) {
                String sub = "Agency Disapproved for Jigimigi";
                String msg = "Your Travel Agency has been disapproved. You can try contacting the admin or try re-registration through the website.";
                Mailer.send(agency_email, sub, msg);

                RequestDispatcher rd = request.getRequestDispatcher("confirm_disapprove.jsp");
                rd.forward(request, response);
            } else {
                out.println("Some error occurred, Try again");
                RequestDispatcher rd = request.getRequestDispatcher("Admin_home.html");
                rd.include(request, response);
            }
            cn.close();
out.println("<br>");
out.println("<br>");
out.println("<br>");
out.println("<form action=\"LogoutServlet\" method=\"post\">");
out.println("<button type=\"submit\">Logout</button>");
out.println("</form>");

        } catch (ClassNotFoundException | SQLException e) {
            out.println(e.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
