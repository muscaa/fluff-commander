package fluff.commander.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentInput;
import fluff.functions.gen.obj.obj.TVoidFunc2;

public class ForwardCommand implements ICommand {
	
	protected final Map<String, ICommand> commands = new HashMap<>();
	
	private final ICommand actionCommand;
	
	public ForwardCommand(ICommand actionCommand) {
		this.actionCommand = actionCommand;
	}
	
	@Override
	public String getName() {
		return actionCommand.getName();
	}
	
	@Override
	public void onAction(FluffCommander fc, IArgumentInput in) throws CommanderException {
		actionCommand.onAction(fc, in);
		
		if (in.isNull()) return;
		
		ICommand cmd = commands.get(in.consume());
		if (cmd == null) throw new CommanderException("Command not found!");
		
		cmd.onAction(fc, in);
	}
	
	public ForwardCommand command(ICommand command) {
		if (command instanceof AbstractCommand ac) ac.parent = this;
		commands.put(command.getName(), command);
		return this;
	}
	
	public ForwardCommand command(String name, List<IArgument<?>> argList, TVoidFunc2<FluffCommander, CommandArguments, CommanderException> action) {
		return command(new FunctionCommand(name, argList, action));
	}
	
	public ForwardCommand forward(ICommand actionCommand) {
		ForwardCommand cmd = new ForwardCommand(actionCommand);
		command(cmd);
		return cmd;
	}
	
	public ForwardCommand forward(String name) {
		return forward(new EmptyCommand(name));
	}
	
	public ICommand getActionCommand() {
		return actionCommand;
	}
}
