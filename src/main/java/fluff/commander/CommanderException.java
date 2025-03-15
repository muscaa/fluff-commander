package fluff.commander;

/**
 * Main exception class for this library.
 */
public class CommanderException extends Exception {
    
    private static final long serialVersionUID = -402320519751824367L;
    
    /**
     * Constructs a new CommanderException with the specified message.
     *
     * @param message the message
     */
    public CommanderException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new CommanderException with the specified message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public CommanderException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new CommanderException with the specified cause.
     *
     * @param cause the cause
     */
    public CommanderException(Throwable cause) {
        super(cause);
    }
}
