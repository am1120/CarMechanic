package database;

import ldap.Uthldap;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CMUser;
import model.Car;
import model.Comment;
import model.Problem;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import model.Category;
import utilities.CMRoles;

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
    private static final String DBPSW = "123456";

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
     * *
     * Searches user db to find if a username already exists
     *
     * @param username
     * @return
     */
    public boolean userExists(String username) {

        ResultSet rs = getUser(username);

        if (rs != null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Add a user to our db who used LDAP to login for first time
     *
     * @param username
     * @param ldap
     * @return
     */
    public ResultSet addUser(String username, Uthldap ldap) {

        if (!ldap.auth() || !IS_CONNECTED) {
            // Not authenticated or no db connection
            return null;
        }
        try {
            // Prepare a statement to insert a user
            PreparedStatement pstmt
                    = conn.prepareStatement(
                            "INSERT INTO users(username, password, account_type, role, first_name, "
                            + "last_name, email, notifications, misc,birthdate) VALUES (?,?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, username);
            pstmt.setString(2, "NULL"); // LDAP Doesnt need password
            pstmt.setString(3, "LDAP");
            pstmt.setInt(4, CMRoles.SIMPLE_USER); // Role 1 - Simple user 
            pstmt.setString(5, ldap.getName());
            pstmt.setString(6, ldap.getName());
            pstmt.setString(7, ldap.getMail());
            pstmt.setBoolean(8, false);
            pstmt.setString(9, "NULL");
            pstmt.setString(10, ldap.getBirthYear());

            // Execute insert query
            boolean result = pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            if (result) {
                System.out.println("ERROR: Wrong Query");
                return null;
            } else {
                System.out.println("User " + username + " was added successfully");
                return rs;
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Exception" + e.getMessage());

        }

        // Was added sucessfuly
        return null;
    }

    /**
     * Add a user to our db who used regular sign up
     *
     * @param username
     * @param ldap
     * @return
     */
    public ResultSet addUser(CMUser user) {

        if (!IS_CONNECTED) {
            // No db connection
            return null;
        }
        try {
            // Prepare a statement to insert a user
            PreparedStatement pstmt
                    = conn.prepareStatement(
                            "INSERT INTO users(username, password, account_type, role, first_name, "
                            + "last_name, email, notifications, misc,birthdate) VALUES (?,?,?,?,?,?,?,?,?,?)");

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getAccountType());
            pstmt.setInt(4, user.getRole());
            pstmt.setString(5, user.getUname());
            pstmt.setString(6, user.getUname());
            pstmt.setString(7, user.getEmail());
            pstmt.setBoolean(8, false);
            pstmt.setString(9, user.getMisc());
            pstmt.setString(10, user.getBirthDate());

            // Execute insert query
            boolean result = pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            if (result) {
                System.out.println("ERROR: Wrong Query");
                return null;
            } else {
                System.out.println("User " + user.getUsername() + " was added successfully");
                return rs;
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Exception" + e.getMessage());

        }

        // Was added sucessfuly
        return null;
    }

    /* * * Car Methods * * */
    public Map<Integer, String> getModels(String maker_id) {
        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;
        Map<Integer, String> result = new HashMap<>();

        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM car_model WHERE ManufacturerID = '" + maker_id + "'");

            while (rs.next()) {
                // Get Model
                result.put(rs.getInt("ModelsId"), rs.getString("ModelDescription"));
            }

        } catch (SQLException e) {
            System.out.println("getModels exception:" + e.getMessage());
        }

        return result;

    }

    public Map<Integer, String> getYears(String model_id) {
        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;
        Map<Integer, String> result = new HashMap<>();

        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM car_model WHERE ModelsID = '" + model_id + "'");

            while (rs.next()) {
                // Get Model
                String startYear = String.valueOf(rs.getInt("YearBegin"));
                String endYear = String.valueOf(rs.getInt("YearEnd"));
                result.put(rs.getInt("ModelsId"), startYear + " - " + endYear);
            }

        } catch (SQLException e) {
            System.out.println("getYears exception:" + e.getMessage());
        }

        return result;

    }

    public Map<Integer, String> getEngine(String model_id) {
        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;
        Map<Integer, String> result = new HashMap<>();

        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM car_model WHERE ModelsID = '" + model_id + "'");

            while (rs.next()) {
                // Get Model

                result.put(rs.getInt("ModelsId"), rs.getString("SubModelDescription1"));
            }

        } catch (SQLException e) {
            System.out.println("getEngine exception:" + e.getMessage());
        }

        return result;

    }

    public List<Car> searchCars(String maker, String model, String year, String engine) {
        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        String startYear = "%";
        String endYear = "%";
        if (!year.equals("%")) {
            startYear = year.substring(0, 4);
            endYear = year.substring(7, 11);
        }
        ResultSet rs = null;
        List<Car> result = new ArrayList<>();
        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM car_maker WHERE maker = '" + maker + "'");

            if (rs.next()) {
                // We found a maker return it as result set
                System.out.println("Maker found");
                int maker_id = rs.getInt("m_id");

                rs = stmt.executeQuery("SELECT * FROM car_model WHERE ManufacturerID LIKE '" + maker_id
                        + "' " + "AND ModelDescription LIKE '" + model
                        + "' " + "AND YearBegin LIKE '" + startYear
                        + "' " + "AND YearEnd LIKE '" + endYear
                        + "' " + "AND SubModelDescription1 LIKE '" + engine + "'");

                while (rs.next()) {
                    // Get car info
                    Car newCar = new Car(maker, String.valueOf(rs.getInt("ModelsID")), rs.getString("ModelDescription"), rs.getString("YearBegin") + " - " + rs.getString("YearEnd"), rs.getString("SubModelDescription1"));
                    System.out.println("New car: " + newCar.toString());
                    result.add(newCar);
                }
            } else {
                System.out.println("No Cars found");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("SearchCar exception:" + e.getMessage());
        }

        return result;
    }

    public List<Car> searchCars(String[] arguments) {

        List<Car> result = null;

        switch (arguments.length) {
            case 1: // makers only
                result = searchCars(arguments[0], "%", "%", "%");
                break;
            case 2: //maker & model
                result = searchCars(arguments[0], arguments[1], "%", "%");
                break;
            case 3: //maker, model, year
                //result = searchCars(arguments[0], arguments[2], arguments[3], "%");
                // Ignore for now
                result = searchCars(arguments[0], arguments[1], "%", "%");

                break;
            case 4: //maker,model,year, engine
                //result = searchCars(arguments[0], arguments[1], arguments[2], arguments[3]);
                // ignore for now
                result = searchCars(arguments[0], arguments[1], "%", "%");

                break;
            default:
                result = searchCars(arguments[0], arguments[1], "%", "%");
                System.out.println("Search arguments incorrect");
                break;
        }
        return result;
    }

    public Car getCar(int modelid) {

        ResultSet rs = null;
        ResultSet rsMaker = null;
        Car result = null;

        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM car_model WHERE ModelsID = '" + modelid + "'");

            if (rs.next()) {
                // We found the model

                int maker_id = rs.getInt("ManufacturerID");
                String model = rs.getString("ModelDescription");
                String year = rs.getString("YearBegin") + " - " + rs.getString("YearEnd");
                String engine = rs.getString("SubModelDescription1");
                String modelId = String.valueOf(rs.getInt("ModelsID"));

                // Get Maker
                rsMaker = stmt.executeQuery("SELECT * FROM car_maker WHERE m_id = '" + maker_id + "'");

                if (rsMaker.next()) {
                    // Get car info
                    Car newCar = new Car(rsMaker.getString("maker"), modelId, model, year, engine);
                    result = newCar;
                }
            } else {
                System.out.println("No Cars found");
                return null;
            }

        } catch (SQLException e) {
            System.out.println("SearchCar exception:" + e.getMessage());
        }

        return result;

    }

    /* * * Problem Methods * * */
    public Map<Integer,Category> getProblems(int model_id) {

        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;
        
        Map<Integer, String> categories;
        Map<Integer, Category> catResult = null;
        try {

            // Get Categories
            categories = getProblemCategories();

            // Create enhanced result
            catResult = new HashMap<>();
            for (Map.Entry<Integer, String> entry : categories.entrySet()) {
                catResult.put(entry.getKey(),new Category(entry.getKey(),entry.getValue()));
            }

            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM problems WHERE model_id = '" + model_id + "'");

            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                Problem newProblem = new Problem(rs.getInt("p_id"), rs.getInt("model_id"), rs.getString("description"),
                        rs.getString("solution"), rs.getDate("created_at"), rs.getString("status"),rs.getInt("user_id"),category_id);
                System.out.println("Problem fetched: " + newProblem.toString());
                if(catResult == null) System.out.println("Null 1");
                if(catResult.get(category_id) == null) System.out.println("Null 2");
                if(catResult.get(category_id).getProblems() == null) System.out.println("Null 3");
                
                
                catResult.get(category_id).getProblems().add(newProblem);
            }

        } catch (SQLException e) {
            System.out.println("SearchProblem exception:" + e.getMessage());
        }

        return catResult;

    }

    public List<Problem> getUserProblems(int user_id) {

        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;
        List<Problem> result = new ArrayList<>();
        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM problems WHERE user_id = '" + user_id + "'");

            while (rs.next()) {
                Problem newProblem = new Problem(rs.getInt("p_id"), rs.getInt("model_id"), rs.getString("description"),
                        rs.getString("solution"), rs.getDate("created_at"), rs.getString("status"));
                System.out.println("Problem fetched: " + newProblem.toString());
                result.add(newProblem);
            }

        } catch (SQLException e) {
            System.out.println("SearchProblem exception:" + e.getMessage());
        }

        return result;

    }

    public Problem getProblemDetails(int p_id) {

        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;
        Problem problem = null;
        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM problems WHERE p_id = '" + p_id + "'");

            if (rs.next()) {
                problem = new Problem(rs.getInt("p_id"), rs.getInt("model_id"), rs.getString("description"),
                        rs.getString("solution"), rs.getDate("created_at"), rs.getString("status"));
                System.out.println("Problem details: " + problem.toString());

            }

        } catch (SQLException e) {
            System.out.println("SearchProblem exception:" + e.getMessage());
        }

        return problem;

    }

    public void insertProblem(String modelId, String description, String solution, String photoPath, int userId, String category) {

        // Status check
        if (!IS_CONNECTED) {
            return;
        }

        try {
            // Prepare a statement to insert a user
            PreparedStatement pstmt
                    = conn.prepareStatement(
                            "INSERT INTO problems( model_id,description,solution, photo_path,created_at,status,user_id,category_id)"
                            + "VALUES (?,?,?,?,?,?,?,?)");

            pstmt.setInt(1, Integer.parseInt(modelId));
            pstmt.setString(2, description);
            pstmt.setString(3, solution);
            pstmt.setString(4, photoPath);

            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pstmt.setDate(5, sqlDate);
            if (solution.isEmpty()) {
                pstmt.setString(6, "UNSOLVED");
            } else {
                pstmt.setString(6, "SOLVED");
            }
            pstmt.setInt(7, userId);
            pstmt.setInt(8, Integer.valueOf(category));

            // Execute insert query
            boolean result = pstmt.execute();

            if (result) {
                System.out.println("ERROR: Wrong Query");
                return;
            } else {
                System.out.println("Problem was added successfully");
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Exception " + e.getMessage());

        }

    }

    public Map<Integer, String> getProblemCategories() {

        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;
        Map<Integer, String> result = new HashMap<>();

        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM problem_categories");

            while (rs.next()) {
                // Get Categories
                result.put(rs.getInt("cat_id"), rs.getString("latin_cat_name"));
            }
        } catch (SQLException e) {
            System.out.println("getEngine exception:" + e.getMessage());
        }

        return result;

    }

    /* * * Comments methods * * */
    public List<Comment> getComments(int problemId) {

        // Status check
        if (!IS_CONNECTED) {
            return null;
        }

        ResultSet rs = null;
        List<Comment> result = new ArrayList<>();

        try {
            // Prepare statement to db
            Statement stmt = conn.createStatement();

            // Run a query to fetch requested maker
            rs = stmt.executeQuery("SELECT * FROM comments WHERE prob_id = '" + problemId + "'");

            while (rs.next()) {
                Comment newComment = new Comment(String.valueOf(rs.getInt("c_id")), String.valueOf(rs.getInt("prob_id")),
                        String.valueOf(rs.getInt("author_id")), rs.getDate("create_at"), rs.getString("content"));
                System.out.println("Comment fetched: " + newComment.toString());
                result.add(newComment);
            }

        } catch (SQLException e) {
            System.out.println("SearchProblem exception:" + e.getMessage());
        }

        return result;
    }

    public void insertComment(String problemId, String content, int authorId) {

        // Status check
        if (!IS_CONNECTED) {
            return;
        }

        try {
            // Prepare a statement to insert a user
            PreparedStatement pstmt
                    = conn.prepareStatement(
                            "INSERT INTO comments(prob_id, author_id, create_at,content )"
                            + "VALUES (?,?,?,?)");

            pstmt.setInt(1, Integer.parseInt(problemId));
            pstmt.setInt(2, authorId);
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            pstmt.setDate(3, sqlDate);
            pstmt.setString(4, content);

            // Execute insert query
            boolean result = pstmt.execute();

            if (result) {
                System.out.println("ERROR: Wrong Query");
                return;
            } else {
                System.out.println("Comment was added successfully");
            }

        } catch (SQLException e) {
            System.out.println("ERROR: Exception" + e.getMessage());

        }

    }

    /* * * Closing methods * * */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
            }
        }
    }

}
