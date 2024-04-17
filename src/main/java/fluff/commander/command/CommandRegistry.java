package fluff.commander.command;

import fluff.commander.CommanderRegistry;

/**
 * A registry for storing and managing commands.
 */
public class CommandRegistry extends CommanderRegistry<ICommand> {
    
    /**
     * Retrieves the keys associated with the given command.
     *
     * @param value the command for which keys are retrieved
     * @return an array of keys associated with the command
     */
    @Override
    protected String[] getKeys(ICommand value) {
        return new String[]{value.getName()};
    }
}
