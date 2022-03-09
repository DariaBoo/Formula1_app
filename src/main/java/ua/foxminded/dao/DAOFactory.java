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
public class DAOFactory {
    private final String DB_USERNAME = "postgres";
    private final String DB_PASSWORD = "555";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    /**
     * Attempts to establish a connection to the database URL.
     * 
     * @return a Connection to the URL.
     * @throws DAOException if a database access error occurs.
     */
    public final Connection getConnection() throws DAOException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException sqlE) {
            throw new DAOException("Failed to connect to database.", sqlE);
        }
        return connection;
    }
}
