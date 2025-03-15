package fluff.commander.argument.parsers;

import fluff.commander.argument.ArgumentException;
import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.IArgumentParser;

/**
 * A parser for string command line arguments.
 */
public class ArgumentParserString implements IArgumentParser<String> {
    
    @Override
    public String parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        
        return in.consume();
    }
    
    @Override
    public String[] getValues() {
        return new String[] {
        		"string"
        };
    }
    
	@Override
	public boolean acceptsNull() {
		return false;
	}
}
