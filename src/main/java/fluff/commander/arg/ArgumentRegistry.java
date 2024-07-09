package fluff.commander.arg;

import java.util.LinkedList;
import java.util.List;

import fluff.commander.CommanderRegistry;

/**
 * A registry for managing arguments and their associated parsers.
 */
public class ArgumentRegistry extends CommanderRegistry<IArgument<?>> {
    
    private ArgumentParsers parsers = new ArgumentParsers();
    private boolean allowMissing = false;
    private List<IArgument<?>> inlines = new LinkedList<>();
    
    @Override
    protected String[] getKeys(IArgument<?> value) {
        return value.getNames();
    }
    
    @Override
    public void register(IArgument<?> value) {
    	if (value.isInline()) inlines.add(value);
    	
    	super.register(value);
    }
    
    /**
     * Retrieves the default argument.
     *
     * @return the default argument
     */
    public List<IArgument<?>> getInlines() {
    	return inlines;
    }
    
    /**
     * Retrieves the argument parsers associated with this registry.
     *
     * @return the argument parsers
     */
    public ArgumentParsers getParsers() {
        return parsers;
    }
    
    /**
     * Sets the argument parsers for this registry.
     *
     * @param parsers the argument parsers to set
     */
    public void setParsers(ArgumentParsers parsers) {
        this.parsers = parsers;
    }
    
	/**
	 * Checks if missing arguments are allowed.
	 *
	 * @return true if missing arguments are allowed, false otherwise
	 */
	public boolean isAllowMissing() {
		return allowMissing;
	}
	
	/**
	 * Sets whether missing arguments are allowed.
	 *
	 * @param allowMissing true to allow missing arguments, false otherwise
	 */
	public void setAllowMissing(boolean allowMissing) {
		this.allowMissing = allowMissing;
	}
}
