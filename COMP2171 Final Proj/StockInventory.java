import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class StockInventory {
    private List<Stock> inventory;

    public StockInventory() {
        this.inventory = new ArrayList<>();
    }

    public void addStock(String itemName, int quantity, double price) {
        Stock newStock = new Stock(itemName, quantity, price);
        inventory.add(newStock);
        System.out.println("Stock added: " + itemName);
    }

    public void updateStock(String itemName, int newQuantity) {
        for (Stock stock : inventory) {
            if (stock.getItemName().equalsIgnoreCase(itemName)) {
                stock.updateQuantity(newQuantity);
                System.out.println("Updated quantity for: " + itemName);
                return;
            }
        }
        System.out.println("Stock item not found: " + itemName);
    }

    public void displayInventory() {
        System.out.println("\n--- Current Inventory ---");
        if (inventory.isEmpty()) {
            System.out.println("No items in the inventory.");
        } else {
            for (Stock stock : inventory) {
                stock.displayStock();
            }
        }
    }

    public Stock searchStock(String itemName) {
        for (Stock stock : inventory) {
            if (stock.getItemName().equalsIgnoreCase(itemName)) {
                return stock;
            }
        }
        return null;
    }

    public void exportReport(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Inventory Report");
            writer.println("================");
            for (Stock stock : inventory) {
                writer.printf("Item: %s | Quantity: %d | Price: $%.2f%n",
                        stock.getItemName(), stock.getQuantity(), stock.getPrice());
            }
            System.out.println("Report exported to " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting report: " + e.getMessage());
        }
    }

    // Method to calculate total inventory value
    public double calculateInventoryValue() {
        double totalValue = 0.0;
        for (Stock stock : inventory) {
            totalValue += stock.getQuantity() * stock.getPrice();
        }
        return totalValue;
    }
}


