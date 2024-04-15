package fluff.commander.command;

import fluff.commander.CommanderRegistry;

public class CommandRegistry extends CommanderRegistry<ICommand> {
	
	@Override
	protected String[] getKeys(ICommand value) {
		return new String[] { value.getName() };
	}
}
