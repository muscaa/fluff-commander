package fluff.commander.command;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.IArgumentInput;

public interface ICommand {
	
	String getName();
	
	void onAction(FluffCommander fc, IArgumentInput in) throws CommanderException;
}
