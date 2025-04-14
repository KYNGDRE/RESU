public class Stock {
    private String itemName;
    private int quantity;
    private double price;

    // Constructor to initialize stock item details
    public Stock(String itemName, int quantity, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters for stock details
    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Update the quantity of the stock item
    public void updateQuantity(int newQuantity) {
        this.quantity = newQuantity;
    }

    // Display stock details
    public void displayStock() {
        System.out.printf("Item: %s | Quantity: %d | Price: $%.2f%n", itemName, quantity, price);
    }
}
