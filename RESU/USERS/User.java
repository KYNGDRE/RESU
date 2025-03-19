package USERS;

// Abstract class representing a generic user
public abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
         this.username = username;
         this.password = password;
    }

    // Basic login method for demonstration purposes
    public boolean login(String username, String password) {
         return this.username.equals(username) && this.password.equals(password);
    }
}