import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InventoryManagementSystemGUI {

    private JFrame loginFrame;
    private StockInventory stockInventory;
    private CustomerDatabase customerDatabase;
    private SupplierDatabase supplierDatabase;

    public InventoryManagementSystemGUI() {
        stockInventory = new StockInventory();
        customerDatabase = new CustomerDatabase();
        supplierDatabase = new SupplierDatabase();

        // Show the splash screen before the login screen
        showSplashScreen();
    }

    private void showSplashScreen() {
        JWindow splashScreen = new JWindow();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(400, 300));

        JLabel titleLabel = new JLabel("RESU Inventory Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(0, 50, 400, 20

        );
        // Load the image (make sure the path is correct)
        ImageIcon splashImage = new ImageIcon("uwi.png"); // Change to your image path
        JLabel imageLabel = new JLabel(splashImage);
        imageLabel.setBounds(50, 80 
        , splashImage.getIconWidth(), splashImage.getIconHeight());

        JLabel splashLabel = new JLabel("Loading...", JLabel.CENTER);
        splashLabel.setFont(new Font("Arial", Font.BOLD, 16));
        splashLabel.setForeground(Color.BLACK);
        splashLabel.setBounds(10, 200, 400, 120);

        layeredPane.add(imageLabel, Integer.valueOf(0));
        layeredPane.add(titleLabel, Integer.valueOf(1));
        layeredPane.add(splashLabel, Integer.valueOf(1));

        splashScreen.getContentPane().add(layeredPane);
        splashScreen.pack();
        splashScreen.setLocationRelativeTo(null);
        splashScreen.setVisible(true);

        Timer timer = new Timer(3000, e -> {
            splashScreen.dispose();
            showLoginScreen();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void showLoginScreen() {
        loginFrame = new JFrame("Login - RESU Inventory Management System");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 250);
        loginFrame.setLayout(new BorderLayout());
        loginFrame.setLocationRelativeTo(null);
        loginFrame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        JPanel loginPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        // Set button colors
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusable(false);

        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel("")); // Spacer
        loginPanel.add(loginButton);

        JLabel welcomeLabel = new JLabel("Welcome to RESU Inventory Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        welcomeLabel.setForeground((Color.BLACK)); // Change color for a more appealing effect

        loginFrame.add(welcomeLabel, BorderLayout.NORTH);
        loginFrame.add(loginPanel, BorderLayout.CENTER);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.equals("resu") && password.equals("password123")) {
                JOptionPane.showMessageDialog(loginFrame, "Login successful! Welcome, " + username + ".");
                loginFrame.dispose();
                showMainMenu();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid credentials. Please try again.");
            }
        });

        loginFrame.setVisible(true);
    }

    private void showMainMenu() {
        JFrame mainMenuFrame = new JFrame("Main Menu - RESU Inventory Management System");
        mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame.setSize(500, 300);
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton stockButton = new JButton("Manage Stock Inventory");
        JButton customerButton = new JButton("Manage Customer Database");
        JButton supplierButton = new JButton("Manage Supplier Database");

        // Set button colors        qaaq    
        stockButton.setBackground(new Color(0, 123, 255));
        customerButton.setBackground(new Color(0, 123, 255));
        supplierButton.setBackground(new Color(0, 123, 255));

        stockButton.setForeground(Color.WHITE);
        customerButton.setForeground(Color.WHITE);
        supplierButton.setForeground(Color.WHITE);

        buttonPanel.add(stockButton);
        buttonPanel.add(customerButton);
        buttonPanel.add(supplierButton);

        mainMenuFrame.add(new JLabel("Select an Option:", JLabel.CENTER), BorderLayout.NORTH);
        mainMenuFrame.add(buttonPanel, BorderLayout.CENTER);

        stockButton.addActionListener(e -> showStockInventoryWindow());
        customerButton.addActionListener(e -> showCustomerDatabaseWindow());
        supplierButton.addActionListener(e -> showSupplierDatabaseWindow());

        mainMenuFrame.setVisible(true);
    }

    private void showStockInventoryWindow() {
        JFrame stockFrame = createFrame("Stock Inventory Management", 800, 600);
        JTable stockTable = createTable(new Object[]{"Item Name", "Quantity", "Price"});
        JPanel inputPanel = createStockInputPanel(stockTable);

        stockFrame.add(new JScrollPane(stockTable), BorderLayout.CENTER);
        stockFrame.add(inputPanel, BorderLayout.NORTH);
        stockFrame.setVisible(true);
    }

    private JFrame createFrame(String title, int width, int height) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(240, 240, 240)); // Light gray background
        return frame;
    }

    private JTable createTable(Object[] columnNames) {
        return new JTable(new DefaultTableModel(columnNames, 0));
    }

    private JPanel createStockInputPanel(JTable stockTable) {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField itemNameField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField priceField = new JTextField();
        JButton addButton = new JButton("Add Stock");
        JButton updateButton = new JButton("Update Stock");
        JButton calculateButton = new JButton("Calculate Value");

        // Set button styles
        addButton.setBackground(new Color(0, 123, 255));
        updateButton.setBackground(new Color(0, 123, 255));
        calculateButton.setBackground(new Color(0, 123, 255));

        addButton.setForeground(Color.WHITE);
        updateButton.setForeground(Color.WHITE);
        calculateButton.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Item Name:"));
        inputPanel.add(itemNameField);
        inputPanel.add(new JLabel("Quantity:"));
        inputPanel.add(quantityField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(priceField);
        inputPanel.add(addButton);
        inputPanel.add(updateButton);

        addButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                double price = Double.parseDouble(priceField.getText());
                stockInventory.addStock(itemName, quantity, price);

                DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
                model.addRow(new Object[]{itemName, quantity, price});

                JOptionPane.showMessageDialog(inputPanel.getTopLevelAncestor(), "Stock added successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(inputPanel.getTopLevelAncestor(), "Invalid quantity or price. Please enter valid numbers.");
            }
        });

        updateButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            try {
                int quantity = Integer.parseInt(quantityField.getText());
                stockInventory.updateStock(itemName, quantity);

                DefaultTableModel model = (DefaultTableModel) stockTable.getModel();
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(itemName)) {
                        model.setValueAt(quantity, i, 1);
                        break;
                    }
                }

                JOptionPane.showMessageDialog(inputPanel.getTopLevelAncestor(), "Stock updated successfully.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(inputPanel.getTopLevelAncestor(), "Invalid quantity. Please enter a valid number.");
            }
        });

        calculateButton.addActionListener(e -> {
            double totalValue = stockInventory.calculateInventoryValue();
            JOptionPane.showMessageDialog(inputPanel.getTopLevelAncestor(), "Total Inventory Value: $" + totalValue);
        });

        return inputPanel;
    }

    private void showCustomerDatabaseWindow() {
        JFrame customerFrame = createFrame("Customer Database Management", 800, 600);
        JTable customerTable = createTable(new Object[]{"Name", "Contact Info"});
        JPanel inputPanel = createCustomerInputPanel(customerTable);

        customerFrame.add(new JScrollPane(customerTable), BorderLayout.CENTER);
        customerFrame.add(inputPanel, BorderLayout.NORTH);
        customerFrame.setVisible(true);
    }

    private JPanel createCustomerInputPanel(JTable customerTable) {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JButton addButton = new JButton("Add Customer");

        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Contact Info:"));
        inputPanel.add(contactField);
        inputPanel.add(new JLabel("")); // Spacer
        inputPanel.add(addButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String contactInfo = contactField.getText();

            customerDatabase.addCustomer(name, contactInfo);

            DefaultTableModel model = (DefaultTableModel) customerTable.getModel();
            model.addRow(new Object[]{name, contactInfo});

            JOptionPane.showMessageDialog(inputPanel.getTopLevelAncestor(), "Customer added successfully.");
        });

        return inputPanel;
    }

    private void showSupplierDatabaseWindow() {
        JFrame supplierFrame = createFrame("Supplier Database Management", 800, 600);
        JTable supplierTable = createTable(new Object[]{"Name", "Contact Info", "Supplied Item"});
        JPanel inputPanel = createSupplierInputPanel(supplierTable);

        supplierFrame.add(new JScrollPane(supplierTable), BorderLayout.CENTER);
        supplierFrame.add(inputPanel, BorderLayout.NORTH);
        supplierFrame.setVisible(true);
    }

    private JPanel createSupplierInputPanel(JTable supplierTable) {
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JTextField nameField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField suppliedItemField = new JTextField();
        JButton addButton = new JButton("Add Supplier");

        addButton.setBackground(new Color(0, 123, 255));
        addButton.setForeground(Color.WHITE);

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Contact Info:"));
        inputPanel.add(contactField);
        inputPanel.add(new JLabel("Supplied Item:"));
        inputPanel.add(suppliedItemField);
        inputPanel.add(new JLabel("")); // Empty cell for layout purposes
        inputPanel.add(addButton);

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String contactInfo = contactField.getText();
            String suppliedItem = suppliedItemField.getText();

            supplierDatabase.addSupplier(name, contactInfo, suppliedItem);

            DefaultTableModel model = (DefaultTableModel) supplierTable.getModel();
            model.addRow(new Object[]{name, contactInfo, suppliedItem});

            JOptionPane.showMessageDialog(inputPanel.getTopLevelAncestor(), "Supplier added successfully.");
        });

        return inputPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InventoryManagementSystemGUI::new);
    }
}
