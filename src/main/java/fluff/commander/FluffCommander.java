package fluff.commander;

import fluff.commander.arg.IArgumentInput;
import fluff.commander.command.EmptyCommand;
import fluff.commander.command.ForwardCommand;
import fluff.commander.command.ICommandOutput;
import fluff.commander.command.SystemCommandOutput;

public class FluffCommander extends ForwardCommand {
	
	private ICommandOutput out = new SystemCommandOutput();
	
	public FluffCommander() {
		super(new EmptyCommand(null));
	}
	
	public void execute(IArgumentInput in) throws CommanderException {
		onAction(this, in);
	}
	
	public ICommandOutput out() {
		return out;
	}
	
	public void setOut(ICommandOutput out) {
		this.out = out;
	}
}
