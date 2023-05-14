import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "deleteTask", urlPatterns = "/deleteTask")
public class DeleteTaskServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int taskId = Integer.parseInt(request.getParameter("taskId"));
        int projectId = Integer.parseInt(request.getParameter("projectId"));

        try {
            Connection conn = ConnectMySQL.getConnection(ConnectMySQL.DB_URL, ConnectMySQL.USER_NAME,
                    ConnectMySQL.PASSWORD);

            String sql = "DELETE FROM task WHERE task_id = ?";

            // create statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, taskId);

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
