package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for short integer command line arguments.
 */
public class ArgumentParserShort implements IArgumentParser<Short> {
    
    @Override
    public Short parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        
        try {
            return Short.parseShort(in.consume());
        } catch (NumberFormatException e) {
            throw new ArgumentException("Parse error!", e);
        }
    }
    
    @Override
    public String[] getValues() {
        return new String[] {
            "short"
        };
    }
}
