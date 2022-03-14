package ua.foxminded.exception;

public class ReaderException extends Exception {

    public ReaderException() {
        
    }
    public ReaderException(String message) {
        super(message);
    }
    public ReaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
