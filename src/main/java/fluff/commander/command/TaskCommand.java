package fluff.commander.command;

import java.util.LinkedHashMap;
import java.util.Map;

import fluff.commander.CommanderException;
import fluff.commander.FluffCommander;
import fluff.commander.arg.IArgumentInput;
import fluff.functions.gen.obj.VoidFunc1;

public class TaskCommand extends AbstractCommand {
	
	protected final Map<String, ICommand> commands = new LinkedHashMap<>();
	
	public TaskCommand(String name) {
		super(name);
	}
	
	@Override
	public void initArguments() {}
	
	@Override
	public boolean onAction(FluffCommander fc, CommandArguments args) throws CommanderException {
		return false;
	}
	
	@Override
	public boolean onAction(FluffCommander fc, IArgumentInput in) throws CommanderException {
		if (super.onAction(fc, in)) return true;
		
		if (in.isNull()) return false;
		
		ICommand cmd = commands.get(in.consume());
		if (cmd == null) throw new CommanderException("Command not found!");
		
		return cmd.onAction(fc, in);
	}
	
	public <V extends ICommand> V command(V command) {
		if (command instanceof AbstractCommand ac) ac.parent = this;
		
		commands.put(command.getName(), command);
		return command;
	}
	
	public <V extends ICommand> void command(V command, VoidFunc1<V> func) {
		func.invoke(command(command));
	}
	
	public TaskCommand task(String name) {
		return command(new TaskCommand(name));
	}
	
	public void task(String name, VoidFunc1<TaskCommand> func) {
		func.invoke(task(name));
	}
}
