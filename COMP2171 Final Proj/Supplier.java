public class Supplier {
    private String name;
    private String contactInfo;
    private String suppliedItem;

    // Constructor to initialize the Supplier object
    public Supplier(String name, String contactInfo, String suppliedItem) {
        this.name = name;
        this.contactInfo = contactInfo;
        this.suppliedItem = suppliedItem;
    }

    // Getter methods for each attribute
    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getSuppliedItem() {
        return suppliedItem;
    }

    // Method to display the supplier's information
    public void displaySupplier() {
        System.out.printf("Supplier: %s | Contact: %s | Supplies: %s%n", name, contactInfo, suppliedItem);
    }
}
