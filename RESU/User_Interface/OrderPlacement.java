package User_Interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import User_Interface.LoginPage;


public class OrderPlacement extends JFrame implements ActionListener {
    // Components
    private JLabel labelUser, labelPass, labelMessage;
    private JTextField textUser;
    private JPasswordField textPass;
    private JButton buttonLogin, buttonClear;

    public OrderPlacement() {
        // Frame settings
        setTitle("Place Order");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10)); // Grid Layout with spacing

        // Labels
        labelUser = new JLabel("Search for the item:");
        // labelPass = new JLabel("Password:");
        labelMessage = new JLabel("", SwingConstants.CENTER);

        // Text Fields
        textUser = new JTextField();
        // textPass = new JPasswordField();

        // Buttons
        buttonLogin = new JButton("Search");
        // buttonClear = new JButton("Clear");

        // Add action listeners
        buttonLogin.addActionListener(this);
        buttonClear.addActionListener(this);

        // Add components to frame
        add(labelUser);
        add(textUser);
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
}