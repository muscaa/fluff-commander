package fluff.commander.arg;

public interface IArgumentInput {
	
	int current();
	
	int size();
	
	String get(int index);
	
	String peek();
	
	boolean isNull();
	
	String consume();
}
