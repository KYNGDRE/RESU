import java.util.ArrayList;

public class CustomerDatabase {
    private ArrayList<Customer> customers;

    public CustomerDatabase() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(String name, String contactInfo) {
        Customer newCustomer = new Customer(name, contactInfo);
        customers.add(newCustomer);
        System.out.println("Customer added: " + name);
    }

    // Update this to return a single Customer, not a list
    public Customer searchCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;  // No customer found
    }

    public void displayAllCustomers() {
        System.out.println("\n--- Customer Database ---");
        if (customers.isEmpty()) {
            System.out.println("No customers in the database.");
        } else {
            for (Customer customer : customers) {
                customer.displayCustomer();
            }
        }
    }
}

