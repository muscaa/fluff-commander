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
	
	protected final Map<IArgument<?>, Object> values = new HashMap<>();
	protected final List<IArgument<?>> missing = new ArrayList<>();
	
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
     * Retrieves the value associated with the specified argument, or returns the default value if the argument is not present.
     *
     * @param arg the argument
     * @param defaultValue the default value to return if the argument is not present
     * @return the value associated with the argument, or the default value if the argument is not present
     */
	public <V> V getOrDefault(IArgument<V> arg, V defaultValue) {
		return contains(arg) ? get(arg) : defaultValue;
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
     * Checks if the specified argument is contained in the command arguments.
     *
     * @param arg the argument to check
     * @return true if the argument is contained, false otherwise
     */
	public boolean contains(IArgument<?> arg) {
		return values.containsKey(arg);
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
	
	/**
	 * Retrieves the byte value associated with the specified argument.
	 *
	 * @param arg the byte argument
	 * @return the byte value associated with the argument
	 */
	public byte Byte(IArgument<Byte> arg) {
		return get(arg);
	}
	
	/**
	 * Retrieves the char value associated with the specified argument.
	 *
	 * @param arg the char argument
	 * @return the char value associated with the argument
	 */
	public char Char(IArgument<Character> arg) {
		return get(arg);
	}
	
	/**
	 * Retrieves the short value associated with the specified argument.
	 *
	 * @param arg the short argument
	 * @return the short value associated with the argument
	 */
	public short Short(IArgument<Short> arg) {
		return get(arg);
	}
	
	/**
	 * Retrieves the int value associated with the specified argument.
	 *
	 * @param arg the int argument
	 * @return the int value associated with the argument
	 */
	public int Int(IArgument<Integer> arg) {
		return get(arg);
	}
	
	/**
	 * Retrieves the float value associated with the specified argument.
	 *
	 * @param arg the float argument
	 * @return the float value associated with the argument
	 */
	public float Float(IArgument<Float> arg) {
		return get(arg);
	}
	
	/**
	 * Retrieves the long value associated with the specified argument.
	 *
	 * @param arg the long argument
	 * @return the long value associated with the argument
	 */
	public long Long(IArgument<Long> arg) {
		return get(arg);
	}
	
	/**
	 * Retrieves the double value associated with the specified argument.
	 *
	 * @param arg the double argument
	 * @return the double value associated with the argument
	 */
	public double Double(IArgument<Double> arg) {
		return get(arg);
	}
	
    /**
     * Parses the command arguments from the input.
     *
     * @param in the input for parsing arguments
     * @param reg the argument registry
     * @param ignoreMissing whether to ignore missing arguments
     * @return the parsed command arguments
     * @throws CommandException if an error occurs during parsing
     */
	public static CommandArguments parse(IArgumentInput in, ArgumentRegistry reg, boolean ignoreMissing) throws CommandException {
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
		if (args.missing() && !reg.isAllowMissing() && !ignoreMissing) {
			throw new CommandException(
					"Missing arguments: " + args.missing.stream()
							.map(arg -> arg.getNames()[0])
							.collect(Collectors.toList())
					);
		}
		return args;
	}
}
