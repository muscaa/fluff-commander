package fluff.commander.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentInput;
import fluff.commander.arg.IArgumentParser;

/**
 * Represents the arguments associated with a command.
 */
public class CommandArguments {
	
	private final Map<IArgument<?>, Object> values = new HashMap<>();
	private final List<IArgument<?>> missing = new ArrayList<>();
	
	/**
	 * Retrieves the value associated with the specified argument.
	 *
	 * @param arg the argument
	 * @return the value associated with the argument
	 */
	public <V> V get(IArgument<V> arg) {
		return (V) values.get(arg);
	}
	
	/**
	 * Sets the value associated with the specified argument.
	 *
	 * @param arg the argument
	 * @param object the value to set
	 */
	public void set(IArgument<?> arg, Object object) {
		values.put(arg, object);
	}
	
	/**
	 * Checks if there are missing arguments.
	 *
	 * @return true if there are missing arguments, false otherwise
	 */
	public boolean missing() {
		return !missing.isEmpty();
	}
	
	/**
	 * Gets the list of missing arguments.
	 *
	 * @return the list of missing arguments
	 */
	public List<IArgument<?>> getMissingArguments() {
		return missing;
	}
	
	/**
	 * Retrieves the boolean value associated with the specified argument.
	 *
	 * @param arg the boolean argument
	 * @return the boolean value associated with the argument
	 */
	public boolean Boolean(IArgument<Boolean> arg) {
		return get(arg);
	}
	
	// Additional methods for other types of arguments...
	
	/**
	 * Parses the command arguments from the input.
	 *
	 * @param in the input for parsing arguments
	 * @param reg the argument registry
	 * @param generateHelp whether to generate help for missing arguments
	 * @return the parsed command arguments
	 * @throws CommandException if an error occurs during parsing
	 */
	public static CommandArguments parse(IArgumentInput in, ArgumentRegistry reg, boolean generateHelp) throws CommandException {
		CommandArguments args = new CommandArguments();
		for (IArgument<?> arg : reg.getAll()) {
			if (arg.isRequired()) continue;
			
			args.set(arg, arg.getDefaultValue());
		}
		String cmd = null;
		while ((cmd = in.peek()) != null) {
			IArgument<?> arg = reg.get(cmd);
			if (arg == null) break;
			
			IArgumentParser<?> parser = reg.getParsers().get(arg.getParserClass());
			if (parser == null) throw new CommandException("Argument parser doesn't exist for: " + cmd);
			
			in.consume();
			
			Object value;
			try {
				value = parser.parse(in);
			} catch (ArgumentException e) {
				throw new CommandException("Argument parse error!", e);
			}
			args.set(arg, value);
		}
		for (IArgument<?> arg : reg.getAll()) {
			if (!arg.isRequired()) continue;
			if (args.values.containsKey(arg)) continue;
			
			args.missing.add(arg);
		}
		if (args.missing() && !reg.isAllowMissing() && !generateHelp) {
			throw new CommandException(
					"Missing arguments: " + args.missing.stream()
							.map(arg -> arg.getNames()[0])
							.collect(Collectors.toList())
					);
		}
		return args;
	}
}
