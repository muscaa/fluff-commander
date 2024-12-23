package fluff.commander.command;

import java.util.Arrays;
import java.util.List;

import fluff.commander.Commander;
import fluff.commander.CommanderConfig;
import fluff.commander.arg.IArgumentInput;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * Represents a task command.
 *
 * @param <C> the type of Commander associated with this command
 */
public class TaskCommand<C extends Commander<C>> extends AbstractCommand<C> {
	
	protected final CommandRegistry commands = new CommandRegistry(this);
	protected final boolean executable;
	
	/**
	 * Constructs a new task with the specified name and alias.
	 *
	 * @param executable true if the task is executable, false otherwise
	 * @param name the name of the task
	 * @param alias the alias of the task
	 */
	public TaskCommand(boolean executable, String name, String... alias) {
		super(join(name, alias));
		
		this.executable = executable;
		
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
		
		if (in.isNull()) {
			if (!executable) throw new CommandException("Task " + names[0] + " is not executable!");
			
			return UNKNOWN;
		}
		
		ICommand cmd = commands.get(in.consume());
		if (cmd == null) throw new CommandException("Command not found!");
		
		return cmd.execute(c, in);
	}
	
	@Override
	public String getUsage() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.getUsage());
		
		List<ICommand> cmds = commands.getNotIgnored();
		if (!cmds.isEmpty()) {
			String[] brackets = executable ? CommanderConfig.OPTIONAL : CommanderConfig.REQUIRED;
			
			sb.append(" ");
			sb.append(brackets[0]);
			sb.append("command");
			sb.append(brackets[1]);
		}
		
		return sb.toString();
	}
	
	@Override
	public void generateHelp(HelpBuilder help) {
		super.generateHelp(help);
		
		help.tab();
		{
			if (!commands.isEmpty()) {
				help.append("Commands:")
						.newLine();
				
				help.tab();
				{
					for (ICommand cmd : commands.getNotIgnored()) {
						String[] cmdNames = cmd.getNames();
						String cmdDescription = cmd.getDescription();
						
						help.append(cmdNames[0])
								.append(":")
								.newLine();
						
						help.tab();
						{
							if (cmdDescription != null) {
								help.append("Description: ")
										.append(cmdDescription)
										.newLine();
							}
							if (cmdNames.length > 1) {
								help.append("Alias: ")
										.append(String.join(CommanderConfig.SEPARATOR_OR, Arrays.copyOfRange(cmdNames, 1, cmdNames.length)))
										.newLine();
							}
							help.append("Usage: ")
									.append(cmd.getUsage())
									.newLine();
						}
						help.untab();
					}
				}
				help.untab();
			}
		}
		help.untab();
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
	 * @param alias the alias of the task
	 * @return the registered task command
	 */
	public TaskCommand<C> task(String name, String... alias) {
		return command(new TaskCommand<>(false, name, alias));
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
	 * Creates and registers a new task under this task command and applies additional configuration using a functional interface.
	 *
	 * @param name the name of the task
	 * @param alias the alias of the task
	 * @param func the function to apply to the task
	 */
	public void task(String name, String[] alias, VoidFunc1<TaskCommand<C>> func) {
		func.invoke(task(name, alias));
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
