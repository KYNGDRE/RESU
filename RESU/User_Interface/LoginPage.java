package User_Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import User_Interface.LoginPage;


public class LoginPage extends JFrame implements ActionListener {
    // Components
    private JLabel labelUser, labelPass, labelMessage;
    private JTextField textUser;
    private JPasswordField textPass;
    private JButton buttonLogin, buttonClear;

    public LoginPage() {
        // Frame settings
        setTitle("Login Page");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10)); // Grid Layout with spacing

        // Labels
        labelUser = new JLabel("Username:");
        labelPass = new JLabel("Password:");
        labelMessage = new JLabel("", SwingConstants.CENTER);

        // Text Fields
        textUser = new JTextField();
        textPass = new JPasswordField();

        // Buttons
        buttonLogin = new JButton("Login");
        buttonClear = new JButton("Clear");

        // Add action listeners
        buttonLogin.addActionListener(this);
        buttonClear.addActionListener(this);

        // Add components to frame
        add(labelUser);
        add(textUser);
        add(labelPass);
        add(textPass);
        add(buttonLogin);
        add(buttonClear);
        add(labelMessage);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogin) {
            String username = textUser.getText();
            String password = new String(textPass.getPassword());

            // Dummy validation
            if (username.equals("admin") && password.equals("pass")) {
                labelMessage.setText("Login Successful!");
                labelMessage.setForeground(Color.GREEN);
                JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");
                new MainMenu();
            } else {
                labelMessage.setText("Invalid Credentials!");
                labelMessage.setForeground(Color.RED);
            }
        } else if (e.getSource() == buttonClear) {
            textUser.setText("");
            textPass.setText("");
            labelMessage.setText("");
        }
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}


// public class LoginPage extends JFrame implements ActionListener {
//     // Database credentials
//     private static final String DB_URL = "jdbc:mysql://localhost:3306/RESU";
//     private static final String DB_USER = "RESU-ADMIN"; // MySQL username
//     private static final String DB_PASS = "password"; //MySQL password

//     // UI Components
//     private JLabel labelUser, labelPass, labelMessage;
//     private JTextField textUser;
//     private JPasswordField textPass;
//     private JButton buttonLogin, buttonClear;

//     public LoginPage() {
//         setTitle("Login Page");
//         setSize(350, 200);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLocationRelativeTo(null);
//         setLayout(new GridLayout(4, 2, 10, 10));

//         labelUser = new JLabel("Username:");
//         labelPass = new JLabel("Password:");
//         labelMessage = new JLabel("", SwingConstants.CENTER);

//         textUser = new JTextField();
//         textPass = new JPasswordField();

//         buttonLogin = new JButton("Login");
//         buttonClear = new JButton("Clear");

//         buttonLogin.addActionListener(this);
//         buttonClear.addActionListener(this);

//         add(labelUser);
//         add(textUser);
//         add(labelPass);
//         add(textPass);
//         add(buttonLogin);
//         add(buttonClear);
//         add(labelMessage);

//         setVisible(true);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if (e.getSource() == buttonLogin) {
//             String username = textUser.getText();
//             String password = new String(textPass.getPassword());

//             if (authenticateUser(username, password)) {
//                 labelMessage.setText("Login Successful!");
//                 labelMessage.setForeground(Color.GREEN);
//                 JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");
//                 new MainMenu();
//             } else {
//                 labelMessage.setText("Invalid Credentials!");
//                 labelMessage.setForeground(Color.RED);
//             }
//         } else if (e.getSource() == buttonClear) {
//             textUser.setText("");
//             textPass.setText("");
//             labelMessage.setText("");
//         }
//     }

//     private boolean authenticateUser(String username, String password) {
//         try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {

//             stmt.setString(1, username);
//             stmt.setString(2, password);
//             ResultSet rs = stmt.executeQuery();
//             return rs.next(); // Returns true if user exists

//         } catch (SQLException ex) {
//             ex.printStackTrace();
//             JOptionPane.showMessageDialog(this, "Database Error!", "Error", JOptionPane.ERROR_MESSAGE);
//             return false;
//         }
//     }

// // Main class to demonstrate the functionality
//     public static void main(String[] args) {
//          // Create some sample items
//          Item item1 = new Item(1, "Item A", 10.0);
//          Item item2 = new Item(2, "Item B", 15.5);
//          List<Item> items = new ArrayList<>();
//          items.add(item1);
//          items.add(item2);

//          // Create a customer and attempt login
//         //  Customer customer = new Customer("john_doe", "password123");
//         //  if (customer.login("john_doe", "password123")) {
//         //      System.out.println("Customer logged in successfully.");
//         //  } else {
//         //      System.out.println("Login failed for customer.");
//         //  }

//         new LoginPage();

//          // Customer places an order
//         //  Order order = customer.placeOrder(items);
//         //  System.out.println("Order ID: " + order.getOrderId());
//         //  System.out.println("Total Order Amount: " + order.calculateTotal());

//         //  // Create a staff member and demonstrate inventory management
//         //  Staff staff = new Staff("jane_staff", "staffpass");
//         //  if (staff.login("jane_staff", "staffpass")) {
//         //      staff.manageInventory();
//         //  } else {
//         //      System.out.println("Login failed for staff.");
//         //  }
//     }

    
//     // public static void main(String[] args) {
        
// }
