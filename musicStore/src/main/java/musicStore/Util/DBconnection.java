package musicStore.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBconnection {

    private static final String url = "jdbc:mysql://localhost:3306/musicstore";
    private static final String user = "root";
    private static final String password = "2003@achini";
    
//use loggers for track database connecting issues
    private static final Logger logger = Logger.getLogger(DBconnection.class.getName());

    // Single instance of DBconnection
    private static DBconnection instance;

    // Private constructor to prevent external instantiation
    private DBconnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "MySQL JDBC Driver not found", e);
        }
    }

    // Get the singleton instance of DBconnection
    public static DBconnection getInstance() {
        if (instance == null) {
            synchronized (DBconnection.class) {
                if (instance == null) {
                    instance = new DBconnection();
                }
            }
        }
        return instance;
    }

    // Get a new DB connection
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            logger.info("Database connection established successfully.");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL error during database connection", e);
        }
        return conn;
    }
}
