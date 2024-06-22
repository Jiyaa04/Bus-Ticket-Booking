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
public class Agent_registeration extends HttpServlet {

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
                        
                        String agent_name=request.getParameter("agent_name");
                        String agency_name=request.getParameter("agency_name");
                        String email=request.getParameter("email");
                        String mobileno=request.getParameter("mobileno");
                        String gstno=request.getParameter("gstno");
                        String age=request.getParameter("age");
                        String password=request.getParameter("password");
                        String gender=request.getParameter("gender");
                        
                        
                        
           Class.forName("com.mysql.cj.jdbc.Driver");
           
           Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo","root","");
           
           Statement smt = cn.createStatement();
           
           int i=smt.executeUpdate("insert into agent_data(agent_name, agency_name, email, mobileno, gstno, Age, password, gender,status) values('"+agent_name+"','"+agency_name+"','"+email+"','"+mobileno+"','"+gstno+"','"+age+"','"+password+"','"+gender+"',false)");
           
           if(i>0){
               //out.println("SUCCESSFULLY REGISTERED");
               //out.println("NOW TRY LOGIN WHEN YOU GET APPROVED BY THE ADMINISTRATION");
               RequestDispatcher rd= request.getRequestDispatcher("Agent_succesful_registeration.jsp");
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
