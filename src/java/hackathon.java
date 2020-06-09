/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebListener;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;



/**
 *
 * @author PRASANNA
 */
public class hackathon extends HttpServlet {
    
    

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
        /* try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           /** out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet nothing</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<table>");
            out.println("<tr><td>Full Name:</td><td>");
            out.println("<input type=text name=name></td></tr>");
            out.println(" <tr><td>Mobile:</td><td><input type=number name=mobile></td></tr>");
            out.println(" <tr><td>E-Mail:</td><td><input type=email name=email></td></tr>");
               // <!--<tr><td>Date:</td><td><input type="date" name="date"></td></tr>-->
            out.println(" <tr><td>Registration ID:</td><td><input type=password name=reg_id/></td></tr>");
            out.println(" </table>");
            out.println("  <input type=file name=idcard><br><br>");
            out.println("<input type=submit value=save>");
            out.println("<h1>Servlet nothing at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
        
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
    
    protected void doPost(HttpServletRequest request,HttpServletResponse response)
            throws ServletException, IOException {
               processRequest(request, response);
               response.setContentType("text/html");
               //response.setContentType("image/jpg");
          request.getRequestDispatcher("registration.jsp");
         PrintWriter pw = response.getWriter();
         String connectionURL = "jdbc:mysql://localhost:3306/hackathon?zeroDateTimeBehavior=convertToNull";
        Connection con=null;
        String message = null;
        try
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            String date=dtf.format(now);
            pw.println("Date and time:"+date);  
            String name=request.getParameter("name");
            String mobile=request.getParameter("mobile");
            String email=request.getParameter("email");
            String password=request.getParameter("password1");
            //String idcard=request.getParameter("idcard");
            int id;
           pw.println(id=generateId());
           String str1 = Integer.toString(id);
           
           
                	

           // String ID=String.parseString(id);
            //List<FileItem> fileItemsList = uploader.parseRequest(request);
			//Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
            if(name==null)
            {
                pw.println("name is null");
            }
          try{
           // pw.println("idcard="+idcard+"name="+name);
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        con = DriverManager.getConnection(connectionURL, "root", "");
        PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?)");
        Statement stmt= con.createStatement();
        ps.setString(1,name);
        ps.setString(2,mobile);
        ps.setString(3,email);
        ps.setString(4,date);
        ps.setInt(5,id);
        ps.setString(6,password);
        int row=ps.executeUpdate();
         
        if (row > 0) {
            pw.println("Data updated successfully");
                           message = "Image is uploaded successfully into the Database";
                request.setAttribute("idaff", id);
         RequestDispatcher requestDispatcher = request.getRequestDispatcher("upload.jsp"); 
  requestDispatcher.forward(request, response);
           }
        }catch (SQLException ex) { 
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        }
          }catch (Exception e){
    e.printStackTrace();
System.out.println(e);
}
        
        
        request.setAttribute("Message", message);
    
       
    }
    public int generateId(){  
    int randomId = 0;    
    Random rand = new Random();     
    for (int j=0;j < 10;j++)    
   {       
       randomId = rand.nextInt();      
   }     
      return randomId;     
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
