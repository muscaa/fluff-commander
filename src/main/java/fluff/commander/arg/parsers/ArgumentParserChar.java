package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for character command line arguments.
 */
public class ArgumentParserChar implements IArgumentParser<Character> {
    
    /**
     * Parses the input argument as a character value.
     *
     * @param in the input argument to parse
     * @return the parsed character value
     * @throws ArgumentException if the input value is null or
     *                           if it does not represent a single character
     */
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
    
    /**
     * Retrieves the possible values represented by this parser.
     *
     * @return an array containing a single string "char",
     *         representing the type of value expected by this parser
     */
    @Override
    public String[] getValues() {
        return new String[] {
            "char"
        };
    }
}
