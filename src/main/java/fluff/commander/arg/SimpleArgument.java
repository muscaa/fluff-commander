package fluff.commander.arg;

public class SimpleArgument<V> implements IArgument<V> {
	
	private final Class<V> parserClass;
	private final String[] names;
	private final String description;
	private final V defaultValue;
	private final boolean required;
	
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
