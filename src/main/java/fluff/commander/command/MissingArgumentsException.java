package fluff.commander.command;

import java.util.stream.Collectors;

/**
 * Represents an exception thrown when a command is missing arguments.
 */
public class MissingArgumentsException extends CommandException {
    
	private static final long serialVersionUID = 6684049183771205282L;
	
	/**
     * Constructs a new missing arguments exception with the specified arguments.
     *
     * @param args the command arguments
     */
	public MissingArgumentsException(CommandArguments args) {
		super("Missing arguments: " + args.getMissingArguments()
				.stream()
				.map(arg -> arg.getNames()[0])
				.collect(Collectors.toList())
				);
	}
}
