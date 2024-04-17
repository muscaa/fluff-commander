package fluff.commander;

/**
 * Signals that an exception specific to the Commander framework has occurred.
 */
public class CommanderException extends Exception {
    
    private static final long serialVersionUID = -402320519751824367L;
    
    /**
     * Constructs a new CommanderException with the specified detail message.
     *
     * @param message the detail message
     */
    public CommanderException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new CommanderException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public CommanderException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new CommanderException with the specified cause and a detail message of (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause).
     *
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public CommanderException(Throwable cause) {
        super(cause);
    }
}
