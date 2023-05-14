import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "ImageServlet", urlPatterns = { "*.jpg", "*.jpeg", "*.png", "*.gif" })
public class ImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy đường dẫn của file ảnh được yêu cầu
        String imagePath = request.getServletPath();

        // Đọc nội dung của file ảnh
        InputStream in = getServletContext().getResourceAsStream(imagePath);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead = 0;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        in.close();
        out.flush();
        out.close();
    }
}
