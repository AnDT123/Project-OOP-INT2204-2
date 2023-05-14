import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "calendar", urlPatterns = "/getCalendar")
public class CalendarServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Calendar calendar = Calendar.getInstance();
        String monthText = (String) request.getParameter("month");
        String yearText = (String) request.getParameter("year");
        String option = (String) request.getParameter("option");
        int year, month;
        if (yearText == null || monthText == null || option == null) {
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
        } else {
            year = Integer.parseInt(yearText);
            month = Integer.parseInt(monthText);
            if (option.equals("prev")) {
                month--;
                if (month < 1) {
                    month = 12;
                    year--;
                }
            } else if (option.equals("next")) {
                month++;
                if (month > 12) {
                    month = 1;
                    year++;
                }
            }
            calendar.set(year, month - 1, 1);
        }

        request.setAttribute("month", month);
        request.setAttribute("monthString",
                calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()));
        request.setAttribute("year", year);

        RequestDispatcher view = request.getRequestDispatcher("/calendar.jsp");
        view.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            doGet(request, response);
    }
}
