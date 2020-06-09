/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.sql.*;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PRASANNA
 */
@MultipartConfig(maxFileSize = 16177215) // upload file up to 16MB
public class UploadServlet extends HttpServlet {
        
    Connection con;    
    PreparedStatement ps = null;    
   
public int generateId(){  
    int randomId = 0;    
    Random rand = new Random();     
    for (int j=0;j<10;j++)    
   {       
       randomId = rand.nextInt();      
   }     
      return randomId;     
} 

    private static final long serialVersionUID = -1623656324694499109L;
    private Connection conn;
      protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
         response.setContentType("text/html");
        request.getRequestDispatcher("upload.jsp");
        PrintWriter pw = response.getWriter();
        String connectionURL = "jdbc:mysql://localhost:3306/phackathon?zeroDateTimeBehavior=convertToNull";
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(connectionURL, "root", "");
  //PrintWriter pw = response.getWriter(); 
request.getRequestDispatcher("registration.jsp");  
 // String connectionURL = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
      //  Connection con=null;
      //  PrintWriter pw = response.getWriter();
        pw.println("line 36");
//Class.forName("com.mysql.jdbc.Driver").newInstance();
pw.println("line 38");
//con = DriverManager.getConnection(connectionURL, "root", "");
pw.println("line 39");
//PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES(?,?)");
Statement stmt= con.createStatement();
pw.println("line 42");
//File file = new File("C:/hack6.jpg");

//FileInputStream fs = new FileInputStream(file);
//ps.setInt(1,3);
//ps.setBinaryStream(2,fs,fs.available());
//int i = ps.executeUpdate();
//ps.close();
//con.close();

pw.println("Connected");
/*if(i!=0){
pw.println("image inserted successfully");
}else{
pw.println("problem in image insertion");
}*/

        // gets values of text fields
        String firstName = request.getParameter("id");
        pw.println("idaff="+firstName);
        String acc_no=request.getParameter("acc");
        String ifsc=request.getParameter("ifsc");
        String password=request.getParameter("password1");
        String combo=request.getParameter("reg_type"); 
        //String lastName = request.getParameter("lastName");

        InputStream inputStream = null;
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // debug messages
            pw.println(filePart.getName());
            pw.println(filePart.getSize());
            pw.println(filePart.getContentType());

            // obtains input stream of the upload file
           pw.println( inputStream = filePart.getInputStream());
            pw.println("line:83");
        }
        String message = null; // message will be sent back to client
pw.println("line:86");
        try {          
            pw.println("line:91");
            PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?)");
            pw.println("line:93");
            //statement.setString(1, firstName);
            ps.setString(1, firstName);
            pw.println("line:95");
            //statement.setString(2, lastName);

            if (inputStream!= null) {
                // fetches input stream of the upload file for the blob column
                //statement.setBlob(2, inputStream);
                ps.setBlob(2, inputStream);
            }
            
            ps.setString(3, acc_no);
            ps.setString(4, ifsc);
            ps.setString(5, password);
            ps.setString(6,combo);
            // sends the statement to the database server
            //int row = statement.executeUpdate();
            int row = ps.executeUpdate();
            if (row > 0) {
                message = "Image is uploaded successfully into the Database";
            }
        } catch (SQLException ex) {
            message = "ERROR: " + ex.getMessage();
            ex.printStackTrace();
        }
        // sets the message in request scope
        request.setAttribute("Message", message);
        
        // forwards to the message page
        request.setAttribute("idaff",firstName);
        getServletContext().getRequestDispatcher("/Display.jsp").forward(request, response);
        }catch (Exception e){
    e.printStackTrace();
System.out.println(e);
}finally{
            //request.setAttribute("idaff",FirstName);
            //getServletContext().getRequestDispatcher("/submit.jsp").forward(request, response);
          
      }
    }
}
