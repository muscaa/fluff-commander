package fluff.commander.command;

import fluff.commander.FluffCommander;
import fluff.commander.arg.IArgumentInput;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * Represents a task command.
 *
 * @param <C> the type of FluffCommander associated with this command
 */
public class TaskCommand<C extends FluffCommander<C>> extends AbstractCommand<C> {
	
	private final CommandRegistry commands = new CommandRegistry();
	
	/**
	 * Constructs a new task command with the specified name.
	 *
	 * @param name the name of the command
	 */
	public TaskCommand(String name) {
		super(name);
	}
	
	@Override
	public void initArguments() {}
	
	@Override
	public int onAction(C fc, CommandArguments args) throws CommandException {
		return UNKNOWN;
	}
	
	@Override
	public int onAction(FluffCommander<?> fc, IArgumentInput in) throws CommandException {
		int argsExit = super.onAction(fc, in);
		if (argsExit != UNKNOWN) return argsExit;
		
		if (in.isNull()) return UNKNOWN;
		
		ICommand cmd = commands.get(in.consume());
		if (cmd == null) throw new CommandException("Command not found!");
		
		return cmd.onAction(fc, in);
	}
	
	/**
	 * Registers a command for this task.
	 *
	 * @param command the command to register
	 * @param <V> the type of the command
	 * @return the registered command
	 */
	public <V extends ICommand> V command(V command) {
		if (command instanceof AbstractCommand<?> ac) ac.parent = this;
		
		commands.register(command);
		return command;
	}
	
	/**
	 * Registers a command for this task and applies additional configuration using a functional interface.
	 *
	 * @param command the command to register
	 * @param func the function to apply to the command
	 * @param <V> the type of the command
	 */
	public <V extends ICommand> void command(V command, VoidFunc1<V> func) {
		func.invoke(command(command));
	}
	
	/**
	 * Creates and registers a new task under this task command.
	 *
	 * @param name the name of the task
	 * @return the registered task command
	 */
	public TaskCommand<C> task(String name) {
		return command(new TaskCommand<>(name));
	}
	
	/**
	 * Creates and registers a new task under this task command and applies additional configuration using a functional interface.
	 *
	 * @param name the name of the task
	 * @param func the function to apply to the task
	 */
	public void task(String name, VoidFunc1<TaskCommand<C>> func) {
		func.invoke(task(name));
	}
	
	/**
	 * Retrieves the command registry associated with this task command.
	 *
	 * @return the command registry
	 */
	public CommandRegistry getCommandRegistry() {
		return commands;
	}
}
