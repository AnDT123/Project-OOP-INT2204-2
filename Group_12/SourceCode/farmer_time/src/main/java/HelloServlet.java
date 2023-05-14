
// Import required java libraries
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

// Extend HttpServlet class
public class HelloServlet extends HttpServlet {

   private String message;

   ServletConfig config = null;

   public String querySQL() {
      try {
         // connect to database 'sakila'
         Connection conn = ConnectMySQL.getConnection(ConnectMySQL.DB_URL, ConnectMySQL.USER_NAME,
               ConnectMySQL.PASSWORD);
         // crate statement
         Statement stmt = conn.createStatement();
         // get data from table 'customers'
         ResultSet rs = stmt.executeQuery("select * from payments");

         String s = "";
         // show data
         while (rs.next()) {
            s = s + rs.getInt(1) + "  " + rs.getString(2) + "<br>";
         }
         conn.close();
         return s;
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return "";
   }

   public void init(ServletConfig config) throws ServletException {
      // Do required initialization
      message = "Hello Servlet!!";
      this.config = config;
      System.out.println("Servlet is initialized");

   }

   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      // Set response content type
      response.setContentType("text/html");
      // Actual logic goes here.
      PrintWriter out = response.getWriter();
      out.print("<html><body>");
      out.println("<h1>" + message + "</h1>");
      out.println("<p>" + querySQL() + "</p>");
      out.print("</body></html>");
   }

   // Runs as a thread whenever there is HTTP POST request
   public void doPost(HttpServletRequest request, HttpServletResponse response)
         throws IOException, ServletException {
      // do the same thing as HTTP GET request
      doGet(request, response);
   }

   public void destroy() {
      System.out.println("servlet is destroyed");
   }
}