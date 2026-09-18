package IrrigationAndFertilizerAdvisor;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import LoginPage.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class FertilizerAdvisor extends JFrame {

    private JComboBox<String> cropDropdown;
    private JTextArea adviceArea;
    private JSONObject cropsData;
    private String username;
    private BufferedImage bgImage;

    public FertilizerAdvisor(String uname) {
        super("Fertilizer Advisory");
        setSize(500, 450);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        username = uname;

        // Load background image
        try {
            bgImage = ImageIO.read(new File("src/LoginPage/assets/bg.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create panel with background image
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

        // Crop dropdown
        cropDropdown = new JComboBox<>();
        cropDropdown.setBounds(20, 20, 200, 30);
        add(cropDropdown);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(370, 20, 90, 30);
        add(backButton);

        // Load JSON and populate dropdown
        try {
            String content = new String(Files.readAllBytes(Paths.get(
                "src/IrrigationAndFertilizerAdvisor/adviceSet.json")));
            cropsData = new JSONObject(content);

            for (String category : cropsData.keySet()) {
                JSONObject categoryObj = cropsData.getJSONObject(category);
                for (String crop : categoryObj.keySet()) {
                    cropDropdown.addItem(category + " -> " + crop);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading JSON: " + e.getMessage());
            e.printStackTrace();
        }

        // Advice panel (layout container)
        JPanel advicePanel = new JPanel();
        advicePanel.setBounds(20, 70, 460, 330);
        advicePanel.setLayout(new BorderLayout());
        advicePanel.setOpaque(false);
        add(advicePanel);

        // Advice area
        adviceArea = new JTextArea();
        adviceArea.setLineWrap(true);
        adviceArea.setWrapStyleWord(true);
        adviceArea.setEditable(false);
        adviceArea.setFont(new Font("Dialog", Font.PLAIN, 14));
        adviceArea.setForeground(Color.WHITE);
        adviceArea.setBackground(new Color(0,0,0,150)); // semi-transparent black
        adviceArea.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(adviceArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        advicePanel.add(scrollPane, BorderLayout.CENTER);

        // Dropdown action
        cropDropdown.addActionListener(e -> showAdvice());
        if (cropDropdown.getItemCount() > 0)
            cropDropdown.setSelectedIndex(0);

        // Back action
        backButton.addActionListener(e -> {
            this.dispose();
            HomePage hp = new HomePage(uname);
            hp.setVisible(true);
        });

        setVisible(true);
    }

    private void showAdvice() {
        String selected = (String) cropDropdown.getSelectedItem();
        if (selected == null) return;

        String[] parts = selected.split(" -> ");
        String category = parts[0];
        String crop = parts[1];

        JSONObject cropObj = cropsData.getJSONObject(category).getJSONObject(crop);
        JSONObject fertilizer = cropObj.getJSONObject("fertilizer");

        StringBuilder sb = new StringBuilder();
        sb.append("Crop: ").append(crop).append("\n\n");

        sb.append("Nitrogen: ").append(fertilizer.optString("nitrogen", "N/A")).append("\n");
        sb.append("Phosphorus: ").append(fertilizer.optString("phosphorus", "N/A")).append("\n");
        sb.append("Potassium: ").append(fertilizer.optString("potassium", "N/A")).append("\n");
        sb.append("Organic: ").append(fertilizer.optString("organic", "N/A")).append("\n\n");

        sb.append("Application Schedule:\n");
        if (fertilizer.has("application_schedule")) {
            for (int i = 0; i < fertilizer.getJSONArray("application_schedule").length(); i++) {
                sb.append(" - ").append(fertilizer.getJSONArray("application_schedule").getString(i)).append("\n");
            }
        }

        adviceArea.setText(sb.toString());
    }
}
