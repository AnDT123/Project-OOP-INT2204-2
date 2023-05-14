import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "task", urlPatterns = "/addTask")
public class AddTaskServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int projectId = Integer.parseInt(request.getParameter("projectId"));

        String taskName = (String) request.getParameter("taskName");
        taskName = taskName.trim();
        if (taskName == "")
            taskName = "No title";
            
        taskName = taskName.replaceAll("\n", "<br>");

        try {
            Connection conn = ConnectMySQL.getConnection(ConnectMySQL.DB_URL, ConnectMySQL.USER_NAME,
                    ConnectMySQL.PASSWORD);

            String sql = "INSERT INTO task(project_id, task_name) VALUES(?, ?)";

            // create statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, projectId);
            stmt.setString(2, taskName);

            stmt.execute();

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        request.setAttribute("projectId", projectId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("showTask");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
