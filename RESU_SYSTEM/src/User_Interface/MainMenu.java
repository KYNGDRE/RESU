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
    private JButton button1, button2, button3, button4;
    private JFrame A;

    public MainMenu() {
        setTitle("DASHBOARD");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 10, 10));


        button1 = new JButton("Search and Browse");
        button2 = new JButton("Place Order");
        // button4 = new JButton("Browse");
        // button3 = new JButton("Place");
        button3 = new JButton("Cance Order");


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        // button4.addActionListener(this);

        add(button1);
        add(button2);
        add(button3);
        // add(button4);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button2) {
            new OrderPlacement();
        }
        else if (e.getSource() == button1) {
            new Search();
        }
        else{
            // new Cancel();
        }
        // else if (e.getSource() == button1) {
        //     new Search();
        // }
    }


    // @Override
    // public void actionPerformed(ActionEvent e) {
    //     if (e.getSource() == button2) {
    //         new OrderPlacement();
    //     }
    // }
}


