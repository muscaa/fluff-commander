package fluff.commander.arg;

/**
 * Represents a simple argument input reader.
 */
public class SimpleArgumentReader implements IArgumentInput {
    
    private final String[] args;
    
    private int current;
    
    /**
     * Constructs a SimpleArgumentReader instance with the specified array of arguments.
     *
     * @param args the array of arguments
     */
    public SimpleArgumentReader(String[] args) {
        this.args = args;
        
        current = 0;
    }
    
    /**
     * Gets the current index of the argument being read.
     *
     * @return the current index of the argument being read
     */
    @Override
    public int current() {
        return current;
    }
    
    /**
     * Gets the size of the argument array.
     *
     * @return the size of the argument array
     */
    @Override
    public int size() {
        return args.length;
    }
    
    /**
     * Gets the argument at the specified index.
     *
     * @param index the index of the argument
     * @return the argument at the specified index, or null if the index is out of bounds
     */
    @Override
    public String get(int index) {
        if (index >= size()) return null;
        return args[index];
    }
    
    /**
     * Peeks at the current argument being read.
     *
     * @return the current argument being read
     */
    @Override
    public String peek() {
        return get(current());
    }
    
    /**
     * Checks if the current argument being read is null or blank.
     *
     * @return true if the current argument is null or blank, false otherwise
     */
    @Override
    public boolean isNull() {
        return peek() == null || peek().isBlank();
    }
    
    /**
     * Consumes and returns the current argument being read.
     *
     * @return the consumed argument
     */
    @Override
    public String consume() {
        if (isNull()) return null;
        return args[current++];
    }
}
