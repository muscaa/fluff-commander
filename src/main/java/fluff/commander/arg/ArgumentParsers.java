package fluff.commander.arg;

import java.util.HashMap;
import java.util.Map;

import fluff.commander.arg.parsers.*;

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
     * Retrieves the parser for the specified class.
     *
     * @param clazz the class type
     * @return the parser for the class
     */
    public IArgumentParser<?> get(Class<?> clazz) {
        return parsers.get(clazz);
    }
}
