package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for boolean command line arguments.
 */
public class ArgumentParserBoolean implements IArgumentParser<Boolean> {
    
    @Override
    public Boolean parse(IArgumentInput in) throws ArgumentException {
        if ("true".equalsIgnoreCase(in.peek())) {
            in.consume();
            return true;
        }
        if ("false".equalsIgnoreCase(in.peek())) {
            in.consume();
            return false;
        }
        return true; // Default value if no valid boolean is found
    }
    
    @Override
    public String[] getValues() {
        return new String[] {
        		"true",
        		"false"
        };
    }
    
	@Override
	public boolean acceptsNull() {
		return true;
	}
}
