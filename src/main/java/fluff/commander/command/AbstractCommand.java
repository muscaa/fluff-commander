package fluff.commander.command;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgumentInput;

public abstract class AbstractCommand implements ICommand {
	
	public final ArgumentRegistry reg = new ArgumentRegistry();
	private final String name;
	
	ICommand parent;
	
	public AbstractCommand(String name) {
		this.name = name;
		
		initArguments();
	}
	
	public abstract void initArguments();
	
	public abstract boolean onAction(FluffCommander fc, CommandArguments args) throws CommanderException;
	
	@Override
	public boolean onAction(FluffCommander fc, IArgumentInput in) throws CommanderException {
		CommandArguments args = CommandArguments.parse(in, reg);
		
		return onAction(fc, args);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public String getDescription() {
		return null;
	}
	
	public <V extends ICommand> V parent() {
		return (V) parent;
	}
}
