package fluff.commander.command;

import fluff.commander.Commander;
import fluff.commander.arg.IArgumentInput;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * Represents a task command.
 *
 * @param <C> the type of Commander associated with this command
 */
public class TaskCommand<C extends Commander<C>> extends AbstractCommand<C> {
	
	private final CommandRegistry commands = new CommandRegistry(this);
	
	/**
	 * Constructs a new task with the specified name and alias.
	 *
	 * @param name the name of the task
	 * @param alias the alias of the task
	 */
	public TaskCommand(String name, String... alias) {
		super(join(name, alias));
		
		init();
	}
	
	@Override
	public void init() {}
	
	@Override
	public int onAction(C c, CommandArguments args) throws CommandException {
		return UNKNOWN;
	}
	
	@Override
	public int execute(Commander<?> c, IArgumentInput in) throws CommandException {
		int argsExit = super.execute(c, in);
		if (argsExit != UNKNOWN) return argsExit;
		
		if (in.isNull()) return UNKNOWN;
		
		ICommand cmd = commands.get(in.consume());
		if (cmd == null) throw new CommandException("Command not found!");
		
		return cmd.execute(c, in);
	}
	
	/**
	 * Registers a command for this task.
	 *
	 * @param command the command to register
	 * @param <V> the type of the command
	 * @return the registered command
	 */
	public <V extends ICommand> V command(V command) {
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
