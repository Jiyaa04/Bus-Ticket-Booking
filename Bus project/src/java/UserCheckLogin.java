/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Jiya
 */
public class UserCheckLogin extends HttpServlet {

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
        try{
        
             String email=request.getParameter("email");
             String password=request.getParameter("password");
                        
Class.forName("com.mysql.cj.jdbc.Driver");

Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo", "root", "");

            Statement smt =  cn.createStatement();

         ResultSet rs=smt.executeQuery("select * from User_Data where email='"+email+"' and binary password='"+password+"'");   
           
         if(rs.next())
            {
               String name = rs.getString("name");
               HttpSession hs = request.getSession(true); // This will create a new session if one does not exist
               hs.setAttribute("User_email", email);
               hs.setAttribute("User_name", name);
               String encodedURL = response.encodeURL("User_home.jsp");
                response.sendRedirect(encodedURL);
            }
            
         else
         {
          out.println("<h2>Invalid User ID or password</h2>");
          RequestDispatcher rd=request.getRequestDispatcher("index.html");
          rd.include(request, response);
         }
         cn.close();     
        }
        catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
    e.printStackTrace(out);
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
