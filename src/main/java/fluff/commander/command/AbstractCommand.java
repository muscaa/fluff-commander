package fluff.commander.command;

import fluff.commander.Commander;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentInput;
import fluff.commander.utils.HelpGenerator;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * Represents an abstract command.
 *
 * @param <C> the type of Commander associated with this command
 */
public abstract class AbstractCommand<C extends Commander<C>> implements ICommand {
	
	private final ArgumentRegistry arguments = new ArgumentRegistry();
	private final String[] names;
	
	ICommand parent;
	
	AbstractCommand(String[] names) {
		this.names = names;
		
		if (shouldGenerateHelp()) {
			argument(HelpGenerator.ARG_HELP);
			arguments.ignore(HelpGenerator.ARG_HELP);
		}
	}
	
	/**
	 * Constructs a new abstract command with the specified name and alias.
	 *
	 * @param name the name of the command
	 * @param alias the alias of the command
	 */
	public AbstractCommand(String name, String... alias) {
		this(join(name, alias));
		
		init();
	}
	
	/**
	 * Initializes the arguments for this command.
	 */
	public void init() {}
	
	/**
	 * Executes the action associated with this command.
	 *
	 * @param fc the Commander instance
	 * @param args the command arguments
	 * @return the exit code of the command after execution
	 * @throws CommandException if an error occurs during execution
	 */
	public abstract int onAction(C c, CommandArguments args) throws CommandException;
	
	@Override
	public int execute(Commander<?> c, IArgumentInput in) throws CommandException {
		CommandArguments args = CommandArguments.parse(in, arguments, shouldGenerateHelp());
		
		if (shouldGenerateHelp()) {
			if (args.Boolean(HelpGenerator.ARG_HELP)) {
				HelpGenerator help = HelpGenerator.of(this);
				help.getLines().forEach(System.out::println);
				return HELP;
			}
			
			if (args.missing()) CommandArguments.throwMissingArguments(args);
		}
		
		return onAction((C) c, args);
	}
	
	@Override
	public String[] getNames() {
		return names;
	}
	
	@Override
	public String getDescription() {
		return null;
	}
	
	/**
	 * Indicates whether help generation is enabled for this command.
	 *
	 * @return true if help generation is enabled, false otherwise
	 */
	protected boolean shouldGenerateHelp() {
		return true;
	}
	
	/**
	 * Registers an argument for this command.
	 *
	 * @param argument the argument to register
	 * @param <T> the type of the argument
	 * @param <V> the argument type
	 * @return the registered argument
	 */
	public <T, V extends IArgument<T>> V argument(V argument) {
		arguments.register(argument);
		return argument;
	}
	
	/**
	 * Registers an argument for this command and applies additional configuration using a functional interface.
	 *
	 * @param argument the argument to register
	 * @param func the function to apply to the argument
	 * @param <T> the type of the argument
	 * @param <V> the argument type
	 */
	public <T, V extends IArgument<T>> void argument(V argument, VoidFunc1<V> func) {
		func.invoke(argument(argument));
	}
	
	/**
	 * Retrieves the parent command of this command.
	 *
	 * @param <V> the type of the parent command
	 * @return the parent command
	 */
	public <V extends ICommand> V parent() {
		return (V) parent;
	}
	
	/**
	 * Retrieves the argument registry associated with this command.
	 *
	 * @return the argument registry
	 */
	public ArgumentRegistry getArgumentRegistry() {
		return arguments;
	}
	
	static String[] join(String name, String[] alias) {
		String[] result = new String[alias.length + 1];
		int i = 0;
		result[i++] = name;
		for (String s : alias) {
			result[i++] = s;
		}
		return result;
	}
}
