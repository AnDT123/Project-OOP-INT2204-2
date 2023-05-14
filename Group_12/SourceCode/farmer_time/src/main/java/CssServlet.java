import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "CssServlet", urlPatterns = { "*.css" })
public class CssServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy đường dẫn của file CSS được yêu cầu
        String cssPath = request.getServletPath();

        // Đọc nội dung của file CSS
        InputStream in = getServletContext().getResourceAsStream(cssPath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line + "\n");
        }
        reader.close();

        // Thiết lập header cho phản hồi
        response.setContentType("text/css");

        // Ghi nội dung của file CSS vào phản hồi
        PrintWriter out = response.getWriter();
        out.println(stringBuilder.toString());
        out.close();
    }
}
