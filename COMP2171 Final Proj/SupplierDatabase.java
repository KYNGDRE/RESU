import java.util.ArrayList;

public class SupplierDatabase {
    private ArrayList<Supplier> suppliers;

    public SupplierDatabase() {
        this.suppliers = new ArrayList<>();
    }

    // Add a new supplier to the database
    public void addSupplier(String name, String contactInfo, String suppliedItem) {
        if (name == null || name.isEmpty() || contactInfo == null || contactInfo.isEmpty() || suppliedItem == null || suppliedItem.isEmpty()) {
            System.out.println("Invalid supplier data.");
            return;
        }
        Supplier newSupplier = new Supplier(name, contactInfo, suppliedItem);
        suppliers.add(newSupplier);
        System.out.println("Supplier added: " + name);
    }

    // Search for suppliers by name (returns a list of matching suppliers)
    public ArrayList<Supplier> searchSupplier(String name) {
        ArrayList<Supplier> foundSuppliers = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            if (supplier.getName().equalsIgnoreCase(name)) {
                foundSuppliers.add(supplier);
            }
        }
        return foundSuppliers;
    }

    // Display all suppliers in the database
    public void displayAllSuppliers() {
        System.out.println("\n--- Supplier Database ---");
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers in the database.");
        } else {
            for (Supplier supplier : suppliers) {
                supplier.displaySupplier();
            }
        }
    }
}
