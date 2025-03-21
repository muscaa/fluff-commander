package fluff.commander.command;

import fluff.commander.Commander;
import fluff.commander.argument.IArgumentInput;

/**
 * Interface representing a command to be executed by a commander.
 */
public interface ICommand {
	
	int UNKNOWN = -1;
	int SUCCESS = 0;
	int FAIL = 1;
	int HELP = 2;
	
    /**
     * Executes the action associated with this command.
     *
     * @param c the Commander instance managing the command
     * @param source the source where the command was executed from
     * @param in the input containing command arguments
     * @return the exit code of the command after execution
     * @throws CommandException if an error occurs during command execution
	 * @throws CommandNotFoundException if the command is not found
	 * @throws MissingArgumentsException if the required arguments are not provided
     */
    int execute(Commander<?, ?> c, ICommandSource source, IArgumentInput in) throws CommandException, CommandNotFoundException, MissingArgumentsException;
    
    /**
     * Retrieves the names of this command.
     *
     * @return the names of the command
     */
    String[] getNames();
    
    /**
     * Retrieves the description of this command.
     *
     * @return the description of the command
     */
    String getDescription();
    
    /**
     * Retrieves the usage of this command.
     *
     * @return the usage of the command
     */
    String getUsage();
    
    /**
     * Generates the help output message for this command.
     *
     * @param ob the output builder to write the help message to
     */
    void generateHelp(OutputBuilder ob);
}
