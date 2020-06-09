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
public class EmailUtility1 {
    public static void sendEmail(String host, String port,
            final String userName, final String password, 
            String subject,String reg_id, String message) throws AddressException,
            MessagingException {
       // PrintWriter out = response.getWriter();
 //String toAddress,
 String toAddress="prasannanura28@gmail.com";
 String s=null,s2=null;
 int s1;
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
        
                   
                      
                     Connection con=null,con1=null;
                     

                     String connectionURL = "jdbc:mysql://localhost:3306/phackathon?zeroDateTimeBehavior=convertToNull";
                      //String connectionURL1 = "jdbc:mysql://localhost:3306/hackathon?zeroDateTimeBehavior=convertToNull";
                    
                      con = DriverManager.getConnection(connectionURL, "root", "");
                     // con1 = DriverManager.getConnection(connectionURL1, "root", ""); 
                    
                      Statement stmt= con.createStatement();
                      //Statement stmt1= con1.createStatement();
                      
                     PreparedStatement ps=con.prepareStatement("select * from customer where reg_id =?");
                     
                     

                     ps.setString(1,reg_id);         
                      ResultSet rs=ps.executeQuery(); 
                       ResultSetMetaData rsmd=rs.getMetaData();
                       while(rs.next())

                        {

                    
                          s1=rs.getInt(1);
                          Class.forName("com.mysql.jdbc.Driver").newInstance();
                          String connectionURL1 = "jdbc:mysql://localhost:3306/hackathon?zeroDateTimeBehavior=convertToNull";
                           con1 = DriverManager.getConnection(connectionURL1, "root", "");
                           Statement stmt1= con1.createStatement();
                        //s=rs.getString(1);
                        //s1=Integer.valueOf(s);
                        PreparedStatement ps1=con1.prepareStatement("select * from customer where s1=?");
                         ps1.setInt(1,s1);
                        ResultSet rs1=ps1.executeQuery(); 
                        ResultSetMetaData rsmd1=rs1.getMetaData();
                       while(rs1.next())
                       {
                        s2=rs1.getString(3);
                        Session session = Session.getInstance(properties, auth);
                        
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(s2) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);
 
        // sends the e-mail
        Transport.send(msg);
                     }
                        }
        
        
        }catch (Exception e2)

                {

                    e2.printStackTrace();

                }
 
    }
}