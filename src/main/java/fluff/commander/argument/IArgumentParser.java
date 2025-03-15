package fluff.commander.argument;

/**
 * Represents a parser for command line arguments.
 *
 * @param <V> the type of the parsed argument value
 */
public interface IArgumentParser<V> {
    
    /**
     * Parses the input argument and returns the parsed value.
     *
     * @param in the input argument to parse
     * @return the parsed value
     * @throws ArgumentException if an error occurs during parsing
     */
    V parse(IArgumentInput in) throws ArgumentException;
    
    /**
     * Retrieves the possible values represented by this parser.
     *
     * @return an array of strings representing the possible values
     *         parsed by this parser, used for generating help text
     *         or displaying available options
     */
    String[] getValues();
    
    /**
     * Checks if this argument parser accepts null as a value.
     *
     * @return true if the argument parser accepts null, false otherwise
     */
    boolean acceptsNull();
}
