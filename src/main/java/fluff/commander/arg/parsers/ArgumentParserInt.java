package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for integer command line arguments.
 */
public class ArgumentParserInt implements IArgumentParser<Integer> {
    
    /**
     * Parses the input argument as an integer value.
     *
     * @param in the input argument to parse
     * @return the parsed integer value
     * @throws ArgumentException if the input value is null or
     *                           if it cannot be parsed as an integer
     */
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
    
    /**
     * Retrieves the possible values represented by this parser.
     *
     * @return an array containing a single string "int",
     *         representing the type of value expected by this parser
     */
    @Override
    public String[] getValues() {
        return new String[] {
            "int"
        };
    }
}
