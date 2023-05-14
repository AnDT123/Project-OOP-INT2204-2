import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "project", urlPatterns = "/addProject")
public class AddProjectServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String projectName = (String) request.getParameter("projectName");
        projectName = projectName.trim();
        if(projectName == "") projectName = "No title";

        String des = (String) request.getParameter("des");
        des = des.replaceAll("\n", "<br>");
        
        String img = (String) request.getParameter("img");
        String priority = (String) request.getParameter("priority");
        String status = (String) request.getParameter("status");

        String startTime = (String) request.getParameter("startTime");
        if (startTime.isEmpty()) startTime = null;

        String finishTime = (String) request.getParameter("finishTime");
        if (finishTime.isEmpty()) finishTime = null;
        
        try {
            Connection conn = ConnectMySQL.getConnection(ConnectMySQL.DB_URL, ConnectMySQL.USER_NAME,
                    ConnectMySQL.PASSWORD);

            String sql = "INSERT INTO project(project_name, description, img, priority, status, start_date, finish_date) VALUES(?, ?, ?, ?, ?, ?, ?)";

            // create statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, projectName);
            stmt.setString(2, des);
            stmt.setString(3, img);
            stmt.setString(4, priority);
            stmt.setString(5, status);
            stmt.setString(6, startTime);
            stmt.setString(7, finishTime);
            
            stmt.execute();
            
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        response.sendRedirect("showProject");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
