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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jiya
 */
public class Register_Bus extends HttpServlet {

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
                HttpSession hs = request.getSession(false); 

        try{ 
       
        String bus_num = request.getParameter("bus_num");
        String from=request.getParameter("from");
        String to = request.getParameter("to");
        String date=request.getParameter("date");
        String dtimings=request.getParameter("dtimings");
        String atimings=request.getParameter("atimings");
        String sseats=request.getParameter("sseats");
        String slseats=request.getParameter("slseats");
        String driver_name =request.getParameter("driver_name");
        String driver_num =request.getParameter("driver_num");
        String sprice= request.getParameter("sprice");
        String slprice = request.getParameter("slprice");
        String agency_email = (String) hs.getAttribute("Agency_email");
        String Agent_id = (String) hs.getAttribute("Agent_id");
        String agency_name = (String) hs.getAttribute("Agency_name");  
        
        Class.forName("com.mysql.cj.jdbc.Driver");
           
           Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo","root","");
           
           Statement smt = cn.createStatement();
           
 String query = "INSERT INTO registered_bus (bus_num, agency_name, `from`, `to`, `date`, dtimings, atimings, sseats, slseats, driver_name, driver_num, sprice, slprice, agency_email, agency_id, bus_status) " +
                           "VALUES ('" + bus_num + "', '" + agency_name + "', '" + from + "', '" + to + "', '" + date + "', '" + dtimings + "', '" + atimings + "', '" + sseats + "', '" + slseats + "', '" + driver_name + "', '" + driver_num + "', '" + sprice + "', '" + slprice + "', '" + agency_email + "', '" + Agent_id + "', 1)";

            int i = smt.executeUpdate(query);
           if(i>0){
               out.println("BUS DETAILS SUCCESSFULLY REGISTERED");
               out.println("<br>");
               RequestDispatcher rd= request.getRequestDispatcher("Agent_home");
               rd.include(request, response);   
           }
           else{
           out.println("Data not inserted");
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
