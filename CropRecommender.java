package CropRecommender;

import javax.swing.*;
import java.awt.*;
import LoginPage.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class CropRecommender extends JFrame {
    private JPanel mainPanel, resultPanel;
    private JTextArea resultArea;
    private String username;
    private BufferedImage bgImage;

    private static final String[] districts = {
        "Ariyalur","Chengalpattu","Chennai","Coimbatore","Cuddalore","Dharmapuri",
        "Dindigul","Erode","Kallakurichi","Kancheepuram","Kanyakumari","Karur",
        "Krishnagiri","Madurai","Mayiladuthurai","Nagapattinam","Namakkal","Nilgiris",
        "Perambalur","Pudukottai","Ramanathapuram","Ranipet","Salem","Sivaganga",
        "Tenkasi","Thanjavur","Theni","Thoothukudi","Tiruchirappalli","Tirunelveli",
        "Tirupathur","Tiruppur","Tiruvallur","Tiruvannamalai","Tiruvarur","Vellore",
        "Viluppuram","Virudhunagar"
    };

    private static final String[] cropTypes = {"cereal","fruit","vegetable","cash","ornamental"};
    private static final String[] months = {
        "January","February","March","April","May","June","July","August",
        "September","October","November","December"
    };

    public CropRecommender(String uname) {
        setTitle("Crop Recommender");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        username = uname;

        // Load background image
        try {
            bgImage = ImageIO.read(new File("src/LoginPage/assets/bg.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Custom content pane with background
        JPanel bgPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bgImage != null) {
                    g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        bgPanel.setLayout(new BorderLayout());
        setContentPane(bgPanel);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        mainPanel.setOpaque(false); // make panel transparent to show bg

        JComboBox<String> districtDD = new JComboBox<>(districts);
        JComboBox<String> cropTypeDD = new JComboBox<>(cropTypes);
        JComboBox<String> monthDD = new JComboBox<>(months);

        JButton submitButton = new JButton("Get Recommendation");
        submitButton.setBackground(new Color(34, 139, 34));
        submitButton.setForeground(Color.WHITE);

        JButton backButton = new JButton("Back");

        // Result panel
        resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Suggestions"));
        resultPanel.setPreferredSize(new Dimension(460, 250));
        resultPanel.setOpaque(false); // transparent

        resultArea = new JTextArea("Fill the fields and click SUBMIT");
        resultArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        resultArea.setForeground(Color.BLACK);
        resultArea.setBackground(new Color(255, 255, 255, 200)); // slightly transparent white
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setMargin(new Insets(10, 10, 10, 10));

        resultPanel.setBackground(Color.WHITE);
        resultPanel.setOpaque(true);

        resultArea.setBackground(Color.WHITE);
        resultArea.setOpaque(true);

        resultArea.setForeground(Color.BLACK);


        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        resultPanel.add(scrollPane, BorderLayout.CENTER);

        // Add components
        mainPanel.add(new JLabel("District:"));
        mainPanel.add(districtDD);
        mainPanel.add(new JLabel("Crop Type:"));
        mainPanel.add(cropTypeDD);
        mainPanel.add(new JLabel("Month:"));
        mainPanel.add(monthDD);
        mainPanel.add(submitButton);
        mainPanel.add(backButton);

        bgPanel.add(mainPanel, BorderLayout.NORTH);
        bgPanel.add(resultPanel, BorderLayout.CENTER);

        // Submit button action
        submitButton.addActionListener(e -> {
            String districtSelected = (String) districtDD.getSelectedItem();
            String cropTypeSelected = (String) cropTypeDD.getSelectedItem();
            String monthSelected = (String) monthDD.getSelectedItem();

            try {
                String suggestions = new CropSearcher(districtSelected, cropTypeSelected, monthSelected).display();
                resultArea.setText(suggestions);
            } catch (Exception ex) {
                resultArea.setText("Error fetching crop data.\nPlease check file path or input.");
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            this.dispose();
            HomePage hp = new HomePage(uname);
            hp.setVisible(true);
        });

        setVisible(true);
    }
}
