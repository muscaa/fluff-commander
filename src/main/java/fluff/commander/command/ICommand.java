package fluff.commander.command;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.IArgumentInput;

public interface ICommand {
	
	boolean onAction(FluffCommander fc, IArgumentInput in) throws CommanderException;
	
	String getName();
	
	String getDescription();
}
