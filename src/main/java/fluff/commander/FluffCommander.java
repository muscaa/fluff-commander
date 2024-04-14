package fluff.commander;

import fluff.commander.arg.IArgumentInput;
import fluff.commander.command.TaskCommand;

public class FluffCommander extends TaskCommand {
	
	public FluffCommander(String name) {
		super(name);
	}
	
	public boolean execute(IArgumentInput in) throws CommanderException {
		return onAction(this, in);
	}
}
