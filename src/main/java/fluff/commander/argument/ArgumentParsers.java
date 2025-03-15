package fluff.commander.argument;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import fluff.commander.argument.parsers.ArgumentParserBoolean;
import fluff.commander.argument.parsers.ArgumentParserByte;
import fluff.commander.argument.parsers.ArgumentParserChar;
import fluff.commander.argument.parsers.ArgumentParserDouble;
import fluff.commander.argument.parsers.ArgumentParserFloat;
import fluff.commander.argument.parsers.ArgumentParserInt;
import fluff.commander.argument.parsers.ArgumentParserLong;
import fluff.commander.argument.parsers.ArgumentParserShort;
import fluff.commander.argument.parsers.ArgumentParserString;

/**
 * Utility class for managing argument parsers.
 */
public class ArgumentParsers {
    
    /** The parser for boolean arguments. */
    public static final IArgumentParser<Boolean> BOOLEAN = new ArgumentParserBoolean();
    
    /** The parser for byte arguments. */
    public static final IArgumentParser<Byte> BYTE = new ArgumentParserByte();
    
    /** The parser for char arguments. */
    public static final IArgumentParser<Character> CHAR = new ArgumentParserChar();
    
    /** The parser for short arguments. */
    public static final IArgumentParser<Short> SHORT = new ArgumentParserShort();
    
    /** The parser for int arguments. */
    public static final IArgumentParser<Integer> INT = new ArgumentParserInt();
    
    /** The parser for float arguments. */
    public static final IArgumentParser<Float> FLOAT = new ArgumentParserFloat();
    
    /** The parser for long arguments. */
    public static final IArgumentParser<Long> LONG = new ArgumentParserLong();
    
    /** The parser for double arguments. */
    public static final IArgumentParser<Double> DOUBLE = new ArgumentParserDouble();
    
    /** The parser for string arguments. */
    public static final IArgumentParser<String> STRING = new ArgumentParserString();
    
    protected final Map<Class<?>, IArgumentParser<?>> parsers = new HashMap<>();
    protected final List<ArgumentParsers> parents = new LinkedList<>();
    
    /**
     * Constructs an instance of ArgumentParsers and registers the default parsers.
     */
    public ArgumentParsers() {
        register(boolean.class, BOOLEAN);
        register(Boolean.class, BOOLEAN);
        register(byte.class, BYTE);
        register(Byte.class, BYTE);
        register(char.class, CHAR);
        register(Character.class, CHAR);
        register(short.class, SHORT);
        register(Short.class, SHORT);
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
    
    /**
     * Registers a parser for a specific class.
     *
     * @param clazz the class type
     * @param parser the parser instance
     */
    public void register(Class<?> clazz, IArgumentParser<?> parser) {
        parsers.put(clazz, parser);
    }
    
    /**
     * Unregisters a parser for a specific class.
     * 
     * @param clazz the class type
     */
	public void unregister(Class<?> clazz) {
		parsers.remove(clazz);
	}
    
    /**
     * Copies parsers from another ArgumentParsers instance.
     *
     * @param from the source ArgumentParsers instance
     */
    public void from(ArgumentParsers from) {
        for (Map.Entry<Class<?>, IArgumentParser<?>> e : from.parsers.entrySet()) {
            register(e.getKey(), e.getValue());
        }
    }
    
    /**
     * Extends the current ArgumentParsers with parsers from another ArgumentParsers instance.
     * 
     * @param parent the parent ArgumentParsers instance
     */
	public void extend(ArgumentParsers parent) {
		parents.add(parent);
	}
    
    /**
     * Retrieves the parser for the specified class.
     *
     * @param clazz the class type
     * @return the parser for the class
     */
    public IArgumentParser<?> get(Class<?> clazz) {
    	IArgumentParser<?> parser = parsers.get(clazz);
		if (parser != null) return parser;
		
		for (ArgumentParsers parent : parents) {
			parser = parent.get(clazz);
            if (parser != null) return parser;
		}
		
		return null;
    }
    
	/**
	 * Clears all parsers.
	 */
    public void clear() {
    	parsers.clear();
    }
}
