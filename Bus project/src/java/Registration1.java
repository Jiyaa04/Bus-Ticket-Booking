/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
public class Registration1 extends HttpServlet {

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
                        
                        String name=request.getParameter("name");
                        String email=request.getParameter("email");
                        String mobileno = request.getParameter("mobileno");
                        String age=request.getParameter("age");
                        String city=request.getParameter("city");
                        String password=request.getParameter("password");
                        String gender=request.getParameter("gender");
                        
           Class.forName("com.mysql.cj.jdbc.Driver");
           
           Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo","root","");
           
           Statement smt = cn.createStatement();
           
           int i = smt.executeUpdate("insert into User_Data(name,email,mobileno,age,city,password,gender) values('" + name + "','" + email + "','" + mobileno + "','" + age + "','" + city + "','" + password + "','" + gender + "')");

           if(i>0){
               out.println("SUCCESSFULLY REGISTERED");
               out.println("<br>");
               out.println("Please login");
               RequestDispatcher rd= request.getRequestDispatcher("User_login.html");
               rd.include(request, response);
               
           }
           cn.close();
        }
        
        catch(IOException | ClassNotFoundException | SQLException | ServletException e){
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
