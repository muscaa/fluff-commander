package fluff.commander;

import fluff.commander.arg.IArgumentInput;
import fluff.commander.command.ICommandOutput;
import fluff.commander.command.SystemCommandOutput;
import fluff.commander.command.TaskCommand;

public class FluffCommander extends TaskCommand {
	
	private ICommandOutput out = new SystemCommandOutput();
	
	public FluffCommander() {
		super(null);
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
