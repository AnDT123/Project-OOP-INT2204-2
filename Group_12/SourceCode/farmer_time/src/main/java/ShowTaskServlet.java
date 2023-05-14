import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import information.TaskInfo;
import information.ProjectInfo;

@WebServlet(name = "showTask", urlPatterns = "/showTask")
public class ShowTaskServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<TaskInfo> arrayListTask = new ArrayList<TaskInfo>();
        ProjectInfo projectInfo = null;
        int projectId = 1;

        try {
            String sid = (String) request.getParameter("projectId");

            if (sid == null || sid.isEmpty()) {
                projectId = (int) request.getAttribute("projectId");

            } else {
                projectId = Integer.parseInt(sid);
            }
        } catch (Exception ex) {
            projectId = 1;
        }

        try {
            Connection conn = ConnectMySQL.getConnection(ConnectMySQL.DB_URL, ConnectMySQL.USER_NAME,
                    ConnectMySQL.PASSWORD);

            String sql = "SELECT * FROM task WHERE project_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, projectId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int taskId = rs.getInt(1);
                String taskName = rs.getString(3);
                String description = rs.getString(4);
                String priority = rs.getString(7);
                String status = rs.getString(8);
                String startTime = rs.getString(5);
                String finishTime = rs.getString(6);
                if (finishTime != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date endDate = sdf.parse(finishTime);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    finishTime = dateFormat.format(endDate);
                }
                TaskInfo taskInfo = new TaskInfo(taskId, taskName, description, priority, status, startTime,
                        finishTime);
                arrayListTask.add(taskInfo);
            }

            sql = "SELECT * FROM project WHERE project_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, projectId);

            rs = stmt.executeQuery();

            while (rs.next()) {
                String projectName = rs.getString(3);
                String description = rs.getString(4);
                String img = rs.getString(5);
                String priority = rs.getString(9);
                String status = rs.getString(8);
                String startTime = rs.getString(6);
                String finishTime = rs.getString(7);

                if (finishTime != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date endDate = sdf.parse(finishTime);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    finishTime = dateFormat.format(endDate);
                }
                projectInfo = new ProjectInfo(projectName, description, img, priority, status, startTime,
                        finishTime, projectId);
            }

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        response.setContentType("application/json");
        request.setAttribute("arrayListTask", arrayListTask);
        request.setAttribute("projectInfo", projectInfo);
        request.setAttribute("projectId", projectId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/todoList.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
