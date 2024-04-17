package fluff.commander;

import fluff.commander.arg.IArgumentInput;
import fluff.commander.command.CommandException;
import fluff.commander.command.TaskCommand;

/**
 * Represents a FluffCommander, which is a type of task command.
 *
 * @param <C> the type of FluffCommander associated with this command
 */
public class FluffCommander<C extends FluffCommander<C>> extends TaskCommand<C> {
	
	/**
	 * Constructs a new FluffCommander with the specified name.
	 *
	 * @param name the name of the FluffCommander
	 */
	public FluffCommander(String name) {
		super(name);
	}
	
	/**
	 * Executes the FluffCommander with the given input arguments.
	 *
	 * @param in the input arguments
	 * @return the exit code of the command after execution
	 * @throws CommandException if an error occurs during command execution
	 */
	public int execute(IArgumentInput in) throws CommandException {
		return onAction(this, in);
	}
}
