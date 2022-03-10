package ua.foxminded.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Provides access to the database PostgreSQL by getting a Connection instance
 * using database URL, user name and password.
 * <p>
 * The database URL of the form jdbc:postgresql://host:port/database.
 * <p>
 * User - the database user under which the connection is being made.
 * <p>
 * Password - the database user's password.
 * 
 * @author Daria Bogush
 * @version 1.0
 */
public class DAOConnection {
    private static DAOConnection instance;
    private Connection connection;
    private static String DB_USERNAME = "postgres";
    private static String DB_PASSWORD = "555";
    private static String DB_URL = "jdbc:postgresql://localhost:5432/postgres";


    private DAOConnection() throws DAOException {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect to database.", sqlE);
        }
    }
    /**
     * Attempts to establish a connection to the database URL.
     * 
     * @return a Connection to the URL.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Creates an instance of the class.
     * 
     * @return an instance of the class.
     * @throws DAOException if a database access error occurs.
     */
    public static DAOConnection getInstance() throws DAOException {
        try {
            if (instance == null) {
                instance = new DAOConnection();
            } else if (instance.getConnection().isClosed()) {
                instance = new DAOConnection();
            }
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect to database.", sqlE);
        }
        return instance;
    }
}
