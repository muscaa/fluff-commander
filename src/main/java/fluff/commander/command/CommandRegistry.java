package fluff.commander.command;

import fluff.commander.CommanderRegistry;

/**
 * A registry for storing and managing commands.
 */
public class CommandRegistry extends CommanderRegistry<ICommand> {
    
	private final ICommand parent;
	
	public CommandRegistry(ICommand parent) {
		this.parent = parent;
	}
	
    @Override
    protected String[] getKeys(ICommand value) {
        return value.getNames();
    }
    
    @Override
	public void register(ICommand value) {
    	if (value instanceof AbstractCommand<?, ?> ac) ac.parent = parent;
    	
		super.register(value);
	}
}
