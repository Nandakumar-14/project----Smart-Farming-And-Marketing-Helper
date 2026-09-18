# Smart Farming Web Application

## Project Overview

A comprehensive desktop application designed to assist farmers with agricultural decision-making through intelligent recommendations and real-time weather information. This application provides crop recommendations, irrigation and fertilizer advisories, and weather data to help farmers optimize their agricultural practices.

## Problem Statement

Farmers often struggle with making informed decisions about crop selection, irrigation schedules, and fertilizer applications due to:
- Lack of access to expert agricultural knowledge
- Inability to predict weather conditions accurately
- Limited information about district-specific crop recommendations
- Need for personalized agricultural guidance

## Objectives

- Provide intelligent crop recommendations based on district, crop type, and season
- Offer irrigation and fertilizer advisories for various crops
- Deliver real-time weather information for agricultural planning
- Create a user-friendly interface for farmers of all technical levels
- Ensure secure user authentication and data management

## Main Features

### 1. **Crop Recommendation**
- District-specific crop suggestions for Tamil Nadu, India
- Crop type filtering (cereal, fruit, vegetable, cash, ornamental)
- Seasonal recommendations based on planting months
- Comprehensive database of 38 districts with seasonal crop data

### 2. **Irrigation Advisory**
- Detailed irrigation methods for different crops
- Frequency and water requirement guidelines
- Critical growth stage irrigation schedules
- Best practices and tips for efficient water management

### 3. **Fertilizer Advisory**
- NPK (Nitrogen, Phosphorus, Potassium) recommendations
- Organic fertilizer suggestions
- Application schedules for different growth stages
- Crop-specific nutrient management guidelines

### 4. **Weather Information**
- Real-time weather data using Open-Meteo API
- Temperature, humidity, and wind speed information
- Weather condition classification (Sunny, Cloudy, Rainy)
- Location-based weather forecasting

### 5. **User Authentication**
- Secure login and registration system
- MySQL database for user management
- Password encryption and validation
- Session management for personalized experience

### 6. **User-Friendly Interface**
- Graphical User Interface (GUI) built with Java Swing
- Intuitive navigation between different modules
- Responsive design with visual feedback
- Background images and icons for enhanced user experience

## Technology Stack

### Frontend
- **Java Swing** - Desktop GUI framework
- **AWT (Abstract Window Toolkit)** - Graphics and user interface
- **Java 2D** - Image processing and rendering

### Backend
- **Java** - Core programming language
- **JDBC (Java Database Connectivity)** - Database interaction
- **JSON Simple** - JSON parsing and data handling

### Database
- **MySQL** - User authentication and data storage
- **JDBC Driver** - MySQL connectivity

### APIs
- **Open-Meteo API** - Weather data retrieval
- **Geocoding API** - Location-based services

### Data Storage
- **JSON Files** - Crop, irrigation, and fertilizer data
- **MySQL Database** - User credentials and session management

## Project Architecture

```
SmartFarmingHelper-00-02-09/
├── src/
│   ├── LoginPage/
│   │   ├── Login.java           # User authentication interface
│   │   ├── HomePage.java        # Main dashboard and navigation
│   │   ├── LoginPage.java       # Login page controller
│   │   └── assets/
│   │       └── bg.jpg           # Background image
│   ├── CropRecommender/
│   │   ├── CropRecommender.java    # Crop recommendation UI
│   │   ├── CropSearcher.java       # Crop data search logic
│   │   └── tn_districts_seasons_crops.json  # Crop database
│   ├── IrrigationAndFertilizerAdvisor/
│   │   ├── IrrigationAdvisor.java     # Irrigation advisory UI
│   │   ├── FertilizerAdvisor.java     # Fertilizer advisory UI
│   │   └── adviceSet.json             # Advisory data
│   └── WeatherReport/
│       ├── WeatherInfo.java          # Weather display UI
│       ├── WeatherRetriever.java     # Weather API integration
│       └── assets/
│           ├── weather_bg.jpg         # Weather background
│           ├── sunny.png              # Weather icons
│           ├── cloudy.png
│           ├── rainy.png
│           ├── partlycloud.png
│           ├── clear-sky (1).png
│           ├── humidity.png
│           └── windy.png
└── .gitignore                        # Git ignore rules
```

## Installation Requirements

### Prerequisites
- **Java Development Kit (JDK) 8 or higher**
- **MySQL Server 5.7 or higher**
- **MySQL JDBC Driver** (mysql-connector-java)
- **JSON Simple Library** (org.json.simple)
- **Internet Connection** (for weather API)

### Required Libraries
1. **MySQL Connector/J** - Database connectivity
   - Download: [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
   - Add to project classpath

2. **JSON Simple** - JSON parsing
   - Download: [JSON Simple](https://code.google.com/archive/p/json-simple/)
   - Add to project classpath

## Database Setup

### 1. Install MySQL Server
```bash
# Download and install MySQL Server from:
# https://dev.mysql.com/downloads/mysql/
```

### 2. Create Database
```sql
CREATE DATABASE userdb;
USE userdb;
```

### 3. Create Users Table
```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### 4. Configure Database Connection
Set the following environment variables:
```bash
# Windows (PowerShell)
$env:DB_URL="jdbc:mysql://localhost:3306/userdb"
$env:DB_USER="root"
$env:DB_PASSWORD="your_mysql_password"

# Linux/Mac
export DB_URL="jdbc:mysql://localhost:3306/userdb"
export DB_USER="root"
export DB_PASSWORD="your_mysql_password"
```

**Note**: If environment variables are not set, the application will use default values (change these in the code before production use).

## Backend Setup and Run Instructions

### 1. Configure Project Structure
```bash
# Navigate to project directory
cd SmartFarmingHelper-00-02-09

# Ensure all Java files are in correct packages:
# src/LoginPage/*.java
# src/CropRecommender/*.java
# src/IrrigationAndFertilizerAdvisor/*.java
# src/WeatherReport/*.java
```

### 2. Compile Java Files
```bash
# Compile all Java files
javac -cp ".;lib/mysql-connector-java.jar;lib/json-simple.jar" src/LoginPage/*.java src/CropRecommender/*.java src/IrrigationAndFertilizerAdvisor/*.java src/WeatherReport/*.java -d bin/
```

### 3. Run Application
```bash
# Run the main application
java -cp ".;bin;lib/mysql-connector-java.jar;lib/json-simple.jar" LoginPage.Login
```

## Frontend Setup and Run Instructions

### 1. GUI Application Launch
The application uses Java Swing as its frontend, which is included in the Java Runtime Environment (JRE). No separate frontend setup is required.

### 2. User Interface Navigation
1. **Login Page**: Enter username and password, or create a new account
2. **Home Page**: Access all features from the main dashboard
3. **Feature Modules**: Navigate to specific features using buttons

## Environment Variable Configuration

### Required Environment Variables
```bash
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/userdb
DB_USER=root
DB_PASSWORD=your_mysql_password
```

### Setting Environment Variables

**Windows PowerShell:**
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/userdb"
$env:DB_USER="root"
$env:DB_PASSWORD="your_password"
```

**Windows Command Prompt:**
```cmd
set DB_URL=jdbc:mysql://localhost:3306/userdb
set DB_USER=root
set DB_PASSWORD=your_password
```

**Linux/Mac:**
```bash
export DB_URL="jdbc:mysql://localhost:3306/userdb"
export DB_USER="root"
export DB_PASSWORD="your_password"
```

## API Information

### Weather API (Open-Meteo)
- **Base URL**: https://api.open-meteo.com/v1/forecast
- **Geocoding URL**: https://geocoding-api.open-meteo.com/v1/search
- **Parameters**: latitude, longitude, hourly data
- **Rate Limit**: Free, no authentication required
- **Data Provided**: Temperature, humidity, wind speed, weather codes

### API Usage in Application
```java
// Weather data retrieval
JSONObject weatherData = WeatherRetriever.getWeatherData("Chennai");

// Returns:
// - temperature (°C)
// - weather_condition (Sunny/Cloudy/Rainy)
// - humidity (%)
// - windspeed (km/h)
```

## Screenshots

### Login Screen
*[Screenshot placeholder: Login interface with username/password fields]*
- User authentication interface
- Account creation option
- Secure credential handling

### Home Dashboard
*[Screenshot placeholder: Main dashboard with feature buttons]*
- Navigation to all features
- Welcome message with username
- Quick access buttons

### Crop Recommendation
*[Screenshot placeholder: Crop selection dropdowns and results]*
- District selection
- Crop type and month selection
- Recommended crops display

### Weather Information
*[Screenshot placeholder: Weather data display with icons]*
- Real-time weather data
- Weather condition icons
- Temperature and humidity display

### Irrigation Advisory
*[Screenshot placeholder: Irrigation recommendations panel]*
- Crop-specific irrigation advice
- Water requirement details
- Irrigation schedules

### Fertilizer Advisory
*[Screenshot placeholder: Fertilizer recommendations panel]*
- NPK recommendations
- Application schedules
- Organic fertilizer options

## Running the Project Locally

### Step-by-Step Instructions

1. **Clone/Download the Project**
   ```bash
   # Extract the project to your desired location
   cd SmartFarmingHelper-00-02-09
   ```

2. **Install Prerequisites**
   - Install Java JDK 8+
   - Install MySQL Server
   - Download required libraries

3. **Setup Database**
   ```sql
   CREATE DATABASE userdb;
   CREATE TABLE users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL);
   ```

4. **Configure Environment Variables**
   ```bash
   export DB_URL="jdbc:mysql://localhost:3306/userdb"
   export DB_USER="root"
   export DB_PASSWORD="your_password"
   ```

5. **Add Libraries to Classpath**
   - Place mysql-connector-java.jar in lib/ directory
   - Place json-simple.jar in lib/ directory

6. **Compile the Project**
   ```bash
   javac -cp ".;lib/*" src/**/*.java -d bin/
   ```

7. **Run the Application**
   ```bash
   java -cp ".;bin;lib/*" LoginPage.Login
   ```

8. **Create User Account**
   - Launch the application
   - Click "Create account"
   - Enter username and password
   - Login with your credentials

## Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure MySQL server is running
   - Check database credentials
   - Verify database exists

2. **Class Not Found Error**
   - Ensure all JAR files are in classpath
   - Check library versions compatibility

3. **Image Loading Error**
   - Verify image paths are correct
   - Check assets folder structure

4. **Weather API Error**
   - Ensure internet connection is active
   - Check API service availability

## Future Enhancements

### Planned Features
- **Mobile Application**: React Native or Flutter mobile app
- **Web Interface**: React.js web application
- **Spring Boot Backend**: RESTful API architecture
- **PostgreSQL Migration**: Move from MySQL to PostgreSQL
- **Machine Learning**: AI-powered crop recommendations
- **Market Price Integration**: Real-time crop price data
- **Multi-language Support**: Regional language options
- **Farmer Community**: Social features for farmers
- **Expert Consultation**: Connect with agricultural experts
- **IoT Integration**: Sensor data for smart farming
- **Data Analytics**: Advanced farming analytics dashboard

### Technical Improvements
- **Modern UI Framework**: Migration to JavaFX or web technologies
- **Cloud Deployment**: AWS/Azure cloud hosting
- **Database Optimization**: Query optimization and indexing
- **Security Enhancement**: OAuth2/JWT authentication
- **API Documentation**: Swagger/OpenAPI documentation
- **Testing Framework**: JUnit and Mockito testing
- **CI/CD Pipeline**: Automated build and deployment

## Author Information

**Project Name**: Smart Farming Web Application  
**Version**: 1.0  
**License**: MIT License  

**Development Team**:  
- Software Development Team  
- Agricultural Domain Experts  

**Contact**:  
- For support and queries, please refer to the project repository

## Acknowledgments

- **Open-Meteo API** - Weather data services
- **MySQL Community** - Database technology
- **Java Community** - Programming language and libraries
- **Agricultural Experts** - Domain knowledge and crop data

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Contributing

Contributions are welcome! Please follow these steps:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## Disclaimer

This application is for educational and demonstration purposes. Agricultural recommendations should be validated with local agricultural experts before implementation. Weather data is provided by third-party APIs and may not always be accurate.
