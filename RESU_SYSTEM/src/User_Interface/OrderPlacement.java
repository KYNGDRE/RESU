package User_Interface;
import javax.swing.*;
import java.util.*;

import RESU.Item;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import User_Interface.LoginPage;


public class OrderPlacement extends JFrame implements ActionListener {
    // Components
    private JLabel labelUser, labelMessage;
    private JTextField textUser;
    private JButton buttonLogin, buttonClear, buttonBack, buttonOrder;
    private String[] items = new String[]{"Item A", "Item B", "Item C", "Item D"};

    public OrderPlacement() {
        setTitle("Place Order");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    
        // Set vertical BoxLayout on the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
    
        // Labels
        labelUser = new JLabel("Search for the item:");
        labelUser.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        labelMessage = new JLabel("", SwingConstants.CENTER);
        labelMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Text Fields
        textUser = new JTextField(15);
        textUser.setMaximumSize(new Dimension(200, 25)); // To prevent full width
        textUser.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Item List
        
        JList<String> itemList = new JList<>(items);
        itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemList.setVisibleRowCount(2);
        JScrollPane scrollPane = new JScrollPane(itemList);
        scrollPane.setMaximumSize(new Dimension(200, 60));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Buttons
        buttonLogin = new JButton("Search");
        buttonClear = new JButton("Clear");
        buttonBack = new JButton("Back");
        buttonOrder = new JButton("Back");

        buttonLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonClear.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonOrder.setAlignmentX(Component.CENTER_ALIGNMENT);


        // Add listeners
        buttonLogin.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonBack.addActionListener(this);
        buttonOrder.addActionListener(this);
    
        // Add components
        contentPane.add(Box.createVerticalStrut(10)); // Spacer
        contentPane.add(labelUser);
        contentPane.add(Box.createVerticalStrut(5));
        contentPane.add(textUser);
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(scrollPane);
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(buttonLogin);
        contentPane.add(buttonClear);
        contentPane.add(buttonBack);
        contentPane.add(buttonOrder);
        contentPane.add(Box.createVerticalStrut(10));
        contentPane.add(labelMessage);
    
        setVisible(true);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonLogin) {
            String name = textUser.getText();
            if (Arrays.asList(items).contains(name)) {
                labelMessage.setText("Item Found!");
                labelMessage.setForeground(Color.GREEN);
            } 
            else {
                labelMessage.setText("Cannot Find Item!");
                labelMessage.setForeground(Color.RED);
            }
        } else if (e.getSource() == buttonClear) {
            textUser.setText("");
            // textPass.setText("");
            labelMessage.setText("");
        }
        else if (e.getSource() == buttonOrder){
            String name = textUser.getText();
            if (Arrays.asList(items).contains(name)) {
                Order Ordero = 
            } 
            else {
                labelMessage.setText("Cannot Find Item!");
                labelMessage.setForeground(Color.RED);
            }
        }
        else{
            setVisible(false);
        }
    }
}