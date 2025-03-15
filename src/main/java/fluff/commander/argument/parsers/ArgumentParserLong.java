package fluff.commander.argument.parsers;

import fluff.commander.argument.ArgumentException;
import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.IArgumentParser;

/**
 * A parser for long integer command line arguments.
 */
public class ArgumentParserLong implements IArgumentParser<Long> {
    
    @Override
    public Long parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        
        try {
            return Long.parseLong(in.consume());
        } catch (NumberFormatException e) {
            throw new ArgumentException("Parse error!", e);
        }
    }
    
    @Override
    public String[] getValues() {
        return new String[] {
        		"long"
        };
    }
    
	@Override
	public boolean acceptsNull() {
		return false;
	}
}
