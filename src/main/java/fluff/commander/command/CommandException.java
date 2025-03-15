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
     * @param message the message
     */
    public CommandException(String message) {
        super(message);
    }
    
    /**
     * Constructs a new CommandException with the specified message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Constructs a new CommandException with the specified cause.
     *
     * @param cause the cause
     */
    public CommandException(Throwable cause) {
        super(cause);
    }
}
