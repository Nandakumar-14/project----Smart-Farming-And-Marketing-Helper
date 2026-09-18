package WeatherReport;

import java.io.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;
import LoginPage.*;

public class WeatherInfo extends JFrame {

    String[] districts = {
        "Ariyalur","Chengalpattu","Chennai","Coimbatore","Cuddalore","Dharmapuri",
        "Dindigul","Erode","Kallakurichi","Kancheepuram","Kanyakumari","Karur",
        "Krishnagiri","Madurai","Mayiladuthurai","Nagapattinam","Namakkal","Nilgiris",
        "Perambalur","Pudukottai","Ramanathapuram","Ranipet","Salem","Sivaganga",
        "Tenkasi","Thanjavur","Theni","Thoothukudi","Tiruchirappalli","Tirunelveli",
        "Tirupathur","Tiruppur","Tiruvallur","Tiruvannamalai","Tiruvarur","Vellore",
        "Viluppuram","Virudhunagar"
    };

    private JComboBox<String> districtDD;
    private JButton btnSubmit, backButton;
    private JLabel weatherConditionImage, temperatureText, weatherConditionDesc;
    private JLabel humidityImage, humidityText, windImage, windText, jLabel1;
    private String username;
    private BufferedImage bgImage;

    public WeatherInfo(String uname) {
        username = uname;

        // Load background image
        try {
            bgImage = ImageIO.read(new File("src/WeatherReport/assets/weather_bg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setTitle("Weather Info");
        setSize(500, 450);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        bgPanel.setLayout(null); // Keep absolute positioning
        setContentPane(bgPanel);

        initComponents();
        populateDistricts();
        addWeatherComponents();

        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void initComponents() {
        // District label
        jLabel1 = new JLabel("District:");
        jLabel1.setBounds(30, 20, 70, 25);
        add(jLabel1);

        // District combo box
        districtDD = new JComboBox<>();
        districtDD.setBounds(100, 20, 200, 25);
        add(districtDD);

        // Submit button
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(320, 20, 100, 25);
        btnSubmit.addActionListener(evt -> onSubmit());
        add(btnSubmit);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(190, 380, 100, 30);
        backButton.addActionListener(e -> {
            this.dispose();
            HomePage hp = new HomePage(username);
            hp.setVisible(true);
        });
        add(backButton);
    }

    private void populateDistricts() {
        for (String district : districts) {
            districtDD.addItem(district);
        }
        districtDD.setSelectedIndex(0);
    }

    private void addWeatherComponents() {
        // Weather condition image
        weatherConditionImage = new JLabel(loadImage("src/WeatherReport/assets/cloudy.png"));
        weatherConditionImage.setBounds(150, 60, 200, 120);
        add(weatherConditionImage);

        // Temperature
        temperatureText = new JLabel("10°C", SwingConstants.CENTER);
        temperatureText.setBounds(0, 190, 500, 40);
        temperatureText.setFont(new Font("CooperBlack", Font.PLAIN, 36));
        temperatureText.setForeground(Color.WHITE); // for visibility on bg
        add(temperatureText);

        // Weather description
        weatherConditionDesc = new JLabel("Cloudy", SwingConstants.CENTER);
        weatherConditionDesc.setBounds(0, 240, 500, 30);
        weatherConditionDesc.setFont(new Font("Dialog", Font.PLAIN, 24));
        weatherConditionDesc.setForeground(Color.WHITE); // for visibility
        add(weatherConditionDesc);

        // Humidity
        humidityImage = new JLabel(loadImage("src/WeatherReport/assets/humidity.png"));
        humidityImage.setBounds(150, 280, 40, 40);
        add(humidityImage);

        humidityText = new JLabel("Humidity");
        humidityText.setBounds(200, 280, 200, 40);
        humidityText.setFont(new Font("Dialog", Font.PLAIN, 14));
        humidityText.setForeground(Color.WHITE);
        add(humidityText);

        // Wind
        windImage = new JLabel(loadImage("src/WeatherReport/assets/windy.png"));
        windImage.setBounds(150, 320, 40, 40);
        add(windImage);

        windText = new JLabel("Wind speed 15 km/h");
        windText.setBounds(200, 320, 200, 40);
        windText.setFont(new Font("Dialog", Font.PLAIN, 14));
        windText.setForeground(Color.WHITE);
        add(windText);
    }

    private ImageIcon loadImage(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            return new ImageIcon(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void onSubmit() {
        String district = (String) districtDD.getSelectedItem();
        if (district == null) return;

        // Fetch weather data
        JSONObject weatherData = WeatherRetriever.getWeatherData(district);

        if (weatherData != null) {
            double temperature = ((Number) weatherData.getOrDefault("temperature", 0.0)).doubleValue();
            String condition = (String) weatherData.getOrDefault("weather_condition", "Unknown");
            long humidity = ((Number) weatherData.getOrDefault("humidity", 0)).longValue();
            double windspeed = ((Number) weatherData.getOrDefault("windspeed", 0.0)).doubleValue();

            temperatureText.setText(String.format("%.1f°C", temperature));
            weatherConditionDesc.setText(condition);

            // Update weather image
            switch (condition) {
                case "Cloudy":
                    weatherConditionImage.setIcon(loadImage("src/WeatherReport/assets/cloudy.png"));
                    break;
                case "Rain":
                    weatherConditionImage.setIcon(loadImage("src/WeatherReport/assets/rainy.png"));
                    break;
                case "Sunny":
                    weatherConditionImage.setIcon(loadImage("src/WeatherReport/assets/sunny.png"));
                    break;
            }

            humidityText.setText("Humidity : " + humidity + "%");
            windText.setText("Wind speed : " + windspeed + " km/h");
        } else {
            JOptionPane.showMessageDialog(this, "Could not fetch weather for " + district);
        }
    }
}
