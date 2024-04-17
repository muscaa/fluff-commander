package fluff.commander.command;

import fluff.commander.FluffCommander;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentInput;
import fluff.commander.utils.HelpGenerator;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * Represents an abstract command.
 *
 * @param <C> the type of FluffCommander associated with this command
 */
public abstract class AbstractCommand<C extends FluffCommander<C>> implements ICommand {
	
	private final ArgumentRegistry arguments = new ArgumentRegistry();
	private final String name;
	
	ICommand parent;
	
	/**
	 * Constructs a new abstract command with the specified name.
	 *
	 * @param name the name of the command
	 */
	public AbstractCommand(String name) {
		this.name = name;
		
		if (shouldGenerateHelp()) {
			argument(HelpGenerator.ARG_HELP);
			arguments.ignore(HelpGenerator.ARG_HELP);
		}
		
		initArguments();
	}
	
	/**
	 * Initializes the arguments for this command.
	 */
	public void initArguments() {}
	
	/**
	 * Executes the action associated with this command.
	 *
	 * @param fc the FluffCommander instance
	 * @param args the command arguments
	 * @return true if the action was executed successfully, false otherwise
	 * @throws CommandException if an error occurs during execution
	 */
	public abstract boolean onAction(C fc, CommandArguments args) throws CommandException;
	
	@Override
	public boolean onAction(FluffCommander<?> fc, IArgumentInput in) throws CommandException {
		CommandArguments args = CommandArguments.parse(in, arguments, shouldGenerateHelp());
		
		if (shouldGenerateHelp() && (args.missing() || args.Boolean(HelpGenerator.ARG_HELP))) {
			HelpGenerator help = HelpGenerator.of(this);
			help.getLines().forEach(System.out::println);
			return true;
		}
		
		return onAction((C) fc, args);
	}
	
	@Override
	public String getName() {
		return name;
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
}
