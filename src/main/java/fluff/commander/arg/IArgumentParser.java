package fluff.commander.arg;

public interface IArgumentParser<V> {
	
	V parse(IArgumentInput in) throws ArgumentException;
	
	String[] getValues();
}
