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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jiya
 */
public class Unverified extends HttpServlet {

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
        out.println("<center><h3>Unverified Travel agencies </h3></center>");     
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo","root","");
        
        Statement  smt = cn.createStatement();
        
        ResultSet rs =smt.executeQuery("select * from agent_data where status= 'false'");
        out.println("<table border='2'>");
        out.println("<tr><th>Id</th><th>Agent Name</th><th>Agency Name</th><th>Email id</th><th>Contact_No</th><th>GST Number</th><th>Age</th><th>Password</th><th>Gender</th><th>Approve</th></tr>");


        while(rs.next()){
            String id=rs.getString("id");
            String agent_name=rs.getString("agent_name");
            String agency_name=rs.getString("agency_name");
            String email=rs.getString("email");
            String mobileno=rs.getString("mobileno");
            String gstno=rs.getString("gstno");
            String age=rs.getString("age");
            String password=rs.getString("password");
            String gender=rs.getString("gender");
            
        out.println("<tr><td>"+id+"</td><td>"+agent_name+"</td><td>"+agency_name+"</td><td>"+email+"</td><td>"+mobileno+"</td><td>"+gstno+"</td><td>"+age+"</td><td>"+password+"</td><td>"+gender+"</td><td><a href='Approve.jsp?agency_email=" + email + "&agency_name=" + agency_name + "'>Approve</a></td></tr>");               
        }
        out.println("</table>");  
        out.println("<br>");
out.println("<br>");
out.println("<br>");
out.println("<form action=\"LogoutServlet\" method=\"post\">");
out.println("<button type=\"submit\">Logout</button>");
out.println("</form>");
    }
    catch(ClassNotFoundException | SQLException e){
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
