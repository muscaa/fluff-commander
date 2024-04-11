package fluff.commander.arg;

import java.util.HashMap;
import java.util.Map;

import fluff.commander.arg.parsers.*;

public class ArgumentParsers {
	
	public static final IArgumentParser<Boolean> BOOLEAN = new ArgumentParserBoolean();
	public static final IArgumentParser<Byte> BYTE = new ArgumentParserByte();
	public static final IArgumentParser<Character> CHAR = new ArgumentParserChar();
	public static final IArgumentParser<Short> SHORT = new ArgumentParserShort();
	public static final IArgumentParser<Integer> INT = new ArgumentParserInt();
	public static final IArgumentParser<Float> FLOAT = new ArgumentParserFloat();
	public static final IArgumentParser<Long> LONG = new ArgumentParserLong();
	public static final IArgumentParser<Double> DOUBLE = new ArgumentParserDouble();
	public static final IArgumentParser<String> STRING = new ArgumentParserString();
	
	private final Map<Class<?>, IArgumentParser<?>> parsers = new HashMap<>();
	
	public ArgumentParsers() {
		register(boolean.class, BOOLEAN);
		register(Boolean.class, BOOLEAN);
		register(byte.class, BYTE);
		register(Byte.class, BYTE);
		register(char.class, CHAR);
		register(Character.class, CHAR);
		register(short.class, SHORT);
		register(short.class, SHORT);
		register(int.class, INT);
		register(Integer.class, INT);
		register(float.class, FLOAT);
		register(Float.class, FLOAT);
		register(long.class, LONG);
		register(Long.class, LONG);
		register(double.class, DOUBLE);
		register(Double.class, DOUBLE);
		register(String.class, STRING);
	}
	
	public void register(Class<?> clazz, IArgumentParser<?> parser) {
		parsers.put(clazz, parser);
	}
	
	public IArgumentParser<?> get(Class<?> clazz) {
		return parsers.get(clazz);
	}
}
