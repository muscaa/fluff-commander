package fluff.commander.argument;

/**
 * Represents input for processing command line arguments.
 */
public interface IArgumentInput {
    
    /**
     * Retrieves the current index of the input.
     *
     * @return the current index of the input
     */
    int current();
    
    /**
     * Retrieves the size of the input.
     *
     * @return the size of the input
     */
    int size();
    
    /**
     * Retrieves the argument at the specified index.
     *
     * @param index the index of the argument to retrieve
     * @return the argument at the specified index
     */
    String get(int index);
    
    /**
     * Retrieves the next argument without consuming it.
     *
     * @return the next argument
     */
    String peek();
    
    /**
     * Checks if the input is null or empty.
     *
     * @return true if the input is null or empty, false otherwise
     */
    boolean isNull();
    
    /**
     * Consumes and retrieves the next argument.
     *
     * @return the next argument
     */
    String consume();
}
