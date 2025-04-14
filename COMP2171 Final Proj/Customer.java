import java.util.ArrayList;

public class Customer {
    private String name;
    private String contactInfo;
    private ArrayList<String> purchaseHistory;

    public Customer(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.purchaseHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    // Add a purchase item to the history
    public void addPurchase(String item) {
        purchaseHistory.add(item);
    }

    // Display customer information
    public void displayCustomer() {
        System.out.printf("Customer: %s | Contact: %s | Purchases: ", name, contactInfo);
        
        // Check if the purchase history is empty
        if (purchaseHistory.isEmpty()) {
            System.out.println("No purchases yet.");
        } else {
            // Print each purchase on a new line
            for (String item : purchaseHistory) {
                System.out.println(item);
            }
        }
    }

    // Getter for the purchase history
    public ArrayList<String> getPurchaseHistory() {
        return purchaseHistory;
    }
}

