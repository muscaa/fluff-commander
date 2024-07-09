package fluff.commander.arg;

/**
 * Represents a simple argument configuration.
 *
 * @param <V> the type of the argument value
 */
public class SimpleArgument<V> implements IArgument<V> {
    
    private final Class<V> parserClass;
    private final String[] names;
    private final String description;
    private final V defaultValue;
    private final boolean required;
    
    /**
     * Constructs a SimpleArgument instance with the specified parameters.
     *
     * @param parserClass the class of the argument parser
     * @param names the names of the argument
     * @param description the description of the argument
     * @param defaultValue the default value of the argument
     * @param required true if the argument is required, false otherwise
     */
    public SimpleArgument(Class<V> parserClass, String[] names, String description, V defaultValue, boolean required) {
        this.parserClass = parserClass;
        this.names = names;
        this.description = description;
        this.defaultValue = defaultValue;
        this.required = required;
    }
    
    @Override
    public Class<V> getParserClass() {
        return parserClass;
    }
    
    @Override
    public String[] getNames() {
        return names;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public V getDefaultValue() {
        return defaultValue;
    }
    
    @Override
    public boolean isRequired() {
        return required;
    }
}
