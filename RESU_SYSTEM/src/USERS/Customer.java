package USERS;
import java.util.List;

import RESU.Item;
import RESU.Order;
// import java.util.ArrayList;
// Customer class that extends User and can place orders
public class Customer extends User {

    public Customer(String username, String password) {
         super(username, password);
    }

    // Method to place an order by providing a list of items
    public Order placeOrder(List<Item> items) {
         Order order = new Order(Order.getNextOrderId(), items);
         System.out.println("Order placed by customer " + username);
         return order;
    }
}
