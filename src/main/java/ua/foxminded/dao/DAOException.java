package ua.foxminded.dao;

/**
 * The class DAOException is a DAO layer exception which can be thrown when a
 * database access error occurs.
 * 
 * @author Daria Bogush
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DAOException extends Exception {
    /**
     * Constructs a new exception with null as its detail message.
     */
    public DAOException() {
        super();
    }

    /**
     * Constructs a new exception with the specified detail message.
     * 
     * @param message the detail message.
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause   the cause.
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}