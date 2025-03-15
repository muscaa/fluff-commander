package fluff.commander.argument;

/**
 * A builder class for creating command line arguments.
 *
 * @param <V> the type of value expected by the argument
 */
public class ArgumentBuilder<V> {
    
    private Class<V> parserClass;
    private String[] names;
    private String[] values;
    private String description;
    private V defaultValue;
    private boolean required;
    private boolean inline;
    
    /**
     * Sets the parser class for the argument.
     *
     * @param parserClass the parser class for the argument
     * @return the argument builder instance
     */
    public ArgumentBuilder<V> parserClass(Class<V> parserClass) {
        this.parserClass = parserClass;
        return this;
    }
    
    /**
     * Sets the names of the argument.
     *
     * @param names the names of the argument
     * @return the argument builder instance
     */
    public ArgumentBuilder<V> names(String... names) {
        this.names = names;
        return this;
    }
    
    /**
     * Sets the values of the argument.
     *
     * @param values the values of the argument
     * @return the argument builder instance
     */
    public ArgumentBuilder<V> values(String[] values) {
    	this.values = values;
    	return this;
    }
    
    /**
     * Sets the description of the argument.
     *
     * @param description the description of the argument
     * @return the argument builder instance
     */
    public ArgumentBuilder<V> description(String description) {
        this.description = description;
        return this;
    }
    
    /**
     * Sets the default value of the argument.
     *
     * @param defaultValue the default value of the argument
     * @return the argument builder instance
     */
    public ArgumentBuilder<V> defaultValue(V defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }
    
    /**
     * Marks the argument as required.
     *
     * @return the argument builder instance
     */
    public ArgumentBuilder<V> required() {
        this.required = true;
        return this;
    }
    
    /**
     * Marks the argument as inline.
     *
     * @return the argument builder instance
     */
    public ArgumentBuilder<V> inline() {
    	this.inline = true;
    	return this;
    }
    
    /**
     * Builds the argument based on the provided configuration.
     *
     * @return the constructed argument
     */
    public IArgument<V> build() {
        return new DefaultArgument<>(parserClass, names, values, description, defaultValue, required, inline);
    }
    
    /**
     * Creates a new argument builder with the specified parser class and names.
     *
     * @param <V> the type of value expected by the argument
     * @param parserClass the parser class for the argument
     * @param names the names of the argument
     * @return the newly created argument builder instance
     */
    public static <V> ArgumentBuilder<V> of(Class<V> parserClass, String... names) {
        return new ArgumentBuilder<V>().parserClass(parserClass).names(names);
    }
    
    // Factory methods for commonly used argument types
    
    public static ArgumentBuilder<Boolean> Boolean(String... names) {
        return of(Boolean.class, names).defaultValue(false);
    }
    
    public static ArgumentBuilder<Byte> Byte(String... names) {
        return of(Byte.class, names);
    }
    
    public static ArgumentBuilder<Character> Char(String... names) {
        return of(Character.class, names);
    }
    
    public static ArgumentBuilder<Short> Short(String... names) {
        return of(Short.class, names);
    }
    
    public static ArgumentBuilder<Integer> Int(String... names) {
        return of(Integer.class, names);
    }
    
    public static ArgumentBuilder<Float> Float(String... names) {
        return of(Float.class, names);
    }
    
    public static ArgumentBuilder<Long> Long(String... names) {
        return of(Long.class, names);
    }
    
    public static ArgumentBuilder<Double> Double(String... names) {
        return of(Double.class, names);
    }
    
    public static ArgumentBuilder<String> String(String... names) {
        return of(String.class, names);
    }
}
