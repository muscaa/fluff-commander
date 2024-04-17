package fluff.commander.arg;

import fluff.commander.CommanderException;

/**
 * Exception thrown when an error occurs while processing command line arguments.
 */
public class ArgumentException extends CommanderException {
    
    private static final long serialVersionUID = -2303062509504751226L;
    
    /**
     * Constructs a new argument exception with the specified detail message.
     *
     * @param message the detail message
     */
    public ArgumentException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new argument exception with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public ArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new argument exception with the specified cause.
     *
     * @param cause the cause
     */
    public ArgumentException(Throwable cause) {
        super(cause);
    }
}
