package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for double precision floating-point command line arguments.
 */
public class ArgumentParserDouble implements IArgumentParser<Double> {
    
    /**
     * Parses the input argument as a double precision floating-point value.
     *
     * @param in the input argument to parse
     * @return the parsed double value
     * @throws ArgumentException if the input value is null or
     *                           if it cannot be parsed as a double
     */
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
    
    /**
     * Retrieves the possible values represented by this parser.
     *
     * @return an array containing a single string "double",
     *         representing the type of value expected by this parser
     */
    @Override
    public String[] getValues() {
        return new String[] {
            "double"
        };
    }
}
