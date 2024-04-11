package fluff.commander.command;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentInput;

public abstract class AbstractCommand implements ICommand {
	
	private final ArgumentRegistry reg = new ArgumentRegistry();
	private final String name;
	
	ICommand parent;
	
	public AbstractCommand(String name) {
		this.name = name;
		
		initArguments(reg);
	}
	
	public abstract void initArguments(ArgumentRegistry reg);
	
	public abstract void onAction(FluffCommander fc, CommandArguments args) throws CommanderException;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void onAction(FluffCommander fc, IArgumentInput in) throws CommanderException {
		CommandArguments args = CommandArguments.parse(in, reg);
		
		onAction(fc, args);
	}
	
	protected <V> IArgument<V> arg(String name) {
		return (IArgument<V>) reg.get(name);
	}
	
	protected <V extends ICommand> V parent() {
		return (V) parent;
	}
}
