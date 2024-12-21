package in;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class ServletWeather
 */
@WebServlet("/ServletWeather")
public class ServletWeather extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletWeather() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // API setup
        String apiKey = "bbc56ef8d59c55b8f04830826a10f99d";

        // Getting city from form input
        String city = request.getParameter("city");

        if (city == null || city.trim().isEmpty()) {
            request.setAttribute("error", "City name cannot be empty.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        // URL-encode the city name to handle spaces and special characters
        String encodedCity = URLEncoder.encode(city.trim(), StandardCharsets.UTF_8);

        // Create the URL for the OpenWeatherMap API request
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCity + "&appid=" + apiKey;

        HttpURLConnection connection = null;
        try {
            // API integration
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Check HTTP response code
            int responseCode = connection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                request.setAttribute("error", "Unable to fetch weather data. Please try again.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            // Reading the data from the network
            InputStream inputStream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);

            // Use StringBuilder to store response content
            StringBuilder responseContent = new StringBuilder();
            Scanner sc = new Scanner(reader);
            while (sc.hasNext()) {
                responseContent.append(sc.nextLine());
            }
            sc.close();

            // Parsing JSON response
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(responseContent.toString(), JsonObject.class);

            // Extract data from JSON response
            long dateTimestamp = jsonResponse.get("dt").getAsLong() * 1000;
            String date = new Date(dateTimestamp).toString();

            double temperatureKelvin = jsonResponse.getAsJsonObject("main").get("temp").getAsDouble();
            int temperatureCelsius = (int) (temperatureKelvin - 273.15);

            int humidity = jsonResponse.getAsJsonObject("main").get("humidity").getAsInt();
            double windSpeed = jsonResponse.getAsJsonObject("wind").get("speed").getAsDouble();
            String weatherCondition = jsonResponse.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();

            // Set the data as request attributes (for sending to the JSP page)
            request.setAttribute("date", date);
            request.setAttribute("city", city);
            request.setAttribute("temperature", temperatureCelsius);
            request.setAttribute("weatherCondition", weatherCondition);
            request.setAttribute("humidity", humidity);
            request.setAttribute("windSpeed", windSpeed);

            // Forward the request to the index.jsp page for rendering
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while processing your request. Please try again.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
