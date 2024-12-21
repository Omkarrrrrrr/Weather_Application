

```markdown
# Weather Web App

A simple Weather Web App that provides real-time weather data based on the city name entered by the user. The app uses the OpenWeather API to fetch the weather details and displays them in a user-friendly interface.

## Project Overview

This Weather Web App allows users to enter a city name and receive the current weather data, including temperature, humidity, wind speed, and weather conditions. The app dynamically updates the weather details using Java Servlets as the backend and utilizes the OpenWeather API to get the weather information.

### Features:
- **City Search**: Allows users to search for weather information by entering a city name.
- **Weather Details**: Displays the temperature, humidity, wind speed, and weather condition.
- **Responsive Design**: Mobile-friendly design to ensure good user experience on all devices.
- **API Integration**: Fetches real-time weather data from the OpenWeather API.

## Technologies Used

- **Frontend**: 
  - HTML
  - CSS
  - JavaScript
- **Backend**: 
  - Java (JSP, Servlets)
  - OpenWeather API
- **Other**: 
  - Maven for project management
  - Apache Tomcat (or any servlet container)

## Setup Instructions

### 1. Clone the Repository

To get started, clone this repository to your local machine:

```bash
git clone https://github.com/your-username/weather-web-app.git
```

### 2. Prerequisites

Before running the project, make sure you have the following installed:

- **Java 8 or higher**
- **Apache Tomcat** (or any servlet container)
- **Maven** for project management
- **IDE** (e.g., Eclipse, IntelliJ IDEA) for running the project

### 3. Setting Up API Key

To access the weather data from the OpenWeather API, you need an API key:

1. Visit [OpenWeather](https://openweathermap.org/) and create an account if you don't have one.
2. Get your free API key from the OpenWeather dashboard.
3. Replace the `apiKey` value in the `ServletWeather.java` file with your API key:

```java
String apiKey = "your-api-key";
```

### 4. Running the Application

1. **Import the project** into your IDE.
2. **Build the project** using Maven. This will resolve dependencies automatically.
3. **Configure Tomcat** (or your chosen servlet container) to deploy the app.
4. **Run the application** on Tomcat, and open your browser to:

```
http://localhost:8080/WeatherApp2/
```

You should see the weather app, where you can enter a city name and view the weather data.

## Directory Structure

The directory structure for the project is as follows:

```
/WeatherApp
    /src
        /main
            /java
                /in
                    /ServletWeather.java    # Main servlet to fetch and display weather data
            /webapp
                /WEB-INF
                    /web.xml               # Servlet configuration
                /images
                    /weather-logo.png     # Weather logo for the web page
                /index.jsp                # Frontend display page
                /style.css                # Custom CSS for the frontend
```

- **`ServletWeather.java`**: Contains the backend logic to fetch weather data from the OpenWeather API.
- **`index.jsp`**: Displays the HTML page with the search bar and weather details.
- **`style.css`**: Contains all the custom styling for the app's layout and design.
- **`weather-logo.png`**: Image logo for the app.

## Contributions & License

Feel free to fork this project, raise issues, or submit pull requests if you want to contribute.


---

Thank you for using the Weather Web App! 😊
```

