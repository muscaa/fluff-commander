package fluff.commander.arg;

public class SimpleArgumentReader implements IArgumentInput {
	
	private final String[] args;
	
	private int current;
	
	public SimpleArgumentReader(String[] args) {
		this.args = args;
		
		current = 0;
	}
	
	@Override
	public int current() {
		return current;
	}
	
	@Override
	public int size() {
		return args.length;
	}
	
	@Override
	public String get(int index) {
		if (index >= size()) return null;
		return args[index];
	}
	
	@Override
	public String peek() {
		return get(current());
	}
	
	@Override
	public boolean isNull() {
		return peek() == null || peek().isBlank();
	}
	
	@Override
	public String consume() {
		if (isNull()) return null;
		return args[current++];
	}
}
