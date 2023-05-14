import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import information.Plants;

@WebServlet(name = "showPlants", urlPatterns = "/plants")
public class PlantsListServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Plants> arrayListPlants = new ArrayList<Plants>();

        try {
            Connection conn = ConnectMySQL.getConnection(ConnectMySQL.DB_URL, ConnectMySQL.USER_NAME,
                    ConnectMySQL.PASSWORD);

            String sql = "SELECT * FROM plants";

            // create statement
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String plantName = rs.getString(2);
                String cropName = rs.getString(3);
                int plantingMonth = rs.getInt(4);
                int plantingTime = rs.getInt(5);
                String weatherConditions = rs.getString(6);
                String uses = rs.getString(7);
                String notes = rs.getString(8);
                String plantImg = rs.getString(9);
                Plants plants = new Plants(plantName, cropName, plantingMonth, plantingTime, weatherConditions, uses,
                        notes, plantImg);
                arrayListPlants.add(plants);
            }

            stmt.close();
            conn.close();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        response.setContentType("application/json");
        request.setAttribute("arrayListPlants", arrayListPlants);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/plants.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
