package fluff.commander.argument.parsers;

import fluff.commander.argument.ArgumentException;
import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.IArgumentParser;

/**
 * A parser for integer command line arguments.
 */
public class ArgumentParserInt implements IArgumentParser<Integer> {
    
    @Override
    public Integer parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        
        try {
            return Integer.parseInt(in.consume());
        } catch (NumberFormatException e) {
            throw new ArgumentException("Parse error!", e);
        }
    }
    
    @Override
    public String[] getValues() {
        return new String[] {
        		"int"
        };
    }
    
	@Override
	public boolean acceptsNull() {
		return false;
	}
}
