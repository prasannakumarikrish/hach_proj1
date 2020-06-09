package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class Display_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<body bgcolor=\"Yellow\">\n");
      out.write("  <table border=\"1\" width=\"30%\" height=\"30%\">\n");
      out.write("  <tr><th><font color='Red'>EMP ID</font></th><th><font color='Red'>EMP NAME</font></th><th><font color='Red'>SALARY</font></th></tr>\n");

    try{
 Connection con=null;
 String connectionURL = "jdbc:mysql://localhost:3306/hackathon?zeroDateTimeBehavior=convertToNull";
 con = DriverManager.getConnection(connectionURL, "root", "");
Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery("SELECT *  FROM customer WHERE reg_id=-556352414");
  while(rs.next())
  {
      String Username=rs.getString(1);
      String Useremail=rs.getString(2);
      String Userphone=rs.getString(3);
      int Salary=rs.getInt(5);
  
      out.write("\n");
      out.write("<tr>\n");
      out.write("<td><b><font color='#663300'>");
      out.print(Username);
      out.write("</font></b></td>\n");
      out.write("<td><b><font color='#663300'>");
      out.print(Useremail);
      out.write("</font></b></td>\n");
      out.write("<td><b><font color='#663300'>");
      out.print(Userphone);
      out.write("</font></b></td>\n");
      out.write("<td><b><font color='#663300'>");
      out.print(Salary);
      out.write("</font></b></td>\n");
      out.write("</tr>\n");

  }
}catch(Exception e)
{
 e.printStackTrace();
}
 
      out.write("\n");
      out.write(" </table>\n");
      out.write("     </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
