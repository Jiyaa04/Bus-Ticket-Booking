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

/**
 *
 * @author Jiya
 */
public class Edit_bus extends HttpServlet {

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
        out.println("ON GOING BUSES");
        try{
            String agency_namee=request.getParameter("agency_name");
            
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demoo","root","");
        
        Statement  smt = cn.createStatement();
        
        ResultSet rs =smt.executeQuery("select * from registered_bus where agency_name='"+agency_namee+"'");
        out.println("<table border='2'>");
        out.println("<tr><th>Bus ID</th><th>Vehicle Number</th><th>Agency Name</th><th>Arrival Point</th><th>Destination Point</th><th>Date</th><th>Departure Timings</th><th>Arrival Timings</th><th>Seater seats</th><th>Sleaper Seats</th><th>Driver Name</th><th>Driver's Mobile Number</th><th>Seater Price</th><th>Sleeper Price</th><th>Delete</th></tr>");

        while(rs.next()){
            String id=rs.getString("id");
            String bus_num=rs.getString("bus_num");
            String agency_name=rs.getString("agency_name");
            String from=rs.getString("from");
            String to=rs.getString("to");
            String date=rs.getString("date");
            String dtimings=rs.getString("dtimings");
            String atimings=rs.getString("atimings");
            String sseats=rs.getString("sseats");
            String slseats=rs.getString("slseats");
            String driver_name=rs.getString("driver_name");
            String driver_num=rs.getString("driver_num");
            String sprice=rs.getString("sprice");
            String slprice=rs.getString("slprice");
           
            out.println("<tr><td>"+id+"</td><td>"+bus_num+"</td><td>"+agency_name+"</td><td>"+from+"</td><td>"+to+"</td><td>"+date+"</td><td>"+dtimings+"</td><td>"+atimings+"</td><td>"+sseats+"</td><td>"+slseats+"</td><td>"+driver_name+"</td><td>"+driver_num+"</td><td>"+sprice+"</td><td>"+slprice+"</td><td><a href='Delete_bus?idd="+id+"'>Delete</a></td></tr>");
            
        }
        out.println("</table>");
        cn.close();
        out.println("<br>");
out.println("<br>");
out.println("<br>");
out.println("<form action=\"LogoutServlet\" method=\"post\">");
out.println("<button type=\"submit\">Logout</button>");
out.println("</form>");
        }
        catch(ClassNotFoundException | SQLException e){
        out.print(e.getMessage());
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
