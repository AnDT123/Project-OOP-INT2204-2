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

import information.ProjectInfo;

@WebServlet(name = "showProject", urlPatterns = "/showProject")
public class ShowProjectServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<ProjectInfo> arrayListProject = new ArrayList<ProjectInfo>();

        try {
            Connection conn = ConnectMySQL.getConnection(ConnectMySQL.DB_URL, ConnectMySQL.USER_NAME,
                    ConnectMySQL.PASSWORD);

            String sql = "SELECT project_name, description, img, priority, status, start_date, finish_date, project_id FROM project LIMIT 20";

            // create statement
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String projectName = rs.getString(1);
                String description = rs.getString(2);
                String img = rs.getString(3);
                String priority = rs.getString(4);
                String status = rs.getString(5);
                String startTime = rs.getString(6);
                String finishTime = rs.getString(7);
                int projectId = rs.getInt(8);
                if (finishTime != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date endDate = sdf.parse(finishTime);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    finishTime = dateFormat.format(endDate);
                }
                ProjectInfo projectInfo = new ProjectInfo(projectName, description, img, priority, status, startTime,
                        finishTime, projectId);
                arrayListProject.add(projectInfo);
            }

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        response.setContentType("application/json");
        request.setAttribute("arrayListProject", arrayListProject);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/project.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
