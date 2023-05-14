import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(urlPatterns = { "/" })
public class NotFoundServlet extends HttpServlet {
    public void init() throws ServletException {
        getServletContext().getServletRegistration("default").addMapping("/");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.sendRedirect("notFound.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // do the same thing as HTTP GET request
        doGet(request, response);
    }

    public void destroy() {
        System.out.println("servlet is destroyed");
    }
}