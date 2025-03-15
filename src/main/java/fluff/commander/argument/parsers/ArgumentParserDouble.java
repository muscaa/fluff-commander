package fluff.commander.argument.parsers;

import fluff.commander.argument.ArgumentException;
import fluff.commander.argument.IArgumentInput;
import fluff.commander.argument.IArgumentParser;

/**
 * A parser for double precision floating-point command line arguments.
 */
public class ArgumentParserDouble implements IArgumentParser<Double> {
    
    @Override
    public Double parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        
        try {
            return Double.parseDouble(in.consume());
        } catch (NumberFormatException e) {
            throw new ArgumentException("Parse error!", e);
        }
    }
    
    @Override
    public String[] getValues() {
        return new String[] {
        		"double"
        };
    }
    
	@Override
	public boolean acceptsNull() {
		return false;
	}
}
