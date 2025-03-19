package RESU;
// Item class representing individual items available for order
public class Item {
    private int itemId;
    private String name;
    private double price;

    public Item(int itemId, String name, double price) {
         this.itemId = itemId;
         this.name = name;
         this.price = price;
    }

    // Getter for item ID
    public int getItemId() {
         return itemId;
    }

    // Getter for item name
    public String getName() {
         return name;
    }

    // Getter for item price
    public double getPrice() {
         return price;
    }
}
