import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.*;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.AddressComponent;
import com.google.maps.model.GeocodingResult;

import information.Weather;

import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

@WebServlet(name = "fetch", urlPatterns = "/getData")
public class WeatherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String API_KEY = "bf0fca40ef5c3565bb0d7b06f9952fae";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy thông tin về thành phố từ request parameter
        String city = request.getParameter("city");

        if (city == null || city.isEmpty()) {
            city = "Ha Noi";
        }

        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDQ0oIeBKADZTxl-TbBljeY_Hq7KwltpFk")
                .build();
        try {
            GeocodingResult[] results = GeocodingApi.geocode(context, "your-latitude, your-longitude").await();
            List<AddressComponent> components = Arrays.asList(results[0].addressComponents);
            for (AddressComponent component : components) {
                if (component.types[0].toString().equals("administrative_area_level_1")) {
                    System.out.println("Current province is: " + component.longName);
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Cannot determine current province: " + e.getMessage());
        }

        // Gửi yêu cầu tới API và lấy thông tin thời tiết
        HttpClient httpClient = HttpClientBuilder.create().build();
        String url = String.format(API_URL, URLEncoder.encode(city, StandardCharsets.UTF_8), API_KEY);

        HttpGet httpGet = new HttpGet(url);

        HttpResponse httpResponse = httpClient.execute(httpGet);
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode != HttpServletResponse.SC_OK) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.sendRedirect("/farmer_time/weather.jsp");
            return;
        }

        HttpEntity httpEntity = httpResponse.getEntity();
        String json = EntityUtils.toString(httpEntity);

        Object obj = JSONValue.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        city = (String) jsonObject.get("name");

        JSONObject main = (JSONObject) jsonObject.get("main");

        double temperature = 27.5;

        if (main.get("temp") instanceof Long) {
            long t = (Long) main.get("temp");
            temperature = (double) t;
        } else if (main.get("temp") instanceof Double) {
            temperature = (Double) main.get("temp");
        }


        double fellsLike = 10.5;
        if (main.get("feels_like") instanceof Long) {
            long t = (Long) main.get("feels_like");
            fellsLike = (double) t;
        } else if (main.get("temp") instanceof Double) {
            fellsLike = (Double) main.get("feels_like");
        }

        JSONObject wind = (JSONObject) jsonObject.get("wind");

        double windSpeed = (Double) wind.get("speed");
        windSpeed = (double) Math.round(windSpeed * 3.6 * 10) / 10;

        long humidity = (Long) main.get("humidity");

        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");

        JSONObject firstWeather = (JSONObject) weatherArray.get(0);
        String des = (String) firstWeather.get("description");

        String icon = (String) firstWeather.get("icon");

        String iconImage = "https://thumbs.dreamstime.com/z/sun-cloud-line-icon-sun-cloud-line-icon-partly-cloudy-weather-symbol-vector-illustration-155290413.jpg";

        try {
            Connection conn = ConnectMySQL.getConnection(ConnectMySQL.DB_URL, ConnectMySQL.USER_NAME,
                    ConnectMySQL.PASSWORD);

            String sql = "SELECT iconImage FROM weather WHERE iconName = ?";
            // crate statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, icon);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                iconImage = rs.getString(1);
            }
            
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (httpEntity != null) {
            // Lưu thông tin thời tiết vào request attribute và forward sang JSP
            Weather w = new Weather(city, temperature, des, humidity, windSpeed, fellsLike, iconImage);
            response.setContentType("application/json");
            request.setAttribute("weather", w);
            request.getRequestDispatcher("/weather.jsp").forward(request, response);
        } else {
            EntityUtils.consume(httpEntity);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            response.sendRedirect("/farmer_time/getData");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                doGet(request, response);
            }
}