package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

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
}
