package fluff.commander.command;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.IArgumentInput;

public class EmptyCommand implements ICommand {
	
	private final String name;
	
	public EmptyCommand(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void onAction(FluffCommander fc, IArgumentInput in) throws CommanderException {}
}
