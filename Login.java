package LoginPage;

import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.sql.SQLIntegrityConstraintViolationException;

public class Login extends JFrame {

    private JTextField edtusername;
    private JPasswordField edtpassword;
    private JButton btnlogin, btnreset, btncreateaccount;
    private JLabel jLabel1, jLabel2;
    private JSeparator jSeparator1;

    public Login() {
        setTitle("Smart Farming Helper");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Set content pane with background
        BackgroundPanel bgPanel = new BackgroundPanel("src/LoginPage/assets/bg.jpg");
        setContentPane(bgPanel);
        bgPanel.setLayout(null);

        initComponents();
        addComponents(bgPanel);
    }

    private void initComponents() {
        // Labels
        jLabel1 = new JLabel("Username");
        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 14));
        jLabel2 = new JLabel("Password");
        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 15));

        // Text fields
        edtusername = new JTextField();
        edtpassword = new JPasswordField();

        // Buttons
        btnlogin = new JButton("Login");
        btnlogin.setBackground(new Color(51, 51, 255));
        btnlogin.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnlogin.addActionListener(e -> loginAction());

        btnreset = new JButton("Reset");
        btnreset.setBackground(new Color(51, 51, 255));
        btnreset.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnreset.addActionListener(e -> resetAction());

        btncreateaccount = new JButton("Create account");
        btncreateaccount.setBackground(new Color(51, 51, 255));
        btncreateaccount.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btncreateaccount.addActionListener(e -> createAccountAction());

        // Separator
        jSeparator1 = new JSeparator();

        // Set bounds for absolute positioning
        jLabel1.setBounds(100, 120, 70, 25);
        edtusername.setBounds(180, 120, 150, 25);
        jLabel2.setBounds(100, 160, 70, 25);
        edtpassword.setBounds(180, 160, 150, 25);
        btnlogin.setBounds(100, 220, 90, 30);
        btnreset.setBounds(200, 220, 90, 30);
        btncreateaccount.setBounds(300, 220, 130, 30);
        jSeparator1.setBounds(50, 260, 400, 10);
    }

    private void addComponents(JPanel panel) {
        panel.add(jLabel1);
        panel.add(jLabel2);
        panel.add(edtusername);
        panel.add(edtpassword);
        panel.add(btnlogin);
        panel.add(btnreset);
        panel.add(btncreateaccount);
        panel.add(jSeparator1);
    }

    // Background panel inner class
    class BackgroundPanel extends JPanel {
        private Image bgImage;

        public BackgroundPanel(String path) {
            bgImage = new ImageIcon(path).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bgImage != null) {
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Button actions
    private void resetAction() {
        edtusername.setText("");
        edtpassword.setText("");
    }

    private void loginAction() {
        String username = edtusername.getText();
        String password = new String(edtpassword.getPassword());

        String url = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:3306/userdb";
        String dbUser = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
        String dbPassword = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "your_password_here";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);

            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                HomePage hp = new HomePage(username);
                hp.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!");
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
        }
    }

    private void createAccountAction() {
        String username = edtusername.getText();
        String password = new String(edtpassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.");
            return;
        }

        String url = System.getenv("DB_URL") != null ? System.getenv("DB_URL") : "jdbc:mysql://localhost:3306/userdb";
        String dbUser = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
        String dbPassword = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "your_password_here";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, dbUser, dbPassword);

            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "User created successfully!");
            con.close();
        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(this, "Username already exists!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
