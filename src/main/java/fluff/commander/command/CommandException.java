package fluff.commander.command;

import fluff.commander.CommanderException;

/**
 * Exception class for errors that occur during command execution.
 */
public class CommandException extends CommanderException {
    
    private static final long serialVersionUID = 5017222905790795894L;
    
    /**
     * Constructs a new CommandException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public CommandException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new CommandException with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new CommandException with the specified cause and a detail message of (cause==null ? null : cause.toString())
     *
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public CommandException(Throwable cause) {
        super(cause);
    }
}
