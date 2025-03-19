package User_Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainMenu extends JFrame implements ActionListener {

    // UI Components
    // private JLabel labelUser, labelPass, labelMessage;
    // private JTextField textUser;
    // private JPasswordField textPass;
    private JButton button1, button2;
    private JFrame A;

    public MainMenu() {
        setTitle("Main Menu");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));


        button1 = new JButton("Search");
        button2 = new JButton("Order");
        // button3 = new JButton("");

        button1.addActionListener(this);
        button2.addActionListener(this);

        add(button1);
        add(button2);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        A = new OrderPlacement();
    }

    // private boolean authenticateUser(String username, String password) {
    //     try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    //         PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

    //         stmt.setString(1, username);
    //         stmt.setString(2, password);
    //         ResultSet rs = stmt.executeQuery();
    //         return rs.next(); // Returns true if user exists

    //     } catch (SQLException ex) {
    //         ex.printStackTrace();
    //         JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
    //         return false;
    //     }
    // }
}


