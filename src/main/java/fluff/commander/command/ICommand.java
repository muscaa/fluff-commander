package fluff.commander.command;

import fluff.commander.FluffCommander;
import fluff.commander.arg.IArgumentInput;

/**
 * Interface representing a command to be executed by a commander.
 */
public interface ICommand {
    
    /**
     * Executes the action associated with this command.
     *
     * @param fc the FluffCommander instance managing the command
     * @param in the input containing command arguments
     * @return true if the action was executed successfully, otherwise false
     * @throws CommandException if an error occurs during command execution
     */
    boolean onAction(FluffCommander fc, IArgumentInput in) throws CommandException;
    
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
