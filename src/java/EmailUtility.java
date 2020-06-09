import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.io.*;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
    public static void sendEmail(String host, String port,
            final String userName, final String password, 
            String subject,int reg_id, String message) throws AddressException,
            MessagingException {
       // PrintWriter out = response.getWriter();
 //String toAddress,
 String toAddress="prasannanura28@gmail.com";
 String s=null;
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        
        try{
        Class.forName("com.mysql.jdbc.Driver").newInstance();
                   
                      
                     Connection con=null;
                     

                     String connectionURL = "jdbc:mysql://localhost:3306/hackathon?zeroDateTimeBehavior=convertToNull";
                     
                     con = DriverManager.getConnection(connectionURL, "root", "");
                       
                     Statement stmt= con.createStatement();
                      
                     PreparedStatement ps=con.prepareStatement("select * from customer where reg_id =?");
                     
                     

                     ps.setInt(1,reg_id);         
                      ResultSet rs=ps.executeQuery(); 
                       ResultSetMetaData rsmd=rs.getMetaData();
                       while(rs.next())

                        {

                    // out.print("<tr>");

                    // out.print("<td>"+rsmd.getColumnName(1)+"</td>");

                     //   out.print("<td>"+(s=rs.getString(1))+"</td></tr>");

                      //  out.print("<tr><td>"+rsmd.getColumnName(2)+"</td>");

                       // out.print("<td>"+rs.getString(2)+"</td></tr>");

                       // out.print("<tr><td>"+rsmd.getColumnName(3)+"</td>");

                      //  out.print("<td>"+rs.getString(3)+"</td></tr>");

                      //  out.print("<tr><td>"+rsmd.getColumnName(4)+"</td>");

                       // out.print("<td>"+rs.getString(4)+"</td></tr>");   
                       
                        s=rs.getString(3);
                        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(s) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
 
        // sends the e-mail
        Transport.send(msg);
                     }
        
        
        
        }catch (Exception e2)

                {

                    e2.printStackTrace();

                }
 
    }
}