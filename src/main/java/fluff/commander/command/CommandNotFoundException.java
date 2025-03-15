package fluff.commander.command;

/**
 * Represents an exception thrown when a command is not found.
 */
public class CommandNotFoundException extends CommandException {
    
	private static final long serialVersionUID = -5979291923315426282L;
	
	/**
	 * Constructs a new CommandNotFoundException.
	 */
	public CommandNotFoundException() {
        super("Command not found!");
    }
}
