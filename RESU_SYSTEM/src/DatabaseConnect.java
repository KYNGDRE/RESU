import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util. logging.Level; 
import java.util. logging.Logger;

public class DatabaseConnect {
    // public static void main (String[] args) {
    //     DatabaseConnect pro = new DatabaseConnect();
    //     pro.createConnection();
    // }

    void createConnection (){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver") ;
            Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3306/RESU", "RESU-ADMIN", "password");
            System.out.println("Databage Connection Success") ;
        }catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnect.class.getName()).log (Level. SEVERE,null, ex);
        }catch (SQLException ex) {
            Logger. getLogger (DatabaseConnect.class.getName()).log (Level. SEVERE,null, ex);
        }
    }
}

