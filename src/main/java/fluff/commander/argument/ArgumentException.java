package fluff.commander.argument;

import fluff.commander.CommanderException;

/**
 * Exception thrown when an error occurs while processing command line arguments.
 */
public class ArgumentException extends CommanderException {
    
    private static final long serialVersionUID = -2303062509504751226L;
    
    /**
     * Constructs a new argument exception with the specified message.
     *
     * @param message the message
     */
    public ArgumentException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new argument exception with the specified message and cause.
     *
     * @param message the message
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
