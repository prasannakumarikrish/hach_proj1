import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * A servlet that takes message details from user and send it as a new e-mail
 * through an SMTP server.
 *
 * @author www.codejava.net
 *
 */
@WebServlet("/EmailSendingServlet1")
public class EmailSendingServlet1 extends HttpServlet {
    private String host;
    private String port;
    private String user;
    private String pass;
    private String name;
    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");
        name = context.getInitParameter("name");
    }
 
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         PrintWriter out = response.getWriter(); 
        // reads form fields     
 String s=null;        
//final String recipient="prasannanura28@gmail.com";
String recipient = null;
String password2=null;
        String acc_no = request.getParameter("name");
        int reg_id=Integer.valueOf(acc_no);
        String subject="Passaword wrong";
        String content="I think your password is hacked";
        String password1=request.getParameter("password");
        //String subject = request.getParameter("subject");
        //String content = request.getParameter("content");
        try{
                   

                     Class.forName("com.mysql.jdbc.Driver").newInstance();
                   
                      
                     Connection con=null;
                     

                     String connectionURL = "jdbc:mysql://localhost:3306/phackathon?zeroDateTimeBehavior=convertToNull";
                     
                     con = DriverManager.getConnection(connectionURL, "root", "");
                       
                     Statement stmt= con.createStatement();
                     
                      
                    
                     
                     // PreparedStatement ps=con.prepareStatement("select * from customer where reg_id =?");

                    // ps.setInt(1,reg_id);                   
                     
                     out.print("<table width=25% border=1>");

                     out.print("<center><h1>Result:</h1></center>");

                     //ResultSet rs=ps.executeQuery();                

                     /* Printing column names */

//                     ResultSetMetaData rsmd=rs.getMetaData();
                    
                   /*  while(rs.next())

                        {

                    

                     }*/

                     //out.print("</table>");
                         
                     

             // recipient=rs.getString(3);

 
        String resultMessage = "";
 
         try {
              
              PreparedStatement ps=con.prepareStatement("select * from customer where acc_no =?");
             ps.setString(1,acc_no);
             out.println("line-123");
             ResultSet rs=ps.executeQuery(); 
             while(rs.next())

                      {
                     // final String
                     recipient=rs.getString(3);
                     password2=rs.getString(5);
                       }
             if(password1.equals(password2)){
                 resultMessage="your account password is correct";
                 request.setAttribute("Message", resultMessage); 
                 getServletContext().getRequestDispatcher("/Result.jsp").forward(request,response);
                      }
             else{
                  EmailUtility1.sendEmail(host, port, user, pass,  subject, acc_no,
                    content);
           
  resultMessage = "someone access your account will you go to see your registered E-mail to get your password";
  request.setAttribute("Message", resultMessage);
            getServletContext().getRequestDispatcher("/Result.jsp").forward(
                    request, response);
                  
             }
  
        } catch (Exception ex) {
            ex.printStackTrace();
            resultMessage = "There were an error: " + ex.getMessage();
        } finally {
            //request.setAttribute("Message", resultMessage);
            //getServletContext().getRequestDispatcher("/Result.jsp").forward(
              //      request, response);
        }
        }catch (Exception e2)

                {

                    e2.printStackTrace();

                }
    }
}