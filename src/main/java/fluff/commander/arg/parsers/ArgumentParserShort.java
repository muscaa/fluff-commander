package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for short integer command line arguments.
 */
public class ArgumentParserShort implements IArgumentParser<Short> {
    
    /**
     * Parses the input argument as a short integer value.
     *
     * @param in the input argument to parse
     * @return the parsed short integer value
     * @throws ArgumentException if the input value is null or
     *                           if it cannot be parsed as a short integer
     */
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
    
    /**
     * Retrieves the possible values represented by this parser.
     *
     * @return an array containing a single string "short",
     *         representing the type of value expected by this parser
     */
    @Override
    public String[] getValues() {
        return new String[] {
            "short"
        };
    }
}
