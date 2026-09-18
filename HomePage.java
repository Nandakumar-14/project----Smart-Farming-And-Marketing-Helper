package LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import WeatherReport.*;
import IrrigationAndFertilizerAdvisor.*;
import CropRecommender.*;

public class HomePage extends JFrame {

    private String username;
    private BufferedImage bgImage;

    // Components
    private JButton btncroprecommender;
    private JButton btnfertilizer;
    private JButton btnirrigation;
    private JButton btnweatherinfo;
    private JLabel welcomeLabel;

    public HomePage(String uname) {
        super("Smart Farming Helper");
    username = uname;

    try {
        bgImage = ImageIO.read(new File("src/LoginPage/assets/bg.jpg"));
    } catch (IOException e) {
        e.printStackTrace();
    }

    setSize(500, 450);
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel bgPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bgImage != null) {
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    };
    bgPanel.setLayout(null);
    setContentPane(bgPanel);

    initComponents();

    setLocationRelativeTo(null); 

    setVisible(true);
}


    private void initComponents() {
        
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        bgPanel.setLayout(null); 
        setContentPane(bgPanel);

        // Add components with their original bounds
        welcomeLabel = new JLabel("Welcome "+username+" !");
        welcomeLabel.setBounds(200, 30, 200, 30); // adjust if needed
        bgPanel.add(welcomeLabel);

        btnweatherinfo = new JButton("Weather info");
        btnweatherinfo.setBounds(180, 80, 140, 30);
        btnweatherinfo.addActionListener(e -> {
            WeatherInfo wi = new WeatherInfo(username);
            wi.setVisible(true);
            this.dispose();
        });
        bgPanel.add(btnweatherinfo);

        btnirrigation = new JButton("Irrigation advisor");
        btnirrigation.setBounds(180, 130, 140, 30);
        btnirrigation.addActionListener(e -> {
            IrrigationAdvisor ia = new IrrigationAdvisor(username);
            ia.setVisible(true);
            this.dispose();
        });
        bgPanel.add(btnirrigation);

        btnfertilizer = new JButton("Fertilizer advisor");
        btnfertilizer.setBounds(180, 180, 140, 30);
        btnfertilizer.addActionListener(e -> {
            FertilizerAdvisor fa = new FertilizerAdvisor(username);
            fa.setVisible(true);
            this.dispose();
        });
        bgPanel.add(btnfertilizer);

        btncroprecommender = new JButton("Crop recommender");
        btncroprecommender.setBounds(180, 230, 140, 30);
        btncroprecommender.addActionListener(e -> {
            CropRecommender cr = new CropRecommender(username);
            cr.setVisible(true);
            this.dispose();
        });
        bgPanel.add(btncroprecommender);
    }
}
