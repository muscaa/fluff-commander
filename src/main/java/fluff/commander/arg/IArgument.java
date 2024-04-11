package fluff.commander.arg;

public interface IArgument<V> {
	
	Class<V> getParserClass();
	
	String[] getNames();
	
	String getDescription();
	
	V getDefaultValue();
	
	boolean isRequired();
}
