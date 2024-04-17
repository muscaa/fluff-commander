package fluff.commander.command;

import fluff.commander.FluffCommander;
import fluff.commander.arg.IArgumentInput;

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
     * @param fc the FluffCommander instance managing the command
     * @param in the input containing command arguments
     * @return the exit code of the command after execution
     * @throws CommandException if an error occurs during command execution
     */
    int onAction(FluffCommander<?> fc, IArgumentInput in) throws CommandException;
    
    /**
     * Retrieves the name of this command.
     *
     * @return the name of the command
     */
    String getName();
    
    /**
     * Retrieves the description of this command.
     *
     * @return the description of the command
     */
    String getDescription();
}
