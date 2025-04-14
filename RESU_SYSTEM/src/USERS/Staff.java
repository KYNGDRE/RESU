package USERS;
// Staff class that extends User and can manage inventory
public class Staff extends User {

    public Staff(String username, String password) {
         super(username, password);
    }

    // Stub method for inventory management
    public void manageInventory() {
         System.out.println("Staff " + username + " is managing inventory.");
    }
}