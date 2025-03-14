package fluff.commander.command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import fluff.commander.Commander;
import fluff.commander.CommanderConfig;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentInput;
import fluff.commander.arg.IArgumentParser;
import fluff.functions.gen.obj.VoidFunc1;

/**
 * Represents an abstract command.
 *
 * @param <C> the type of Commander associated with this command
 * @param <S> the type of ICommandSource associated with this command
 */
public abstract class AbstractCommand<C extends Commander<C, S>, S extends ICommandSource> implements ICommand {
	
	protected final ArgumentRegistry arguments = new ArgumentRegistry();
	protected final String[] names;
	
	ICommand parent;
	
	AbstractCommand(String[] names) {
		this.names = names;
		
		if (shouldGenerateHelp()) {
			argument(HelpBuilder.ARG_HELP);
			arguments.ignore(HelpBuilder.ARG_HELP);
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
	 * Executes the pre action associated with this command ignoring missing arguments.
	 *
	 * @param c the Commander instance
	 * @param source the command source
	 * @param args the command arguments
	 * @throws CommandException if an error occurs during execution
	 */
	public int onPreAction(C c, S source, CommandArguments args) throws CommandException {
		if (shouldGenerateHelp() && args.Boolean(HelpBuilder.ARG_HELP)) {
			HelpBuilder help = new HelpBuilder();
			generateHelp(help);
			help.getLines().forEach(System.out::println);
			return HELP;
		}
		return UNKNOWN;
	}
	
	/**
	 * Executes the action associated with this command.
	 *
	 * @param c the Commander instance
	 * @param source the command source
	 * @param args the command arguments
	 * @return the exit code of the command after execution
	 * @throws CommandException if an error occurs during execution
	 */
	public abstract int onAction(C c, S source, CommandArguments args) throws CommandException;
	
	@Override
	public int execute(Commander<?, ?> c, ICommandSource source, IArgumentInput in) throws CommandException {
		CommandArguments args = CommandArguments.parse(in, arguments, true);
		
		int pre = onPreAction((C) c, (S) source, args);
		if (pre != UNKNOWN) return pre;
		
		if (args.missing()) CommandArguments.throwMissingArguments(args);
		
		return onAction((C) c, (S) source, args);
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
	
	@Override
	public String getUsage() {
		StringBuilder sb = new StringBuilder();
		sb.append(names[0]);
		
		List<IArgument<?>> args = arguments.getNotIgnored();
		
		int less = 0;
		StringBuilder more = new StringBuilder();
		for (IArgument<?> arg : args) {
			if (!arg.isRequired() && !arg.isInline()) {
				less++;
				continue;
			}
			
			IArgumentParser<?> parser = arguments.getParsers().get(arg.getParserClass());
			String[] values = arg.getValues() != null ? arg.getValues() : parser.getValues();
			
			String[] nameBrackets = arg.isInline() ?
					CommanderConfig.OPTIONAL
					: new String[] { "", "" };
			String[] valuesBrackets = arg.isRequired() ?
					parser.acceptsNull() ?
							CommanderConfig.OPTIONAL
							: CommanderConfig.REQUIRED
					: CommanderConfig.REQUIRED;
			
			more.append(" ");
			more.append(nameBrackets[0]);
			more.append(arg.getNames()[0]);
			more.append(nameBrackets[1]);
			
			more.append(" ");
			more.append(valuesBrackets[0]);
			more.append(String.join(CommanderConfig.SEPARATOR_OR, values));
			more.append(valuesBrackets[1]);
		}
		
		if (less > 0) {
			sb.append(" ");
			sb.append(CommanderConfig.OPTIONAL[0]);
			sb.append("args");
			sb.append(CommanderConfig.OPTIONAL[1]);
		}
		
		sb.append(more.toString());
		
		return sb.toString();
	}
	
	@Override
	public void generateHelp(HelpBuilder help) {
		help.append(names[0])
				.append(":")
				.newLine();
		
		help.tab();
		{
			String description = getDescription();
			if (description != null) {
				help.append("Description: ")
						.append(description)
						.newLine();
			}
			if (names.length > 1) {
				help.append("Alias: ")
						.append(String.join(CommanderConfig.SEPARATOR_OR, Arrays.copyOfRange(names, 1, names.length)))
						.newLine();
			}
			
			help.append("Usage: ")
					.append(getUsage())
					.newLine();
			
			if (!arguments.isEmpty()) {
				help.append("Arguments:")
						.newLine();
				
				help.tab();
				{
					for (IArgument<?> arg : arguments.getNotIgnored()) {
						List<String> argProperties = new LinkedList<>();
						if (arg.isRequired()) argProperties.add("required");
						if (arg.isInline()) argProperties.add("inline");
						
						String[] argNames = arg.getNames();
						String argDescription = arg.getDescription();
						
						help.append(argNames[0])
								.append(":")
								.newLine();
						
						help.tab();
						{
							if (!argProperties.isEmpty()) {
								help.append("Properties: ")
										.append(String.join(CommanderConfig.SEPARATOR_AND, argProperties))
										.newLine();
							}
							if (argDescription != null) {
								help.append("Description: ")
										.append(argDescription)
										.newLine();
							}
							if (argNames.length > 1) {
								help.append("Alias: ")
										.append(String.join(CommanderConfig.SEPARATOR_OR, Arrays.copyOfRange(argNames, 1, argNames.length)))
										.newLine();
							}
							
							IArgumentParser<?> parser = arguments.getParsers().get(arg.getParserClass());
							String[] brackets = parser.acceptsNull() ? CommanderConfig.OPTIONAL : CommanderConfig.REQUIRED;
							String[] values = arg.getValues() != null ? arg.getValues() : parser.getValues();
							help.append("Usage: ")
									.append(argNames[0])
									.append(" ")
									.append(brackets[0])
									.append(String.join(CommanderConfig.SEPARATOR_OR, values))
									.append(brackets[1])
									.newLine();
							if (!arg.isRequired() && arg.getDefaultValue() != null) {
								help.append("Default: ")
										.append(arg.getDefaultValue())
										.newLine();
							}
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
