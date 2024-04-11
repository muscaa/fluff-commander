package fluff.commander.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import fluff.commander.CommanderException;
import fluff.commander.arg.ArgumentRegistry;
import fluff.commander.arg.IArgument;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

public class CommandArguments {
	
	private final Map<IArgument<?>, Object> values = new HashMap<>();
	
	CommandArguments() {}
	
	public <V> V get(IArgument<V> arg) {
		return (V) values.get(arg);
	}
	
	public boolean getBoolean(IArgument<Boolean> arg) {
		return get(arg);
	}
	
	public byte getByte(IArgument<Byte> arg) {
		return get(arg);
	}
	
	public char getChar(IArgument<Character> arg) {
		return get(arg);
	}
	
	public short getShort(IArgument<Short> arg) {
		return get(arg);
	}
	
	public int getInt(IArgument<Integer> arg) {
		return get(arg);
	}
	
	public float getFloat(IArgument<Float> arg) {
		return get(arg);
	}
	
	public long getLong(IArgument<Long> arg) {
		return get(arg);
	}
	
	public double getDouble(IArgument<Double> arg) {
		return get(arg);
	}
	
	public String getString(IArgument<String> arg) {
		return get(arg);
	}
	
	public static CommandArguments parse(IArgumentInput in, ArgumentRegistry reg) throws CommanderException {
		CommandArguments args = new CommandArguments();
		for (IArgument<?> arg : reg.all()) {
			if (arg.isRequired()) continue;
			
			args.values.put(arg, arg.getDefaultValue());
		}
		String cmd = null;
		while ((cmd = in.peek()) != null) {
			IArgument<?> arg = reg.get(cmd);
			if (arg == null) break;
			
			IArgumentParser<?> parser = reg.getParsers().get(arg.getParserClass());
			if (parser == null) throw new CommanderException("Argument parser doenst exist for: " + cmd);
			
			in.consume();
			
			Object value = parser.parse(in);
			args.values.put(arg, value);
		}
		List<IArgument<?>> missing = new ArrayList<>();
		for (IArgument<?> arg : reg.all()) {
			if (!arg.isRequired()) continue;
			if (args.values.containsKey(arg)) continue;
			
			missing.add(arg);
		}
		if (!missing.isEmpty()) {
			throw new CommanderException(
					"Missing arguments: " + missing.stream()
							.map(arg -> arg.getNames()[0])
							.collect(Collectors.toList())
					);
		}
		return args;
	}
}
