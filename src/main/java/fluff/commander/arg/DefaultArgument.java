package fluff.commander.arg;

/**
 * Represents a default argument.
 *
 * @param <V> the type of the argument value
 */
public class DefaultArgument<V> implements IArgument<V> {
    
    private final Class<V> parserClass;
    private final String[] names;
    private final String[] values;
    private final String description;
    private final V defaultValue;
    private final boolean required;
    private final boolean inline;
    
    /**
     * Constructs a DefaultArgument instance with the specified parameters.
     *
     * @param parserClass the class of the argument parser
     * @param names the names of the argument
     * @param values the accepted values of the argument
     * @param description the description of the argument
     * @param defaultValue the default value of the argument
     * @param required true if the argument is required, false otherwise
     * @param inline true if the argument is inline, false otherwise
     */
    public DefaultArgument(Class<V> parserClass, String[] names, String[] values, String description, V defaultValue, boolean required, boolean inline) {
        this.parserClass = parserClass;
        this.names = names;
        this.values = values;
        this.description = description;
        this.defaultValue = defaultValue;
        this.required = required;
        this.inline = inline;
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
    public String[] getValues() {
    	return values;
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
    
    @Override
    public boolean isInline() {
    	return inline;
    }
}
