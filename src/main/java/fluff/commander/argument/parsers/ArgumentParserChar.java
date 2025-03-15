package fluff.commander.argument.parsers;

import fluff.commander.argument.ArgumentException;
import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.IArgumentParser;

/**
 * A parser for character command line arguments.
 */
public class ArgumentParserChar implements IArgumentParser<Character> {
    
    @Override
    public Character parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        if (in.peek().length() > 1) {
            throw new ArgumentException("Not a character!");
        }
        
        return in.consume().charAt(0);
    }
    
    @Override
    public String[] getValues() {
        return new String[] {
        		"char"
        };
    }
    
	@Override
	public boolean acceptsNull() {
		return false;
	}
}
