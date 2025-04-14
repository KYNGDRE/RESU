package RESU;
import java.util.List;
// import java.util.ArrayList;

// Order class that aggregates a list of items and calculates total price
public class Order {
    private static int orderCounter = 1; // Simple static counter for generating order IDs
    private int orderId;
    private List<Item> items;

    public Order(int orderId, List<Item> items) {
         this.orderId = orderId;
         this.items = items;
    }

    // Generates and returns a unique order ID
    public static int getNextOrderId() {
         return orderCounter++;
    }

    // Calculates the total price of the order by summing up item prices
    public double calculateTotal() {
         double total = 0;
         for(Item item : items) {
             total += item.getPrice();
         }
         return total;
    }

    // Getter for order ID
    public int getOrderId() {
         return orderId;
    }

    // Getter for the list of items in the order
    public List<Item> getItems() {
         return items;
    }
}
