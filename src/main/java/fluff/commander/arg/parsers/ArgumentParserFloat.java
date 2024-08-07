package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for single precision floating-point command line arguments.
 */
public class ArgumentParserFloat implements IArgumentParser<Float> {
    
    @Override
    public Float parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        
        try {
            return Float.parseFloat(in.consume());
        } catch (NumberFormatException e) {
            throw new ArgumentException("Parse error!", e);
        }
    }
    
    @Override
    public String[] getValues() {
        return new String[] {
        		"float"
        };
    }
    
	@Override
	public boolean acceptsNull() {
		return false;
	}
}
