package fluff.commander;

import fluff.commander.arg.IArgumentInput;
import fluff.commander.command.CommandException;
import fluff.commander.command.TaskCommand;

/**
 * Represents a Commander, which is a type of task command.
 *
 * @param <C> the type of Commander associated with this command
 */
public class Commander<C extends Commander<C>> extends TaskCommand<C> {
	
	/**
	 * Constructs a new Commander with the specified name.
	 *
	 * @param executable true if the Commander is executable, false otherwise
	 * @param name the name of the Commander
	 * @param alias the alias of the Commander
	 */
	public Commander(boolean executable, String name, String... alias) {
		super(executable, name, alias);
	}
	
	/**
	 * Executes the Commander with the given input arguments.
	 *
	 * @param in the input arguments
	 * @return the exit code of the command after execution
	 * @throws CommandException if an error occurs during command execution
	 */
	public int execute(IArgumentInput in) throws CommandException {
		return execute(this, in);
	}
}
