package fluff.commander;

import fluff.commander.argument.IArgumentInput;
import fluff.commander.command.CommandException;
import fluff.commander.command.ICommandSource;
import fluff.commander.command.TaskCommand;

/**
 * Represents a Commander, which is a type of task command.
 *
 * @param <C> the type of Commander associated with this command
 * @param <S> the type of ICommandSource associated with this command
 */
public class Commander<C extends Commander<C, S>, S extends ICommandSource> extends TaskCommand<C, S> {
	
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
	 * @param source the source where the command was executed from
	 * @param in the input arguments
	 * @return the exit code of the command after execution
	 * @throws CommandException if an error occurs during command execution
	 */
	public int execute(S source, IArgumentInput in) throws CommandException {
		return execute(this, source, in);
	}
}
