package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

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
}
