package fluff.commander.arg.parsers;

import fluff.commander.arg.ArgumentException;
import fluff.commander.arg.IArgumentParser;
import fluff.commander.arg.IArgumentInput;

/**
 * A parser for string command line arguments.
 */
public class ArgumentParserString implements IArgumentParser<String> {
    
    /**
     * Parses the input argument as a string value.
     *
     * @param in the input argument to parse
     * @return the parsed string value
     * @throws ArgumentException if the input value is null
     */
    @Override
    public String parse(IArgumentInput in) throws ArgumentException {
        if (in.isNull()) {
            throw new ArgumentException("Specified value is null!");
        }
        
        return in.consume();
    }
    
    /**
     * Retrieves the possible values represented by this parser.
     *
     * @return an array containing a single string "string",
     *         representing the type of value expected by this parser
     */
    @Override
    public String[] getValues() {
        return new String[] {
            "string"
        };
    }
}
