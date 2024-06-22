/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jiya
 */
public class agent_check_login extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo", "root", "")) {
                Statement smt = cn.createStatement();

                ResultSet rs = smt.executeQuery("SELECT * FROM agent_data WHERE email='" + email + "' AND BINARY password='" + password + "'");

                if (rs.next()) {
                    int status = rs.getInt("status");
                    if (status == 1) {
                        String Agent_name = rs.getString("agent_name");
                        String Agent_id = rs.getString("id");
                        String Agency_name = rs.getString("agency_name");
                        String Mob_num = rs.getString("mobileno");
                        String Gst_no = rs.getString("gstno");
                        String Agency_email = rs.getString("email");

                        HttpSession hs = request.getSession(true);
                        hs.setAttribute("mail", email);
                        hs.setAttribute("Agent_name", Agent_name);
                        hs.setAttribute("Agent_id", Agent_id);
                        hs.setAttribute("Agency_name", Agency_name);
                        hs.setAttribute("Mob_num", Mob_num);
                        hs.setAttribute("Gst_no", Gst_no);
                        hs.setAttribute("Status", status);
                        hs.setAttribute("Agency_email", Agency_email);

                        RequestDispatcher rd = request.getRequestDispatcher("Agent_home.jsp");
                        rd.forward(request, response);
                    } else {
                        out.println("<h2>Your account is not approved yet. Please contact the admin.</h2>");
                        RequestDispatcher rd = request.getRequestDispatcher("Agent_Login.html");
                        rd.include(request, response);
                    }
                } else {
                    out.println("<h2>Invalid User ID or password</h2>");
                    out.println("<h2>Try Again</h2>");
                    RequestDispatcher rd = request.getRequestDispatcher("Agent_Login.html");
                    rd.include(request, response);
                }
            } catch (SQLException e) {
                out.println("<h2>SQL Error: " + e.getMessage() + "</h2>");
            }
        } catch (ClassNotFoundException e) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<h2>An error occurred: " + e.getMessage() + "</h2>");
            }
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
