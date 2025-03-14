package fluff.commander.argument;

/**
 * Represents a command line argument.
 *
 * @param <V> the type of the argument value
 */
public interface IArgument<V> {
    
    /**
     * Retrieves the class responsible for parsing the argument value.
     *
     * @return the parser class for the argument value
     */
    Class<V> getParserClass();
    
    /**
     * Retrieves the names associated with this argument.
     *
     * @return an array of names for this argument
     */
    String[] getNames();
    
    /**
     * Retrieves the values accepted by this argument.
     *
     * @return an array of values accepted by this argument
     */
    String[] getValues();
    
    /**
     * Retrieves the description of this argument.
     *
     * @return the description of the argument
     */
    String getDescription();
    
    /**
     * Retrieves the default value of this argument.
     *
     * @return the default value of the argument
     */
    V getDefaultValue();
    
    /**
     * Checks if this argument is required.
     *
     * @return true if the argument is required, false otherwise
     */
    boolean isRequired();
    
    /**
     * Checks if this argument can be inline.
     *
     * @return true if the argument can be inline, false otherwise
     */
    boolean isInline();
}
