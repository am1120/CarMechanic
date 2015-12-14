package database;

import ldap.Uthldap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class that handles Database Operations
 *
 * @author Alexander
 *
 * Used code by AAA Team from AuthServlet.java
 */
public class DatabaseManager {

    /* * * Manager status * * */
    private static boolean IS_CONNECTED = false;

    /* * * Database config * * */
    private static final String DB = "jdbc:mysql://localhost:3306/car_mechanic";
    private static final String DBUSER = "www";
    private static final String DBPSW = "";

    /* * * Database Connection * * */
    Connection conn = null;

    /**
     * Constructor
     *
     * Initializes db connection
     */
    public DatabaseManager() {

        /* * * Initialize connection to db * * */
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB, DBUSER, DBPSW);
            IS_CONNECTED = true;
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getCause());
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
            Logger.getLogger(DatabaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    /**
     * Query a username in db, if it was registered previously
     *
     * @param username
     * @return A ResultSet of the User found - or null if not found
     */
    public ResultSet getUser(String username) {

        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;

        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested user
            rs = stmt.executeQuery("SELECT * FROM users WHERE username='" + username + "'");

            if (rs.next()) {
                // We found a user return it as result set
                System.out.println("User found");
                return rs;
            } else {
                System.out.println("User not found");
                return null;
            }

        } catch (SQLException e) {

        }
        System.out.println("User not found");
        return null;
    }

    /**
     * Add a user to our db - Currently with LDAP
     *
     * @param username
     * @param mis
     * @return
     */
    public boolean addUser(String username, Uthldap ldap) {

        if (!ldap.auth() || !IS_CONNECTED) {
            // Not authenticated or no db connection
            return false;
        }
        try {
            // Prepare a statement to insert a user
            PreparedStatement pstmt
                    = conn.prepareStatement(
                            "INSERT INTO users(username, password, account_type, role, first_name, "
                            + "last_name, email, notifications, misc) VALUES (?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, username);
            pstmt.setString(2, "NULL"); // LDAP Doesnt need password
            pstmt.setString(3, "LDAP");
            pstmt.setInt(4, 1); // Role 1 - Simple user 
            pstmt.setString(5, ldap.getName()); 
            pstmt.setString(6, ldap.getName());
            pstmt.setString(7, ldap.getMail());
            pstmt.setBoolean(8, false);
            pstmt.setString(9, "NULL");

            // Execute insert query
            boolean result = pstmt.execute();

            if (result) {
                System.out.println("ERROR: Wrong Query");
                return false;
            } else {
                System.out.println("User " + username + " was added successfully");
            }

        } catch (SQLException e) {
                System.out.println("ERROR: Exception" + e.getMessage());

        }

        // Was added sucessfuly
        return true;
    }

}
